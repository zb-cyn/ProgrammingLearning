<template>
    <div>


        <el-card>
            <div slot="header" class="clearfix">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                    <div class="header-left">
                        <span>选项卡</span>
                        <el-button type="primary" size="small" style="margin-left: 15px;" @click="handleSave">
                            保存
                        </el-button>
                    </div>
                    <div class="statistics-info">
                        <div>总分值：{{ totalScore }}</div>
                        <div>单选题：{{ singleChoiceStats }}</div>
                        <div>多选题：{{ multipleChoiceStats }}</div>
                        <div>判断题：{{ judgmentStats }}</div>
                        <div>主观题：{{ subjectiveStats }}</div>
                    </div>
                </div>
            </div>
            <div style="display: flex;flex-wrap: wrap;" v-if="detail.SubmitTime == null">
                <div v-for="(item, index) in QuestionnaireDetList" @click="ScrollToViewQuestion(item)">

                    <el-tag type="primary" style="margin-right:10px;margin-top:10px;">第{{
                        index + 1 }}题</el-tag>

                </div>

            </div>


        </el-card>

        <el-row :gutter="10" style="margin-top: 10px;">
            <el-col :span="8">
                <!-- 右侧固定题目列表 -->
                <el-card class="fixed-question-list">
                    <div slot="header" class="clearfix">
                        <!-- 搜索框 -->
                        <div class="search-box">
                            <el-input v-model="searchKeyword" placeholder="请输入关键词搜索题目" prefix-icon="el-icon-search"
                                clearable @input="handleSearch">
                            </el-input>
                        </div>
                    </div>
                    <el-collapse>
                        <el-collapse-item title="单选题" name="1">

                            <!-- 单选题列表 -->
                            <el-checkbox-group v-model="selectedSingleQuestions" @change="handleSingleQuestionsChange">

                                <el-checkbox :label="item.Id" v-for="item in filteredSingleQuestions" :key="item.Id">
                                    {{ item.Title }}
                                </el-checkbox>

                            </el-checkbox-group>
                        </el-collapse-item>
                        <el-collapse-item title="多选题" name="2">
                            <!-- 多选题列表 -->
                            <el-checkbox-group v-model="selectedMultipleQuestions" @change="handleMultipleQuestionsChange">
                                <el-checkbox :label="item.Id" v-for="item in filteredMultipleQuestions">
                                    {{ item.Title }}
                                </el-checkbox>
                            </el-checkbox-group>
                        </el-collapse-item>
                        <el-collapse-item title="判断题" name="3">
                            <!-- 判断题列表 -->
                            <el-checkbox-group v-model="selectedJudgmentQuestions" @change="handleJudgmentQuestionsChange">
                                <el-checkbox :label="item.Id" v-for="item in filteredJudgmentQuestions">
                                    {{ item.Title }}
                                </el-checkbox>
                            </el-checkbox-group>
                        </el-collapse-item>
                        <el-collapse-item title="主观题" name="4">
                            <!-- 主观题列表 -->
                            <el-checkbox-group v-model="selectedSubjectiveQuestions"
                                @change="handleSubjectiveQuestionsChange">
                                <el-checkbox :label="item.Id" v-for="item in filteredSubjectiveQuestions">
                                    {{ item.Title }}
                                </el-checkbox>
                            </el-checkbox-group>
                        </el-collapse-item>
                    </el-collapse>

                </el-card>

            </el-col>
            <el-col :span="16">
                <template v-for="(item, index) in QuestionnaireDetList">
                    <SingleChoiseQuestion v-if="item.QuestionType == 1" :QuestionId="item.QuestionId" :Index="index"
                        :QuestionnaireDetDto="item" :SingleChoiseQuestionDto="item.SingleChoiseQuestionDto"
                        :QuestionType="item.QuestionType" :isFirst="index === 0"
                        :isLast="index === QuestionnaireDetList.length - 1" @moveUp="handleMoveUp(index)"
                        @moveDown="handleMoveDown(index)" />
                    <MultipleChoiceQuestion v-if="item.QuestionType == 2" :QuestionId="item.QuestionId" :Index="index"
                        :QuestionnaireDetDto="item" :MultipleChoiceQuestionDto="item.MultipleChoiceQuestionDto"
                        :QuestionType="item.QuestionType" :isFirst="index === 0"
                        :isLast="index === QuestionnaireDetList.length - 1" @moveUp="handleMoveUp(index)"
                        @moveDown="handleMoveDown(index)" />
                    <JudgmentQuestion v-if="item.QuestionType == 3" :QuestionId="item.QuestionId" :Index="index"
                        :QuestionnaireDetDto="item" :JudgmentQuestionDto="item.JudgmentQuestionDto"
                        :QuestionType="item.QuestionType" :isFirst="index === 0"
                        :isLast="index === QuestionnaireDetList.length - 1" @moveUp="handleMoveUp(index)"
                        @moveDown="handleMoveDown(index)" />
                    <SubjectiveQuestion v-if="item.QuestionType == 4" :QuestionId="item.QuestionId" :Index="index"
                        :QuestionnaireDetDto="item" :SubjectiveQuestionDto="item.SubjectiveQuestionDto"
                        :QuestionType="item.QuestionType" :isFirst="index === 0"
                        :isLast="index === QuestionnaireDetList.length - 1" @moveUp="handleMoveUp(index)"
                        @moveDown="handleMoveDown(index)" />
                </template>
            </el-col>
        </el-row>


    </div>
</template>

<script>
import JudgmentQuestion from '@/components/GroupQuestion/JudgmentQuestion.vue';
import MultipleChoiceQuestion from '@/components/GroupQuestion/MultipleChoiceQuestion.vue';
import SingleChoiseQuestion from '@/components/GroupQuestion/SingleChoiseQuestion.vue';
import SubjectiveQuestion from '@/components/GroupQuestion/SubjectiveQuestion.vue';
import moment from 'moment';
import { mapGetters } from 'vuex';
export default {
    name: 'QuestionnaireDet',
    components: {
        SingleChoiseQuestion,
        MultipleChoiceQuestion,
        JudgmentQuestion,
        SubjectiveQuestion
    },
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ]),
        filteredSingleQuestions() {
            if (!this.searchKeyword) return this.SingleChoiseQuestionDataList;
            return this.SingleChoiseQuestionDataList.filter(item =>
                item.Title.toLowerCase().includes(this.searchKeyword.toLowerCase()) ||
                String(item.Type).includes(this.searchKeyword)
            );
        },
        filteredMultipleQuestions() {
            if (!this.searchKeyword) return this.MultipleChoiceQuestionDataList;
            return this.MultipleChoiceQuestionDataList.filter(item =>
                item.Title.toLowerCase().includes(this.searchKeyword.toLowerCase()) ||
                String(item.Type).includes(this.searchKeyword)
            );
        },
        filteredJudgmentQuestions() {
            if (!this.searchKeyword) return this.JudgmentQuestionDataList;
            return this.JudgmentQuestionDataList.filter(item =>
                item.Title.toLowerCase().includes(this.searchKeyword.toLowerCase()) ||
                String(item.Type).includes(this.searchKeyword)
            );
        },
        filteredSubjectiveQuestions() {
            if (!this.searchKeyword) return this.SubjectiveQuestionDataList;
            return this.SubjectiveQuestionDataList.filter(item =>
                item.Title.toLowerCase().includes(this.searchKeyword.toLowerCase()) ||
                String(item.Type).includes(this.searchKeyword)
            );
        },
        totalScore() {
            return this.QuestionnaireDetList.reduce((sum, item) => {
                const score = item.Score || 0;  // 如果Score不存在则默认为0
                return sum + score;
            }, 0);
        },
        singleChoiceStats() {
            const count = this.QuestionnaireDetList.filter(q => q.QuestionType === 1).length;
            const score = this.QuestionnaireDetList
                .filter(q => q.QuestionType === 1)
                .reduce((sum, q) => sum + (q.Score || 0), 0);
            const percentage = this.totalScore ? ((score / this.totalScore) * 100).toFixed(1) : 0;
            return `${count}题 ${score}分 (${percentage}%)`;
        },
        multipleChoiceStats() {
            const count = this.QuestionnaireDetList.filter(q => q.QuestionType === 2).length;
            const score = this.QuestionnaireDetList
                .filter(q => q.QuestionType === 2)
                .reduce((sum, q) => sum + (q.Score || 0), 0);
            const percentage = this.totalScore ? ((score / this.totalScore) * 100).toFixed(1) : 0;
            return `${count}题 ${score}分 (${percentage}%)`;
        },
        judgmentStats() {
            const count = this.QuestionnaireDetList.filter(q => q.QuestionType === 3).length;
            const score = this.QuestionnaireDetList
                .filter(q => q.QuestionType === 3)
                .reduce((sum, q) => sum + (q.Score || 0), 0);
            const percentage = this.totalScore ? ((score / this.totalScore) * 100).toFixed(1) : 0;
            return `${count}题 ${score}分 (${percentage}%)`;
        },
        subjectiveStats() {
            const count = this.QuestionnaireDetList.filter(q => q.QuestionType === 4).length;
            const score = this.QuestionnaireDetList
                .filter(q => q.QuestionType === 4)
                .reduce((sum, q) => sum + (q.Score || 0), 0);
            const percentage = this.totalScore ? ((score / this.totalScore) * 100).toFixed(1) : 0;
            return `${count}题 ${score}分 (${percentage}%)`;
        }
    },
    filters: {

    },
    data() {
        let now = moment().format('YYYY-MM-DD');
        return {
            now: now,
            detail: {},
            SingleChoiseQuestionDataList: [],
            MultipleChoiceQuestionDataList: [],
            JudgmentQuestionDataList: [],
            SubjectiveQuestionDataList: [],
            Type: null,
            selectedSingleQuestions: [],
            selectedMultipleQuestions: [],
            selectedJudgmentQuestions: [],
            selectedSubjectiveQuestions: [],
            searchKeyword: '',

            QuestionnaireDetList: [],
        };
    },
    mounted() {
        this.SingleChoiseQuestionListApi();
        this.MultipleChoiceQuestionListApi();
        this.JudgmentQuestionListApi();
        this.SubjectiveQuestionListApi();
        this.QuestionnaireDetListApi();
    },
    destroyed() {

    },
    methods: {
        //试卷的明细
        async QuestionnaireDetListApi() {
            let { Data: { Items } } = await this.$Post('/QuestionnaireDet/List', { QuestionnaireId: this.$route.query.QuestionnaireId });
            this.QuestionnaireDetList = Items;
            //单选题勾选
            this.selectedSingleQuestions = this.QuestionnaireDetList.filter(item => item.QuestionType == 1).map(item => item.QuestionId);

            //多选题勾选
            this.selectedMultipleQuestions = this.QuestionnaireDetList.filter(item => item.QuestionType == 2).map(item => item.QuestionId);

            //判断题勾选
            this.selectedJudgmentQuestions = this.QuestionnaireDetList.filter(item => item.QuestionType == 3).map(item => item.QuestionId);

            //主观题勾选
            this.selectedSubjectiveQuestions = this.QuestionnaireDetList.filter(item => item.QuestionType == 4).map(item => item.QuestionId);

        },

        async SingleChoiseQuestionListApi() {
            let { Data: { Items } } = await this.$Post('/SingleChoiseQuestion/List', { Type: this.Type });
            this.SingleChoiseQuestionDataList = Items;
        },
        async MultipleChoiceQuestionListApi() {
            let { Data: { Items } } = await this.$Post('/MultipleChoiceQuestion/List', { Type: this.Type });
            this.MultipleChoiceQuestionDataList = Items;
        },
        async JudgmentQuestionListApi() {
            let { Data: { Items } } = await this.$Post('/JudgmentQuestion/List', { Type: this.Type });
            this.JudgmentQuestionDataList = Items;
        },
        async SubjectiveQuestionListApi() {
            let { Data: { Items } } = await this.$Post('/SubjectiveQuestion/List', { Type: this.Type });
            this.SubjectiveQuestionDataList = Items;
        },
        handleSearch() {
            // 搜索防抖可以在这里添加
        },
        handleSingleQuestionsChange(value) {
            this.updateQuestionnaireList(value, 1, this.SingleChoiseQuestionDataList);
        },

        handleMultipleQuestionsChange(value) {
            this.updateQuestionnaireList(value, 2, this.MultipleChoiceQuestionDataList);
        },

        handleJudgmentQuestionsChange(value) {
            this.updateQuestionnaireList(value, 3, this.JudgmentQuestionDataList);
        },

        handleSubjectiveQuestionsChange(value) {
            this.updateQuestionnaireList(value, 4, this.SubjectiveQuestionDataList);
        },

        updateQuestionnaireList(selectedIds, questionType, sourceList) {
            // 获取当前类型的现有题目ID列表
            const existingIds = this.QuestionnaireDetList
                .filter(item => item.QuestionType === questionType)
                .map(item => item.QuestionId);

            // 找出需要新增的题目ID
            const newIds = selectedIds.filter(id => !existingIds.includes(id));

            // 找出需要移除的题目ID
            const removeIds = existingIds.filter(id => !selectedIds.includes(id));

            // 移除取消选中的题目
            this.QuestionnaireDetList = this.QuestionnaireDetList.filter(
                item => !(item.QuestionType === questionType && removeIds.includes(item.QuestionId))
            );

            // 添加新选中的题目
            const newQuestions = sourceList
                .filter(item => newIds.includes(item.Id))
                .map(item => {
                    const QuestionnaireDet = {
                        QuestionId: item.Id,
                        Score: 0,
                        QuestionnaireId: this.$route.query.QuestionnaireId,
                        QuestionType: questionType,
                        Title: item.Title
                    };

                    if (questionType === 1) {
                        QuestionnaireDet.SingleChoiseQuestionDto = item;
                    }
                    if (questionType === 2) {
                        QuestionnaireDet.MultipleChoiceQuestionDto = item;
                    }
                    if (questionType === 3) {
                        QuestionnaireDet.JudgmentQuestionDto = item;
                    }
                    if (questionType === 4) {
                        QuestionnaireDet.SubjectiveQuestionDto = item;
                    }
                    return QuestionnaireDet;
                });

            this.QuestionnaireDetList = [...this.QuestionnaireDetList, ...newQuestions];
        },
        //滚动到自定题目
        async ScrollToViewQuestion(item) {
            var target = document.getElementById("Quetsion" + item.QuestionType + item.QuestionId);
            target.scrollIntoView({ behavior: 'smooth' });
        },
        async handleSave() {
            //判断明细是否都有分值
            let hasScore = this.QuestionnaireDetList.every(item => item.Score);
            if (!hasScore) {
                //得到没有分值的第一个
                let firstNoScore = this.QuestionnaireDetList.find(item => !item.Score);
                //滚动到第一个没有分值的题目
                let target = document.getElementById("Quetsion" + firstNoScore.QuestionType + firstNoScore.QuestionId);
                target.scrollIntoView({ behavior: 'smooth' });
                this.$message.error('请为所有题目设置分值');
                return;
            }
            try {
                await this.$Post('/QuestionnaireDet/BatchCreate', this.QuestionnaireDetList);
                this.$message.success('保存成功');
                this.$router.go(-1);
            } catch (error) {
                this.$message.error('保存失败');
            }
        },
        // 添加上移方法
        handleMoveUp(index) {
            if (index > 0) {
                const list = [...this.QuestionnaireDetList];
                const temp = list[index];
                list[index] = list[index - 1];
                list[index - 1] = temp;
                this.QuestionnaireDetList = list;
            }
        },
        // 添加下移方法
        handleMoveDown(index) {
            if (index < this.QuestionnaireDetList.length - 1) {
                const list = [...this.QuestionnaireDetList];
                const temp = list[index];
                list[index] = list[index + 1];
                list[index + 1] = temp;
                this.QuestionnaireDetList = list;
            }
        },
    }
}
</script>

<style lang="scss" scoped>
/* 搜索框样式 */
.search-box {
    margin-bottom: 15px;
}



/* 滚动条样式优化 */
.fixed-question-list::-webkit-scrollbar {
    width: 6px;
}

.fixed-question-list::-webkit-scrollbar-thumb {
    background-color: #dcdfe6;
    border-radius: 3px;
}

.fixed-question-list::-webkit-scrollbar-track {
    background-color: #f5f7fa;
}

/* 统计信息样式 */
.statistics-info {
    display: flex;
    gap: 20px;

    div {
        font-size: 14px;
        color: #606266;
    }
}

.header-left {
    display: flex;
    align-items: center;
}
</style>
