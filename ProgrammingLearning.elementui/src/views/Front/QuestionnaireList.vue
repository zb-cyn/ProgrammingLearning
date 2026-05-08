<template>
    <div>
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>编程学习试卷列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>



        <div class="card margin-top-xs">


            <div class=" header-title ">编程学习试卷列表
            </div>

            <PaginationBox url="/Test/List" ref="PaginationBox" :where="{ IsMy: true }">
                <template v-slot:content="{ data }">
                    <!-- 考试列表卡片 -->
                    <div class="exam-list">
                        <div v-for="item in data" :key="item.Id" class="exam-card" @click="ToDetail(item)">
                            <div class="exam-header">
                                <h3 class="exam-title">{{ item.Title }}</h3>
                                <div class="exam-status">
                                    <span class="exam-type">{{ item.QuestionnaireDto.Type }}</span>
                                    <span :class="['finish-status', item.IsFinish ? 'finished' : 'unfinished']">
                                        {{ item.IsFinish ? '已完成' : '未完成' }}
                                    </span>
                                </div>
                            </div>

                            <div class="exam-info">


                                <div class="exam-details">
                                    <div class="detail-item">
                                        <i class="el-icon-time"></i>
                                        <span>考试时长：{{ item.QuestionnaireDto.LimitMinute }}分钟</span>
                                    </div>
                                    <div class="detail-item">
                                        <i class="el-icon-date"></i>
                                        <span>开始时间：{{ item.BeginActiveTime }}</span>
                                    </div>
                                    <div class="detail-item">
                                        <i class="el-icon-date"></i>
                                        <span>结束时间：{{ item.EndActiveTime }}</span>
                                    </div>

                                </div>
                            </div>

                            <div class="exam-footer">
                                <span class="exam-subtitle">{{ item.QuestionnaireDto.SmallTitle }}</span>
                                <el-button type="primary" size="small" v-if="!item.IsFinish">进入考试</el-button>
                                <el-button type="primary" size="small" v-else>再次测试</el-button>
                            </div>
                        </div>
                    </div>
                </template>
            </PaginationBox>
        </div>

    </div>
</template>


<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import SelectTab from '@/components/Select/SelectTab.vue';
import { mapGetters } from 'vuex';
export default {
    name: 'Home',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    components: {
        PaginationBox,
        SelectTab
    },
    data() {
        return {

            where: {}
        }
    },
    created() {

    },
    methods: {

        async ToDetail(item) {
            if (this.CheckIsLogin()) {

                let { Data, Success } = await this.$Post("/UserAnswer/CheckIsExist", { TestId: item.Id, QuestionnaireId: item.QuestionnaireId, UserId: this.UserId })
                if (Success) {
                    this.$router.push({
                        path: "/Front/QuestionnaireAnswer",
                        query: {
                            TestId: item.Id,
                            QuestionnaireId: item.QuestionnaireId,
                            UserAnswerId: Data.Id
                        }
                    })
                }
            }
        },
        async TypeCallBack() {
            this.$refs.PaginationBox.Reload(this.where);
        },
        async ToAiAnswer() {
            this.$router.push({
                path: '/Front/MyAi',
                query: {

                }
            })
        }
    },


}
</script>


<style scoped>
/* 考试列表容器 */
.exam-list {
    display: flex;
    flex-direction: column;
    gap: 20px;
    padding: 20px;
    width: 100%;
    margin: 0 auto;
}

/* 考试卡片样式 */
.exam-card {
    width: 100%;
    background: #fff;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s;
    cursor: pointer;
}

.exam-card:hover {
    transform: translateY(-5px);
}

/* 考试卡片头部 */
.exam-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
}

.exam-title {
    font-size: 18px;
    color: #303133;
    margin: 0;
}

.exam-type {
    background: #409EFF;
    color: white;
    padding: 4px 8px;
    border-radius: 4px;
    font-size: 12px;
}

/* 教师信息样式 */
.teacher-info {
    display: flex;
    align-items: center;
    margin-bottom: 15px;
}

.teacher-avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
    object-fit: cover;
}

.teacher-detail {
    display: flex;
    flex-direction: column;
}

.teacher-name {
    font-weight: bold;
    color: #303133;
}

.teacher-title {
    font-size: 12px;
    color: #909399;
}

/* 考试详情样式 */
.exam-details {
    background: #f5f7fa;
    padding: 10px;
    border-radius: 4px;
    margin-bottom: 15px;
}

.detail-item {
    display: flex;
    align-items: center;
    margin: 5px 0;
    color: #606266;
    font-size: 14px;
}

.detail-item i {
    margin-right: 5px;
    color: #409EFF;
}

/* 考试卡片底部 */
.exam-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 15px;
    padding-top: 15px;
    border-top: 1px solid #EBEEF5;
}

.exam-subtitle {
    color: #909399;
    font-size: 13px;
}

/* 添加考试状态样式 */
.exam-status {
    display: flex;
    gap: 10px;
    align-items: center;
}

.finish-status {
    padding: 4px 8px;
    border-radius: 4px;
    font-size: 12px;
}

.finished {
    background: #67C23A;
    color: white;
}

.unfinished {
    background: #E6A23C;
    color: white;
}
</style>