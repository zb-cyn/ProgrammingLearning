<template>
    <div v-if="detail">
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>{{ IsMarking ? "考试结果" : "开始考试" }}</el-breadcrumb-item>
            </el-breadcrumb>
        </div>



        <el-row class="card margin-bottom-xs margin-top-xs">
            <el-col>
                <div class="text-bold text-center">{{ detail.QuestionnaireDto.Title }}</div>
                <div class="text-center " style="font-size: 12px;margin-top:5px;color:gray">{{
                    detail.QuestionnaireDto.SmallTitle }}
                </div>
                <div style="font-size: 12px;margin-top: 5px;text-align: center;" v-if="detail.QuestionnaireDto.LimitMinute">
                    <el-tag size="mini" type="success">限时{{ detail.QuestionnaireDto.LimitMinute }}分钟</el-tag>
                </div>
            </el-col>

        </el-row>
        <el-row :gutter="10" style="">
            <el-col :span="18">
                <template v-for="(item, index) in detail.UserAnswerDetDtoList">


                    <SingleChoiseQuestion v-model="item.UserAnswer" v-if="item.QuestionType == 1" :ShowResult="IsMarking"
                        :ShowGetScore="IsMarking" :ReadOnly="IsMarking" :QuestionType="item.QuestionType"
                        :SingleChoiseQuestionDto="item.SingleChoiseQuestionDto" :UserAnswerDetDto="item" :Index="index"
                        @Change="SigleChange" />

                    <MultipleChoiceQuestion v-model="item.UserAnswer" v-if="item.QuestionType == 2" :ShowResult="IsMarking"
                        :ShowGetScore="IsMarking" :ReadOnly="IsMarking" :QuestionType="item.QuestionType"
                        :MultipleChoiceQuestionDto="item.MultipleChoiceQuestionDto" :UserAnswerDetDto="item" :Index="index"
                        @Change="MultipleChoiceQuestionChange" />


                    <JudgmentQuestion v-model="item.UserAnswer" v-if="item.QuestionType == 3" :ShowResult="IsMarking"
                        :ShowGetScore="IsMarking" :ReadOnly="IsMarking" :QuestionType="item.QuestionType"
                        :JudgmentQuestionDto="item.JudgmentQuestionDto" :UserAnswerDetDto="item" :Index="index"
                        @Change="JudgmentQuestionChange" />

                    <SubjectiveQuestion v-model="item.UserAnswer" v-if="item.QuestionType == 4" :ShowResult="IsMarking"
                        :ShowGetScore="IsMarking" :ReadOnly="IsMarking" :QuestionType="item.QuestionType"
                        :SubjectiveQuestionDto="item.SubjectiveQuestionDto" :UserAnswerDetDto="item" :Index="index"
                        @Change="SubjectiveQuestionChange" />


                </template>



            </el-col>
            <el-col :span="6">
                <el-card class="box-card question-item">
                    <div slot="header" class="clearfix">
                        <div style="display: flex; justify-content: space-between; align-items: center;">
                            <span>答 题 卡</span>
                            <span v-if="!detail.SubmitTime && remainingTime" class="countdown">
                                剩余时间: {{ formatTime(remainingTime) }}
                            </span>
                        </div>
                    </div>
                    <div style="display: flex;flex-wrap: wrap;" v-if="detail.SubmitTime == null">
                        <div v-for="(item, index) in detail.UserAnswerDetDtoList" @click="ScrollToViewQuestion(item)">
                            <el-tag :type="item.UserAnswer ? 'success' : 'info'"
                                style="margin-right:10px;margin-top:10px;">第{{
                                    index + 1 }}题</el-tag>
                        </div>
                        <el-button style="width: 100%;margin-top: 10px;" size="mini" type="primary" @click="Sumbit()">提
                            交</el-button>
                    </div>
                    <div style="display: flex;flex-wrap: wrap;" v-else>
                        <div v-for="(item, index) in detail.UserAnswerDetDtoList" @click="ScrollToViewQuestion(item)">
                            <el-tag :type="item.GetScore == item.TotalScore ? 'success' : 'danger'"
                                style="margin-right:10px;margin-top:10px;">第{{
                                    index + 1 }}题</el-tag>
                        </div>

                    </div>



                </el-card>
            </el-col>
        </el-row>


    </div>
</template>


<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import JudgmentQuestion from '@/components/QuestionComponent/JudgmentQuestion.vue';
import MultipleChoiceQuestion from '@/components/QuestionComponent/MultipleChoiceQuestion.vue';
import SingleChoiseQuestion from '@/components/QuestionComponent/SingleChoiseQuestion.vue';
import SubjectiveQuestion from '@/components/QuestionComponent/SubjectiveQuestion.vue';
import SelectTab from '@/components/Select/SelectTab.vue';
import { mapGetters } from 'vuex';
export default {
    name: 'QuestionnaireAnswer',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    components: {
        PaginationBox,
        SelectTab,
        SingleChoiseQuestion,
        MultipleChoiceQuestion,
        JudgmentQuestion,
        SubjectiveQuestion
    },
    filters: {
        ToMintute: (val) => {
            return val ? parseFloat(val / 60).toFixed("2") + "分钟" : "未设置";
        }
    },
    data() {
        return {
            detail: null,
            UserAnswerId: null,
            where: {},
            remainingTime: 0,
            timer: null,
            isPageVisible: true,
        }
    },
    async created() {
        this.IsMarking = this.$route.query.IsMarking;
        this.UserAnswerId = this.$route.query.UserAnswerId;
        if (this.IsMarking) {
            await this.GetDetailApi();
        } else {
            await this.BeginApi();
            await this.GetDetailApi();
        }

    },
    methods: {
        /**
         * 创建答题记录
         */
        async BeginApi() {
            let { Data } = await this.$Post(`/UserAnswer/BeginAnswer`, { TestId: this.$route.query.TestId, UserId: this.UserId, QuestionnaireId: this.$route.query.QuestionnaireId });
            this.UserAnswerId = Data.Id;
            this.$router.replace({
                path: "/Front/QuestionnaireAnswer",
                query: {
                    UserAnswerId: this.UserAnswerId,
                    TestId: this.$route.query.TestId,
                    QuestionnaireId: this.$route.query.QuestionnaireId
                }
            })
        },
        /**
         * 得到试卷详情
         */
        async GetDetailApi() {

            let { Data } = await this.$Post(`/UserAnswer/Get`, { Id: this.UserAnswerId });

            this.detail = Data;
            this.startCountdown();
        },
        //滚动到自定题目
        async ScrollToViewQuestion(item) {
            var target = document.getElementById("Quetsion" + item.QuestionType + item.QuestionId);
            target.scrollIntoView({ behavior: 'smooth' });
        },

        //提交
        async Sumbit() {
            let { Success } = await this.$Post("/UserAnswer/Sumbit", { Id: this.$route.query.UserAnswerId })
            if (Success) {
                this.WriteLog("用户提交了答题")
                this.$router.replace({
                    path: "/Front/UserAnswerList",
                    query: {

                    }
                })
            }
        },
        //单选题发生变动
        async SigleChange({ Index, Value }) {
            let item = this.detail.UserAnswerDetDtoList[Index];
            item.UserAnswerId = this.$route.query.UserAnswerId;
            item.QuestionId = item.QuestionId;
            item.QuestionType = item.QuestionType;
            await this.$Post("/UserAnswerDet/CreateOrEdit", item)
        },
        //多选题变动
        async MultipleChoiceQuestionChange({ Index, Value }) {
            let item = this.detail.UserAnswerDetDtoList[Index];
            item.UserAnswerId = this.$route.query.UserAnswerId;
            item.QuestionId = item.QuestionId;
            item.QuestionType = item.QuestionType;
            await this.$Post("/UserAnswerDet/CreateOrEdit", item)
        },
        //判断题变动
        async JudgmentQuestionChange({ Index, Value }) {
            let item = this.detail.UserAnswerDetDtoList[Index];
            item.UserAnswerId = this.$route.query.UserAnswerId;
            item.QuestionId = item.QuestionId;
            item.QuestionType = item.QuestionType;
            await this.$Post("/UserAnswerDet/CreateOrEdit", item)

        },
        //主观题变动
        async SubjectiveQuestionChange({ Index, Value }) {
            let item = this.detail.UserAnswerDetDtoList[Index];
            item.UserAnswerId = this.$route.query.UserAnswerId;
            item.QuestionId = item.QuestionId;
            item.QuestionType = item.QuestionType;

            await this.$Post("/UserAnswerDet/CreateOrEdit", item)
        },

        //写入日志
        async WriteLog(content) {
            if (this.detail.SubmitTime) {
                return;
            }
            await this.$Post("/UserAnswerLog/CreateOrEdit", {
                UserId: this.UserId,
                TestId: this.detail.TestId,
                Content: content
            })
        },
        // 格式化时间
        formatTime(seconds) {
            const minutes = Math.floor(seconds / 60);
            const remainingSeconds = seconds % 60;
            return `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;
        },

        // 开始倒计时
        startCountdown() {
            if (this.detail.SubmitTime || !this.detail.QuestionnaireDto.LimitMinute) return;

            const creationTime = new Date(this.detail.CreationTime).getTime();
            const limitMs = this.detail.QuestionnaireDto.LimitMinute * 60 * 1000;
            const endTime = creationTime + limitMs;
            const now = new Date().getTime();

            this.remainingTime = Math.max(0, Math.floor((endTime - now) / 1000));

            this.timer = setInterval(() => {
                if (this.remainingTime <= 0) {
                    clearInterval(this.timer);
                    this.Sumbit(); // 时间到自动提交
                    return;
                }
                this.remainingTime--;
            }, 1000);
        },
        // 处理页面可见性变化
        handleVisibilityChange() {
            if (document.hidden) {
                this.isPageVisible = false;
                this.$message.warning('请不要切换页面，否则可能会影响您的答题！');
                this.WriteLog("用户在答题过程中切换了页面");
            } else {
                this.isPageVisible = true;
            }
        },
    },
    mounted() {
        // 添加页面可见性监听
        document.addEventListener('visibilitychange', this.handleVisibilityChange);
    },

    beforeDestroy() {
        if (this.timer) {
            clearInterval(this.timer);
        }
        // 移除页面可见性监听
        document.removeEventListener('visibilitychange', this.handleVisibilityChange);
        this.WriteLog("用户离开了答题页面");
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

.countdown {
    color: #f56c6c;
    font-size: 14px;
}
</style>
