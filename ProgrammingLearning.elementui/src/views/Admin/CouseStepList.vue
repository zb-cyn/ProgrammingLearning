<template>
    <div class="app-container">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <el-button type="primary" size="mini" icon="el-icon-search" @click="SearchClick">搜 索</el-button>
                <el-button type="warning" size="mini" @click="ResetClick" icon="el-icon-s-promotion">重 置</el-button>
            </div>
            <div class="tb-body">
                <el-form ref="searchFormRef" :model="searchForm" :inline="true" label-width="120px" :style="`flex:1;`">
                    <el-form-item label="课程小节" prop="Name">
                        <el-input v-model.trim="searchForm.Name" placeholder="请输入课程小节" :clearable="true"></el-input>
                    </el-form-item>

                </el-form>
            </div>

        </el-card>


        <el-dialog :title="formData.Id ? '修改课程章节' : '添加课程章节'" :visible.sync="editorShow" width="50%" :lock-scroll="true"
            height="800px">
            <el-form v-if="editorShow == true" ref="editModalForm" :rules="editModalFormRules" :model="formData"
                label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody">
                    <el-col :span="24">
                        <el-form-item label="课程小节" prop="Name">
                            <el-input type="text" v-model.trim="formData.Name" placeholder="请输入课程小节"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="所属课程" prop="CouseId">
                            <SigleSelect url="/Couse/List" columnName="Name" columnValue="Id" :disabled="true"
                                v-model="formData.CouseId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <!-- <el-col :span="24">
                        <el-form-item label="小节封面" prop="Cover">
                            <UploadImages :limit="1" v-model="formData.Cover"></UploadImages>
                        </el-form-item>
                    </el-col> -->

                    <el-col :span="24">
                        <el-form-item label="小节视频" prop="VideoUrls">
                            <UploadFiles :limit="1" v-model="formData.VideoUrls" @change="VideoChange"></UploadFiles>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="小节时长" prop="Durtion">
                            <el-input type="number" v-model.trim="formData.Durtion" placeholder="请输入小节时长"
                                :disabled="true" :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="附件" prop="FileUrls">
                            <UploadFiles :limit="8" v-model="formData.FileUrls"></UploadFiles>
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


        <PaginationTable ref="PaginationTableId" url="/CouseStep/List" :column="dataColum" :where="where">
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
    name: "CouseStepList",
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
                    key: "Name",
                    title: "课程小节",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "Durtion",
                    title: "小节时长",

                    type: store.getters.ColumnType.SHORTTEXT,
                    width: "100px",
                    template: function (item, index) {
                        console.log(item);
                        return (item.Durtion / 1000).toFixed(2) + "秒";
                    }
                },

                {
                    key: "VideoUrls",
                    title: "小节视频",
                    width: "140px",
                    type: store.getters.ColumnType.VIDEO,
                },
                {
                    key: "FileUrls",
                    title: "附件",

                    type: store.getters.ColumnType.FILESLINK,
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
                    title: "操作",
                    width: "300px",
                    key: "Operate",
                    type: store.getters.ColumnType.USERDEFINED,
                },
            ],
            editModalFormRules: {
                "Name": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "CouseId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "Cover": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "Durtion": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "VideoUrls": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "FileUrls": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
            },
            formData: {

            },//保存或者修改定义的数据对象
            editorShow: false,

        };
    },
    created() {
        this.where.CouseId = this.$route.query.CouseId;
    },
    methods: {
        async VideoChange(url) {
            this.formData.Durtion = await this.$Comm.GetVideoDuration(url) * 1000;
        },
        //修改属性
        async UpdateEntityAsync(Id, title, data) {
            let { Data } = await this.$PostSigleUpdate(`/CouseStep/Get`, `/CouseStep/CreateOrEdit`, Id, title, data);

            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击新增或者编辑的时候会触发
         */
        async ShowEditModal(Id) {

            let { Data } = await this.$Post(`/CouseStep/Get`, { Id: Id });
            Data.CouseId = this.$route.query.CouseId;
            this.formData = Data;

            this.editorShow = true;

        },
        /**
         * 点击保存的时候会触发
         */
        async CreateOrEditForm() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    var { Success } = await this.$Post(`/CouseStep/CreateOrEdit`, this.formData);

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
            await this.$PostDelete(`/CouseStep/Delete`, { Id: Id });
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
            await this.$PostDelete(`/CouseStep/BatchDelete`, { Ids: ids });

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