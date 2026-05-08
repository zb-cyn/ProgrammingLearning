<template>
    <div v-if="Couse">
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item
                    :to="{ path: `/Front/CourseDetail?CourseId=${$route.query.CouseId}` }">课程详情</el-breadcrumb-item>
                <el-breadcrumb-item>课程购买</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="card margin-top-xs">
            <div class="header-title">课程预约</div>
            <el-form ref="editModalForm" :rules="editModalFormRules" :model="formData" label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody" style="max-height: 600px;overflow-y: scroll">

                    <el-col :span="24">
                        <el-form-item label="课程" prop="CouseId">
                            <SigleSelect url="/Couse/List" columnName="Name" columnValue="Id" :disabled="true"
                                v-model="formData.CouseId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="下单人" prop="UserId">
                            <SigleSelect url="/User/List" columnName="Name" columnValue="Id" v-model="formData.UserId"
                                :disabled="true">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>


                    <el-col :span="24" v-if="formData.PayMoney > 0 && formData.IsFee">
                        <el-form-item label="支付类型" prop="PayType">
                            <el-select v-model="formData.PayType" placeholder="请选择支付类型" style="width: 100%">
                                <el-option v-for="item in paymentTypes" :key="item.value" :label="item.label"
                                    :value="item.value">
                                    <span style="float: left">{{ item.label }}</span>
                                    <span style="float: right; color: #8492a6; font-size: 13px">
                                        <i :class="item.icon"></i>
                                    </span>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>

                </el-row>

                <div class="submit-area">
                    <div class="payment-summary">
                        <span class="total-label">支付总额:</span>
                        <template v-if="formData.IsVip && formData.IsFee">
                            <span class="original-price">¥{{ formData.OriginalPrice }}</span>
                            <span class="total-amount">¥{{ formData.PayMoney }}</span>
                            <span class="discount-tag">会员折扣</span>
                        </template>
                        <span v-else class="total-amount">
                            ¥{{ formData.IsFee ? formData.PayMoney : '免费' }}
                        </span>
                    </div>
                    <el-button type="primary" class="submit-button" :loading="submitting" @click="CreateOrEditForm()">
                        立即报名
                    </el-button>
                </div>

            </el-form>
        </div>
    </div>
</template>
<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import SelectTab from '@/components/Select/SelectTab.vue';
import { mapGetters } from 'vuex';
export default {
    name: 'CouseOrder',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', 'UserId', 'MemberRecordDto'
        ])
    },
    components: {
        PaginationBox,
        SelectTab
    },
    data() {
        return {
            Couse: null,
            editModalFormRules: {
                "No": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "CouseId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "UserId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],

                "PayType": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "PayMoney": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
            },
            formData: {

            },//保存或者修改定义的数据对象
            editorShow: false,
            submitting: false,
            paymentTypes: [
                { value: 'alipay', label: '支付宝支付', icon: 'el-icon-money' },
                { value: 'wechat', label: '微信支付', icon: 'el-icon-chat-dot-square' },
                { value: 'card', label: '银行卡支付', icon: 'el-icon-bank-card' }
            ],
            discountRate: 0.8, // 添加会员折扣率，这里设置为8折
        }
    },
    watch: {
        "$route": async function (to, from) {

        }
    },
    created() {
        this.GetCouseApi();
    },
    methods: {

        /**
        * 点击保存的时候会触发
        */
        async CreateOrEditForm() {
            this.submitting = true;
            try {
                this.$refs.editModalForm.validate(async valid => {
                    if (valid) {
                        var { Success } = await this.$Post(`/CouseOrder/Enroll`, this.formData);

                        if (Success) {
                            this.editorShow = false;
                            this.$message.success('报名成功！');
                            this.$router.replace(`/Front/MyCouseOrder`);
                        }
                    }
                })
            } finally {
                this.submitting = false;
            }
        },
        //得到数据接口
        async GetCouseApi() {
            let { Data
            } = await this.$Post("/Couse/Get",
                {
                    Id: this.$route.query.CouseId
                })
            this.Couse = Data;

            // 计算支付金额，考虑会员折扣
            let finalPrice = Data.Price;
            if (this.MemberRecordDto && this.MemberRecordDto.Id) {
                finalPrice = Data.Price * this.discountRate; // 应用会员折扣
            }

            this.formData = {
                CouseId: this.Couse.Id,
                UserId: this.UserId,
                IsReturn: false,
                IsFee: !Data.IsFree,
                PayMoney: finalPrice,
                PayType: Data.Price == 0 ? 'free' : '',
                IsVip: this.MemberRecordDto && this.MemberRecordDto.Id ? true : false, // 添加会员标识
                OriginalPrice: Data.Price, // 保存原价
            };
            this.editorShow = true;
        },
    },
}
</script>
<style lang="scss" scoped>
.submit-area {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 15px 20px;
    background: white;
    box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: space-between;
    align-items: center;
    z-index: 1000;

    .payment-summary {
        .total-label {
            font-size: 14px;
            color: #606266;
            margin-right: 8px;
        }

        .total-amount {
            font-size: 20px;
            color: #F56C6C;
            font-weight: bold;
        }
    }

    .submit-button {
        width: 200px;
        height: 40px;
        font-size: 16px;
    }
}

.EditFromBody {
    padding-bottom: 80px; // 为固定底部留出空间
}

.payment-summary {
    .original-price {
        font-size: 14px;
        color: #909399;
        text-decoration: line-through;
        margin-right: 8px;
    }

    .discount-tag {
        background: #F56C6C;
        color: white;
        padding: 2px 6px;
        border-radius: 4px;
        font-size: 12px;
        margin-left: 8px;
    }
}
</style>