<template>
    <el-card class="box-card question-item" :class="{ 'answered': UserAnswerDetDto.Id != null }"
        :id="'Quetsion' + QuestionType + SingleChoiseQuestionDto.Id">

        <div slot="header" class="question-header">
            <div>
                <el-tag>
                    单选题({{ UserAnswerDetDto.TotalScore }})分
                </el-tag>
                <span class="question-title">题目{{ Index + 1 }}: {{
                    SingleChoiseQuestionDto.Title
                }}</span>
            </div>
            <div v-if="ShowGetScore">
                <el-tag type="success" v-if="UserAnswerDetDto.GetScore > 0">得分：{{
                    UserAnswerDetDto.GetScore }}分</el-tag>
                <el-tag type="danger" v-else>得分：0分</el-tag>

            </div>

        </div>
        <el-row class="margin-b-md">
            <el-radio-group v-model="selectValue" @input="SigleChange" :disabled="ReadOnly">
                <div class="sigle-question-item">
                    <el-radio label="A">A:{{
                        SingleChoiseQuestionDto.OptionA
                    }}</el-radio>
                </div>
                <div class="sigle-question-item">
                    <el-radio label="B">B:{{
                        SingleChoiseQuestionDto.OptionB
                    }}</el-radio>
                </div>
                <div class="sigle-question-item">
                    <el-radio label="C">C:{{
                        SingleChoiseQuestionDto.OptionC
                    }}</el-radio>
                </div>
                <div class="sigle-question-item">
                    <el-radio label="D">D:{{
                        SingleChoiseQuestionDto.OptionD
                    }}</el-radio>
                </div>

            </el-radio-group>

        </el-row>
        <el-row v-if="ShowResult">
            <div class="right-answer">正确选项:{{ SingleChoiseQuestionDto.RightAnswer }}</div>
            <div class="analyzeItem">解释:</div>
            <el-input type="textarea" v-model.trim="SingleChoiseQuestionDto.AnalyzeResult" readonly=""></el-input>
        </el-row>
    </el-card>
</template>

<script>

export default {
    name: 'SingleChoiseQuestion',
    components: {

    },
    props: {
        UserAnswerDetDto: {
            type: Object,
            default: () => ({})
        },

        SingleChoiseQuestionDto: {
            type: Object,
            default: () => ({})
        },

        QuestionType: {
            type: Number,
            default: 1
        },

        value: {
            type: [Number, String],
            default: ''
        }, ReadOnly: {//是否只读
            type: Boolean,
            default: false
        },
        ShowResult: {
            type: Boolean,
            default: false
        },
        ShowGetScore: {
            type: Boolean,
            default: false
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

        SigleChange() {
            console.log('单选题发生变动', this.selectValue)
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
