<template>
<div class="content" :style='{"padding":"30px"}'>
	<div class="text" :style='{"margin":"50px auto","fontSize":"24px","color":"rgb(51, 51, 51)","textAlign":"center","fontWeight":"bold"}'>欢迎使用 {{this.$project.projectName}}</div>
    <div class="cardView">
        <div class="cards" :style='{"margin":"0 0 20px 0","alignItems":"center","justifyContent":"center","display":"flex"}'>
			<div :style='{"margin":"0 10px","backgroundSize":"100% 100%","borderRadius":"4px","backgroundImage":"url(http://codegen.caihongy.cn/20220723/4d7db0e7023e4771b17f359d29c6bd9c.png)","display":"flex"}' v-if="isAuth('yinlexinxi','首页总数')">
				<div :style='{"background":"red"}'></div>
				<div :style='{"width":"220px","padding":"5px 0 5px 30px","alignItems":"center","flexDirection":"column","justifyContent":"center","display":"flex"}'>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"20px","color":"#333","fontWeight":"bold","height":"24px"}'>{{yinlexinxiCount}}</div>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"16px","color":"#666","height":"24px"}'>音乐信息总数</div>
				</div>
			</div>
        </div>
        <div style="display: flex;align-items: center;width: 100%;margin-bottom: 10px;">
            <el-card style="width: 50%;margin: 0 10px;" v-if="isAuth('yinlexinxi','首页统计')">
                <div id="yinlexinxiChart1" style="width:100%;height:400px;"></div>
            </el-card>
            <el-card style="width: 50%;margin: 0 10px;" v-if="isAuth('yinlexinxi','首页统计')">
                <div id="yinlexinxiChart2" style="width:100%;height:400px;"></div>
            </el-card>
        </div>
    </div>
</div>
</template>
<script>
//2
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
            yinlexinxiCount: 0,
		};
	},
  mounted(){
    this.init();
    this.getyinlexinxiCount();
    this.yinlexinxiChat1();
    this.yinlexinxiChat2();
  },
  methods:{
    init(){
        if(this.$storage.get('Token')){
        this.$http({
            url: `${this.$storage.get('sessionTable')}/session`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code != 0) {
            router.push({ name: 'login' })
            }
        });
        }else{
            router.push({ name: 'login' })
        }
    },
    getyinlexinxiCount() {
        this.$http({
            url: `yinlexinxi/count`,
            method: "get"
        }).then(({
            data
        }) => {
            if (data && data.code == 0) {
                this.yinlexinxiCount = data.data
            }
        })
    },

    yinlexinxiChat1() {
      this.$nextTick(()=>{

        var yinlexinxiChart1 = echarts.init(document.getElementById("yinlexinxiChart1"),'macarons');
        this.$http({
            url: "yinlexinxi/group/yinleleixing",
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].yinleleixing);
                    yAxis.push(parseFloat((res[i].total)));
                    pArray.push({
                        value: parseFloat((res[i].total)),
                        name: res[i].yinleleixing
                    })
                }
                var option = {};
                option = {
                        title: {
                            text: '音乐类型统计',
                            left: 'center'
                        },
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c} ({d}%)'
                        },
                        series: [
                            {
                                type: 'pie',
                                radius: ['25%', '55%'],
                                center: ['50%', '60%'],
                                data: pArray,
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
                // 使用刚指定的配置项和数据显示图表。
                yinlexinxiChart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    yinlexinxiChart1.resize();
                };
            }
        });
      })
    },

    yinlexinxiChat2() {
      this.$nextTick(()=>{

        var yinlexinxiChart2 = echarts.init(document.getElementById("yinlexinxiChart2"),'macarons');
        this.$http({
            url: "yinlexinxi/group/singer",
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].singer);
                    yAxis.push(parseFloat((res[i].total)));
                    pArray.push({
                        value: parseFloat((res[i].total)),
                        name: res[i].singer
                    })
                }
                var option = {};
                option = {
                    title: {
                        text: '歌手作品数量统计',
                        left: 'center'
                    },
                    tooltip: {
                      trigger: 'item',
                      formatter: '{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        data: xAxis,
                        axisLabel : {
                            rotate:70
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: yAxis,
                        type: 'bar'
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                yinlexinxiChart2.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    yinlexinxiChart2.resize();
                };
            }
        });
      })
    },





  }
};
</script>
<style lang="scss" scoped>
    .cardView {
        display: flex;
        flex-wrap: wrap;
        width: 100%;

        .cards {
            display: flex;
            align-items: center;
            width: 100%;
            margin-bottom: 10px;
            justify-content: center;
            .card {
                width: calc(25% - 20px);
                margin: 0 10px;
                /deep/.el-card__body{
                    padding: 0;
                }
            }
        }
    }
</style>
