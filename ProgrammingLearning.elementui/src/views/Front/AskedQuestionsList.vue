<template>
    <div>
        <div style="display: flex;align-items: center;font-size: 14px;">
            <span style="font-weight: bolder;margin-right: 10px;">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>常见问题</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="card margin-top-xs">
            <el-tabs :tab-position="'left'">
                <el-tab-pane :label="item" v-for="item in AskedQuestionsTypeList" :key="item.Id">

                    <PaginationBox ref="PaginationBox" :where="{ Type: item }" url="/AskedQuestions/List">
                        <template v-slot:content="{ data }">
                            <div v-for="(item, index) in data" :key="item.Id" class="question-item"
                                @click="ToDetail(item.Id)">
                                <div class="question-title">
                                    <i class="el-icon-question"></i>
                                    <span>{{ item.Title }}</span>
                                </div>
                                <div class="question-info">
                                    <span>{{ item.CreationTime }}</span>
                                    <span>类型：{{ item.Type }}</span>
                                </div>
                            </div>
                        </template>
                    </PaginationBox>

                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>
<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";
import SelectTab from '@/components/Select/SelectTab.vue';
import { mapGetters } from 'vuex';
export default {
    name: 'AskedQuestionsList',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', 'UserId'])
    },
    components: {
        PaginationBox,
        SelectTab
    },
    data() {
        return {
            activeNames: 0,
            where: {},
            AskedQuestionsTypeList: []
        }
    },
    created() {
        this.GetAskedQuestionsTypeList();
    },
    methods: {
        async GetAskedQuestionsTypeList() {
            let { Data } = await this.$Post('/AskedQuestions/GetTypeList', {});
            this.AskedQuestionsTypeList = Data;
        },
        ToDetail(Id) {
            this.$router.push({
                path: '/Front/AskedQuestionsDetail',
                query: {
                    AskedQuestionsId: Id,
                }
            })
        }
    },
}
</script>
<style scoped>
.question-item {
    width: 100%;
    padding: 15px;
    border-bottom: 1px solid #eee;
    cursor: pointer;
    transition: all 0.3s;
}

.question-item:hover {
    background-color: #f5f7fa;
}

.question-title {
    font-size: 16px;
    color: #303133;
    margin-bottom: 8px;
    display: flex;
    align-items: center;
}

.question-title i {
    color: #409EFF;
    margin-right: 8px;
}

.question-info {
    font-size: 12px;
    color: #909399;
}

.question-info span {
    margin-right: 15px;
}
</style>