<template>
    <el-card class="box-card question-item" :class="{ 'answered': UserAnswerDetDto.Id != null }"
        :id="'Quetsion' + QuestionType + FillQuestionDto.Id">

        <div slot="header" class="question-header">
            <div>
                <el-tag>
                    填空题
                </el-tag>
                <span class="question-title">题目{{ Index + 1 }}: <span
                        v-html="formatTitle(FillQuestionDto.Title)"></span></span>
            </div>
            <div v-if="ShowGetScore">
                <el-tag type="success" v-if="UserAnswerDetDto.GetScore > 0">得分：{{
                    UserAnswerDetDto.GetScore }}分</el-tag>
                <el-tag type="danger" v-else>得分：0分</el-tag>

            </div>

        </div>
        <el-row class="margin-b-md">

            <div v-for="(answer, index) in AnswerList" :key="index" class="answer-item">
                <span>问题 #{{ index + 1 }}: </span>
                <el-input v-model="answer.AnswerContent" placeholder="请输入答案" style="width: 200px; margin-right: 10px;"
                    @input="FillQuestionChange()" :readonly="UserAnswerDetDto.Id != null">
                </el-input>
                <span v-if="answer.IsRight != null">
                    <el-tag type="success" v-if="answer.IsRight">正确</el-tag>
                    <el-tag type="danger" v-else>错误</el-tag>
                </span>
            </div>

        </el-row>
        <el-row v-if="ShowResult == true">
            <div class="right-answer">正确答案:
                <div v-for="(answer, index) in AnswerList" :key="index">
                    <span style="margin-right: 10px;">第{{ index + 1 }}位置: </span>
                    <span>
                        {{ answer.Result }}
                    </span>
                </div>
            </div>
            <div class="analyzeItem">解释:</div>
            <el-input type="textarea" v-model.trim="FillQuestionDto.AnalyzeResult" readonly=""></el-input>
        </el-row>
    </el-card>
</template>

<script>

export default {
    name: 'FillQuestion',

    props: {
        UserAnswerDetDto: {
            type: Object,
            default: () => ({})
        },

        FillQuestionDto: {
            type: Object,
            default: () => ({})
        },

        QuestionType: { //题目类型
            type: Number,
            default: 4
        },
        ShowResult: {//显示结果
            type: Boolean,
            default: false
        },
        ReadOnly: {//是否只读
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

        Index: { //索引
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
            AnswerList: []
        }
    },
    created() {
        let answerList = JSON.parse(this.FillQuestionDto.RightAnswer);
        console.log(answerList)
        this.selectValue = this.$props.value;
        if (this.selectValue) {
            //转换成json
            let userAnswerList = JSON.parse(this.selectValue);
            this.AnswerList = answerList.map(item => ({
                AnswerContent: userAnswerList.find(userItem => userItem.Question === item.Question)?.AnswerContent || '',
                Question: item.Question,
                Result: item.Result,
                IsRight: userAnswerList.find(userItem => userItem.Question === item.Question)?.IsRight || false
            }));
        }
        else {
            this.AnswerList = answerList.map(item => ({
                AnswerContent: '',
                Question: item.Question,
                Result: item.Result,
                IsRight: null
            }));
        }



    },
    methods: {
        formatTitle(title) {
            if (!title) return '';
            return title.replace(/<#Question\d+>/g, '<span class="fill-blank">____</span>');
        },

        FillQuestionChange() {
            console.log('填空题发生变动', this.selectValue)
            this.$emit('input', JSON.stringify(this.AnswerList));
            this.$emit("Change", { Index: this.Index, Value: JSON.stringify(this.AnswerList) });
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

:deep(.fill-blank) {
    color: #ff4444;
    font-weight: bold;
    padding: 0 4px;
}

.answer-item {
    margin-bottom: 10px;
}
</style>
