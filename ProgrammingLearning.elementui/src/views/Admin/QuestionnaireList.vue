<template>
    <div class="app-container">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <el-button type="primary" size="mini" icon="el-icon-search" @click="SearchClick">搜 索</el-button>
                <el-button type="warning" size="mini" @click="ResetClick" icon="el-icon-s-promotion">重 置</el-button>
            </div>
            <div class="tb-body">
                <el-form ref="searchFormRef" :model="searchForm" :inline="true" label-width="120px" :style="`flex:1;`">
                    <el-form-item label="试卷标题" prop="Title">
                        <el-input v-model.trim="searchForm.Title" placeholder="请输入试卷标题" :clearable="true"></el-input>
                    </el-form-item>
                    <el-form-item label="小标题" prop="SmallTitle">
                        <el-input v-model.trim="searchForm.SmallTitle" placeholder="请输入小标题" :clearable="true"></el-input>
                    </el-form-item>



                </el-form>
            </div>

        </el-card>


        <el-dialog :title="formData.Id ? '修改试卷' : '添加试卷'" :visible.sync="editorShow" width="80%" :lock-scroll="true"
            height="800px">
            <el-form v-if="editorShow == true" ref="editModalForm" :rules="editModalFormRules" :model="formData"
                label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody">
                    <el-col :span="24">
                        <el-form-item label="试卷标题" prop="Title">
                            <el-input type="text" v-model.trim="formData.Title" placeholder="请输入试卷标题"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="小标题" prop="SmallTitle">
                            <el-input type="text" v-model.trim="formData.SmallTitle" placeholder="请输入小标题"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="试卷标签" prop="Type">
                            <LabelSelect url="/SysLabel/List" columnName="Name" v-model.trim="formData.Type">
                            </LabelSelect>

                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="是否限时" prop="IsTimeLimit">
                            <el-switch v-model="formData.IsTimeLimit" :disabled="true">
                            </el-switch>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="分钟" prop="LimitMinute" v-if="formData.IsTimeLimit">
                            <el-input type="number" v-model.trim="formData.LimitMinute" placeholder="请输入分钟"
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


        <el-dialog :title="'自动组题'" :visible.sync="autoQuestionnaireShow" width="80%" :lock-scroll="true" height="800px">
            <el-form v-if="autoQuestionnaireShow == true" ref="editModalForm" :rules="editModalFormRules" :model="formData"
                label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody">
                    <el-col :span="24">
                        <el-form-item label="试卷标题" prop="Title">
                            <el-input type="text" v-model.trim="formData.Title" placeholder="请输入试卷标题"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="小标题" prop="SmallTitle">
                            <el-input type="text" v-model.trim="formData.SmallTitle" placeholder="请输入小标题"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="试卷标签" prop="Type">
                            <LabelSelect url="/SysLabel/List" columnName="Name" v-model.trim="formData.Type">
                            </LabelSelect>

                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="是否限时" prop="IsTimeLimit">
                            <el-switch v-model="formData.IsTimeLimit" :disabled="true">
                            </el-switch>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="分钟" prop="LimitMinute" v-if="formData.IsTimeLimit">
                            <el-input type="number" v-model.trim="formData.LimitMinute" placeholder="请输入分钟"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="单选题个数" prop="SingleChoiseQuestionCount">
                            <el-input type="number" v-model.trim="formData.SingleChoiseQuestionCount" placeholder="请输入单选题个数"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="多选题个数" prop="MultipleChoiceQuestionCount">
                            <el-input type="number" v-model.trim="formData.MultipleChoiceQuestionCount"
                                placeholder="请输入多选题个数" :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="判断题个数" prop="JudgmentQuestionCount">
                            <el-input type="number" v-model.trim="formData.JudgmentQuestionCount" placeholder="请输入判断题个数"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="主观题个数" prop="SubjectiveQuestionCount">
                            <el-input type="number" v-model.trim="formData.SubjectiveQuestionCount" placeholder="请输入主观题个数"
                                :clearable="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="难度" prop="QuestionLevel">
                            <el-radio-group v-model="formData.QuestionLevel">
                                <el-radio :label="1">简单</el-radio>
                                <el-radio :label="2">中等</el-radio>
                                <el-radio :label="3">困难</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>


                <el-row type="flex" justify="end" align="bottom">
                    <el-form-item>
                        <el-button type="primary" plain @click="AutoQuestionnaire()">确 定</el-button>
                        <el-button @click="autoQuestionnaireShow = false">取 消</el-button>
                    </el-form-item>
                </el-row>
            </el-form>

        </el-dialog>

        <PaginationTable ref="PaginationTableId" url="/Questionnaire/List" :column="dataColum" :where="where">
            <template v-slot:header>
                <el-button type="primary" size="mini" plain icon="el-icon-edit" @click="ShowEditModal()">新 增</el-button>
                <el-button type="primary" size="mini" plain icon="el-icon-edit"
                    @click="ShowAutoQuestionnaireModal()">自动组题</el-button>
                <el-button type="danger" size="mini" icon="el-icon-delete" @click="ShowBatchDeleteModal()">批 量 删
                    除</el-button>
            </template>
            <template v-slot:Operate="scope">
                <el-button class="margin-top-xs" type="primary" size="mini" @click="ShowEditModal(scope.row.Id)">修
                    改</el-button>
                <el-button class="margin-top-xs" type="success" size="mini"
                    @click="ToCreateOrEditQuestionnaireDet(scope.row.Id)">组题</el-button>
                <!-- <el-button class="margin-top-xs" type="success" size="mini" @click="ToDet(scope.row.Id)">查看试卷</el-button> -->
                <el-button class="margin-top-xs" type="warning" size="mini"
                    @click="ToDataCollect(scope.row.Id)">数据分析</el-button>
                <el-button class="margin-top-xs" type="danger" size="mini" @click="ShowDeleteModal(scope.row.Id)">删
                    除</el-button>
            </template>
        </PaginationTable>
    </div>
</template>

<script>
import LabelSelect from '@/components/Select/LabelSelect.vue';
import store from '@/store';
import { mapGetters } from 'vuex';

export default {
    name: "QuestionnaireList",
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    components: {
        LabelSelect
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
                    title: "试卷标题",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "SmallTitle",
                    title: "小标题",

                    type: store.getters.ColumnType.SHORTTEXT,
                },

                {
                    key: "Type",
                    title: "试卷类型",
                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "PublishUserId",
                    hidden: true,
                },
                {
                    key: "PublishUserDto.Name",
                    title: "发布人",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "IsTimeLimit",
                    title: "是否限时",
                    width: "140px",
                    type: store.getters.ColumnType.JUDGMENTTAG,
                },
                {
                    key: "LimitMinute",
                    title: "分钟",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "SingleChoiseQuestionIds",
                    title: "单选题数",
                    type: store.getters.ColumnType.SHORTTEXT,
                    template: function (item, index) {
                        return item.SingleChoiseQuestionIds.length + "个"
                    }
                },
                {
                    key: "MultipleChoiceQuestionIds",
                    title: "多选题数",
                    type: store.getters.ColumnType.SHORTTEXT,
                    template: function (item, index) {
                        return item.MultipleChoiceQuestionIds.length + "个"
                    }
                },
                {
                    key: "JudgmentQuestionIds",
                    title: "判断题数",
                    type: store.getters.ColumnType.SHORTTEXT,
                    template: function (item, index) {
                        return item.JudgmentQuestionIds.length + "个"
                    }
                },
                {
                    key: "SubjectiveQuestionIds",
                    title: "主观题数",
                    type: store.getters.ColumnType.SHORTTEXT,
                    template: function (item, index) {
                        return item.SubjectiveQuestionIds.length + "个"
                    }
                },
                {
                    key: "Score",
                    title: "总分值",
                    type: store.getters.ColumnType.SHORTTEXT,
                    template: function (item, index) {
                        return item.QuestionnaireDetDtos.reduce((sum, item) => sum + (item.Score || 0), 0) + "分"
                    }
                }, {
                    key: "CreatorAppUserDto.Name",
                    title: "创建人",
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
                "PublishUserId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "IsTimeLimit": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "SmallTitle": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "LimitMinute": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "Type": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "SingleChoiseQuestionCount": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "MultipleChoiceQuestionCount": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "JudgmentQuestionCount": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "SubjectiveQuestionCount": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "QuestionLevel": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
            },
            formData: {

            },//保存或者修改定义的数据对象
            editorShow: false,
            autoQuestionnaireShow: false,

        };
    },
    created() {

    },
    methods: {
        ToCreateOrEditQuestionnaireDet(Id) {
            this.$router.push({
                path: '/Admin/CreateOrEditQuestionnaireDet',
                query: {
                    QuestionnaireId: Id
                }
            })
        },
        async ToDataCollect(Id) {
            this.$router.push({
                path: '/Admin/QuestionOptionsDataCollect',
                query: {
                    QuestionnaireId: Id
                }
            })
        },
        async ToDet(Id) {
            this.$router.push({
                path: '/Admin/QuestionnaireDet',
                query: {
                    QuestionnaireId: Id
                }
            })
        },

        //修改属性
        async UpdateEntityAsync(Id, title, data) {
            let { Data } = await this.$PostSigleUpdate(`/Questionnaire/Get`, `/Questionnaire/CreateOrEdit`, Id, title, data);

            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击新增或者编辑的时候会触发
         */
        async ShowEditModal(Id) {

            let { Data } = await this.$Post(`/Questionnaire/Get`, { Id: Id });
            if (!Id) {
                Data.PublishUserId = this.UserId;
                Data.IsTimeLimit = true;

            }
            this.formData = Data;

            this.editorShow = true;

        },
        async ShowAutoQuestionnaireModal() {
            let { Data } = await this.$Post(`/Questionnaire/Get`, { Id: null });

            Data.PublishUserId = this.UserId;
            Data.IsTimeLimit = true;

            this.formData = Data;
            this.autoQuestionnaireShow = true;
        },
        async AutoQuestionnaire() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    var { Success } = await this.$Post(`/Questionnaire/AutoCreate`, this.formData);

                    if (Success) {
                        this.autoQuestionnaireShow = false;
                        this.$refs.PaginationTableId.Reload(this.searchForm);
                    }
                }
            })
        },
        /**
         * 点击保存的时候会触发
         */
        async CreateOrEditForm() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    var { Success } = await this.$Post(`/Questionnaire/CreateOrEdit`, this.formData);

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
            await this.$PostDelete(`/Questionnaire/Delete`, { Id: Id });
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
            await this.$PostDelete(`/Questionnaire/BatchDelete`, { Ids: ids });

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
<style>
.el-transfer-panel {
    width: 40%;
}
</style>