<template>
    <div class="app-container">


        <el-row class="margin-top-xs card">

            <el-col :span="24" v-for="(item, index ) in DataList" class="margin-top-xs">

                <el-row>
                    <el-col :span="24">
                        <div class="echart" :id="'QuestionOptionsRightDataCollect' + item.questionnaireId"
                            :style="{ float: 'left', width: '100%', height: '500px' }">
                        </div>
                    </el-col>

                </el-row>

                <el-row>
                    <el-col :span="24">
                        <div class="echart" :id="'QuestionOptionsDataCollect' + item.questionnaireId"
                            :style="{ float: 'left', width: '100%', height: '500px' }">
                        </div>
                    </el-col>

                </el-row>
                <el-row>
                    <el-col :span="12" v-for="(optionData) in item.options">

                        <div class="echart"
                            :id="'QuestionOptionsDataCollectPie_' + optionData.questionType + '_' + optionData.questionId"
                            :style="{ float: 'left', width: '100%', height: '500px' }">
                        </div>
                    </el-col>
                </el-row>

            </el-col>

            <el-empty description="暂时没有数据" v-if="DataList.length == 0"></el-empty>

        </el-row>


    </div>
</template>

<script>
import * as echarts from "echarts";
import { mapGetters } from 'vuex';

export default {
    name: "QuestionOptionsDataCollect",
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },

    data() {

        return {
            where: {},
            searchForm: {},//搜索定义的数据对象
            isLoad: false,
            DataList: []
        };
    },
    created() {



        this.searchForm.Id = this.$route.query.QuestionnaireId;

        this.GetDataCollectApi()

    },
    mounted() {

    },
    methods: {

        async GetDataCollectApi() {
            let { Data } = await this.$Post("/Questionnaire/QuestionOptionsDataCollect", this.searchForm)
            this.DataList = Data;
            this.$nextTick(() => {

                for (let item of this.DataList) {
                    let option = {
                        title: {
                            text: item.questionnaireName + "[选项正确率]"
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'cross',
                                label: {
                                    backgroundColor: '#6a7985'
                                }
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '15%',  // 增加底部空间来放置缩放控件
                            containLabel: true
                        },
                        dataZoom: [
                            {
                                type: 'slider',  // 滑动条
                                show: true,
                                xAxisIndex: [0],
                                start: 0,
                                end: 20,         // 默认显示前20%的数据
                                bottom: '5%'     // 调整滑块位置
                            },
                            {
                                type: 'inside', // 内置型数据区域缩放组件（使用鼠标滚轮缩放）
                                xAxisIndex: [0],
                                start: 0,
                                end: 20
                            }
                        ],
                        toolbox: {
                            feature: {
                                saveAsImage: {},

                            }
                        },
                        xAxis: {
                            type: 'category',
                            data: item.options.map((x, index) => '第' + (index + 1) + "题" + x.questionName)
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: 'A',
                                type: 'bar',
                                stack: 'total',
                                label: {
                                    show: true,
                                    formatter: '{c}%' // 格式化标签显示内容
                                },
                                emphasis: {
                                    focus: 'series'
                                },
                                data: item.options.map(x => x.rightCount)
                            },


                        ]
                    };

                    this.myChart = echarts.init(document.getElementById("QuestionOptionsRightDataCollect" + item.questionnaireId));// 图标初始化
                    this.myChart.setOption(option);// 渲染页面
                    //随着屏幕大小调节图表
                    window.addEventListener("resize", () => {
                        this.myChart.resize();
                    });
                }



                for (let item of this.DataList) {
                    let option = {
                        title: {
                            text: item.questionnaireName + "[选项选择]"
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {},
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                            }
                        },
                        legend: {
                            data: ['A选项', 'B选项', 'C选项', 'D选项', '正确选项', '错误选项'],
                            type: 'scroll'  // 让图例也可以滚动
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '15%',  // 增加底部空间来放置缩放控件
                            containLabel: true
                        },
                        dataZoom: [
                            {
                                type: 'slider',  // 滑动条
                                show: true,
                                xAxisIndex: [0],
                                start: 0,
                                end: 20,         // 默认显示前20%的数据
                                bottom: '5%'     // 调整滑块位置
                            },
                            {
                                type: 'inside', // 内置型数据区域缩放组件（使用鼠标滚轮缩放）
                                xAxisIndex: [0],
                                start: 0,
                                end: 20
                            }
                        ],
                        xAxis: {
                            type: 'category',
                            data: item.options.map((x, index) => '第' + (index + 1) + "题"),
                            axisLabel: {
                                interval: 0,
                                rotate: 45
                            }
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: 'A选项',
                                type: 'bar',
                                barGap: '30%',      // 不同系列的柱间距离
                                barCategoryGap: '20%', // 同一系列的柱间距离
                                emphasis: {
                                    focus: 'series'
                                },
                                data: item.options.map(x => x.optionA)
                            },
                            {
                                name: 'B选项',
                                type: 'bar',
                                emphasis: {
                                    focus: 'series'
                                },
                                data: item.options.map(x => x.optionB)
                            },
                            {
                                name: 'C选项',
                                type: 'bar',
                                emphasis: {
                                    focus: 'series'
                                },
                                data: item.options.map(x => x.optionC)
                            },
                            {
                                name: 'D选项',
                                type: 'bar',
                                emphasis: {
                                    focus: 'series'
                                },
                                data: item.options.map(x => x.optionD)
                            },
                            {
                                name: '正确选项',
                                type: 'bar',
                                emphasis: {
                                    focus: 'series'
                                },
                                data: item.options.map(x => x.optionTrue)
                            },
                            {
                                name: '错误选项',
                                type: 'bar',
                                emphasis: {
                                    focus: 'series'
                                },
                                data: item.options.map(x => x.optionFalse)
                            }
                        ]
                    };

                    this.myChart = echarts.init(document.getElementById("QuestionOptionsDataCollect" + item.questionnaireId));// 图标初始化
                    this.myChart.setOption(option);// 渲染页面
                    //随着屏幕大小调节图表
                    window.addEventListener("resize", () => {
                        this.myChart.resize();
                    });
                }



                for (let item of this.DataList[0].options) {


                    let dataset = [];
                    if (item.questionType == 1 || item.questionType == 2) {
                        dataset = [{
                            name: "A",
                            value: item.optionA
                        },
                        {
                            name: "B",
                            value: item.optionB
                        },
                        {
                            name: "C",
                            value: item.optionC
                        },
                        {
                            name: "D",
                            value: item.optionD
                        },
                        ]
                    }
                    else {
                        dataset = [{
                            name: "正确",
                            value: item.optionTrue,
                        },
                        {
                            name: "错误",
                            value: item.optionFalse
                        }]
                    }


                    let option = {
                        title: {
                            text: item.questionName.length > 20 ? item.questionName.substring(0, 20) + "..." : item.questionName,

                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left'
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {},

                            }
                        },
                        series: [
                            {
                                name: '选项占比',
                                type: 'pie',
                                radius: '50%',
                                data: dataset,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };

                    let myChart = echarts.init(document.getElementById("QuestionOptionsDataCollectPie_" + item.questionType + "_" + item.questionId));// 图标初始化
                    myChart.setOption(option);// 渲染页面
                    //随着屏幕大小调节图表
                    window.addEventListener("resize", () => {
                        myChart.resize();
                    });

                }
            })
        },

        async SearchClick() {

            this.GetDataCollectApi();
        },

        async ResetClick() {
            this.searchForm = {};

            this.GetDataCollectApi();
        },



    },
};
</script>