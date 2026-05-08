<template>
    <div class="app-container">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <el-button type="primary" size="mini" icon="el-icon-search" @click="SearchClick">搜 索</el-button>
                <el-button type="warning" size="mini" @click="ResetClick" icon="el-icon-s-promotion">重 置</el-button>
            </div>
            <div class="tb-body">
                <el-form ref="searchFormRef" :model="searchForm" :inline="true" label-width="120px" :style="`flex:1;`">
                    <el-form-item label="用户">
                        <SigleSelect url="/User/List" columnName="Name" :clearable="true" columnValue="Id"
                            v-model="searchForm.UserId" :where="{ RoleType: 2 }">
                        </SigleSelect>
                    </el-form-item>
                    <el-form-item label="试卷">
                        <SigleSelect url="/Questionnaire/List" columnName="Title" :clearable="true" columnValue="Id"
                            v-model="searchForm.QuestionnaireId">
                        </SigleSelect>
                    </el-form-item>
                    <el-form-item label="提交时间">
                        <el-date-picker v-model="searchForm.SubmitTimeRange" type="datetimerange" start-placeholder="开始提交时间"
                            end-placeholder="结束提交时间" :default-time="['00:00:00']" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="开始时间">
                        <el-date-picker v-model="searchForm.BeginTimeRange" type="datetimerange" start-placeholder="开始开始时间"
                            end-placeholder="结束开始时间" :default-time="['00:00:00']" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
            </div>

        </el-card>


        <el-dialog :title="formData.Id ? '修改用户提交答题' : '添加用户提交答题'" :visible.sync="editorShow" width="50%" :lock-scroll="true"
            height="800px">
            <el-form v-if="editorShow == true" ref="editModalForm" :rules="editModalFormRules" :model="formData"
                label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody">
                    <el-col :span="24">
                        <el-form-item label="用户" prop="UserId">
                            <SigleSelect url="/User/List" columnName="Name" columnValue="Id" v-model="formData.UserId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="试卷" prop="QuestionnaireId">
                            <SigleSelect url="/Questionnaire/List" columnName="Title" columnValue="Id"
                                v-model="formData.QuestionnaireId">
                            </SigleSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="提交时间" prop="SubmitTime">
                            <el-date-picker v-model="formData.SubmitTime" align="right" type="datetime" placeholder="选择时间"
                                value-format="yyyy-MM-dd HH:mm:ss">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="开始时间" prop="BeginTime">
                            <el-date-picker v-model="formData.BeginTime" align="right" type="datetime" placeholder="选择时间"
                                value-format="yyyy-MM-dd HH:mm:ss">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="总得分" prop="TotalScore">
                            <el-input type="number" v-model.trim="formData.TotalScore" placeholder="请输入总得分"
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


        <PaginationTable ref="PaginationTableId" url="/UserAnswer/List" :column="dataColum" :where="where">
            <template v-slot:header>

                <el-button type="danger" size="mini" icon="el-icon-delete" @click="ShowBatchDeleteModal()">批 量 删
                    除</el-button>
            </template>
            <template v-slot:Operate="scope">
                <!-- <el-button class="margin-top-xs" type="primary" size="mini" @click="ShowEditModal(scope.row.Id)">修
                    改</el-button> -->
                <el-button class="margin-top-xs" type="primary" size="mini"
                    @click="ToUserAnswerResult(scope.row.Id)">回答情况</el-button>
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
    name: "UserAnswerList",
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId", 
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
                    key: "UserId",
                    hidden: true,
                },
                {
                    key: "UserDto.Name",
                    title: "参考人",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "UserDto.PhoneNumber",
                    title: "联系电话",
                    width: "180px",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "TestDto.Title",
                    title: "测试计划",
                    width: "180px",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "QuestionnaireDto.Title",
                    title: "试卷标题",
                    width: "180px",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "TotalScore",
                    title: "总得分",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "SingleChoiseQuestionGetScore",
                    title: "单选得分",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "MultipleChoiceQuestionGetScore",
                    title: "多选得分",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "JudgmentQuestionGetScore",
                    title: "判断得分",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "SubjectiveQuestionGetScore",
                    title: "主观题得分",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "SubmitTime",
                    title: "提交时间",
                    width: "180px",
                    type: store.getters.ColumnType.DATETIME,
                },
                {
                    key: "BeginTime",
                    title: "开始时间",
                    width: "180px",
                    type: store.getters.ColumnType.DATETIME,
                },
                {
                    title: "操作",
                    width: "200px",
                    key: "Operate",
                    type: store.getters.ColumnType.USERDEFINED,
                },
            ],
            editModalFormRules: {
                "UserId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "QuestionnaireId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "SubmitTime": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "BeginTime": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "TotalScore": [
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
        async ToUserAnswerResult(Id) {
            this.$router.push({
                path: '/Admin/UserAnswerResult',
                query: {
                    UserAnswerId: Id
                }
            })
        },
        //修改属性
        async UpdateEntityAsync(Id, title, data) {
            let { Data } = await this.$PostSigleUpdate(`/UserAnswer/Get`, `/UserAnswer/CreateOrEdit`, Id, title, data);

            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击新增或者编辑的时候会触发
         */
        async ShowEditModal(Id) {

            let { Data } = await this.$Post(`/UserAnswer/Get`, { Id: Id });
            this.formData = Data;

            this.editorShow = true;

        },
        /**
         * 点击保存的时候会触发
         */
        async CreateOrEditForm() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    var { Success } = await this.$Post(`/UserAnswer/CreateOrEdit`, this.formData);

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
            await this.$PostDelete(`/UserAnswer/Delete`, { Id: Id });
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
            await this.$PostDelete(`/UserAnswer/BatchDelete`, { Ids: ids });

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