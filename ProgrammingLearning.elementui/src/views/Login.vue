<template>
    <!-- 登录页面主容器 -->
    <div class="login-container">
        <!-- 左侧区域 - 展示信息 -->
        <div class="login-left">
            <!-- 添加背景图片遮罩层 -->
            <div class="background-overlay"></div>
            <div class="left-content">
                <h1 class="platform-title">编程学习平台</h1>
                <div class="platform-desc">
                    <h4>人人都能写代码，此刻即未来</h4>
                    <ul>
                        <li>丰富的编程课程资源，涵盖多种主流语言</li>
                        <li>编程，是新时代的读写能力</li>
                        <li>构建逻辑，创造秩序</li>
                        <li>从0到1，从1到∞</li>
                    </ul>
                </div>
                <div class="platform-footer">
                    <p>让编程学习更简单 · 助力职业发展</p>
                </div>
            </div>
        </div>

        <!-- 右侧区域 - 登录表单 -->
        <div class="login-right">
            <div class="login-form-container">
                <h2>用户登录</h2>
                <p class="welcome-text">欢迎回来，请输入您的账号信息</p>

                <el-form ref="loginForm" :model="formData" :rules="rules" label-position="top">

                    <el-form-item label="账号" prop="UserName">
                        <el-input v-model.trim="formData.UserName" placeholder="请输入账号">
                            <template #prefix>
                                <i class="el-icon-user"></i>
                            </template>
                        </el-input>
                    </el-form-item>

                    <el-form-item label="密码" prop="Password">
                        <el-input type="password" v-model.trim="formData.Password" placeholder="请输入密码">
                            <template #prefix>
                                <i class="el-icon-lock"></i>
                            </template>
                        </el-input>
                    </el-form-item>

                    <el-form-item label="角色" prop="RoleType">
                        <el-radio-group v-model="formData.RoleType">
                            <el-radio v-for="item in roleOptions" :key="item.Code" :label="item.Code">
                                {{ item.Label }}
                            </el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <el-form-item label="验证码" prop="Code">
                        <div class="verify-code-container">
                            <el-input v-model.trim="formData.Code" placeholder="请输入验证码">
                            </el-input>
                            <div class="verify-code">
                                <ValidCode ref="ValidCode"></ValidCode>
                            </div>
                        </div>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" class="login-button" @click="LoginBtn">
                            登录
                        </el-button>
                    </el-form-item>
                </el-form>

                <div class="form-footer">
                    <div class="register-link">
                        <span>还没有账号？</span>
                        <RouterLink to="/Register">
                            <span class="link-text">立即注册</span>
                        </RouterLink>
                    </div>
                    <RouterLink to="/ForgetPassword" class="forget-password">
                        忘记密码？
                    </RouterLink>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ValidCode from '@/components/Code/canvas.vue';
import store from '@/store';
export default {
    components: {
        ValidCode: ValidCode
    },
    data() {
        return {
            formData: {
                UserName: '',
                Password: '',
                RoleType: "",
                Code: ""
            },
            roleOptions: [],
            rules: {
                UserName: [
                    { required: true, message: '请输入账号', trigger: 'blur' },
                ],
                Password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ],
                RoleType: [
                    { required: true, message: '请选择角色', trigger: 'blur' },
                ],

                Code: [
                    { required: true, message: '请输入验证码', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            let identifyCode = this.$refs.ValidCode.getCode();

                            if (value != identifyCode) {
                                callback(new Error('请输入正确的验证码'));
                            } else {
                                callback();
                            }
                        }, trigger: 'blur'
                    }
                ]
            }
        }

    },
    created() {
        this.GetRoleTypeApi();
    },
    methods: {
        async GetRoleTypeApi() {
            let { Data: { Items } } = await this.$Post("/Select/RoleType");

            this.roleOptions = Items

        },
        LoginBtn() {
            this.$refs.loginForm.validate(async (valid) => {
                if (valid) {

                    let res = await store.dispatch("Login", this.formData);
                    if (res.Success) {
                        this.$message.success("登录成功!");
                        this.$router.push({
                            path: "/Admin"
                        })

                    }

                } else {
                    this.$message.error("登录验证不通过")

                    return false;
                }
            });
        }
    }
}
</script>

<style scoped>
/* 登录页面整体布局 */
.login-container {
    display: flex;
    min-height: 100vh;
    background-color: #f5f7fa;
}

/* 左侧区域样式 */
.login-left {
    flex: 1.6;
    background-color: var(--primary-color);
    /* 添加背景图片相关样式 */
    background-image: url('@/assets/login1.png');
    background-size: cover;
    background-position: center;
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    padding: 40px;
}

/* 添加背景遮罩层样式 */
.background-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5);
    /* 半透明黑色遮罩 */
    z-index: 1;
}

/* 确保内容在遮罩层之上 */
.left-content {
    max-width: 600px;
    position: relative;
    z-index: 2;
}

.platform-title {
    font-size: 42px;
    margin-bottom: 30px;
}

.platform-desc {
    margin: 40px 0;
}

.platform-desc h3 {
    font-size: 24px;
    margin-bottom: 20px;
}

.platform-desc ul {
    list-style: none;
    padding: 0;
}

.platform-desc li {
    font-size: 16px;
    margin: 15px 0;
    padding-left: 20px;
    position: relative;
}

.platform-desc li:before {
    content: "•";
    position: absolute;
    left: 0;
}

.platform-footer {
    margin-top: 60px;
    font-size: 16px;
    opacity: 0.8;
}

/* 右侧区域样式 */
.login-right {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 40px;
}

.login-form-container {
    width: 100%;
    max-width: 480px;
    padding: 40px;
    background: white;
    border-radius: 12px;
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);
}

.welcome-text {
    color: #606266;
    margin: 20px 0 30px;
}

.verify-code-container {
    display: flex;
    gap: 12px;
}

.verify-code {
    flex-shrink: 0;
}

.login-button {
    width: 100%;
    height: 44px;
    font-size: 16px;
    margin-top: 20px;
}

.form-footer {
    display: flex;
    justify-content: space-between;
    margin-top: 24px;
    color: #606266;
}

.link-text {
    color: var(--primary-color);
    margin-left: 8px;
}

.forget-password {
    color: var(--primary-color);
    text-decoration: none;
}

/* 响应式适配 */
@media (max-width: 1200px) {
    .login-container {
        flex-direction: column;
    }

    .login-left,
    .login-right {
        flex: none;
        width: 100%;
    }
}
</style>