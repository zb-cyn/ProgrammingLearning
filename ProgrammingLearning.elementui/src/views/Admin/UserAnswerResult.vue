<template>
    <div v-if="detail">



        <el-row class="card margin-bottom-xs margin-top-xs">
            <el-col>
                <div class="text-bold text-center">{{ detail.QuestionnaireDto.Title }}</div>
                <div class="text-center subtitle">{{ detail.QuestionnaireDto.SmallTitle }}</div>
                <div class="test-info">
                    <div class="info-item">
                        <span>考试名称：</span>{{ detail.TestDto.Title }}
                    </div>
                    <div class="info-item">
                        <span>考生姓名：</span>{{ detail.UserDto.Name }}
                    </div>
                    <div class="info-item">
                        <span>考试时间：</span>
                        {{ detail.TestDto.BeginActiveTime }} 至 {{ detail.TestDto.EndActiveTime }}
                    </div>
                    <div class="info-item" v-if="detail.QuestionnaireDto.LimitMinute">
                        <el-tag size="mini" type="success">限时{{ detail.QuestionnaireDto.LimitMinute }}分钟</el-tag>
                    </div>
                    <div class="info-item" v-if="detail.SubmitTime">
                        <span>提交时间：</span>{{ detail.SubmitTime }}
                    </div>
                    <div class="info-item" v-if="detail.SubmitTime">
                        <span>实际用时：</span>{{ getActualDuration }}
                    </div>

                </div>
            </el-col>

        </el-row>
        <div>

        </div>
        <el-row :gutter="10" style="">
            <el-col :span="18">
                <template v-for="(item, index) in detail.UserAnswerDetDtoList">


                    <SingleChoiseQuestion v-model="item.UserAnswer" v-if="item.QuestionType == 1" :ShowResult="true"
                        :ShowGetScore="true" :ReadOnly="true" :QuestionType="item.QuestionType"
                        :SingleChoiseQuestionDto="item.SingleChoiseQuestionDto" :UserAnswerDetDto="item" :Index="index"
                        @Change="SigleChange" />

                    <MultipleChoiceQuestion v-model="item.UserAnswer" v-if="item.QuestionType == 2" :ShowResult="true"
                        :ShowGetScore="true" :ReadOnly="true" :QuestionType="item.QuestionType"
                        :MultipleChoiceQuestionDto="item.MultipleChoiceQuestionDto" :UserAnswerDetDto="item" :Index="index"
                        @Change="MultipleChoiceQuestionChange" />


                    <JudgmentQuestion v-model="item.UserAnswer" v-if="item.QuestionType == 3" :ShowResult="true"
                        :ShowGetScore="true" :ReadOnly="true" :QuestionType="item.QuestionType"
                        :JudgmentQuestionDto="item.JudgmentQuestionDto" :UserAnswerDetDto="item" :Index="index"
                        @Change="JudgmentQuestionChange" />

                    <SubjectiveQuestion v-model="item.UserAnswer" v-if="item.QuestionType == 4" :ShowResult="true"
                        :ShowGetScore="true" :ReadOnly="true" :IsMarking="true" :QuestionType="item.QuestionType"
                        :SubjectiveQuestionDto="item.SubjectiveQuestionDto" :UserAnswerDetDto="item" :Index="index"
                        @Change="SubjectiveQuestionChange" @ScoreChange="SubjectiveQuestionChangeScore" />


                </template>



            </el-col>
            <el-col :span="6">
                <el-card class="box-card question-item" v-if="detail.SubmitTime">
                    <div slot="header" class="clearfix">
                        <div style="display: flex; justify-content: space-between; align-items: center;">
                            <span>得分统计</span>
                            <el-button type="text" size="small" @click="showLogDialog">
                                查看操作日志
                            </el-button>
                        </div>
                    </div>
                    <div class="score-statistics">
                        <div class="score-item">
                            <div class="score-label">单选题</div>
                            <div class="score-value">
                                {{ detail.SingleChoiseQuestionGetScore }}/{{ detail.SingleChoiseQuestionTotalScore }}
                            </div>
                        </div>
                        <div class="score-item">
                            <div class="score-label">多选题</div>
                            <div class="score-value">
                                {{ detail.MultipleChoiceQuestionGetScore }}/{{ detail.MultipleChoiceQuestionTotalScore }}
                            </div>
                        </div>
                        <div class="score-item">
                            <div class="score-label">判断题</div>
                            <div class="score-value">
                                {{ detail.JudgmentQuestionGetScore }}/{{ detail.JudgmentQuestionTotalScore }}
                            </div>
                        </div>
                        <div class="score-item">
                            <div class="score-label">主观题</div>
                            <div class="score-value">
                                {{ detail.SubjectiveQuestionGetScore }}/{{ detail.SubjectiveQuestionTotalScore }}
                            </div>
                        </div>
                        <div class="score-item total-score">
                            <div class="score-label">总分</div>
                            <div class="score-value">
                                {{ getTotalScore }}/{{ getTotalPossibleScore }}
                            </div>
                        </div>
                    </div>
                </el-card>

                <el-card class="box-card question-item">
                    <div slot="header" class="clearfix">
                        <div style="display: flex; justify-content: space-between; align-items: center;">
                            <span>答 题 卡</span>

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
                            <el-tag :type="item.UserAnswer ? 'success' : 'danger'"
                                style="margin-right:10px;margin-top:10px;">第{{
                                    index + 1 }}题</el-tag>
                        </div>
                        <el-button style="width: 100%;margin-top: 10px;" size="mini" type="primary" v-if="!detail.IsMarking"
                            @click="FinishMarking()">完成阅卷</el-button>
                        <el-button style="width: 100%;margin-top: 10px;" size="mini" type="danger" v-else>修改阅卷</el-button>
                    </div>



                </el-card>
            </el-col>
        </el-row>

        <el-dialog title="用户操作日志" :visible.sync="logDialogVisible" width="50%">
            <el-timeline>
                <el-timeline-item v-for="(log, index) in detail.UserAnswerLogDtoList" :key="index"
                    :timestamp="log.CreationTime" type="primary">
                    {{ log.Content }}
                </el-timeline-item>
            </el-timeline>
        </el-dialog>
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
        ]),
        getTotalScore() {
            if (!this.detail) return 0;
            return (
                this.detail.SingleChoiseQuestionGetScore +
                this.detail.MultipleChoiceQuestionGetScore +
                this.detail.JudgmentQuestionGetScore +
                this.detail.SubjectiveQuestionGetScore
            );
        },
        getTotalPossibleScore() {
            if (!this.detail) return 0;
            return (
                this.detail.SingleChoiseQuestionTotalScore +
                this.detail.MultipleChoiceQuestionTotalScore +
                this.detail.JudgmentQuestionTotalScore +
                this.detail.SubjectiveQuestionTotalScore
            );
        },
        getActualDuration() {
            if (!this.detail || !this.detail.BeginTime || !this.detail.SubmitTime) {
                return '未完成';
            }

            const beginTime = new Date(this.detail.BeginTime);
            const submitTime = new Date(this.detail.SubmitTime);
            const durationInMinutes = Math.floor((submitTime - beginTime) / (1000 * 60));

            return `${durationInMinutes}分钟`;
        }
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
            ShowResult: false,
            logDialogVisible: false,
        }
    },
    async created() {
        this.UserAnswerId = this.$route.query.UserAnswerId;

        await this.GetDetailApi();
    },
    methods: {

        /**
         * 得到试卷详情
         */
        async GetDetailApi() {

            let { Data } = await this.$Post(`/UserAnswer/Get`, { Id: this.UserAnswerId });
            this.detail = Data;

        },
        //滚动到自定题目
        async ScrollToViewQuestion(item) {
            var target = document.getElementById("Quetsion" + item.QuestionType + item.QuestionId);
            target.scrollIntoView({ behavior: 'smooth' });
        },

        //完成阅卷
        async FinishMarking() {


            let { Data, Success } = await this.$Post("/UserAnswer/FinishMarking", { Id: this.$route.query.UserAnswerId })
            if (Success) {
                this.$router.go(-1);
            }
        },
        //单选题发生变动
        async SigleChange({ Index, Value }) {

        },
        //多选题变动
        async MultipleChoiceQuestionChange({ Index, Value }) {

        },
        //判断题变动
        async JudgmentQuestionChange({ Index, Value }) {


        },
        //主观题变动
        async SubjectiveQuestionChange({ Index, Value }) {

        },
        async SubjectiveQuestionChangeScore({ Index, Value }) {

            let item = this.detail.UserAnswerDetDtoList[Index];
            item.UserAnswerId = this.$route.query.UserAnswerId;
            item.QuestionId = item.QuestionId;
            item.QuestionType = item.QuestionType;
            item.GetScore = Value;
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

        // 显示日志弹窗
        showLogDialog() {
            this.logDialogVisible = true;
        },
    },
    mounted() {

    },

    beforeDestroy() {

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

/* 得分统计样式 */
.score-statistics {
    /* 得分统计容器 */
    padding: 10px 0;
}

.score-item {
    /* 每个得分项目 */
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    padding: 5px 0;
    border-bottom: 1px solid #ebeef5;
}

.score-label {
    /* 得分项目标签 */
    color: #606266;
    font-size: 14px;
}

.score-value {
    /* 得分值 */
    color: #409EFF;
    font-weight: bold;
}

.total-score {
    /* 总分样式 */
    margin-top: 15px;
    border-top: 2px solid #ebeef5;
    border-bottom: none;
    padding-top: 15px;

    .score-label {
        font-size: 16px;
        font-weight: bold;
    }

    .score-value {
        font-size: 16px;
        color: #67C23A;
    }
}

/* 新增样式 */
.subtitle {
    font-size: 12px;
    margin-top: 5px;
    color: gray;
}

.test-info {
    margin-top: 15px;
    padding: 10px 15px;
    background-color: #f5f7fa;
    border-radius: 4px;
}

.info-item {
    margin: 8px 0;
    font-size: 14px;
    color: #606266;

    span {
        color: #909399;
        margin-right: 10px;
    }
}

.el-timeline {
    /* 时间线样式 */
    padding: 20px;
    max-height: 400px;
    overflow-y: auto;
}

.el-timeline-item {

    /* 时间线项目样式 */
    .el-timeline-item__content {
        color: #606266;
        font-size: 14px;
    }

    .el-timeline-item__timestamp {
        color: #909399;
        font-size: 12px;
    }
}
</style>
