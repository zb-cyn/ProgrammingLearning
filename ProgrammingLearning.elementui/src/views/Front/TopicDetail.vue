<template>

    <div v-if="Detail != null" class="main-container">

        <el-page-header class="card " @back="goBack" content="话题详情">
        </el-page-header>

        <div class="QuestionHeader margin-top-xs">
            <div class="QuestionHeaderContent">
                <el-tag>{{ Detail.TopicTypeDto.Name }}</el-tag>
                <div class="QuestionHeader-title">
                    {{ Detail.Title }}
                </div>
                <div v-if="Detail.ImageUrls">
                    <el-carousel :interval="5000" height="480px">
                        <el-carousel-item v-for="(item, index) in Detail.ImageUrlList">
                            <img class="banner-img" :src="item.url"></img>
                        </el-carousel-item>

                    </el-carousel>
                </div>
                <div>
                    <div class="rich_content" v v-html="Detail.Content"></div>
                    <div class="flex align-center  margin-top-xs margin-bottom-lg" v-if="Token">
                        <el-button icon="el-icon-star-off" :type="CollectId > 0 ? 'warning' : 'primary'" size="mini"
                            @click="CollectBtn()">{{ CollectId > 0 ? '取消收藏' : '收藏' }}</el-button>
                        <el-button icon="el-icon-share" type="primary" size="mini"
                            @click="ShareBtn()">分享给Ta人</el-button>
                    </div>
                    <div class="text-gray">编辑于 {{ Detail.CreationTime }}</div>

                </div>
            </div>
        </div>
        <div class="Topstory-container">
            <div class="Topstory-mainColumn">

                <div class="Topstory-content">
                    <CommentTree :RelativeId="Detail.Id" Type="Topic"></CommentTree>
                </div>
            </div>
            <div class="side">
                <RelativeAuthorCard :UserId="Detail.CreatorId"></RelativeAuthorCard>

            </div>

        </div>


    </div>
</template>

<script>
import router from '@/router';
import { mapGetters } from 'vuex'
import RelativeAuthorCard from "@/views/Front/RelativeAuthorCard.vue"

import CommentTree from "@/views/Front/CommentTree.vue"
import RecommendAuthorListCard from "@/views/Front/RecommendAuthorListCard.vue"

export default {
    name: 'TopicDetail',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    components: {
        RelativeAuthorCard,
        CommentTree,
        RecommendAuthorListCard,

    },
    data() {
        return {
            Detail: null,
            CollectId: null,

        };
    },
    watch: { // 监听，当路由发生变化的时候执行
        $route(to, from) {
            if (to.query.TopicId != from.query.TopicId) {
                this.GetDetailApi();
                this.GetTopicCollectApi();
            }
        },
    },
    mounted() {
        this.GetDetailApi();
        this.GetTopicCollectApi();
    },
    methods: {
        async GetDetailApi() {
            let { Data } = await this.$Post(`/Topic/Get`, { Id: this.$route.query.TopicId });
            Data.ImageUrlList = this.$Comm.FullConvertUrlArray(Data.ImageUrls);

            this.Detail = Data;
            this.TopicRecordApi();


        },
        async TopicRecordApi() {
            if (this.Token) {
                let { Data } = await this.$Post(`/TopicRecord/CreateOrEdit`, {
                    TopicTypeId: this.Detail.TopicTypeId,
                    TopicId: this.$route.query.TopicId,
                    UserId: this.UserId,

                });
            }

        },
        async GetTopicCollectApi() {
            if (this.UserId > 0) {
                let {
                    Data: {
                        Items
                    }
                } = await this.$Post("/TopicCollect/List", {
                    UserId: this.UserId,
                    TopicId: this.$route.query.TopicId
                });
                this.CollectId = Items.length > 0 ? Items[0].Id : null;

            }

        },
        /**
         * 收藏或者取消按钮
         */
        async CollectBtn() {
            if (this.$Comm.CheckIsLogin()) {
                if (this.CollectId) {
                    await this.$Post("/TopicCollect/Delete", {
                        Id: this.CollectId
                    })
                    this.CollectId = null;
                    this.$message.success("取消收藏");

                } else {
                    let {
                        Data
                    } = await this.$Post("/TopicCollect/CreateOrEdit", {
                        UserId: this.UserId,
                        TopicId: this.$route.query.TopicId
                    })
                    this.$message.success("收藏成功");
                    this.CollectId = Data.Id;
                }
            }
        },
        //分享给Ta人
        async ShareBtn() {
            const path = window.location.href;
            const el = document.createElement('textarea');
            el.value = path;
            document.body.appendChild(el);
            el.select();
            document.execCommand('copy');
            document.body.removeChild(el);
            this.$message.success("以及粘贴到剪切板了,赶紧分享给你的好友吧！");
        },
    }


}
</script>

<style lang="scss" scoped>
.Topstory-container {
    -webkit-box-align: start;
    -ms-flex-align: start;
    align-items: flex-start;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    margin: 10px auto;
    position: relative;
    width: 100%;

}


.Topstory-mainColumn {
    -ms-flex-negative: 0;
    flex-shrink: 0;
    margin-bottom: 0;
    margin-right: 10px;
    flex: 1;

}

.RightSideBar {
    width: 296px;
    -webkit-align-self: normal;
    -ms-flex-item-align: normal;
    align-self: normal;
}

.Topstory-tabs {
    border-bottom: 1px solid #f8f8fa;
}

.TopstoryTabs {
    height: 58px;
    display: flex;
}

.TopstoryTabs-link {
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    font-size: 16px;
    margin: 0 22px;
}

.TopstoryTabs-link.is-active {
    font-weight: 500;
    color: #1772f6;
}

.TopstoryItem {
    border-radius: 0;
    outline: none;
    overflow: visible;
    overflow: initial;
    padding: 16px 20px;
    position: relative;
    border-bottom: 1px solid #f8f8fa;
    padding-bottom: 30px;
}

.ContentItem-title {
    margin-bottom: -4px;
    margin-top: -4px;
    word-break: break-word;
    color: #191b1f;
    line-height: 1.6;
    font-size: 18px;
    font-synthesis: style;
    font-weight: 600;
}

.AuthorInfo {
    -webkit-box-align: center;
    -ms-flex-align: center;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    align-items: center;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    justify-content: space-between;
    max-width: 654px;
    position: relative;
}

.AuthorInfo-avatar {
    box-sizing: border-box;
    margin: 0px;
    min-width: 0px;
    max-width: 100%;
    background-color: rgb(255, 255, 255);
    width: 24px;
    height: 24px;
    border-radius: 2px;
}

.AuthorInfo-name {
    font-synthesis: style;
    font-weight: 600;
}

.AuthorInfo-badgeText {
    color: #535861;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.ContentItem-time {
    color: #8491a5;
    font-size: 14px;
    margin-top: 10px;
}

.content-cut {

    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-all;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 4;

    line-height: 1.2em;
}



.CreatorEntrance {
    height: 52px;
    width: 100%;
    display: flex;
    align-items: center;
    padding: 10px;
}

.QuestionHeader {
    background: #fff;
    min-width: 1032px;

    overflow: hidden;
    padding: 16px 0;
    position: relative;
}

.QuestionHeaderContent {
    margin: 10px auto;
    padding: 0 16px;
    position: relative;
    width: var(--content-width);
}

.QuestionHeader-title {
    color: #191b1f;
    line-height: 32px;
    font-size: 22px;
    font-synthesis: style;
    font-weight: 600;
    margin-bottom: 4px;
    margin-top: 12px;
}

.rich_content {
    color: #2a2a2a;
    font-size: 16px;
    line-height: 32px;
    word-break: break-all;
    word-wrap: break-word;
    position: relative;
}

.comment_content {
    margin-top: 10px;
    color: #2a2a2a;
    font-size: 16px;
    line-height: 32px;
    word-break: break-all;
    word-wrap: break-word;
    position: relative;
    overflow: hidden;
    width: 100%;


}

.children_comment_list {
    margin-left: 40px;
}

.avator-img {
    width: 64px;
    height: 64px;
    border-radius: 5px;
}

.banner-img {
    border-radius: 20px;
    width: 100%;
    height: 480px;
    overflow: hidden;
    object-fit: scale-down;
}
</style>
