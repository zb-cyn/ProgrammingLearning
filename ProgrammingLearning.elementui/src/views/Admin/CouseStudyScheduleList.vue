<template>
    <div class="app-container">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <el-button type="primary" size="mini" icon="el-icon-search" @click="SearchClick">搜 索</el-button>
                <el-button type="warning" size="mini" @click="ResetClick" icon="el-icon-s-promotion">重 置</el-button>
            </div>
            <div class="tb-body">
                <el-form ref="searchFormRef" :model="searchForm" :inline="true" label-width="120px" :style="`flex:1;`">
                    <el-form-item label="学习人">
                        <SigleSelect url="/User/List" columnName="Name" :clearable="true" columnValue="Id"
                            v-model="searchForm.StudyUserId">
                        </SigleSelect>
                    </el-form-item>
                    <el-form-item label="所属章节">
                        <SigleSelect url="/CouseStep/List" columnName="Name" :clearable="true" columnValue="Id"
                            v-model="searchForm.CouseStepId">
                        </SigleSelect>
                    </el-form-item>
                    <el-form-item label="所属课程">
                        <SigleSelect url="/Couse/List" columnName="Name" :clearable="true" columnValue="Id"
                            v-model="searchForm.CouseId">
                        </SigleSelect>
                    </el-form-item>
                    <el-form-item label="开始时间">
                        <el-date-picker v-model="searchForm.BeginTimeRange" type="datetimerange"
                            start-placeholder="开始开始时间" end-placeholder="结束开始时间" :default-time="['00:00:00']"
                            value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="最后上传时间">
                        <el-date-picker v-model="searchForm.LastUploadTimeRange" type="datetimerange"
                            start-placeholder="开始最后上传时间" end-placeholder="结束最后上传时间" :default-time="['00:00:00']"
                            value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
            </div>

        </el-card>


        <el-dialog :title="formData.Id ? '修改课程学习进度' : '添加课程学习进度'" :visible.sync="editorShow" width="50%"
            :lock-scroll="true" height="800px">
            <el-form v-if="editorShow == true" ref="editModalForm" :rules="editModalFormRules" :model="formData"
                label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody">
                    <el-col :span="24">
                        <el-form-item label="学习人" prop="StudyUserId">
                            <SigleSelect url="/User/List" columnName="Name" columnValue="Id"
                                v-model="formData.StudyUserId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="所属章节" prop="CouseStepId">
                            <SigleSelect url="/CouseStep/List" columnName="Name" columnValue="Id"
                                v-model="formData.CouseStepId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="所属课程" prop="CouseId">
                            <SigleSelect url="/Couse/List" columnName="Name" columnValue="Id"
                                v-model="formData.CouseId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="开始时间" prop="BeginTime">
                            <el-date-picker v-model="formData.BeginTime" align="right" type="datetime"
                                placeholder="选择时间" value-format="yyyy-MM-dd HH:mm:ss">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="最后上传时间" prop="LastUploadTime">
                            <el-date-picker v-model="formData.LastUploadTime" align="right" type="datetime"
                                placeholder="选择时间" value-format="yyyy-MM-dd HH:mm:ss">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="学习时长" prop="Durtion">
                            <el-input type="number" v-model.trim="formData.Durtion" placeholder="请输入学习时长"
                                :clearable="true"></el-input>
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


        <PaginationTable ref="PaginationTableId" url="/CouseStudySchedule/List" :column="dataColum" :where="where">
            <template v-slot:header>
                <el-button type="primary" size="mini" plain icon="el-icon-edit" @click="ShowEditModal()">新 增</el-button>
                <el-button type="danger" size="mini" icon="el-icon-delete" @click="ShowBatchDeleteModal()">批 量 删
                    除</el-button>
            </template>
            <template v-slot:Operate="scope">
                <el-button class="margin-top-xs" type="primary" size="mini" @click="ShowEditModal(scope.row.Id)">修
                    改</el-button>
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
    name: "CouseStudyScheduleList",
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
                    key: "StudyUserId",
                    hidden: true,
                },
                {
                    key: "StudyUserDto.Name",
                    title: "名称",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "CouseStepId",
                    hidden: true,
                },
                {
                    key: "CouseStepDto.Name",
                    title: "课程小节",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "CouseId",
                    hidden: true,
                },
                {
                    key: "CouseDto.Name",
                    title: "课程名称",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "Durtion",
                    title: "学习时长",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "BeginTime",
                    title: "开始时间",
                    width: "140px",
                    type: store.getters.ColumnType.DATE,
                },
                {
                    key: "LastUploadTime",
                    title: "最后上传时间",
                    width: "140px",
                    type: store.getters.ColumnType.DATE,
                },
                {
                    title: "操作",
                    width: "300px",
                    key: "Operate",
                    type: store.getters.ColumnType.USERDEFINED,
                },
            ],
            editModalFormRules: {
                "StudyUserId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "CouseStepId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "CouseId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "BeginTime": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "LastUploadTime": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "Durtion": [
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
            let { Data } = await this.$PostSigleUpdate(`/CouseStudySchedule/Get`, `/CouseStudySchedule/CreateOrEdit`, Id, title, data);

            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击新增或者编辑的时候会触发
         */
        async ShowEditModal(Id) {

            let { Data } = await this.$Post(`/CouseStudySchedule/Get`, { Id: Id });
            this.formData = Data;

            this.editorShow = true;

        },
        /**
         * 点击保存的时候会触发
         */
        async CreateOrEditForm() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    var { Success } = await this.$Post(`/CouseStudySchedule/CreateOrEdit`, this.formData);

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
            await this.$PostDelete(`/CouseStudySchedule/Delete`, { Id: Id });
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
            await this.$PostDelete(`/CouseStudySchedule/BatchDelete`, { Ids: ids });

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