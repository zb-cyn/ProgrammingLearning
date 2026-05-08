<template>
    <div>
        <el-container>
            <el-header class="header">
                <el-menu :default-active="active" class="main-container nav-menu" mode="horizontal" text-color="#2c3e50"
                    active-text-color="var(--primary-color)">
                    <el-menu-item index="" class="logo-item">
                        <img class="logo" :src="require('@/assets/logo.png')">
                    </el-menu-item>

                    <div class="nav-items">
                        <el-menu-item index="/Front/Home" @click="ToPath('/Front/Home')">
                            <i class="el-icon-s-home"></i> 首页
                        </el-menu-item>
                        <el-menu-item index="/Front/TopicList" @click="ToPath('/Front/TopicList')">交流中心</el-menu-item>
                        <el-menu-item index="/Front/CouseList" @click="ToPath('/Front/CouseList')">线上课程</el-menu-item>
                        <el-menu-item v-if="Token" index="/Front/QuestionnaireList"
                            @click="ToPath('/Front/QuestionnaireList')">
                            <i class="el-icon-s-order"></i> 编程测试卷
                        </el-menu-item>

                        <el-menu-item v-if="Token" index="/Front/AskedQuestionsList"
                            @click="ToPath('/Front/AskedQuestionsList')">
                            <i class="el-icon-question"></i> 常见问题
                        </el-menu-item>
                    </div>

                    <div class="user-actions">
                        <template v-if="!Token">
                            <el-menu-item index="Register" @click="ToRegister()" class="auth-btn">
                                <i class="el-icon-user-solid"></i> 注册
                            </el-menu-item>
                            <el-menu-item index="Login" @click="ToLogin()" class="auth-btn">
                                <i class="el-icon-key"></i> 登录
                            </el-menu-item>
                        </template>

                        <el-submenu v-else index="UserInfo" class="user-menu">
                            <template slot="title">
                                <i class="el-icon-user"></i>
                                {{ UserInfo.UserName }}
                            </template>
                            <el-menu-item @click="ToPath('/Front/UserCenter')">
                                <i class="el-icon-s-custom"></i> 个人信息
                            </el-menu-item>

                            <el-menu-item @click="LoginOut()">
                                <i class="el-icon-switch-button"></i> 退出
                            </el-menu-item>
                        </el-submenu>
                    </div>
                </el-menu>
            </el-header>
            <div style="height: 50px;"></div>
            <el-main class="main-container main-box">
                <router-view></router-view>
            </el-main>
            <div style="height: 20px;"></div>
            <Footer></Footer>
        </el-container>

        <AIChatWindow></AIChatWindow>
    </div>
</template>

<script>

import AIChatWindow from "@/components/AIChat/AIChatWindow.vue";
import { mapGetters } from 'vuex';
import Footer from "./Footer.vue";
export default {
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    data() {
        return {
            active: 'home',

        }
    },
    components: {
        AIChatWindow,
        Footer
    },
    created() {

    },

    methods: {
        //前往登录
        ToLogin() {
            this.$router.push({
                path: "/Login"
            })
        },
        //前往注册
        ToRegister() {
            this.$router.push({
                path: "/Register"
            })
        },
        //退出
        async LoginOut() {
            await this.$store.dispatch('Logout')
            this.$router.push({
                path: "/Login"
            })
        },
        //跳转
        async ToPath(url) {
            this.$router.push({
                path: url
            })
        },
        //跳转到学生信息
        async ToUserInfo() {
            this.$router.push({
                path: "/Front/UserPerson"
            })
        },
        //跳转到修改密码
        async ToEditPassword() {
            this.$router.push({
                path: "/Front/PasswordEdit"
            })
        },
        //返回上一个页面
        goBack() {
            this.$router.go(-1)
        }

    },


}
</script>

<style scoped>
.main-container {
    width: var(--content-width);
    margin: 0 auto;
}

.main-box {
    min-height: calc(100vh - 120px);
}

.el-main {
    padding: 20px 0px 0px 0px !important;
    overflow: hidden;
}

/* 顶部导航样式 */
.header {
    background-color: white;
    width: 100%;
    position: fixed;
    z-index: 1000;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

/* 导航菜单容器 */
.nav-menu {
    display: flex;
    justify-content: space-between;
    border: none !important;
    height: 60px;
}

/* Logo样式 */
.logo-item {
    padding: 0 20px !important;
}

.logo {
    height: 50px;
    object-fit: contain;
    transition: transform 0.3s ease;
}

.logo:hover {
    transform: scale(1.05);
}

/* 导航项目样式 */
.nav-items {
    display: flex;
    flex: 1;
    margin-left: 20px;
}

/* 学生操作区域 */
.user-actions {
    display: flex;
    align-items: center;
}

/* 菜单项通用样式 */
.el-menu-item {
    transition: all 0.3s ease !important;
    font-size: 15px;
}

.el-menu-item:hover {
    background-color: rgba(var(--primary-color-rgb), 0.1) !important;
    transform: translateY(-2px);
}

/* 登录注册按钮样式 */
.auth-btn {
    margin: 0 5px;
    border-radius: 4px;
}

/* 学生下拉菜单样式 */
.user-menu {
    margin-left: auto;
}

.el-submenu__title {
    transition: all 0.3s ease !important;
}

.el-submenu__title:hover {
    background-color: rgba(var(--primary-color-rgb), 0.1) !important;
}

/* 激活状态的菜单项 */
.el-menu-item.is-active {
    font-weight: bold;
    color: var(--primary-color) !important;
    border-bottom: 2px solid var(--primary-color) !important;
}
</style>
