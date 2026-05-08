<template>
    <div class="forget-password-container">
        <!-- 左侧区域 -->
        <div class="forget-left">
            <div class="background-overlay"></div>
            <div class="left-content">
                <h1 class="platform-title">密码找回</h1>
                <div class="platform-desc">
                    <h3>安全便捷的账号找回服务</h3>
                    <ul>
                        <li>简单快速的身份验证</li>
                        <li>多重安全保障机制</li>
                        <li>便捷的密码重置流程</li>
                        <li>专业的技术支持服务</li>
                    </ul>
                </div>
                <div class="platform-footer">
                    <p>保障账号安全 · 便捷找回服务</p>
                </div>
            </div>
        </div>

        <!-- 右侧区域 -->
        <div class="forget-right">
            <div class="forget-form-container">
                <h2>找回密码</h2>
                <p class="guide-text">请填写以下信息以重置您的密码</p>

                <el-form ref="loginForm" :model="formData" :rules="rules" label-position="top">
                    <el-form-item label="账号" prop="UserName">
                        <el-input v-model.trim="formData.UserName" placeholder="请输入账号">
                            <template #prefix>
                                <i class="el-icon-user"></i>
                            </template>
                        </el-input>
                    </el-form-item>

                    <el-form-item label="邮箱" prop="Email">
                        <el-input v-model.trim="formData.Email" placeholder="请输入邮箱">
                            <template #prefix>
                                <i class="el-icon-message"></i>
                            </template>
                        </el-input>
                    </el-form-item>

                    <el-form-item label="联系方式" prop="PhoneNumber">
                        <el-input v-model.trim="formData.PhoneNumber" placeholder="请输入联系方式">
                            <template #prefix>
                                <i class="el-icon-phone"></i>
                            </template>
                        </el-input>
                    </el-form-item>

                    <el-form-item label="新密码" prop="Password">
                        <el-input type="password" v-model.trim="formData.Password" placeholder="请输入新密码">
                            <template #prefix>
                                <i class="el-icon-lock"></i>
                            </template>
                        </el-input>
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
                        <el-button type="primary" class="submit-button" @click="ForgetBtn">
                            确定重置
                        </el-button>
                    </el-form-item>
                </el-form>

                <div class="form-footer">
                    <div class="login-link">
                        <span>已有账号？</span>
                        <RouterLink to="/Login">
                            <span class="link-text">立即登录</span>
                        </RouterLink>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ValidCode from '@/components/Code/canvas.vue';
export default {
    components: {
        ValidCode: ValidCode
    },
    data() {
        return {
            formData: {
                UserName: '',
                Password: '',

                Code: ""
            },

            rules: {
                UserName: [
                    { required: true, message: '请输入账号', trigger: 'blur' },
                ],
                Password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ],
                "Email": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
                            if (!value || !reg.test(value)) {
                                callback(new Error('请输入正确邮箱'));
                            }
                            else {
                                callback();
                            }
                        }, trigger: 'blur'
                    },
                ],
                "ImageUrls": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "Name": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],

                "PhoneNumber": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            var reg = /^1[34578]\d{9}$/;
                            if (!value || !reg.test(value)) {
                                callback(new Error('请输入正确的手机号'));
                            }
                            else {
                                callback();
                            }
                        }, trigger: 'blur'
                    },
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

    },
    methods: {

        ForgetBtn() {
            this.$refs.loginForm.validate(async (valid) => {
                if (valid) {

                    let res = await this.$Post("/User/ForgetPassword", this.formData)
                    console.log(res);
                    if (res.Success) {
                        this.$message.success("修改密码成功!");
                        this.$router.push({
                            path: "/Login"
                        })
                    }
                    else {

                        this.$refs.ValidCode.refreshCode();
                    }
                } else {
                    this.$message.error("验证不通过")
                    this.$refs.ValidCode.refreshCode();
                    return false;
                }
            });
        }
    }
}
</script>

<style scoped>
/* 整体布局 */
.forget-password-container {
    display: flex;
    min-height: 100vh;
    background-color: #f5f7fa;
}

/* 左侧区域样式 */
.forget-left {
    flex: 1.6;
    background-color: var(--primary-color);
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

/* 背景遮罩层 */
.background-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 1;
}

/* 左侧内容样式 */
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
.forget-right {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 40px;
}

.forget-form-container {
    width: 100%;
    max-width: 480px;
    padding: 40px;
    background: white;
    border-radius: 12px;
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);
}

.guide-text {
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

.submit-button {
    width: 100%;
    height: 44px;
    font-size: 16px;
    margin-top: 20px;
}

.form-footer {
    display: flex;
    justify-content: center;
    margin-top: 24px;
    color: #606266;
}

.link-text {
    color: var(--primary-color);
    margin-left: 8px;
}

/* 响应式适配 */
@media (max-width: 1200px) {
    .forget-password-container {
        flex-direction: column;
    }

    .forget-left,
    .forget-right {
        flex: none;
        width: 100%;
    }
}
</style>

