<template>
    <div v-if="AskedQuestions" class="question-detail-container">
        <!-- 面包屑导航 -->
        <div class="breadcrumb-wrapper">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/Front/AskedQuestionsList' }">常见问题</el-breadcrumb-item>
                <el-breadcrumb-item>问题详情</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <!-- 问题详情卡片 -->
        <div class="question-card">
            <!-- 问题标题区域 -->
            <div class="question-header">
                <h1 class="question-title">{{ AskedQuestions.Title }}</h1>
                <div class="question-meta">
                    <span class="type-tag">{{ AskedQuestions.Type }}</span>
                    <span class="time">发布于 {{ AskedQuestions.CreationTime }}</span>
                </div>
            </div>

            <!-- 问题内容区域 -->
            <div class="question-content" v-html="AskedQuestions.Content">

            </div>

            <!-- 问题反馈区域 -->
            <div class="feedback-section">
                <div class="feedback-title">这个回答对您有帮助吗？</div>
                <div class="feedback-buttons">
                    <el-button type="primary" plain size="small" @click="AvailCountFeedback()">
                        <i class="el-icon-thumb"></i>
                        有帮助 ({{ AskedQuestions.AvailCount }})
                    </el-button>
                    <el-button type="info" plain size="small" @click="NoAvailCountFeedback()">
                        <i class="el-icon-thumb" style="transform: rotate(180deg)"></i>
                        没帮助 ({{ AskedQuestions.NoAvailCount }})
                    </el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.question-detail-container {

    margin: 0 auto;

}

.breadcrumb-wrapper {
    margin-bottom: 24px;
    padding: 12px 0;
    border-bottom: 1px solid #ebeef5;
}

.question-card {
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
    padding: 24px;
}

.question-header {
    margin-bottom: 24px;

    .question-title {
        font-size: 24px;
        color: #303133;
        margin: 0 0 16px 0;
        font-weight: 600;
    }

    .question-meta {
        display: flex;
        align-items: center;
        gap: 16px;
        color: #909399;
        font-size: 14px;

        .type-tag {
            background: #f0f2f5;
            color: #606266;
            padding: 2px 8px;
            border-radius: 4px;
        }

        .time {
            color: #909399;
        }
    }
}

.question-content {
    font-size: 16px;
    line-height: 1.8;
    color: #606266;
    margin-bottom: 32px;
    padding: 16px;
    background: #f8f9fa;
    border-radius: 4px;
}

.feedback-section {
    border-top: 1px solid #ebeef5;
    padding-top: 24px;
    text-align: center;

    .feedback-title {
        font-size: 16px;
        color: #606266;
        margin-bottom: 16px;
    }

    .feedback-buttons {
        display: flex;
        justify-content: center;
        gap: 16px;

        .el-button {
            min-width: 120px;

            i {
                margin-right: 4px;
            }
        }
    }
}
</style>

<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import SelectTab from '@/components/Select/SelectTab.vue';
import { mapGetters } from 'vuex';
export default {
    name: 'AskedQuestions',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', 'UserId'
        ])
    },
    components: {
        PaginationBox,
        SelectTab
    },
    data() {
        return {
            AskedQuestions: null,
        }
    },
    watch: {
        "$route": async function (to, from) {
            await this.GeAskedQuestionsApi();
        }
    },
    created() {
        this.GetAskedQuestionsApi();
    },
    methods: {
        //得到数据接口
        async GetAskedQuestionsApi() {
            let { Data
            } = await this.$Post("/AskedQuestions/Get",
                {
                    Id: this.$route.query.AskedQuestionsId
                })
            this.AskedQuestions = Data;
            this.$nextTick(() => { })
        },

        // 添加新的反馈处理方法
        async AvailCountFeedback() {

            await this.$Post("/AskedQuestions/AvailCountFeedback", {
                Id: this.$route.query.AskedQuestionsId,
            });
            this.AskedQuestions.AvailCount++;
            // TODO: 实现反馈功能
            this.$message({
                message: '感谢您的反馈！',
                type: 'success'
            });
        },
        async NoAvailCountFeedback() {
            await this.$Post("/AskedQuestions/NoAvailCountFeedback", {
                Id: this.$route.query.AskedQuestionsId,
            });
            this.AskedQuestions.NoAvailCount++;
        }

    },
}
</script>