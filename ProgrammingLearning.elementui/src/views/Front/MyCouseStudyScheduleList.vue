<template>
    <div>
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/Front/UserCenter' }">个人中心</el-breadcrumb-item>
                <el-breadcrumb-item>我的课程学习</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <PaginationBox ref="PaginationBox" :where="where" url="/CouseStudySchedule/List">
            <template v-slot:content="{ data }">
                <el-timeline style="width: 100%;">
                    <el-timeline-item v-for="item in data" :key="item.Id" :timestamp="item.BeginTime" placement="top">
                        <el-card>
                            <div class="course-info">
                                <el-image :src="item.CouseDto.Cover" fit="cover"
                                    style="width: 120px; height: 80px; margin-right: 15px"></el-image>
                                <div class="course-details">
                                    <h4>{{ item.CouseDto.Name }}</h4>
                                    <p class="chapter">章节：{{ item.CouseStepDto.Name }}</p>
                                    <div class="study-info">
                                        <span>学习时长：{{ item.TotalDurtion | formatDuration }}</span>
                                        <el-button type="success" size="small" @click="ToDetail(item)">继续学习</el-button>
                                    </div>
                                </div>
                            </div>
                        </el-card>
                    </el-timeline-item>
                </el-timeline>
            </template>
        </PaginationBox>
    </div>
</template>
<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import SelectTab from '@/components/Select/SelectTab.vue';
import { mapGetters } from 'vuex';
export default {
    name: 'CouseStudy',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', 'UserId'])
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
        this.where.StudyUserId = this.UserId;
    },
    methods: {
        ToDetail(item) {
            this.$router.push(`/Front/PlayVideo?CouseStepId=${item.CouseStepId}&CouseId=${item.CouseId}`);
        }
    },
}
</script>
<style scoped>
.course-info {
    display: flex;
    align-items: flex-start;
    width: 100%;
}

.course-details {
    flex: 1;
    width: calc(100% - 135px);
}

:deep(.el-timeline-item__content) {
    width: 100%;
}

:deep(.el-card) {
    width: 100%;
}

.course-details h4 {
    margin: 0 0 10px 0;
    color: #303133;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.chapter {
    color: #606266;
    margin: 5px 0;
    font-size: 14px;
}

.study-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 10px;
    color: #909399;
    font-size: 13px;
}
</style>