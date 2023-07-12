<template>
	<div class="main-content" :style='{"padding":"0"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"width":"90%","margin":"25px auto 20px"}' :inline="true" :model="searchForm">
				<el-row :style='{"display":"block"}' >
					<div :style='{"margin":"0 25px 0 0","display":"inline-block"}'>
						<label :style='{"margin":"0 10px 0 0","color":"#333","display":"inline-block","lineHeight":"40px","fontSize":"14px","fontWeight":"500","height":"40px"}' class="item-label">歌名</label>
						<el-input v-model="searchForm.songname" placeholder="歌名" clearable></el-input>
					</div>
					<div :style='{"margin":"0 25px 0 0","display":"inline-block"}' class="select" label="歌手" prop="singer">
						<label :style='{"margin":"0 10px 0 0","color":"#333","display":"inline-block","lineHeight":"40px","fontSize":"14px","fontWeight":"500","height":"40px"}' class="item-label">歌手</label>
						<el-select  @change="singerChange" clearable v-model="searchForm.singer" placeholder="请选择歌手">
							<el-option v-for="(item,index) in singerOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 25px 0 0","display":"inline-block"}' class="select" label="音乐类型" prop="yinleleixing">
						<label :style='{"margin":"0 10px 0 0","color":"#333","display":"inline-block","lineHeight":"40px","fontSize":"14px","fontWeight":"500","height":"40px"}' class="item-label">音乐类型</label>
						<el-select  @change="yinleleixingChange" clearable v-model="searchForm.yinleleixing" placeholder="请选择音乐类型">
							<el-option v-for="(item,index) in yinleleixingOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 25px 0 0","display":"inline-block"}'>
						<label :style='{"margin":"0 10px 0 0","color":"#333","display":"inline-block","lineHeight":"40px","fontSize":"14px","fontWeight":"500","height":"40px"}' class="item-label">专辑</label>
						<el-input v-model="searchForm.zhuanji" placeholder="专辑" clearable></el-input>
					</div>
					<div :style='{"margin":"0 25px 0 0","display":"inline-block"}' class="select">
						<label :style='{"margin":"0 10px 0 0","color":"#333","display":"inline-block","lineHeight":"40px","fontSize":"14px","fontWeight":"500","height":"40px"}' class="item-label">是否通过</label>
						<el-select  @change="sfshChange" clearable v-model="searchForm.sfsh" placeholder="是否通过">
							<el-option v-for="(item,index) in sfshOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","outline":"none","color":"#fff","borderRadius":"4px","background":"rgba(253, 199, 118, 1)","width":"auto","fontSize":"14px","height":"40px"}' type="success" @click="search()">查询</el-button>
				</el-row>

				<el-row :style='{"margin":"20px 0","justifyContent":"flex-end","display":"flex"}'>
					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#fff","borderRadius":"4px","background":"rgba(253, 199, 118, 1)","width":"auto","fontSize":"14px","height":"40px"}' v-if="isAuth('yinlexinxi','新增')" type="success" @click="addOrUpdateHandler()">新增</el-button>
					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#fff","borderRadius":"4px","background":"rgba(253, 199, 118, 1)","width":"auto","fontSize":"14px","height":"40px"}' v-if="isAuth('yinlexinxi','删除')" :disabled="dataListSelections.length <= 0" type="danger" @click="deleteHandler()">删除</el-button>




					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#fff","borderRadius":"4px","background":"rgba(253, 199, 118, 1)","width":"auto","fontSize":"14px","height":"40px"}' v-if="isAuth('yinlexinxi','音乐类型统计')" type="warning" @click="chartDialog1()">音乐类型统计</el-button>
					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#fff","borderRadius":"4px","background":"rgba(253, 199, 118, 1)","width":"auto","fontSize":"14px","height":"40px"}' v-if="isAuth('yinlexinxi','歌手作品数量统计')" type="warning" @click="chartDialog2()">歌手作品数量统计</el-button>
				</el-row>
			</el-form>
			
			<!-- <div> -->
				<el-table class="tables"
					:stripe='true'
					:style='{"padding":"0","boxShadow":"0px 4px 10px 0px rgba(0,0,0,0.3020)","borderColor":"rgba(255, 214, 162, 1)","margin":"0 auto 15px","borderRadius":"40px 40px 5px 5px","borderWidth":"7px","background":"#fff","width":"90%","borderStyle":"solid"}' 
					v-if="isAuth('yinlexinxi','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="索引" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="songname"
					label="歌名">
						<template slot-scope="scope">
							{{scope.row.songname}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="singer"
					label="歌手">
						<template slot-scope="scope">
							{{scope.row.singer}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="yinleleixing"
					label="音乐类型">
						<template slot-scope="scope">
							{{scope.row.yinleleixing}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="fengmian" width="200" label="封面">
						<template slot-scope="scope">
							<div v-if="scope.row.fengmian">
								<img v-if="scope.row.fengmian.substring(0,4)=='http'" :src="scope.row.fengmian.split(',')[0]" width="100" height="100">
								<img v-else :src="$base.url+scope.row.fengmian.split(',')[0]" width="100" height="100">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="bianqu"
					label="编曲">
						<template slot-scope="scope">
							{{scope.row.bianqu}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="tianci"
					label="填词">
						<template slot-scope="scope">
							{{scope.row.tianci}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="yuyan"
					label="语言">
						<template slot-scope="scope">
							{{scope.row.yuyan}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="zhuanji"
					label="专辑">
						<template slot-scope="scope">
							{{scope.row.zhuanji}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="shizhang"
					label="时长">
						<template slot-scope="scope">
							{{scope.row.shizhang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="faxingfang"
					label="发行方">
						<template slot-scope="scope">
							{{scope.row.faxingfang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="faxingriqi"
					label="发行日期">
						<template slot-scope="scope">
							{{scope.row.faxingriqi}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="songfile" label="音乐文件">
						<template slot-scope="scope">
							<el-button v-if="scope.row.songfile" type="text" size="small" @click="download($base.url+scope.row.songfile)">预览</el-button>
                            <span v-else >无</span>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="zhanghao"
					label="账号">
						<template slot-scope="scope">
							{{scope.row.zhanghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="nicheng"
					label="昵称">
						<template slot-scope="scope">
							{{scope.row.nicheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="shhf" label="审核回复"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="sfsh" label="审核状态">
						<template slot-scope="scope">
							<span style="margin-right:10px" v-if="scope.row.sfsh=='是'">通过</span>
							<span style="margin-right:10px" v-if="scope.row.sfsh=='否'">未通过</span>
							<span style="margin-right:10px" v-if="scope.row.sfsh=='待审核'">待审核</span>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' v-if="isAuth('yinlexinxi','审核')" prop="sfsh" label="审核">
						<template slot-scope="scope">
							<el-button  type="text" size="small" @click="shDialog(scope.row)">审核</el-button>
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button :style='{"border":"2px solid #FFD6A2","cursor":"pointer","padding":"0 10px","margin":"0 4px 5px 0","outline":"none","color":"#f4bc75","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' v-if=" isAuth('yinlexinxi','查看')" type="success" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">详情</el-button>
							<el-button :style='{"border":"2px solid #FFD6A2","cursor":"pointer","padding":"0 10px","margin":"0 4px 5px 0","outline":"none","color":"#f4bc75","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' v-if=" isAuth('yinlexinxi','修改')" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>


							<el-button :style='{"border":"2px solid #FFD6A2","cursor":"pointer","padding":"0 10px","margin":"0 4px 5px 0","outline":"none","color":"#f4bc75","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('yinlexinxi','查看评论')" type="primary" size="mini" @click="disscussListHandler(scope.row.id)">查看评论</el-button>



							<el-button :style='{"border":"2px solid #FFD6A2","cursor":"pointer","padding":"0 10px","margin":"0 4px 5px 0","outline":"none","color":"#f4bc75","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('yinlexinxi','删除') " type="danger" size="mini" @click="deleteHandler(scope.row.id)">删除</el-button>
						</template>
					</el-table-column>
				</el-table>
				<el-pagination
					@size-change="sizeChangeHandle"
					@current-change="currentChangeHandle"
					:current-page="pageIndex"
					background
					:page-sizes="[10, 20, 30, 50]"
					:page-size="pageSize"
					:layout="layouts.join()"
					:total="totalPage"
					prev-text="<"
					next-text=">"
					:hide-on-single-page="false"
					:style='{"padding":"0","margin":"20px auto","whiteSpace":"nowrap","color":"#333","textAlign":"center","width":"90%","fontWeight":"500"}'
				></el-pagination>
			<!-- </div> -->
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>


		<el-dialog title="审核" :visible.sync="sfshVisiable" width="50%">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="审核状态">
					<el-select v-model="shForm.sfsh" placeholder="审核状态">
						<el-option label="通过" value="是"></el-option>
						<el-option label="不通过" value="否"></el-option>
						<el-option label="待审核" value="待审核"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="内容">
					<el-input type="textarea" :rows="8" v-model="shForm.shhf"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="shDialog">取 消</el-button>
				<el-button type="primary" @click="shHandler">确 定</el-button>
			</span>
		</el-dialog>



		<el-dialog
		  :visible.sync="chartVisiable1"
		  width="800">
			<div id="yinleleixingChart1" style="width:100%;height:600px;"></div>
		  <span slot="footer" class="dialog-footer">
			<el-button @click="chartDialog1">返回</el-button>
		  </span>
		</el-dialog>
		<el-dialog
		  :visible.sync="chartVisiable2"
		  width="800">
			<div id="singerChart2" style="width:100%;height:600px;"></div>
		  <span slot="footer" class="dialog-footer">
			<el-button @click="chartDialog2">返回</el-button>
		  </span>
		</el-dialog>
	</div>
</template>

<script>
//饼2
//音乐类型统计
//[]
import * as echarts from 'echarts'
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
export default {
  data() {
    return {
      singerOptions: [],
      yinleleixingOptions: [],
      searchForm: {
        key: ""
      },
      form:{},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      chartVisiable1: false,
      chartVisiable2: false,
      chartVisiable3: false,
      chartVisiable4: false,
      chartVisiable5: false,
      addOrUpdateFlag:false,
      layouts: ["total","prev","pager","next","sizes","jumper"],

    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {
  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
    }
  },
  components: {
    AddOrUpdate,
  },
  methods: {

    contentStyleChange() {
      this.contentPageStyleChange()
    },
    // 分页
    contentPageStyleChange(){
      let arr = []

      // if(this.contents.pageTotal) arr.push('total')
      // if(this.contents.pageSizes) arr.push('sizes')
      // if(this.contents.pagePrevNext){
      //   arr.push('prev')
      //   if(this.contents.pagePager) arr.push('pager')
      //   arr.push('next')
      // }
      // if(this.contents.pageJumper) arr.push('jumper')
      // this.layouts = arr.join()
      // this.contents.pageEachNum = 10
    },



//统计接口
    chartDialog1() {
      this.chartVisiable1 = !this.chartVisiable1;
      this.$nextTick(()=>{

        var yinleleixingChart1 = echarts.init(document.getElementById("yinleleixingChart1"),'macarons');
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
                        legend: {
                          orient: 'vertical',
                          left: 'left'
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
                yinleleixingChart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    yinleleixingChart1.resize();
                };
            }
        });
      })
    },

//统计接口
    chartDialog2() {
      this.chartVisiable2 = !this.chartVisiable2;
      this.$nextTick(()=>{

        var singerChart2 = echarts.init(document.getElementById("singerChart2"),'macarons');
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
                singerChart2.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    singerChart2.resize();
                };
            }
        });
      })
    },




    init () {
        this.sfshOptions = "是,否,待审核".split(',');
          this.$http({
            url: `option/geshou/geshou`,
            method: "get"
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.singerOptions = data.data;
            } else {
              this.$message.error(data.msg);
            }
          });
          this.$http({
            url: `option/yinleleixing/yinleleixing`,
            method: "get"
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.yinleleixingOptions = data.data;
            } else {
              this.$message.error(data.msg);
            }
          });
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
        order: 'desc',
      }
           if(this.searchForm.songname!='' && this.searchForm.songname!=undefined){
            params['songname'] = '%' + this.searchForm.songname + '%'
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
           if(this.searchForm.singer!='' && this.searchForm.singer!=undefined){
            params['singer'] = this.searchForm.singer
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
           if(this.searchForm.yinleleixing!='' && this.searchForm.yinleleixing!=undefined){
            params['yinleleixing'] = this.searchForm.yinleleixing
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
           if(this.searchForm.zhuanji!='' && this.searchForm.zhuanji!=undefined){
            params['zhuanji'] = '%' + this.searchForm.zhuanji + '%'
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
          if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
            params['sfsh'] = this.searchForm.sfsh
          }
      this.$http({
        url: "yinlexinxi/page",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    // 查看评论
    disscussListHandler(id,type) {
	this.$router.push({path:'/discussyinlexinxi',query:{refid:id}});
    },
    // 审核窗口
    shDialog(row){
      this.sfshVisiable = !this.sfshVisiable;
      if(row){
        this.shForm = {
          songname: row.songname,
          singer: row.singer,
          yinleleixing: row.yinleleixing,
          fengmian: row.fengmian,
          bianqu: row.bianqu,
          tianci: row.tianci,
          yuyan: row.yuyan,
          zhuanji: row.zhuanji,
          shizhang: row.shizhang,
          faxingfang: row.faxingfang,
          faxingriqi: row.faxingriqi,
          geci: row.geci,
          songfile: row.songfile,
          zhanghao: row.zhanghao,
          nicheng: row.nicheng,
          sfsh: row.sfsh,
          shhf: row.shhf,
          id: row.id
        }
      }
    },
    // 审核
    shHandler(){
      this.$confirm(`确定操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "yinlexinxi/update",
          method: "post",
          data: this.shForm
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
                this.shDialog()
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    // 下载
    download(file){
      window.open(`${file}`)
    },
    // 删除
    deleteHandler(id) {
      var ids = id
        ? [Number(id)]
        : this.dataListSelections.map(item => {
            return Number(item.id);
          });
      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "yinlexinxi/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },


  }

};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
	  .el-date-editor.el-input {
	    width: auto;
	  }
	}
	
	.el-input {
	  width: auto;
	}
	
	// form
	.center-form-pv .el-input /deep/ .el-input__inner {
				border: 2px solid;
				border-radius: 30px;
				padding: 0 12px;
				outline: none;
				color: rgba(253, 199, 118, 1);
				width: 170px;
				font-size: 14px;
				border-color: rgba(253, 199, 118, 1);
				height: 40px;
			}
	
	.center-form-pv .el-select /deep/ .el-input__inner {
				border: 2px solid;
				border-radius: 30px;
				padding: 0 10px;
				outline: none;
				color: rgba(253, 199, 118, 1);
				width: 170px;
				font-size: 14px;
				border-color: rgba(253, 199, 118, 1);
				height: 40px;
			}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
				border: 2px solid;
				border-radius: 30px;
				padding: 0 10px 0 30px;
				outline: none;
				color: rgba(253, 199, 118, 1);
				width: 170px;
				font-size: 14px;
				border-color: rgba(253, 199, 118, 1);
				height: 40px;
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: #333;
				font-weight: 500;
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 12px 0;
				background: rgba(255, 214, 162, 1);
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 10px;
				word-wrap: normal;
				word-break: break-all;
				white-space: normal;
				font-weight: bold;
				display: inline-block;
				vertical-align: middle;
				width: 100%;
				line-height: 24px;
				position: relative;
				text-overflow: ellipsis;
			}

	
	.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 2px 0;
				color: #333;
				background: #fff;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}
	
		.el-table /deep/ .el-table__body-wrapper tbody tr.el-table__row--striped td {
		background: #FAFAFA;
	}
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 2px 0;
				color: #333;
				background: #fff4e6;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 2px 0;
				color: #333;
				background: #fff;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 10px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}

	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
			}

	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: rgba(255, 214, 162, 1);
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #FFF;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: rgba(255, 214, 162, 1);
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				width: 100px;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: #606266;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				display: inline-block;
				width: 50px;
				font-size: 14px;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
</style>
