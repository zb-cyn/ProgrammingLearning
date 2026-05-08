<template>
    <div class="course-chapters">
        <h3 class="chapter-title">课程章节</h3>
        <div class="chapter-list">
            <div v-for="(chapter, index) in chapters" :key="chapter.Id" class="chapter-item "
                :class="chapter.Id == currentChapterId ? 'is-playing' : ''" @click="handleChapterClick(chapter)">
                <div class="chapter-info">
                    <span class="chapter-index">{{ index + 1 }}</span>
                    <div class="chapter-content">
                        <div class="chapter-name">{{ chapter.Name }}</div>
                        <div style="color: #67C23A;font-size: 12px;">{{ chapter.IsClock ? '已打卡' : '' }}</div>
                        <div class="study-progress">已学习 {{ formatDuration(chapter.StudyTotalDurtion || 0) }}</div>
                    </div>
                </div>
                <div class="chapter-duration">
                    {{ formatDuration(chapter.Durtion) }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CourseChapters',
    props: {
        currentChapterId: {
            type: String,
            default: '1'
        },
        isPlaying: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            chapters: []
        }
    },
    created() {

        this.GetCouseStepListApi()
    },
    methods: {
        //得到课程章节
        async GetCouseStepListApi() {
            let { Data: { Items } } = await this.$Post("/CouseStep/List", { CouseId: this.$route.query.CouseId });
            this.chapters = Items;
        },

        handleChapterClick(chapter) {
            this.$emit('chapter-click', chapter)
        },
        formatDuration(milliseconds) {
            if (!milliseconds) return '0:00';
            const totalSeconds = Math.floor(milliseconds / 1000);
            const minutes = Math.floor(totalSeconds / 60);
            const seconds = totalSeconds % 60;
            return `${minutes}:${seconds.toString().padStart(2, '0')}`;
        },

    }
}
</script>

<style scoped>
.course-chapters {
    width: 100%;
    background: #fff;
    border-radius: 8px;
    padding: 20px;
}

.chapter-title {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 20px;
}

.chapter-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px;
    border-bottom: 1px solid #eee;
    cursor: pointer;
    transition: all 0.3s;
}

.chapter-item:hover {
    background: #f5f5f5;
}

.chapter-item.active {
    background: #e6f7ff;
    border-left: 3px solid #1890ff;
}

.chapter-item.is-playing .chapter-index {
    background: #1890ff;
    color: #fff;
}

.chapter-info {
    display: flex;
    align-items: center;
    flex: 1;
}

.chapter-index {
    width: 24px;
    height: 24px;
    background: #f0f0f0;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: 12px;
    font-size: 14px;
}

.chapter-content {
    flex: 1;
}

.chapter-name {
    font-size: 16px;
    color: #333;
    margin-bottom: 4px;
}

.chapter-desc {
    font-size: 14px;
    color: #666;
}

.chapter-duration {
    color: #999;
    font-size: 14px;
    margin-left: 20px;
}

.study-progress {
    font-size: 12px;
    color: #1890ff;
    margin-top: 4px;
}
</style>