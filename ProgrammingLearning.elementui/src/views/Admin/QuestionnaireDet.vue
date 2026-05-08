<template>
    <div v-if="detail != null">

        <el-row class="card margin-bottom-xs">
            <el-col>
                <div class="text-bold text-center">{{ detail.Title }}</div>
            </el-col>
        </el-row>
        <el-row :gutter="10" style="">
            <el-col :span="18">
                <template v-for="(item, index) in detail.QuestionnaireDetDtos">


                    <el-card class="box-card question-item" :id="'Quetsion' + item.QuestionId"
                        v-if="item.QuestionType == 1">

                        <div slot="header" class="clearfix">
                            <div><el-tag>单选题</el-tag> <span>题目{{ index + 1 }}:{{ item.SingleChoiseQuestionDto.Title
                                    }}</span>
                            </div>
                        </div>
                        <el-row class="margin-b-md">
                            <el-checkbox-group v-model="item.CheckIds">
                                <el-col :span="24" class="sigle-question-item">
                                    <el-checkbox label="A">A:{{
                                        item.SingleChoiseQuestionDto.OptionA
                                    }}</el-checkbox>
                                </el-col>
                                <el-col :span="24" class="sigle-question-item">
                                    <el-checkbox label="B">B:{{
                                        item.SingleChoiseQuestionDto.OptionB
                                    }}</el-checkbox>
                                </el-col>
                                <el-col :span="24" class="sigle-question-item">
                                    <el-checkbox label="C">C:{{
                                        item.SingleChoiseQuestionDto.OptionC
                                    }}</el-checkbox>
                                </el-col>
                                <el-col :span="24" class="sigle-question-item">
                                    <el-checkbox label="D">D:{{
                                        item.SingleChoiseQuestionDto.OptionD
                                    }}</el-checkbox>
                                </el-col>
                            </el-checkbox-group>


                        </el-row>
                        <el-row>
                            <div class="analyzeItem">解释:</div>
                            <el-input type="textarea" v-model.trim="item.SingleChoiseQuestionDto.AnalyzeResult"
                                readonly=""></el-input>
                        </el-row>

                    </el-card>

                    <el-card class="box-card question-item" :id="'Quetsion' + item.QuestionId"
                        v-if="item.QuestionType == 2">
                        <div slot="header" class="clearfix">
                            <div><el-tag>多选题</el-tag> <span>题目{{ index + 1 }}:{{ item.MultipleChoiceQuestionDto.Title
                                    }}</span>
                            </div>
                        </div>
                        <el-row class="margin-b-md">
                            <el-checkbox-group v-model="item.CheckIds">
                                <el-col :span="24" class="sigle-question-item">
                                    <el-checkbox label="A">A:{{
                                        item.MultipleChoiceQuestionDto.OptionA
                                    }}</el-checkbox>
                                </el-col>
                                <el-col :span="24" class="sigle-question-item">
                                    <el-checkbox label="B">B:{{
                                        item.MultipleChoiceQuestionDto.OptionB
                                    }}</el-checkbox>
                                </el-col>
                                <el-col :span="24" class="sigle-question-item">
                                    <el-checkbox label="C">C:{{
                                        item.MultipleChoiceQuestionDto.OptionC
                                    }}</el-checkbox>
                                </el-col>
                                <el-col :span="24" class="sigle-question-item">
                                    <el-checkbox label="D">D:{{
                                        item.MultipleChoiceQuestionDto.OptionD
                                    }}</el-checkbox>
                                </el-col>
                            </el-checkbox-group>


                        </el-row>
                        <el-row>
                            <div class="analyzeItem">解释:</div>
                            <el-input type="textarea" v-model.trim="item.MultipleChoiceQuestionDto.AnalyzeResult"
                                readonly=""></el-input>
                        </el-row>
                    </el-card>

                    <el-card class="box-card question-item" :id="'Quetsion' + item.QuestionId"
                        v-if="item.QuestionType == 3">
                        <div slot="header" class="clearfix">
                            <div><el-tag>判断题</el-tag> <span>题目{{ index + 1 }}:{{ item.JudgmentQuestionDto.Title
                                    }}</span>
                            </div>
                        </div>

                        <el-row class="margin-b-md">
                            <el-radio-group v-model="item.JudgmentQuestionDto.RightAnswer">
                                <el-radio label="true">正确</el-radio>
                                <el-radio label="false">错误</el-radio>
                            </el-radio-group>

                        </el-row>
                        <el-row>
                            <div class="analyzeItem">解释:</div>
                            <el-input type="textarea" v-model.trim="item.JudgmentQuestionDto.AnalyzeResult"
                                readonly=""></el-input>
                        </el-row>


                    </el-card>
                </template>



            </el-col>
            <el-col :span="6">
                <el-card class="box-card question-item">
                    <div slot="header" class="clearfix">
                        <div> 答 题 卡
                        </div>
                    </div>
                    <div style="display: flex;flex-wrap: wrap;" v-if="detail.SubmitTime == null">
                        <div v-for="(item, index) in detail.QuestionnaireDetDtos" @click="ScrollToViewQuestion(item)">

                            <el-tag type="info" style="margin-right:10px;margin-top:10px;">第{{
                                index + 1 }}题</el-tag>

                        </div>

                    </div>


                </el-card>
            </el-col>
        </el-row>




    </div>
</template>

<script>
import router from '@/router';
import { mapGetters } from 'vuex'
import moment from 'moment'
export default {
    name: 'QuestionnaireDet',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    filters: {
        ToMintune: (val) => {
            return val ? parseFloat(val / 60).toFixed("2") + "分钟" : "未设置";
        }
    },
    data() {
        let now = moment().format('YYYY-MM-DD');
        return {

            now: now,
            detail: {},

        };
    },
    mounted() {

        this.GetDetailApi();

    },
    destroyed() {

    },
    methods: {

        /**
         * 得到试卷详情
         */
        async GetDetailApi() {

            let { Data } = await this.$Post(`/Questionnaire/Get`, { Id: this.$route.query.QuestionnaireId });

            for (let quesiton of Data.QuestionnaireDetDtos) {
                if (quesiton.QuestionType == 1) {
                    quesiton.CheckIds = [quesiton.SingleChoiseQuestionDto.RightAnswer]
                }
                if (quesiton.QuestionType == 2) {
                    quesiton.CheckIds = quesiton.MultipleChoiceQuestionDto.RightAnswer.split(',')
                }
            }

            this.detail = Data;


        },

        //滚动到自定题目
        async ScrollToViewQuestion(item) {
            var target = document.getElementById("Quetsion" + item.Id);
            target.scrollIntoView({ behavior: 'smooth' });
        },


    }


}
</script>

<style lang="scss" scoped>
.header_type {
    margin-right: 10px;
}

.question-item {
    margin-bottom: 20px;
}

.sigle-question-item {
    margin: 10px 0px;
}

.item {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
}

.cotent-warp {
    margin-top: 10px;
    padding: 20px;
    background-color: aliceblue;
    border-radius: 10px;
}

.content-cut {

    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-all;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;


}

.card {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    height: 100%;
    border-radius: 10px;

}

.getScore {
    font-size: 20px;

}

.analyzeItem {
    font-size: 14px;
    color: #606266;
    margin-bottom: 10px;
}
</style>
