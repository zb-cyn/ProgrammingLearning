<template>
    <div class="app-container">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <el-button type="primary" size="mini" icon="el-icon-search" @click="SearchClick">搜 索</el-button>
                <el-button type="warning" size="mini" @click="ResetClick" icon="el-icon-s-promotion">重 置</el-button>
            </div>
            <div class="tb-body">
                <el-form ref="searchFormRef" :model="searchForm" :inline="true" label-width="120px" :style="`flex:1;`">
                    <el-form-item label="标题" prop="Title">
                        <el-input v-model.trim="searchForm.Title" placeholder="请输入标题" :clearable="true"></el-input>
                    </el-form-item>
                  
          
                    <el-form-item label="试卷">
                        <SigleSelect url="/Questionnaire/List" columnName="Title" :clearable="true" columnValue="Id"
                            v-model="searchForm.QuestionnaireId">
                        </SigleSelect>
                    </el-form-item>
                    <el-form-item label="有效时间">
                        <el-date-picker v-model="searchForm.ActiveTimeRange" type="datetimerange" start-placeholder="开始有效时间"
                            end-placeholder="结束有效时间" :default-time="['00:00:00']" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="是否随机打乱">
                        <el-select v-model="searchForm.IsRadom" :clearable="true" placeholder="请选择是否随机打乱">
                            <el-option key="true" label="是" value="true">
                            </el-option>
                            <el-option key="false" label="否" value="false">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </div>

        </el-card>


        <el-dialog :title="formData.Id ? '修改考试' : '添加考试'" :visible.sync="editorShow" width="50%" :lock-scroll="true"
            height="800px">
            <el-form v-if="editorShow == true" ref="editModalForm" :rules="editModalFormRules" :model="formData"
                label-width="140px" size="mini">
                <el-row :gutter="10" class="EditFromBody">
                    <el-col :span="24">
                        <el-form-item label="标题" prop="Title">
                            <el-input type="text" v-model.trim="formData.Title" placeholder="请输入标题"
                                :clearable="true"></el-input>
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
                        <el-form-item label="有效时间" prop="ActiveTimeRange">
                            <el-date-picker v-model="formData.ActiveTimeRange" align="right" type="datetimerange"
                                range-separator="-" start-placeholder="开始有效时间" end-placeholder="结束有效时间" placeholder="选择"
                                value-format="yyyy-MM-dd HH:mm:ss">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="是否随机打乱" prop="IsRadom">
                            <el-switch v-model="formData.IsRadom">
                            </el-switch>
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


        <PaginationTable ref="PaginationTableId" url="/Test/List" :column="dataColum" :where="where">
            <template v-slot:header>
                <el-button type="primary" size="mini" plain icon="el-icon-edit" @click="ShowEditModal()"> 新 增</el-button>
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
    name: "TestList",
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
                    key: "QuestionnaireId",
                    hidden: true,
                },
                {
                    key: "QuestionnaireDto.Title",
                    title: "试卷标题",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "ActiveTime",
                    title: "有效时间",
                    width: "350px",
                    type: store.getters.ColumnType.SHORTTEXT,
                    template: function (item, index) {
                        return `${item.BeginActiveTime}至${item.EndActiveTime}`
                    }
                },
                {
                    key: "Title",
                    title: "标题",
                    width: "160px",

                    type: store.getters.ColumnType.SHORTTEXT,
                },
                {
                    key: "IsRadom",
                    title: "是否随机打乱",
                    width: "160px",

                    type: store.getters.ColumnType.JUDGMENTTAG,
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
             
           
                "QuestionnaireId": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "ActiveTimeRange": [
                    { required: true, message: '该项为必填项', trigger: 'blur' },
                ],
                "IsRadom": [
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
            let { Data } = await this.$PostSigleUpdate(`/Test/Get`, `/Test/CreateOrEdit`, Id, title, data);

            this.$refs.PaginationTableId.Reload(this.searchForm);
        },
        /**
         * 点击新增或者编辑的时候会触发
         */
        async ShowEditModal(Id) {

            let { Data } = await this.$Post(`/Test/Get`, { Id: Id });

          
            if (Data.BeginActiveTime && Data.EndActiveTime) {
                Data.ActiveTimeRange = [Data.BeginActiveTime, Data.EndActiveTime];
            }
            this.formData = Data;

            this.editorShow = true;

        },
        /**
         * 点击保存的时候会触发
         */
        async CreateOrEditForm() {

            this.$refs.editModalForm.validate(async valid => {
                if (valid) {
                    this.formData.BeginActiveTime = this.formData.ActiveTimeRange[0];
                    this.formData.EndActiveTime = this.formData.ActiveTimeRange[1];
                    var { Success } = await this.$Post(`/Test/CreateOrEdit`, this.formData);

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
            await this.$PostDelete(`/Test/Delete`, { Id: Id });
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
            await this.$PostDelete(`/Test/BatchDelete`, { Ids: ids });

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
    