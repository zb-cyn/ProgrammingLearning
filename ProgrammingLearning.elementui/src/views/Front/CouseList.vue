<template>
    <div>
        <!-- 面包屑导航 -->
        <div class="breadcrumb-container">
            <span class="location-text">您当前的位置:</span>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>线上课程列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <!-- 搜索区域 -->
        <div class="search-container">
            <div class="search-box">
                <el-input v-model="searchKeyword" placeholder="搜索课程..." @keyup.enter.native="handleSearch" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="handleSearch"></el-button>
                </el-input>
            </div>
            <div class="search-history" v-if="searchHistory.length">
                <div class="history-header">
                    <span>搜索历史</span>
                    <el-button type="text" @click="clearHistory">清除历史</el-button>
                </div>
                <div class="history-tags">
                    <el-tag v-for="(tag, index) in searchHistory" :key="index" closable @click="useHistoryTag(tag)"
                        @close="removeHistoryTag(index)" size="small">
                        {{ tag }}
                    </el-tag>
                </div>
            </div>
        </div>

        <!-- 课程分类导航 -->
        <div class="category-nav">
            <div class="category-list">
                <div class="category-item" :class="{ active: !where.CouseTypeId }" @click="selectCategory(null)">
                    全部
                </div>
                <div class="category-item" v-for="item in CouseTypeDataList" :key="item.Id"
                    :class="{ active: where.CouseTypeId === item.Id }" @click="selectCategory(item.Id)">
                    {{ item.Name }}
                </div>
            </div>
        </div>

        <!-- 课程列表 -->
        <PaginationBox ref="PaginationBox" :where="where" url="/Couse/List" :limit="16">
            <template v-slot:content="{ data }">
                <div class="course-grid">
                    <div v-for="course in data" :key="course.Id" class="course-card" @click="ToDetail(course.Id)">
                        <div class="course-image">
                            <img :src="course.Cover" :alt="course.Name">
                            <span class="course-type">{{ course.CouseTypeDto.Name }}</span>
                        </div>
                        <div class="course-content">
                            <h3 class="course-title">{{ course.Name }}</h3>
                            <div class="course-info">
                                <span class="course-steps">
                                    <i class="el-icon-video-camera"></i>
                                    {{ course.CouseStepCount }}课时
                                </span>
                                <span class="course-students">
                                    <i class="el-icon-user"></i>
                                    {{ course.CouseClockCount }}人学习
                                </span>
                            </div>
                            <div class="course-price">
                                <span v-if="course.IsFree">免费</span>
                                <span v-else>￥{{ course.Price }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
        </PaginationBox>
    </div>
</template>
<script>
import PaginationBox from "@/components/Pagination/PaginationBox.vue";

import { mapGetters } from 'vuex';
export default {
    name: 'Couse',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', 'UserId'])
    },
    components: {
        PaginationBox,

    },
    data() {
        return {
            where: {},
            CouseTypeDataList: [],
            searchKeyword: '',
            searchHistory: JSON.parse(localStorage.getItem('courseSearchHistory') || '[]')
        }
    },
    created() {
        this.CouseTypeListApi();
    },
    methods: {
        async CouseTypeListApi() {
            let { Data: { Items } } = await this.$Post('/CouseType/List', { Id: this.$route.query.CouseTypeId });
            this.CouseTypeDataList = Items;
        },
        ToDetail(Id) {
            this.$router.push({
                path: '/Front/CouseDetail',
                query: {
                    CouseId: Id,
                }
            })
        },
        selectCategory(id) {
            this.where = {
                ...this.where,
                CouseTypeId: id
            };
            this.$refs.PaginationBox.Reload(this.where);
        },
        handleSearch() {
            if (this.searchKeyword.trim()) {

                // 添加到搜索历史
                if (!this.searchHistory.includes(this.searchKeyword)) {
                    this.searchHistory.unshift(this.searchKeyword);
                    // 限制历史记录最多10条
                    if (this.searchHistory.length > 10) {
                        this.searchHistory.pop();
                    }
                    this.saveSearchHistory();
                }
            }

            // 更新搜索条件并刷新列表
            this.where = {
                ...this.where,
                KeyWord: this.searchKeyword
            };
            this.$refs.PaginationBox.Reload(this.where);
        },
        useHistoryTag(tag) {
            this.searchKeyword = tag;
            this.handleSearch();
        },
        removeHistoryTag(index) {
            this.searchHistory.splice(index, 1);
            this.saveSearchHistory();
        },
        clearHistory() {
            this.searchHistory = [];
            this.saveSearchHistory();
        },
        saveSearchHistory() {
            localStorage.setItem('courseSearchHistory', JSON.stringify(this.searchHistory));
        }
    },
}
</script>
<style scoped>
/* 面包屑导航样式 */
.breadcrumb-container {
    display: flex;
    align-items: center;
    font-size: 14px;
    margin-bottom: 20px;
}

.location-text {
    font-weight: bolder;
    margin-right: 10px;
}

/* 课程网格布局 */
.course-grid {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
    gap: 20px;
    padding: 20px 0;
}

/* 课程卡片样式 */
.course-card {
    background: #fff;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease;
    cursor: pointer;
}

.course-card:hover {
    transform: translateY(-5px);
}

/* 课程图片容器 */
.course-image {
    position: relative;
    height: 160px;
    overflow: hidden;
}

.course-image img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.course-type {
    position: absolute;
    top: 10px;
    right: 10px;
    background: rgba(0, 0, 0, 0.6);
    color: #fff;
    padding: 4px 8px;
    border-radius: 4px;
    font-size: 12px;
}

/* 课程内容样式 */
.course-content {
    padding: 15px;
}

.course-title {
    margin: 0 0 10px 0;
    font-size: 16px;
    color: #333;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.course-info {
    display: flex;
    justify-content: space-between;
    color: #666;
    font-size: 13px;
    margin-bottom: 10px;
}

.course-info i {
    margin-right: 4px;
}

.course-price {
    color: #f56c6c;
    font-size: 16px;
    font-weight: bold;
}

.course-price span {
    background: #fff3f3;
    padding: 2px 8px;
    border-radius: 4px;
}

/* 课程分类导航样式 */
.category-nav {
    background: #fff;
    padding: 20px;
    border-radius: 8px;
    margin-bottom: 20px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.category-list {
    display: flex;
    flex-wrap: wrap;
    gap: 12px;
}

.category-item {
    padding: 8px 20px;
    border-radius: 20px;
    background: #f5f7fa;
    color: #606266;
    cursor: pointer;
    transition: all 0.3s ease;
    font-size: 14px;
}

.category-item:hover {
    background: #e6f1fc;
    color: #409eff;
}

.category-item.active {
    background: #409eff;
    color: #fff;
}

/* 搜索区域样式 */
.search-container {
    background: #fff;
    padding: 20px;
    border-radius: 8px;
    margin-bottom: 20px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.search-box {
    max-width: 600px;
    margin: 0 auto;
}

.search-history {
    margin-top: 15px;
}

.history-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    color: #606266;
    font-size: 14px;
}

.history-tags {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;
}

.history-tags .el-tag {
    cursor: pointer;
    user-select: none;
}

/* 确保输入框内的搜索按钮居中对齐 */
.search-box .el-input-group__append {
    background-color: #409eff;
    border-color: #409eff;
    color: #fff;
}

.search-box .el-input-group__append:hover {
    background-color: #66b1ff;
    border-color: #66b1ff;
}
</style>