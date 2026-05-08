<template>
    <div v-if="CouseStep">
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item
                    :to="{ path: `/Front/CouseDetail?CouseId=${$route.query.CouseId}` }">课程详情</el-breadcrumb-item>
                <el-breadcrumb-item>播放视频</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!-- Course information -->
        <div class="course-info margin-top-xs">
            <div class="video-section">
                <!-- Video player -->
                <video id="myVideo" v-if="CouseStep.VideoUrls" :src="CouseStep.VideoUrls" controls
                    class="main-video-player"></video>

                <!-- Video details -->
                <div class="video-details">
                    <h1 class="video-title">{{ CouseStep.Name }}</h1>
                    <div class="video-meta">
                        <span class="duration">
                            <i class="el-icon-time"></i>
                            时长: {{ CouseStep.Durtion | formatDuration }}
                        </span>
                        <span class="publish-date">
                            <i class="el-icon-date"></i>
                            发布时间: {{ CouseStep.CreationTime }}
                        </span>
                        <span class="completion-status" v-if="CouseStep.IsClock">
                            <i class="el-icon-check" style="color: #67C23A;"></i>
                            已完成打卡
                        </span>
                        <el-tooltip class="item" effect="dark" placement="top">
                            <div slot="content">观看时长超过60%将自动完成打卡</div>
                            <i class="el-icon-question" style="cursor: pointer; margin-left: 5px;"></i>
                        </el-tooltip>
                    </div>
                </div>



                <!-- Course materials -->
                <div class="course-materials" v-if="CouseStep.FileUrls">
                    <h3>课程资料</h3>
                    <el-button type="primary" size="medium" @click="downloadMaterial(CouseStep.FileUrls)">
                        <i class="el-icon-download"></i>
                        下载学习资料
                    </el-button>

                </div>


                <CourseChapters :currentChapterId="$route.query.CouseStepId" @chapter-click="handleChapterClick">
                </CourseChapters>


            </div>
        </div>
    </div>
</template>
<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import SelectTab from '@/components/Select/SelectTab.vue';
import CourseChapters from '@/views/Front/CourseChapters.vue';
import moment from 'moment';
import { mapGetters } from 'vuex';
export default {
    name: 'PlayVideo',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', 'UserId'
        ])
    },
    components: {
        CourseChapters,
        PaginationBox,
        SelectTab
    },
    data() {
        return {

            PlayVideo: null,
            CouseStepId: null,
            CouseStep: null,
            Couse: null,
            CurrentCouseStudyId: null,
            timer: null,
        }
    },
    watch: {
        "$route": async function (to, from) {
            this.CouseStepId = this.$route.query.CouseStepId;
            this.GetCouseApi();
            this.GetCouseStepApi();
        }
    },
    created() {
        this.CouseStepId = this.$route.query.CouseStepId;
        this.GetCouseApi();

        this.GetCouseStepApi();
    },
    methods: {

        //视频初始化
        async VideoInit() {
            let elevideo = document.getElementById('myVideo');

            elevideo.addEventListener('waiting', function () { //加载
                console.log("加载中");
            });
            elevideo.addEventListener('loadedmetadata', () => {
                //视频的总长度
                console.log('总长度:' + elevideo.duration);
                clearInterval(this.timer);
            });
            elevideo.addEventListener('play', () => {
                //播放开始执行的函数
                console.log('开始播放');
            });

            elevideo.addEventListener('playing', () => {
                //播放中
                console.log('播放中');
                this.timer = setInterval(() => {
                    console.log('播放进度:' + parseFloat(elevideo.currentTime));
                    this.SyncVideoTime();
                }, 1000);
            });
            elevideo.addEventListener('pause', () => { //暂停开始执行的函数
                console.log("暂停播放");
                clearInterval(this.timer);
            });
            elevideo.addEventListener(
                'ended',
                () => {
                    //结束
                    console.log('播放结束');
                    clearInterval(this.timer);
                    this.SyncVideoTime();
                },
                false
            );
        },

        async SyncVideoTime() {

            let { Data } = await this.$Post("/CouseStudySchedule/UpdateTotalDurtion", {
                Id: this.CurrentCouseStudyId,
                CouseStepId: this.$route.query.CouseStepId,
                Durtion: 1,
                CouseId: this.$route.query.CouseId,
                StudyUserId: this.UserId,
                BeginTime: moment().format("YYYY-MM-DD HH:mm:ss"),
                LastUploadTime: moment().format("YYYY-MM-DD HH:mm:ss"),

            })
            this.CurrentCouseStudyId = Data.Id;
        },
        //得到数据接口
        async GetCouseApi() {
            let { Data
            } = await this.$Post("/Couse/Get",
                {
                    Id: this.$route.query.CouseId
                })
            this.Couse = Data;
            this.$nextTick(() => {

            })
        },



        handleChapterClick(chapter) {
            this.$router.push(`/Front/PlayVideo?CouseStepId=${chapter.Id}&CouseId=${this.$route.query.CouseId}`);
        },
        downloadMaterial(url) {
            window.open(url, '_blank')
        },


        async GetCouseStepApi() {
            let { Data } = await this.$Post("/CouseStep/Get", {
                Id: this.CouseStepId
            })
            this.CouseStep = Data;
            this.$nextTick(() => {
                this.VideoInit();
            })
        },



    },
}
</script>
<style scoped lang="scss">
.video-player-container {
    padding: 20px;
    background: #f5f7fa;
    min-height: 100vh;
}

.breadcrumb-nav {
    background: white;
    padding: 15px;
    border-radius: 8px;
    margin-bottom: 20px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);

    .location-text {
        font-weight: bold;
        margin-right: 10px;
        color: #303133;
    }
}

.video-section {
    background: white;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.main-video-player {
    width: 100%;
    max-height: 600px;
    border-radius: 8px;
    margin-bottom: 20px;
}

.video-details {
    margin-bottom: 30px;

    .video-title {
        font-size: 24px;
        color: #303133;
        margin-bottom: 15px;
    }

    .video-meta {
        display: flex;
        align-items: center;
        gap: 20px;
        color: #909399;
        font-size: 14px;

        .completion-status {
            display: flex;
            align-items: center;
            gap: 5px;
        }

        i {
            margin-right: 5px;
        }
    }
}

.course-description {
    margin-bottom: 30px;

    h3 {
        font-size: 18px;
        color: #303133;
        margin-bottom: 15px;
    }

    p {
        color: #606266;
        line-height: 1.6;
    }
}

.course-materials {
    margin-bottom: 30px;

    h3 {
        font-size: 18px;
        color: #303133;
        margin-bottom: 15px;
    }
}

.points-info {
    margin-top: 20px;

    .points-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 20px;
        text-align: center;
    }

    .point-item {
        padding: 15px;

        .point-value {
            font-size: 24px;
            color: #409EFF;
            font-weight: bold;
        }

        .point-label {
            color: #909399;
            margin-top: 5px;
        }
    }
}
</style>