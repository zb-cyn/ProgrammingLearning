<template>
    <div>
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>我的考试</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <PaginationBox ref="PaginationBox" :where="where" url="/UserAnswer/List">
            <template v-slot:content="{ data }">
                <div class="answer-list">
                    <div v-for="item in data" :key="item.Id" class="answer-item">
                        <div class="answer-header">
                            <div class="title">
                                <i class="el-icon-edit-outline"></i>
                                <span>{{ item.QuestionnaireDto.Title }}</span>
                                <el-tag size="small" type="warning" class="tag">{{ item.QuestionnaireDto.Type }}</el-tag>
                            </div>
                            <div class="status">
                                <el-tag size="small" type="success" v-if="item.IsMarking">已批改</el-tag>
                                <el-tag size="small" type="info" v-else>待批改</el-tag>
                            </div>
                        </div>

                        <div class="answer-content">
                            <div class="info-group">
                                <div class="info-item">
                                    <i class="el-icon-time"></i>
                                    <span>开始时间: {{ item.BeginTime }}</span>
                                </div>
                                <div class="info-item">
                                    <i class="el-icon-timer"></i>
                                    <span>提交时间: {{ item.SubmitTime }}</span>
                                </div>
                            </div>

                            <div class="info-group">
                                <div class="info-item">
                                    <i class="el-icon-document"></i>
                                    <span>试卷说明: {{ item.QuestionnaireDto.SmallTitle }}</span>
                                </div>
                                <div class="info-item">
                                    <i class="el-icon-alarm-clock"></i>
                                    <span>限时: {{ item.QuestionnaireDto.LimitMinute }}分钟</span>
                                </div>
                            </div>

                            <div class="score-section">
                                <div class="score-overview">
                                    <div class="total-questions">
                                        <div class="question-count">
                                            <i class="el-icon-document"></i>
                                            <span>总题量: {{ getTotalQuestions(item) }}题</span>
                                        </div>
                                    </div>
                                    <div class="total-score" v-if="item.IsMarking">
                                        <span class="label">总分:</span>
                                        <span class="value">{{ item.TotalScore }}</span>
                                    </div>
                                </div>

                                <div class="question-details">
                                    <div class="question-type">
                                        <div class="type-header">
                                            <span>单选题</span>
                                            <span class="count">({{ item.SingleChoiseQuestionCount }}题)</span>
                                        </div>
                                        <div class="score-info">
                                            <span>总分: {{ item.SingleChoiseQuestionTotalScore }}</span>
                                            <span v-if="item.IsMarking">得分: {{ item.SingleChoiseQuestionGetScore || 0
                                            }}</span>
                                        </div>
                                    </div>

                                    <div class="question-type">
                                        <div class="type-header">
                                            <span>多选题</span>
                                            <span class="count">({{ item.MultipleChoiceQuestionCount }}题)</span>
                                        </div>
                                        <div class="score-info">
                                            <span>总分: {{ item.MultipleChoiceQuestionTotalScore }}</span>
                                            <span v-if="item.IsMarking">得分: {{ item.MultipleChoiceQuestionGetScore || 0
                                            }}</span>
                                        </div>
                                    </div>

                                    <div class="question-type">
                                        <div class="type-header">
                                            <span>判断题</span>
                                            <span class="count">({{ item.JudgmentQuestionCount }}题)</span>
                                        </div>
                                        <div class="score-info">
                                            <span>总分: {{ item.JudgmentQuestionTotalScore }}</span>
                                            <span v-if="item.IsMarking">得分: {{ item.JudgmentQuestionGetScore || 0 }}</span>
                                        </div>
                                    </div>

                                    <div class="question-type">
                                        <div class="type-header">
                                            <span>主观题</span>
                                            <span class="count">({{ item.SubjectiveQuestionCount }}题)</span>
                                        </div>
                                        <div class="score-info">
                                            <span>总分: {{ item.SubjectiveQuestionTotalScore }}</span>
                                            <span v-if="item.IsMarking">得分: {{ item.SubjectiveQuestionGetScore || 0
                                            }}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="answer-footer">
                            <div class="user-info">
                                <el-avatar :size="30" :src="item.UserDto.ImageUrls"></el-avatar>
                                <span class="username">{{ item.UserDto.Name }}</span>
                            </div>
                            <el-button type="primary" size="small" v-if="item.IsMarking" @click="ToDetail(item.Id)">
                                <i class="el-icon-view"></i>
                                查看详情
                            </el-button>
                            <el-button type="primary" size="small" v-if="item.SubmitTime == null"
                                @click="ContinueTest(item)">
                                <i class="el-icon-edit-outline"></i>
                                接着测试
                            </el-button>
                        </div>
                    </div>
                </div>
            </template>
        </PaginationBox>
    </div>
</template>
<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import { mapGetters } from 'vuex';
export default {
    name: 'UserAnswer',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', 'UserId'])
    },
    components: {
        PaginationBox,
    },
    data() {
        return {
            where: {}
        }
    },
    created() {
        this.where.UserId = this.UserId;
    },
    methods: {
        ToDetail(Id) {
            this.$router.push({
                path: '/Front/QuestionnaireAnswer',
                query: {
                    UserAnswerId: Id,
                    IsMarking: true
                }
            })
        },
        ContinueTest(item) {
            this.$router.push({
                path: '/Front/QuestionnaireAnswer',
                query: {
                    TestId: item.TestId,
                    QuestionnaireId: item.QuestionnaireId,
                    UserAnswerId: item.Id
                }
            })
        },
        getTotalQuestions(item) {
            return item.SingleChoiseQuestionCount +
                item.MultipleChoiceQuestionCount +
                item.JudgmentQuestionCount +
                item.SubjectiveQuestionCount;
        }
    },
}
</script>
<style scoped>
/* 列表容器 */
.answer-list {
    width: 100%;
    padding: 20px;
}

/* 列表项样式 */
.answer-item {
    background: #fff;
    border-radius: 12px;
    padding: 25px;
    margin-bottom: 20px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    transition: all 0.3s;
}

.answer-item:hover {
    transform: translateY(-5px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

/* 头部样式 */
.answer-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 1px solid #eee;
}

.title {
    display: flex;
    align-items: center;
    font-size: 20px;
    font-weight: bold;
    color: #303133;
}

.title i {
    margin-right: 10px;
    color: #409EFF;
    font-size: 24px;
}

.tag {
    margin-left: 12px;
}

/* 内容样式 */
.answer-content {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    margin-bottom: 20px;
}

.info-group {
    display: flex;
    flex-direction: column;
    gap: 12px;
}

.info-item {
    display: flex;
    align-items: center;
    color: #606266;
    font-size: 14px;
}

.info-item i {
    margin-right: 8px;
    color: #409EFF;
}

/* 分数部分 */
.score-section {
    grid-column: span 2;
    background: #f8f9fa;
    border-radius: 8px;
    padding: 20px;
}

.score-overview {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 1px solid #eee;
}

.total-score {
    display: flex;
    align-items: baseline;
}

.total-score .label {
    font-size: 16px;
    font-weight: bold;
    color: #303133;
    margin-right: 10px;
}

.total-score .value {
    font-size: 28px;
    color: #67c23a;
    font-weight: bold;
}

.total-questions {
    color: #606266;
}

.question-details {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 15px;
}

.question-type {
    background: white;
    padding: 15px;
    border-radius: 6px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.type-header {
    font-weight: bold;
    color: #303133;
    margin-bottom: 10px;
}

.type-header .count {
    color: #909399;
    font-size: 0.9em;
    margin-left: 5px;
}

.score-info {
    display: flex;
    flex-direction: column;
    gap: 5px;
    color: #606266;
    font-size: 13px;
}

.score-info span:first-child {
    color: #606266;
}

.score-info span:last-child {
    color: #67c23a;
}

/* 底部样式 */
.answer-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-top: 15px;
    border-top: 1px solid #eee;
}

.user-info {
    display: flex;
    align-items: center;
    gap: 10px;
}

.username {
    color: #606266;
    font-size: 14px;
}
</style>