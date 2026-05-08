<template>
    <div>
        <el-container style="height: 100vh">

            <el-header>
                <div class="bg-header">
                    <div class="flex align-center">
                        <img :src="require('@/assets/logo.png')" style="width: 50px; height: 50px;">
                        <span style="margin-left:10px">编程学习平台管理端</span>
                    </div>
                    <div style="display: flex;align-items: center">
                        <el-avatar :size="60" :src="UserInfo.ImageUrls" size="medium">
                            <img :src="require('@/assets/默认头像.png')" />
                        </el-avatar>
                        <div style="margin-left:10px">
                            <el-dropdown>
                                <span class="el-dropdown-link" style="color:white">
                                    {{ UserInfo.Name }}<i class="el-icon-arrow-down el-icon--right"></i>
                                </span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item>
                                        <div @click="ToPath('/Admin/UserPerson')">个人信息</div>

                                    </el-dropdown-item>
                                    <el-dropdown-item>
                                        <div @click="ToPath('/Admin/PasswordEdit')">修改密码</div>

                                    </el-dropdown-item>
                                    <el-dropdown-item>
                                        <div @click="LoginOut()">退出</div>
                                    </el-dropdown-item>

                                </el-dropdown-menu>
                            </el-dropdown>
                        </div>

                    </div>
                </div>


            </el-header>


            <el-container>
                <el-aside width="200px">

                    <el-menu class="menu-list" :router="true" active-text-color="var(--primary-color)">
                        <el-menu-item index="/Admin/Home">
                            <i class="el-icon-shujufenxi"></i>
                            <span>控制台</span>
                        </el-menu-item>


                        <template v-if="RoleType == '管理员'">

                            <el-submenu index="/Admin/QuestionnaireList">
                                <template slot="title">
                                    <img class="meun-ico" :src="require('@/assets/问卷.png')">
                                    <span>试卷管理</span>
                                </template>
                                <el-menu-item index="/Admin/QuestionnaireList">
                                    <img class="meun-ico" :src="require('@/assets/问卷.png')">
                                    <span>试卷</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/SingleChoiseQuestionList">
                                    <img class="meun-ico" :src="require('@/assets/单选题.png')">
                                    <span>单选题</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/MultipleChoiceQuestionList">
                                    <img class="meun-ico" :src="require('@/assets/多选题.png')">
                                    <span>多选题</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/JudgmentQuestionList">
                                    <img class="meun-ico" :src="require('@/assets/判断题.png')">
                                    <span>判断题型</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/SubjectiveQuestionList">
                                    <img class="meun-ico" :src="require('@/assets/主观题.png')">
                                    <span>主观题</span>
                                </el-menu-item>

                            </el-submenu>
                            <el-submenu index="/Admin/TestList">
                                <template slot="title">
                                    <img class="meun-ico" :src="require('@/assets/测试.png')">
                                    <span>测试管理</span>
                                </template>

                                <el-menu-item index="/Admin/TestList">
                                    <img class="meun-ico" :src="require('@/assets/测试.png')">
                                    <span>测试</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/UserAnswerList">
                                    <img class="meun-ico" :src="require('@/assets/评估.png')">
                                    <span>用户提交答题</span>
                                </el-menu-item>
                            </el-submenu>


                            <el-submenu index="/Admin/CouseList">
                                <template slot="title">
                                    <img class="meun-ico" :src="require('@/assets/课程.png')">
                                    <span>课程管理</span>
                                </template>
                                <el-menu-item index="/Admin/CouseTypeList">
                                    <img class="meun-ico" :src="require('@/assets/课程类别.png')">
                                    <span>课程分类</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/CouseList">
                                    <img class="meun-ico" :src="require('@/assets/课程信息.png')">
                                    <span>课程信息</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/CouseClockList">
                                    <img class="meun-ico" :src="require('@/assets/课程.png')">
                                    <span>课程打卡</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/CouseOrderList">
                                    <img class="meun-ico" :src="require('@/assets/喜爱的课程.png')">
                                    <span>课程订单</span>
                                </el-menu-item>

                            </el-submenu>

                            <el-menu-item index="/Admin/SysLabelList">
                                <img class="meun-ico" :src="require('@/assets/标签.png')">
                                <span>系统标签</span>
                            </el-menu-item>

                            <el-submenu index="/Admin/TopicList">
                                <template slot="title">
                                    <img class="meun-ico" :src="require('@/assets/话题.png')">
                                    <span>话题管理</span>
                                </template>
                                <el-menu-item index="/Admin/TopicTypeList">
                                    <img class="meun-ico" :src="require('@/assets/分类.png')">
                                    <span>话题类型</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/TopicList">

                                    <img class="meun-ico" :src="require('@/assets/话题.png')">
                                    <span>话题信息</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/TopicAnalyseEchart">
                                    <img class="meun-ico" :src="require('@/assets/数据分析.png')">
                                    <span>话题统计</span>
                                </el-menu-item>
                                <el-menu-item index="/Admin/TopicSynthesizeAnalyse">
                                    <i class="el-icon-data-analysis"></i>
                                    <span>话题综合分析</span>
                                </el-menu-item>
                            </el-submenu>



                            <el-menu-item index="/Admin/SysNoticeList">
                                <img class="meun-ico" :src="require('@/assets/系统通知.png')">
                                <span>系统通知</span>
                            </el-menu-item>
                            <el-menu-item index="/Admin/AskedQuestionsList">
                                <img class="meun-ico" :src="require('@/assets/操作手册.png')">
                                <span>常见问题手册</span>
                            </el-menu-item>
                        </template>




                    </el-menu>
                </el-aside>

                <el-main class="admin-main">
                    <el-breadcrumb separator-class="el-icon-arrow-right" class="margin-bottom-xs">
                        <el-breadcrumb-item v-for="(item, index) in breadList" :key="index" :to="item.path">{{
                            item.meta.title
                            }}</el-breadcrumb-item>
                    </el-breadcrumb>

                    <transition>
                        <router-view></router-view>
                    </transition>

                </el-main>

            </el-container>
        </el-container>
    </div>
</template>

<script>

import { adminRouters } from "@/router/index";
import { mapGetters } from "vuex";
export default {
    name: 'Layout',
    data() {
        return {
            routerMenu: [],
            breadList: [],
        }
    },
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'RoleTypeFormat', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    watch: {
        $route() {
            this.getBreadcrumb();
        },
    },

    created() {
        this.routerMenu = adminRouters;
        this.getBreadcrumb();
    },
    methods: {
        //跳转
        async ToPath(url) {
            this.$router.push({
                path: url
            })
        },
        async LoginOut() {

            await this.$store.dispatch('Logout')
            window.location.reload('/Login')
        },
        isHome(route) {
            return route.path === "/Admin";
        },
        getBreadcrumb() {
            let matched = this.$route.matched;
            if (!this.isHome(matched[0])) {
                matched = [{ path: "/Admin", meta: { title: "控制台" } }].concat(matched);
            }
            this.breadList = matched;

        }
    }
}
</script>

<style>
.el-header,
.el-footer {

    text-align: center;
    line-height: 60px;
    padding: 0px !important;
}

.el-aside {

    color: #333;
    text-align: center;
    line-height: 200px;
}


.menu-list {
    height: calc(100vh - 60px);
}

.el-submenu__title {
    text-align: left
}

.el-menu-item {
    text-align: left;
}

.admin-main {
    height: calc(100vh - 60px) !important;
}

.meun-ico {
    width: 25px;
    height: 25px;
    margin-right: 5px;
}
</style>