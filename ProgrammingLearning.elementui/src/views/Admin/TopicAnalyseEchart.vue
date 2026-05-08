<template>
    <div>
        <el-row class=" card">
            <el-col :span="24">
                <div class="echart" id="TopicAnalyseEchart" :style="{ float: 'left', width: '100%', height: '500px' }">
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import * as echarts from "echarts";
import store from '@/store';
import { mapGetters } from 'vuex'
export default {
    name: 'TopicAnalyseEchart',
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    props: {

    },
    data() {
        return {


        };
    },
    created() {

    },
    async mounted() {
        await this.TopicAnalyseEchartApi();


    },
    methods: {

        async TopicAnalyseEchartApi() {
            let { Data } = await this.$Post('/Topic/TopicAnalyseEchart', {});
            this.$nextTick(() => {
                var option = {
                    title: {
                        text: '最近一周各话题浏览人次'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {},
                    toolbox: {
                        show: true,
                        feature: {

                            magicType: { type: ['line', 'bar'] },

                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',

                        data: Data.week
                    },
                    yAxis: {
                        type: 'value',

                    },
                    series: Data.datas.map(x => {
                        return {
                            name: x.name,
                            type: 'bar',
                            data: x.values,
                            barWidth: '10%' // 设置第二个系列的柱子宽度为容器宽度的70%
                        }
                    })


                };

                let myChart = echarts.init(document.getElementById("TopicAnalyseEchart"));// 图标初始化
                myChart.setOption(option);// 渲染页面
                //随着屏幕大小调节图表
                window.addEventListener("resize", () => {
                    myChart.resize();
                });

            })
        },

    }
}       
</script>

<style scoped></style>