<template>
    <div v-if="UserInfo">
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>用户中心</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="card margin-top-xs">
            <!-- User Info Section -->
            <div class="user-info-section">
                <div class="user-header">
                    <img :src="UserInfo.ImageUrls" class="avatar" />
                    <div class="user-details">
                        <h3>{{ UserInfo.Name }}</h3>
                        <p>{{ UserInfo.RoleTypeFormat }}</p>
                        <p>{{ UserInfo.Email }}</p>
                        <p>{{ UserInfo.PhoneNumber }}</p>
                        <p v-if="UserInfo.MemberGradeDto" class="member-info">
                            <span>会员等级: {{ UserInfo.MemberGradeDto.Name }}</span>
                            <span>折扣: {{ (UserInfo.MemberGradeDto.Discount * 100).toFixed(0) }}%</span>

                            <span>余额: ¥{{ UserInfo.Balance || '0.00' }}</span>
                        </p>
                    </div>
                </div>
            </div>


        </div>

        <!-- Feature Menu Section -->
        <div class="feature-menu">
            <el-row :gutter="20">
                <el-col :span="8" class="feature-menu-item">
                    <el-card>
                        <div class="card-content" @click="goTo('/Front/UserPerson')">
                            <div class="menu-item-wrapper">
                                <div class="menu-item-left">
                                    <img :src="require('@/assets/个人信息.png')" class="menu-icon">
                                    <span class="menu-text">个人信息</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>

                <el-col :span="8" class="feature-menu-item">
                    <el-card>
                        <div class="card-content" @click="goTo('/Front/MyTopicCollect')">
                            <div class="menu-item-wrapper">
                                <div class="menu-item-left">
                                    <img :src="require('@/assets/收藏.png')" class="menu-icon">
                                    <span class="menu-text">收藏的话题</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="8" class="feature-menu-item">
                    <el-card>
                        <div class="card-content" @click="goTo('/Front/TopicMy')">
                            <div class="menu-item-wrapper">
                                <div class="menu-item-left">
                                    <img :src="require('@/assets/话题.png')" class="menu-icon">
                                    <span class="menu-text">我的话题</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="8" class="feature-menu-item">
                    <el-card>
                        <div class="card-content" @click="goTo('/Front/MyCouseOrder')">
                            <div class="menu-item-wrapper">
                                <div class="menu-item-left">
                                    <img :src="require('@/assets/个人订单.png')" class="menu-icon">
                                    <span class="menu-text">线上课程订单</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="8" class="feature-menu-item">
                    <el-card>
                        <div class="card-content" @click="goTo('/Front/MyCouseStudyScheduleList')">
                            <div class="menu-item-wrapper">
                                <div class="menu-item-left">
                                    <img :src="require('@/assets/学习进度.png')" class="menu-icon">
                                    <span class="menu-text">线上学习进度</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="8" class="feature-menu-item">
                    <el-card>
                        <div class="card-content" @click="goTo('/Front/UserAnswerList')">
                            <div class="menu-item-wrapper">
                                <div class="menu-item-left">
                                    <img :src="require('@/assets/学习进度.png')" class="menu-icon">
                                    <span class="menu-text">测试结果</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>



                <el-col :span="8" class="feature-menu-item">
                    <el-card>
                        <div class="card-content" @click="goTo('/Front/PasswordEdit')">
                            <div class="menu-item-wrapper">
                                <div class="menu-item-left">
                                    <img :src="require('@/assets/修改密码.png')" class="menu-icon">
                                    <span class="menu-text">修改密码</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>


                <el-col :span="8" class="feature-menu-item">
                    <el-card>
                        <div class="card-content" @click="LoginOut()">
                            <div class="menu-item-wrapper">
                                <div class="menu-item-left">
                                    <img :src="require('@/assets/退出.png')" class="menu-icon">
                                    <span class="menu-text">退出登录</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import SelectTab from '@/components/Select/SelectTab.vue';
import { mapGetters } from 'vuex';
export default {
    name: 'UserCenter',
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

        }
    },
    watch: {
        "$route": async function (to, from) {

        }
    },
    created() {

    },
    methods: {

        //退出
        async LoginOut() {
            await this.$store.dispatch('Logout')
            this.$router.push('/Login')
        },
        goTo(url) {
            console.log(url);
            this.$router.push({
                path: url,
            });
        },
        async WeChat() {
            if (this.Token) {

                this.$router.push({
                    path: "/Front/Wechat",
                    query: {

                    }
                })



            }
            else {
                this.$message.error("请先登录再操作");
            }
        },
    },
}
</script>
<style lang="scss" scoped>
.user-info-section {
    padding: 20px;

    .user-header {
        display: flex;
        align-items: center;

        .avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-right: 20px;
        }

        .user-details {
            h3 {
                margin: 0 0 10px 0;
            }

            p {
                margin: 5px 0;
                color: #666;
            }

            .member-info {
                margin-top: 10px;
                color: #409EFF;

                span {
                    margin-right: 15px;
                }
            }
        }
    }
}

.feature-menu {
    padding: 20px 0px;

    .el-card {
        height: 100%;
        cursor: pointer;
        margin-bottom: 10px;
        transition: all 0.3s;

        .card-content {
            padding: 10px;
        }

        .menu-item-wrapper {
            display: flex;
            justify-content: flex-start;
            align-items: center;
        }

        .menu-item-left {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .menu-icon {
            width: 30px;
            height: 30px;
        }

        .menu-text {
            font-size: 16px;
        }

        &:hover {
            transform: translateX(5px);
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
        }
    }
}

.feature-menu-item {
    margin-bottom: 20px;
}
</style>