<template>
    <el-card class="box-card question-item" :class="{ 'answered': UserAnswerDetDto.Id != null }"
        :id="'Quetsion' + QuestionType + SubjectiveQuestionDto.Id">

        <div slot="header" class="question-header">
            <div>
                <el-tag>
                    主观题({{ UserAnswerDetDto.TotalScore }})分
                </el-tag>
                <span class="question-title">题目{{ Index + 1 }}: {{
                    SubjectiveQuestionDto.Title
                }}</span>
            </div>
            <div v-if="ShowGetScore">
                <el-tag type="success" v-if="UserAnswerDetDto.GetScore > 0">得分：{{
                    UserAnswerDetDto.GetScore }}分</el-tag>
                <el-tag type="danger" v-else>得分：0分</el-tag>

            </div>
        </div>
        <el-row class="margin-b-md">

            <el-row class="margin-b-md">
                <el-input type="textarea" :rows="10" v-model.trim="selectValue" :disabled="ReadOnly"
                    @input="SubjectiveQuestionChange"></el-input>

            </el-row>

        </el-row>
        <el-row v-if="ShowResult == true">
            <div class="right-answer">正确答案:</div>
            <el-input type="textarea" :rows="5" v-if="SubjectiveQuestionDto.RightAnswer"
                v-model.trim="SubjectiveQuestionDto.RightAnswer" readonly=""></el-input>
            <div class="analyzeItem">解释:</div>
            <el-input type="textarea" :rows="5" v-model.trim="SubjectiveQuestionDto.AnalyzeResult" readonly=""></el-input>
            <template v-if="IsMarking">
                <div class="score-item">打分:</div>
                <el-input-number v-model="UserAnswerDetDto.GetScore" :min="0" :max="UserAnswerDetDto.TotalScore" :step="1"
                    @change="SubjectiveQuestionChangeScore" :precision="1" size="small"
                    class="score-input"></el-input-number>
            </template>
        </el-row>
    </el-card>
</template>

<script>
// 添加防抖函数
const debounce = (fn, delay) => {
    let timer = null;
    return function (...args) {
        if (timer) clearTimeout(timer);
        timer = setTimeout(() => {
            fn.apply(this, args);
        }, delay);
    };
};

export default {
    name: 'SubjectiveQuestion',

    props: {
        UserAnswerDetDto: {
            type: Object,
            default: () => ({})
        },

        SubjectiveQuestionDto: {
            type: Object,
            default: () => ({})
        },

        QuestionType: {
            type: Number,
            default: 4
        },
        ShowResult: {
            type: Boolean,
            default: false
        }, ReadOnly: {//是否只读
            type: Boolean,
            default: false
        },
        IsMarking: {//是否阅卷
            type: Boolean,
            default: false
        },
        ShowGetScore: {//是否显示得分
            type: Boolean,
            default: false
        },
        value: {
            type: [Number, String],
            default: ''
        },
        Index: {
            type: Number,
            default: 0
        },
    },
    watch: {
        "value": {
            immediate: true, //该回调将会在侦听开始之后被立即调用
            handler: function (n, o) {

                this.selectValue = n;

            }
        }
    },
    data() {
        return {
            selectValue: undefined,

        }
    },
    created() {
        let that = this;
        this.selectValue = this.$props.value;
        // 创建防抖后的方法
        this.debouncedSubjectiveQuestionChange = debounce(function () {
            console.log('主观题发生变动', that.selectValue);
            that.$emit('input', that.selectValue);
            that.$emit("Change", { Index: that.Index, Value: that.selectValue });
        }, 300); // 300ms 的防抖延迟

        this.debouncedSubjectiveQuestionChangeScore = debounce(function () {
            console.log('打分发生变动', that.UserAnswerDetDto.GetScore);

            that.$emit("ScoreChange", { Index: that.Index, Value: that.UserAnswerDetDto.GetScore });
        }, 300); // 300ms 的防抖延迟

    },
    methods: {

        SubjectiveQuestionChange() {
            this.debouncedSubjectiveQuestionChange();
        },
        SubjectiveQuestionChangeScore() {
            this.debouncedSubjectiveQuestionChangeScore();
        },

    }
}

</script>
<style lang="scss" scoped>
.question-item {
    transition: all 0.3s ease;
    border-radius: 8px;



    &:hover {
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    &.answered {
        border-left: 4px solid #67C23A;
    }

    .question-header {

        display: flex;
        justify-content: space-between;
        align-items: center;

        .el-tag {
            margin-right: 10px;
        }

        .question-title {
            font-size: 16px;
            font-weight: 500;
        }
    }
}

.sigle-question-item {

    border-radius: 4px;

    &:hover {
        background-color: #f5f7fa;
    }

    .el-radio,
    .el-checkbox {
        width: 100%;
        padding: 8px;
    }
}

.right-answer {
    font-size: 14px;
    color: #1fbd8d;
    margin: 15px 0 10px;
    font-weight: 500;
}

.analyzeItem {
    font-size: 14px;
    color: #409EFF;
    margin: 15px 0 10px;
    font-weight: 500;
}

// 答题卡样式优化
.el-tag {
    cursor: pointer;
    transition: all 0.3s;

    &:hover {
        transform: translateY(-2px);
    }

    &.el-tag--success {
        background-color: #f0f9eb;
    }

    &.el-tag--info {
        background-color: #f4f4f5;
    }
}
</style>
