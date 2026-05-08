<template>
    <div>
        <el-row class="card">
            <el-col :span="24">
                <div class="echart" id="CouseEnrollCountEchart" :style="{ float: 'left', width: '100%', height: '500px' }">
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import * as echarts from "echarts";
import { mapGetters } from 'vuex';
export default {
    name: 'CouseEnrollCountEchart',
    computed: {
        ...mapGetters(['Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', 'UserId'])
    },
    props: {},
    data() {
        return {};
    },
    created() { },
    async mounted() {
        await this.CouseEnrollCountEchartApi();
    },
    methods: {
        async CouseEnrollCountEchartApi() {
            let { Data } = await this.$Post('/Couse/CouseEnrollCountEchart', {});
            this.$nextTick(() => {
                // 定义渐变色
                const colors = [
                    ['#FF3366', '#FF9933'],
                    ['#4834d4', '#686de0'],
                    ['#6c5ce7', '#a55eea'],
                    ['#00b894', '#00cec9'],
                    ['#f1c40f', '#f39c12'],
                    ['#e74c3c', '#c0392b'],
                    ['#2ecc71', '#27ae60'],
                    ['#3498db', '#2980b9'],
                    ['#9b59b6', '#8e44ad'],
                    ['#1abc9c', '#16a085']
                ];

                const option = {
                    backgroundColor: '#ffffff',
                    title: {
                        text: '课程报名统计',
                        subtext: '课程数据分析',
                        left: 'center',
                        textStyle: {
                            fontSize: 24,
                            fontWeight: 'bold',
                            color: '#333'
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        right: '5%',
                        top: 'center',
                        textStyle: {
                            fontSize: 14
                        }
                    },
                    series: [
                        {
                            name: '课程报名',
                            type: 'pie',
                            radius: ['40%', '70%'],
                            center: ['40%', '50%'],
                            roseType: 'radius',
                            itemStyle: {
                                borderRadius: 8
                            },
                            label: {
                                show: true,
                                formatter: '{b}: {c}人'
                            },
                            data: Data.map((item, index) => ({
                                name: item.name,
                                value: item.value,
                                itemStyle: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                        { offset: 0, color: colors[index][0] },
                                        { offset: 1, color: colors[index][1] }
                                    ])
                                }
                            }))
                        }
                    ],
                    animation: true,
                    animationDuration: 2000,
                    animationEasing: 'cubicInOut'
                };

                let myChart = echarts.init(document.getElementById("CouseEnrollCountEchart"));
                myChart.setOption(option);

                window.addEventListener("resize", () => {
                    myChart.resize();
                });
            })
        },
    }
}
</script>
<style scoped>
/* 图表容器样式 */
.card {
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

}

.echart {
    transition: all 0.3s ease;
}
</style>