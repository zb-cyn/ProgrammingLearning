<template>
    <div class="app-container">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <el-button type="primary" size="mini" icon="el-icon-search" @click="SearchClick">搜 索</el-button>
                <el-button type="warning" size="mini" @click="ResetClick" icon="el-icon-s-promotion">重 置</el-button>
            </div>
            <div class="tb-body">
                <el-form ref="searchFormRef" :model="searchForm" :inline="true" label-width="120px" :style="`flex:1;`">
                    <el-form-item label="题干" prop="Title">
                        <el-input v-model.trim="searchForm.Title" placeholder="请输入题干" :clearable="true"></el-input>
                    </el-form-item>

                    <el-form-item label="分类" prop="Type">
                        <el-input v-model.trim="searchForm.Type" placeholder="请输入分类" :clearable="true"></el-input>
                    </el-form-item>
                </el-form>
            </div>

        </el-card>


        <el-dialog :title="formData.Id ? '修改多选题' : '添加多选题'" :visible.sync="editorShow" width="50%" :lock-scroll="true"
            height="800px">
            <el-form v-if="editorShow == true" ref="editModalForm" :rules="editModalFormRules" :model="formData"
                label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody" style="max-height: 600px;overflow-y: scroll">
                    <el-col :span="24">
                        <el-form-item label="题干" prop="Title">
                            <el-input type="text" v-model.trim="formData.Title" placeholder="请输入题干"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="分类" prop="Type">
                            <LabelSelect url="/SysLabel/List" columnName="Name" v-model.trim="formData.Type">
                            </LabelSelect>

                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="选项A" prop="OptionA">
                            <el-input type="text" v-model.trim="formData.OptionA" placeholder="请输入选项A"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="选项B" prop="OptionB">
                            <el-input type="text" v-model.trim="formData.OptionB" placeholder="请输入选项B"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="选项C" prop="OptionC">
                            <el-input type="text" v-model.trim="formData.OptionC" placeholder="请输入选项C"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="选项D" prop="OptionD">
                            <el-input type="text" v-model.trim="formData.OptionD" placeholder="请输入选项D"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="正确答案" prop="RightAnswer">
                            <CheckBoxSelect v-model="formData.RightAnswer"
                                :options="[{ name: 'A', value: 'A' }, { name: 'B', value: 'B' }, { name: 'C', value: 'C' }, { name: 'D', value: 'D' }]">
                            </CheckBoxSelect>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="解析" prop="AnalyzeResult">
                            <el-input type="textarea" v-model.trim="formData.AnalyzeResult" placeholder="请输入解析"
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


        <PaginationTable ref="PaginationTableId" url="/MultipleChoiceQuestion/List" :column="dataColum" :where="where">
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
import { mapGetters } from 'vuex'
import moment from 'moment'
import LabelSelect from '@/components/Select/LabelSelect.vue'
import CheckBoxSelect from '@/components/Select/CheckBoxSelect.vue'
export default {
    name: "MultipleChoiceQuestionList",
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    components: {
        LabelSelect,
        CheckBoxSelect
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
                    key: "Title",
                    title: "题干",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "Type",
                    title: "分类",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "OptionA",
                    title: "选项A",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "OptionB",
                    title: "选项B",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "OptionC",
                    title: "选项C",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "OptionD",
                    title: "选项D",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "AnalyzeResult",
                    title: "解析",


                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "RightAnswer",
                    title: "正确答案",
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
                "Title": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "OptionA": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "OptionB": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "OptionC": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "OptionD": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],

                "RightAnswer": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "Type": [
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
            let { Data } = await this.$PostSigleUpdate(`/MultipleChoiceQuestion/Get`, `/MultipleChoiceQuestion/CreateOrEdit`, Id, title, data);

            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击新增或者编辑的时候会触发
         */
        async ShowEditModal(Id) {

            let { Data } = await this.$Post(`/MultipleChoiceQuestion/Get`, { Id: Id });
            this.formData = Data;

            this.editorShow = true;

        },
        /**
         * 点击保存的时候会触发
         */
        async CreateOrEditForm() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    var { Success } = await this.$Post(`/MultipleChoiceQuestion/CreateOrEdit`, this.formData);

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
            await this.$PostDelete(`/MultipleChoiceQuestion/Delete`, { Id: Id });
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
            await this.$PostDelete(`/MultipleChoiceQuestion/BatchDelete`, { Ids: ids });

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