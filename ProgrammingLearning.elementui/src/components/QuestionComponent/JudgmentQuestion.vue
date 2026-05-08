<template>
    <el-card class="box-card question-item" :class="{ 'answered': UserAnswerDetDto.Id != null }"
        :id="'Quetsion' + QuestionType + JudgmentQuestionDto.Id">

        <div slot="header" class="question-header">
            <div>
                <el-tag>
                    判断题({{ UserAnswerDetDto.TotalScore }})分
                </el-tag>
                <span class="question-title">题目{{ Index + 1 }}: {{
                    JudgmentQuestionDto.Title
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
                <el-radio-group v-model="selectValue" @input="JudgmentQuestionChange" :disabled="ReadOnly">
                    <el-radio label="true">正确</el-radio>
                    <el-radio label="false">错误</el-radio>
                </el-radio-group>

            </el-row>

        </el-row>
        <el-row v-if="ShowResult == true">
            <div class="right-answer">正确选项:{{ JudgmentQuestionDto.RightAnswer ? '正确' : '错误' }} </div>
            <div class="analyzeItem">解释:</div>
            <el-input type="textarea" v-model.trim="JudgmentQuestionDto.AnalyzeResult" readonly=""></el-input>
        </el-row>
    </el-card>
</template>

<script>

export default {
    name: 'JudgmentQuestion',

    props: {
        UserAnswerDetDto: {
            type: Object,
            default: () => ({})
        },

        JudgmentQuestionDto: {
            type: Object,
            default: () => ({})
        },

        QuestionType: {
            type: Number,
            default: 3
        },
        ShowResult: {
            type: Boolean,
            default: false
        },
        ShowGetScore: {//是否显示得分
            type: Boolean,
            default: false
        },
        ReadOnly: {//是否只读
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

        this.selectValue = this.$props.value;


    },
    methods: {

        JudgmentQuestionChange() {
            console.log('判断题发生变动', this.selectValue)
            this.$emit('input', this.selectValue);
            this.$emit("Change", { Index: this.Index, Value: this.selectValue });
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
