<template>
    <el-card class="box-card question-item" :id="'Quetsion' + QuestionType + SubjectiveQuestionDto.Id">

        <div slot="header" class="question-header">
            <div>
                <el-tag>
                    主观题
                </el-tag>
                <span class="question-title">题目{{ Index + 1 }}: {{
                    SubjectiveQuestionDto.Title
                }}</span>
            </div>
            <div class="move-buttons">
                <el-button v-if="!isFirst" type="text" icon="el-icon-arrow-up" @click="$emit('moveUp')">上移</el-button>
                <el-button v-if="!isLast" type="text" icon="el-icon-arrow-down" @click="$emit('moveDown')">下移</el-button>
            </div>
        </div>
        <el-row class="margin-b-md">
            <div v-html="SubjectiveQuestionDto.Content" style="min-height: 100px"></div>

        </el-row>
        <el-row>

            <div class="analyzeItem">解释:</div>
            <el-input type="textarea" v-model.trim="SubjectiveQuestionDto.AnalyzeResult" readonly=""></el-input>
            <div class="score-item">分值:</div>
            <el-input-number v-model="QuestionnaireDetDto.Score" :min="0" :max="100" :step="0.5" :precision="1" size="small"
                class="score-input"></el-input-number>
        </el-row>
    </el-card>
</template>

<script>

export default {
    name: 'SubjectiveQuestion',
    components: {

    },
    props: {
        QuestionnaireDetDto: {
            type: Object,
            default: () => ({})
        },
        SubjectiveQuestionDto: {
            type: Object,
            default: () => ({})
        },

        QuestionType: {
            type: Number,
            default: 3
        },

        value: {
            type: [Number, String],
            default: ''
        },
        Index: {
            type: Number,
            default: 0
        },
        isFirst: {
            type: Boolean,
            default: false
        },
        isLast: {
            type: Boolean,
            default: false
        }
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
    margin-bottom: 10px;



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

        .move-buttons {
            display: flex;
            gap: 10px;

            .el-button {
                padding: 3px 0;

                &[disabled] {
                    color: #C0C4CC;
                    cursor: not-allowed;
                }
            }
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

    font-weight: 500;
}

.analyzeItem {
    font-size: 14px;
    color: #409EFF;

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

.score-item {
    font-size: 14px;
    color: #409EFF;
    margin: 15px 0 10px;
    font-weight: 500;
}

.score-input {
    margin: 5px 0;
    width: 120px;
}
</style>
