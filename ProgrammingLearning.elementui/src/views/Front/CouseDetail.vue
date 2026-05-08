<template>
    <div v-if="Couse">
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/Front/CouseList' }">线上课程</el-breadcrumb-item>
                <el-breadcrumb-item>课程详情</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="card margin-top-xs">
            <div class="course-header">
                <div class="course-cover">
                    <img :src="Couse.Cover" :alt="Couse.Name" class="cover-image">
                </div>
                <div class="course-info">
                    <h1 class="course-title">{{ Couse.Name }}</h1>
                    <div class="course-meta">
                        <span class="meta-item"><i class="el-icon-notebook-1"></i>{{ Couse.CouseTypeDto.Name }}</span>
                        <span class="meta-item"><i class="el-icon-time"></i>{{ Couse.CreationTime }}</span>
                        <span class="meta-item"><i class="el-icon-reading"></i>{{ Couse.CouseStepCount }}节</span>
                        <span class="meta-item"><i class="el-icon-user"></i>{{ Couse.EnrollUserCount }}人</span>
                        <span class="meta-item price">
                            <i class="el-icon-price-tag"></i>
                            <template v-if="Couse.IsFree">免费</template>
                            <template v-else>￥{{ Couse.Price }}</template>
                        </span>
                        <span class="meta-item status">{{ Couse.IsEnroll ? '已报名' : '未报名' }}</span>
                    </div>
                    <div class="course-desc" v-html="Couse.Content"></div>
                    <div class="enroll-action">
                        <!-- 未报名且不是免费课程时显示报名按钮 -->
                        <el-button v-if="!Couse.IsEnroll && !Couse.IsFree" type="primary" size="large"
                            @click="handleEnroll">
                            立即报名 ￥{{ Couse.Price }}
                        </el-button>
                        <!-- 未报名且是免费课程时显示免费报名按钮 -->
                        <el-button v-if="!Couse.IsEnroll && Couse.IsFree" type="success" size="large" @click="handleEnroll">
                            免费报名
                        </el-button>
                    </div>
                </div>
            </div>

            <div class="course-steps">
                <h2>锻炼课程</h2>
                <el-collapse v-model="activeNames">
                    <el-collapse-item v-for="(step, index) in Couse.CouseStepDtos" :key="step.Id"
                        :title="`第${index + 1}章：${step.Name}`" :name="step.Id">
                        <div class="step-content">
                            <div class="step-info">
                                <div v-if="step.Durtion" class="duration">
                                    时长：{{ step.Durtion | formatDuration }}
                                </div>
                            </div>
                            <div v-if="Couse.IsEnroll" class="step-actions">
                                <el-button v-if="step.VideoUrls" type="primary" size="small" @click="watchVideo(step)">
                                    观看视频
                                </el-button>
                                <el-button v-if="step.FileUrls" type="success" size="small" @click="downloadFile(step)">
                                    下载资源
                                </el-button>
                            </div>
                            <div v-else class="step-actions">
                                <el-tag type="warning">报名后可观看课程内容</el-tag>
                            </div>
                        </div>
                    </el-collapse-item>
                </el-collapse>
            </div>
        </div>
    </div>
</template>
<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import SelectTab from '@/components/Select/SelectTab.vue';
import { mapGetters } from 'vuex';
export default {
    name: 'Couse',
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
            Couse: null,
            activeNames: [],
        }
    },
    watch: {
        "$route": async function (to, from) {
            await this.GeCouseApi();
        }
    },
    created() {
        this.GetCouseApi();

    },
    methods: {



        //得到数据接口
        async GetCouseApi() {
            let { Data
            } = await this.$Post("/Couse/Get",
                {
                    Id: this.$route.query.CouseId
                })
            this.Couse = Data;
            this.$nextTick(() => { })
        },



        watchVideo(step) {
            this.$router.push({ path: '/Front/PlayVideo', query: { CouseStepId: step.Id, CouseId: this.$route.query.CouseId } });
        },

        downloadFile(step) {
            if (step.FileUrls) {
                window.open(step.FileUrls, '_blank');
            }
        },

        async handleEnroll() {
            if (!this.Token) {
                this.$message.warning('请先登录后再报名');
                return;
            }

            this.$router.push(`/Front/ToAppoint?CouseId=${this.Couse.Id}`);
        }
    },
}
</script>
<style scoped lang="scss">
.card {
    background: #fff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.course-header {
    display: flex;
    gap: 40px;
    margin-bottom: 40px;

    .course-cover {
        width: 320px;

        .cover-image {
            width: 100%;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
            transition: transform 0.3s ease;

            &:hover {
                transform: translateY(-5px);
            }
        }
    }
}

.course-info {
    flex: 1;

    .course-title {
        font-size: 28px;
        font-weight: 600;
        color: #2c3e50;
        margin-bottom: 24px;
        line-height: 1.4;
    }

    .course-meta {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        margin: 20px 0;
        padding: 15px;
        background: #f8f9fa;
        border-radius: 8px;

        .meta-item {
            display: flex;
            align-items: center;
            color: #606266;
            font-size: 14px;

            i {
                margin-right: 6px;
                font-size: 16px;
            }

            &.price {
                color: #f56c6c;
                font-weight: 600;
                font-size: 16px;
            }

            &.status {
                background: #67c23a;
                color: white;
                padding: 4px 12px;
                border-radius: 4px;
            }
        }
    }

    .course-desc {
        color: #606266;
        line-height: 1.8;
        margin: 24px 0;
        padding: 20px;
        background: #fafafa;
        border-radius: 8px;
    }
}

.course-steps {
    h2 {
        margin-bottom: 20px;
    }

    .step-content {
        display: flex;
        justify-content: space-between;
        align-items: center;

        .step-info {
            display: flex;
            align-items: center;
            gap: 15px;

            .step-cover {
                width: 120px;
                border-radius: 4px;
            }

            .duration {
                color: #666;
            }
        }

        .step-actions {
            display: flex;
            gap: 10px;
            align-items: center;

            .el-tag {
                margin: 0;
                font-size: 13px;
            }
        }
    }
}

.margin-top-xs {
    margin-top: 15px;
}

.enroll-action {
    margin-top: 30px;

    .el-button {
        padding: 14px 36px;
        font-size: 16px;
        font-weight: 500;
        border-radius: 8px;

        &:hover {
            transform: translateY(-2px);
            transition: transform 0.2s ease;
        }
    }
}
</style>