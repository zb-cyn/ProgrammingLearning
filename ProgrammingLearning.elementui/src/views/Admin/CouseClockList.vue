<template>
    <div class="app-container">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <el-button type="primary" size="mini" icon="el-icon-search" @click="SearchClick">搜 索</el-button>
                <el-button type="warning" size="mini" @click="ResetClick" icon="el-icon-s-promotion">重 置</el-button>
            </div>
            <div class="tb-body">
                <el-form ref="searchFormRef" :model="searchForm" :inline="true" label-width="120px" :style="`flex:1;`">
                    <el-form-item label="所属课程">
                        <SigleSelect url="/Couse/List" columnName="Name" :clearable="true" columnValue="Id"
                            v-model="searchForm.CouseId">
                        </SigleSelect>
                    </el-form-item>

                    <el-form-item label="用户">
                        <SigleSelect url="/User/List" columnName="Name" :clearable="true" columnValue="Id"
                            v-model="searchForm.UserId">
                        </SigleSelect>
                    </el-form-item>
                    <el-form-item label="打卡时间">
                        <el-date-picker v-model="searchForm.ClockTimeRange" type="datetimerange"
                            start-placeholder="开始打卡时间" end-placeholder="结束打卡时间" :default-time="['00:00:00']"
                            value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
            </div>

        </el-card>


        <el-dialog :title="formData.Id ? '修改课程打卡' : '添加课程打卡'" :visible.sync="editorShow" width="50%" :lock-scroll="true"
            height="800px">
            <el-form v-if="editorShow == true" ref="editModalForm" :rules="editModalFormRules" :model="formData"
                label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody">
                    <el-col :span="24">
                        <el-form-item label="所属课程" prop="CouseId">
                            <SigleSelect url="/Couse/List" columnName="Name" columnValue="Id"
                                v-model="formData.CouseId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="打卡章节" prop="CouseStepId">
                            <SigleSelect url="/CouseStep/List" columnName="Name" columnValue="Id"
                                v-model="formData.CouseStepId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="用户" prop="UserId">
                            <SigleSelect url="/User/List" columnName="Name" columnValue="Id" v-model="formData.UserId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="打卡时间" prop="ClockTime">
                            <el-date-picker v-model="formData.ClockTime" align="right" type="datetime"
                                placeholder="选择时间" value-format="yyyy-MM-dd HH:mm:ss">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>


                <el-row type="flex" justify="end" align="bottom">
                    <el-form-item>
                        <el-button type="primary" plain @click="CreateOrEditForm()">确 定</el-button>
                        <el-button @click="editorShow = false">取 消</el-button>
                    </el-form-item>
                </el-row>
            </el-form>

        </el-dialog>


        <PaginationTable ref="PaginationTableId" url="/CouseClock/List" :column="dataColum" :where="where">
            <template v-slot:header>

                <el-button type="danger" size="mini" icon="el-icon-delete" @click="ShowBatchDeleteModal()">批 量 删
                    除</el-button>
            </template>
            <template v-slot:Operate="scope">

                <el-button class="margin-top-xs" type="danger" size="mini" @click="ShowDeleteModal(scope.row.Id)">删
                    除</el-button>
            </template>
        </PaginationTable>
    </div>
</template>

<script>
import store from '@/store';
import { mapGetters } from 'vuex';
export default {
    name: "CouseClockList",
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
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
                    key: "CouseDto.Name",
                    title: "课程名称",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "ClockTime",
                    title: "打卡时间",
                    width: "140px",
                    type: store.getters.ColumnType.DATE,
                },
                {
                    key: "CouseStepId",
                    hidden: true,
                },
                {
                    key: "CouseStepDto.Name",
                    title: "课程小节",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "UserId",
                    hidden: true,
                },
                {
                    key: "UserDto.Name",
                    title: "名称",

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
                "CouseId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "CouseStepId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "UserId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "ClockTime": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
            },
            formData: {

            },//保存或者修改定义的数据对象
            editorShow: false,

        };
    },
    created() {

    },
    methods: {
        //修改属性
        async UpdateEntityAsync(Id, title, data) {
            let { Data } = await this.$PostSigleUpdate(`/CouseClock/Get`, `/CouseClock/CreateOrEdit`, Id, title, data);

            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击新增或者编辑的时候会触发
         */
        async ShowEditModal(Id) {

            let { Data } = await this.$Post(`/CouseClock/Get`, { Id: Id });
            this.formData = Data;

            this.editorShow = true;

        },
        /**
         * 点击保存的时候会触发
         */
        async CreateOrEditForm() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    var { Success } = await this.$Post(`/CouseClock/CreateOrEdit`, this.formData);

                    if (Success) {
                        this.editorShow = false;
                        this.$refs.PaginationTableId.Reload(this.searchForm);
                    }
                }
            })
        },
        /**
         * 单个删除的时候会触发
         */
        async ShowDeleteModal(Id) {
            await this.$PostDelete(`/CouseClock/Delete`, { Id: Id });
            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 批量删除的时候会触发
         */
        async ShowBatchDeleteModal() {
            var ids = this.$refs.PaginationTableId.GetSelectionRow().map(x => x.Id);
            if (ids.length == 0) {
                this.$message.error("你选择需要删除的记录");
                return;
            }
            await this.$PostDelete(`/CouseClock/BatchDelete`, { Ids: ids });

            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击表格搜索按钮会触发
         */
        async SearchClick() {
            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击清空表单会触发
         */
        async ResetClick() {
            this.searchForm = {};
            this.$refs.PaginationTableId.Reload(this.searchForm);
        },




    },
};
</script>