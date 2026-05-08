<template>
    <div class="home-container">
        <!-- 面包屑导航优化 -->
        <div class="breadcrumb-wrapper">
            <span class="location-text">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="card margin-top-xs" style="display: flex; gap: 20px;">
            <div style="height: 350px;width: 800px;">

                <el-carousel :interval="5000" height="350px">
                    <el-carousel-item>
                        <img class="banner-img" :src="require('@/assets/banner01.jpg')"
                            style="width: 100%;height: 350px;object-fit: cover;" />
                    </el-carousel-item>
                    <el-carousel-item>
                        <img class="banner-img" :src="require('@/assets/banner02.jpg')"
                            style="width: 100%;height: 350px;object-fit: cover;" />
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div class="notice-container" style="flex: 1;">
                <div class="notice-header">
                    <h3>系统公告</h3>
                    <el-button type="text" @click="viewMore">查看更多</el-button>
                </div>
                <div class="notice-list">
                    <div v-for="item in SysNoticeDataList" :key="item.Id" class="notice-item" @click="ToNoticeDetail(item)">
                        <div class="notice-title">{{ item.Title }}</div>
                        <div class="notice-time">{{ item.CreationTime }}</div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 推荐话题标题区域 -->
        <div class="topic-title-container">
            <h2 class="topic-title">
                <span class="title-line left"></span>
                <span class="title-text">推荐交流话题</span>
                <span class="title-line right"></span>
            </h2>
        </div>

        <PaginationBox ref="TopicPaginationBox" :where="{}" url="/Topic/RecommendTop10" :limit="8">
            <template v-slot:content="{ data }">
                <div class="topic-grid">
                    <div v-for="(item, index) in data" :key="item.Id" class="topic-card" @click="toTopicDetail(item)">
                        <div class="topic-image">
                            <img :src="item.Cover" :alt="item.Title" />
                            <div class="topic-type">{{ item.TopicTypeDto.Name }}</div>
                        </div>
                        <div class="topic-content">
                            <div class="topic-header">
                                <div class="user-info">
                                    <img :src="item.CreatorAppUserDto.ImageUrls" class="avatar" />
                                    <span class="username">{{ item.CreatorAppUserDto.Name }}</span>
                                </div>
                                <div class="post-time">{{ item.CreationTime }}</div>
                            </div>
                            <h3 class="topic-title">{{ item.Title }}</h3>
                            <div class="topic-stats">
                                <span class="view-count">
                                    <i class="el-icon-view"></i>
                                    {{ item.ViewCount || 0 }}
                                </span>
                                <span class="comment-count">
                                    <i class="el-icon-chat-dot-round"></i>
                                    {{ item.CommentDtos ? item.CommentDtos.length : 0 }}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
        </PaginationBox>


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
            BannerList: [],
            where: {},
            SysNoticeDataList: []
        }
    },
    created() {
        this.SysNoticeListApi();
    },
    methods: {


        async SysNoticeListApi() {
            let { Data: { Items } } = await this.$Post('/SysNotice/List', { Id: this.$route.query.SysNoticeId });
            this.SysNoticeDataList = Items;
        }, viewMore() {
            this.$router.push('/Front/SysNoticeList');
        },
        async ToNoticeDetail(item) {
            this.$router.push({
                path: '/Front/SysNoticeDetail',
                query: {
                    SysNoticeId: item.Id,
                }
            })
        },
        async ToPathPage(item) {

            window.location.href = item.Path;
        },
        toTopicDetail(item) {
            this.$router.push({
                path: '/Front/TopicDetail',
                query: { TopicId: item.Id }
            });
        }

    },


}
</script>


<style scoped>
.breadcrumb-wrapper {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

.location-text {
    font-weight: bold;
    margin-right: 10px;
    font-size: 14px;
}

.banner-notice-wrapper {
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
}

.content-box {
    display: flex;
    gap: 20px;
    padding: 20px;
}

.banner-carousel {
    flex: 1;
    border-radius: 8px;
    overflow: hidden;
}

.carousel-item {
    border-radius: 8px;
}

.banner-item {
    position: relative;
    height: 100%;
    cursor: pointer;
    transition: transform 0.3s;
}

.banner-item:hover {
    transform: scale(1.01);
}

.banner-item img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 8px;
}

.banner-overlay {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 20px;
    background: linear-gradient(transparent, rgba(0, 0, 0, 0.7));
    color: #fff;
}

.notice-container {
    background: #fff;
    padding: 15px;
    height: 350px;
    overflow: hidden;
}

.notice-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
    border-bottom: 1px solid #eee;
    padding-bottom: 10px;
}

.notice-header h3 {
    margin: 0;
    color: #333;
}

.notice-list {
    overflow-y: auto;
    height: calc(100% - 50px);
}

.notice-item {
    padding: 10px 0;
    border-bottom: 1px solid #f5f5f5;
    cursor: pointer;
}

.notice-item:hover {
    background-color: #f5f5f5;
}

.notice-title {
    font-size: 14px;
    color: #333;
    margin-bottom: 5px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.notice-time {
    font-size: 12px;
    color: #999;
}

@media (max-width: 768px) {
    .content-box {
        flex-direction: column;
    }

    .notice-board {
        width: 100%;
    }

    .article-item {
        flex-direction: column;
    }

    .article-item img {
        width: 100%;
    }
}

/* 推荐话题标题样式 */
.topic-title-container {
    text-align: center;
    padding: 40px 0;
    position: relative;
}

.topic-title {
    display: inline-flex;
    align-items: center;
    font-size: 26px;
    color: #333;
    position: relative;
}

.title-text {
    position: relative;
    padding: 0 20px;
}

.title-text::after {
    content: '';
    position: absolute;
    bottom: -10px;
    left: 50%;
    transform: translateX(-50%);
    width: 40%;
    height: 3px;
    background: #409EFF;
    border-radius: 2px;
}

.title-line {
    width: 60px;
    height: 2px;
    background: linear-gradient(90deg, transparent, #409EFF);
    position: relative;
    margin: 0 15px;
}

.title-line.left {
    background: linear-gradient(90deg, #409EFF, rgba(64, 158, 255, 0.2));
}

.title-line.right {
    background: linear-gradient(90deg, rgba(64, 158, 255, 0.2), #409EFF);
}

.title-line::before {
    content: '';
    position: absolute;
    top: -3px;
    width: 8px;
    height: 8px;
    background: #409EFF;
    border-radius: 50%;
}

.title-line.left::before {
    right: 0;
    animation: pulse 2s infinite;
}

.title-line.right::before {
    left: 0;
    animation: pulse 2s infinite;
}

@keyframes pulse {
    0% {
        transform: scale(1);
        opacity: 1;
    }

    50% {
        transform: scale(1.5);
        opacity: 0.5;
    }

    100% {
        transform: scale(1);
        opacity: 1;
    }
}

/* 话题展示区域样式 */
.topic-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 20px;
    width: 100%;
    padding: 20px 0;
}

.topic-card {
    background: #fff;
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    cursor: pointer;
}

.topic-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.topic-image {
    position: relative;
    height: 200px;
    overflow: hidden;
}

.topic-image img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.topic-type {
    position: absolute;
    top: 10px;
    right: 10px;
    background: rgba(64, 158, 255, 0.9);
    color: #fff;
    padding: 4px 12px;
    border-radius: 20px;
    font-size: 12px;
}

.topic-content {
    padding: 15px;
}

.topic-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.user-info {
    display: flex;
    align-items: center;
    gap: 8px;
}

.avatar {
    width: 24px;
    height: 24px;
    border-radius: 50%;
    object-fit: cover;
}

.username {
    font-size: 14px;
    color: #333;
}

.post-time {
    font-size: 12px;
    color: #999;
}

.topic-title {
    font-size: 16px;
    color: #333;
    margin: 10px 0;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
}

.topic-stats {
    display: flex;
    gap: 15px;
    color: #666;
    font-size: 12px;
}

.topic-stats i {
    margin-right: 4px;
}
</style>