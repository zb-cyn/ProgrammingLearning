<template>
    <div class="app-container">
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>我的课程订单</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <PaginationBox ref="PaginationTableId" :where="where" url="/CouseOrder/List">
            <template v-slot:content="{ data }">
                <el-row :gutter="20" style="width: 100%;">
                    <el-col :span="8" v-for="item in data" :key="item.Id">
                        <el-card class="order-card" :body-style="{ padding: '0px' }">
                            <div class="cover-image">
                                <img :src="item.CouseDto.Cover" :alt="item.CouseDto.Name">
                            </div>
                            <div class="card-content">
                                <h3 class="order-title">{{ item.CouseDto.Name }}</h3>
                                <div class="order-info">
                                    <div class="info-item">
                                        <span class="label">订单编号:</span>
                                        <span class="value">{{ item.No }}</span>
                                    </div>
                                    <div class="info-item">
                                        <span class="label">支付金额:</span>
                                        <span class="value price">￥{{ item.PayMoney }}</span>
                                    </div>
                                    <div class="info-item">
                                        <span class="label">支付类型:</span>
                                        <span class="value">{{ item.PayType }}</span>
                                    </div>

                                </div>
                                <div class="order-status" :class="{ 'returned': item.IsReturn }">
                                    {{ item.IsReturn ? '已退款' : '交易成功' }}
                                </div>
                                <div class="card-actions">
                                    <el-button type="primary" size="small" @click="ShowCouseDetail(item.CouseId)">
                                        查看课程
                                    </el-button>
                                    <el-button v-if="!item.IsReturn" type="danger" size="small"
                                        @click="CancelOrder(item.Id)">
                                        取消订单
                                    </el-button>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </template>
        </PaginationBox>
    </div>
</template>

<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import store from '@/store';
import { mapGetters } from 'vuex';
export default {
    name: "CouseOrderList",
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    components: {
        PaginationBox
    },
    data() {

        return {
            where: {},
            searchForm: {},//搜索定义的数据对象
            dataColum: [
                {
                    key: "Id",
                    hidden: true,

                },
                {
                    key: "CouseId",
                    hidden: true,
                },
                {
                    key: "No",
                    title: "订单编号",
                    width: "250px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "CouseDto.Title",
                    title: "课程标题",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "TeacherId",
                    hidden: true,
                },

                {
                    key: "PayMoney",
                    title: "支付金额",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },

                {
                    key: "PayType",
                    title: "支付类型",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "UserId",
                    hidden: true,
                },
                {
                    key: "UserDto.Name",
                    title: "名称",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "InstitutionId",
                    hidden: true,
                },
                {
                    key: "IsReturn",
                    title: "是否退款",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "ReturnTime",
                    title: "退款时间",
                    width: "160px",
                    type: store.getters.ColumnType.DATETIME,
                },
                {
                    key: "InstitutionDto.Name",
                    title: "机构名称",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },

                {
                    title: "操作",
                    width: "300px",
                    key: "Operate",
                    type: store.getters.ColumnType.USERDEFINED,
                },
            ],
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
                "InstitutionId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "TeacherId": [
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

        };
    },
    created() {
        this.where.UserId = this.UserId;
    },
    methods: {
        ShowCouseDetail(CouseId) {
            this.$router.push(`/Front/CouseDetail?CouseId=${CouseId}`);
        },
        async CancelOrder(Id) {
            let { Data } = await this.$PostSigleUpdate(`/CouseOrder/Get`, `/CouseOrder/Cancel`, Id, '你确定要取消订单吗？', {});
            this.$refs.PaginationTableId.Reload(this.searchForm);
        },

        /**
         * 点击新增或者编辑的时候会触发
         */
        async ShowEditModal(Id) {

            let { Data } = await this.$Post(`/CouseOrder/Get`, { Id: Id });
            this.formData = Data;

            this.editorShow = true;

        },
        /**
         * 点击保存的时候会触发
         */
        async CreateOrEditForm() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    var { Success } = await this.$Post(`/CouseOrder/CreateOrEdit`, this.formData);

                    if (Success) {
                        this.editorShow = false;
                        this.$refs.PaginationTableId.Reload(this.searchForm);
                    }
                }
            })
        },



    },
};
</script>

<style scoped>
/* 订单卡片样式 */
.order-card {
    margin-bottom: 20px;
    transition: all 0.3s;
}

.order-card:hover {
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    transform: translateY(-2px);
}

.card-content {
    padding: 20px;
}

.order-title {
    margin: 0;
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 15px;
    color: #303133;
}

.order-info {
    margin-bottom: 15px;
}

.info-item {
    display: flex;
    margin-bottom: 8px;
}

.label {
    color: #909399;
    width: 80px;
    flex-shrink: 0;
}

.value {
    color: #606266;
}

.value.price {
    color: #F56C6C;
    font-weight: bold;
}

.order-status {
    display: inline-block;
    padding: 2px 10px;
    border-radius: 12px;
    font-size: 12px;
    margin-bottom: 15px;
    background-color: #67C23A;
    color: white;
}

.order-status.returned {
    background-color: #909399;
}

.card-actions {
    border-top: 1px solid #EBEEF5;
    padding-top: 15px;
    display: flex;
    justify-content: flex-end;
    gap: 10px;
}

/* 添加封面图片样式 */
.cover-image {
    width: 100%;
    height: 200px;
    overflow: hidden;
}

.cover-image img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
</style>