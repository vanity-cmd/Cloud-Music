<template>
<div :style='{"padding":"20px 20px 40px","margin":"10px auto 0","borderColor":"#52b646","borderRadius":"12px","background":"#fff","borderWidth":"3px","width":"1200px","position":"relative","borderStyle":"solid dashed double double"}'>
    <el-form
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="80px"
    >
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="歌名" prop="songname">
            <el-input v-model="ruleForm.songname" 
                placeholder="歌名" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}'  label="歌手" prop="singer">
            <el-select v-model="ruleForm.singer" placeholder="请选择歌手"  >
              <el-option
                  v-for="(item,index) in singerOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}'  label="音乐类型" prop="yinleleixing">
            <el-select v-model="ruleForm.yinleleixing" placeholder="请选择音乐类型"  >
              <el-option
                  v-for="(item,index) in yinleleixingOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="封面" v-if="type!='cross' || (type=='cross' && !ro.fengmian)" prop="fengmian">
            <file-upload
            tip="点击上传封面"
            action="file/upload"
            :limit="3"
            :multiple="true"
            :fileUrls="ruleForm.fengmian?ruleForm.fengmian:''"
            @change="fengmianUploadChange"
            ></file-upload>
          </el-form-item>
            <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' class="upload" v-else label="封面" prop="fengmian">
                <img v-if="ruleForm.fengmian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian.split(',')[0]" width="100" height="100">
                <img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.fengmian.split(',')" :src="baseUrl+item" width="100" height="100">
            </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="编曲" prop="bianqu">
            <el-input v-model="ruleForm.bianqu" 
                placeholder="编曲" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="填词" prop="tianci">
            <el-input v-model="ruleForm.tianci" 
                placeholder="填词" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="语言" prop="yuyan">
            <el-input v-model="ruleForm.yuyan" 
                placeholder="语言" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="专辑" prop="zhuanji">
            <el-input v-model="ruleForm.zhuanji" 
                placeholder="专辑" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="时长" prop="shizhang">
            <el-input v-model="ruleForm.shizhang" 
                placeholder="时长" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="发行方" prop="faxingfang">
            <el-input v-model="ruleForm.faxingfang" 
                placeholder="发行方" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="发行日期" prop="faxingriqi" >
              <el-date-picker
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd"
                  v-model="ruleForm.faxingriqi" 
                  type="date"
                  placeholder="发行日期">
              </el-date-picker> 
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="音乐文件" prop="songfile">
            <file-upload
            tip="点击上传音乐文件"
            action="file/upload"
            :limit="1"
            :multiple="true"
            :fileUrls="ruleForm.songfile?ruleForm.songfile:''"
            @change="songfileUploadChange"
            ></file-upload>
          </el-form-item>  
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="账号" prop="zhanghao">
            <el-input v-model="ruleForm.zhanghao" 
                placeholder="账号" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="昵称" prop="nicheng">
            <el-input v-model="ruleForm.nicheng" 
                placeholder="昵称" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="歌词" prop="geci">
            <editor 
                :style='{"padding":"0","boxShadow":"0 0 0px rgba(75,223,201,.5)","margin":"0","borderColor":"#ccc","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"auto"}'
                v-model="ruleForm.geci" 
                class="editor" 
                action="file/upload">
            </editor>
          </el-form-item>

      <el-form-item :style='{"padding":"0","margin":"0"}'>
        <el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"4px","background":"radial-gradient(circle, rgba(132,218,110,1) 0%, rgba(63,187,33,1) 81%, rgba(82,182,70,1) 100%)","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}'  type="primary" @click="onSubmit">提交</el-button>
        <el-button :style='{"border":"1px solid #52b646","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#52b646","borderRadius":"4px","background":"#ecfaea","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' @click="back()">返回</el-button>
      </el-form-item>
    </el-form>
</div>
</template>

<script>
  export default {
    data() {
      return {
        id: '',
        baseUrl: '',
        ro:{
            songname : false,
            singer : false,
            yinleleixing : false,
            fengmian : false,
            bianqu : false,
            tianci : false,
            yuyan : false,
            zhuanji : false,
            shizhang : false,
            faxingfang : false,
            faxingriqi : false,
            geci : false,
            songfile : false,
            zhanghao : false,
            nicheng : false,
            sfsh : false,
            shhf : false,
            clicktime : false,
        },
        type: '',
        userTableName: localStorage.getItem('UserTableName'),
        ruleForm: {
          songname: '',
          singer: '',
          yinleleixing: '',
          fengmian: '',
          bianqu: '',
          tianci: '',
          yuyan: '',
          zhuanji: '',
          shizhang: '',
          faxingfang: '',
          faxingriqi: '',
          geci: '',
          songfile: '',
          zhanghao: '',
          nicheng: '',
          clicktime: '',
        },
        singerOptions: [],
        yinleleixingOptions: [],
        rules: {
          songname: [
            { required: true, message: '歌名不能为空', trigger: 'blur' },
          ],
          singer: [
            { required: true, message: '歌手不能为空', trigger: 'blur' },
          ],
          yinleleixing: [
            { required: true, message: '音乐类型不能为空', trigger: 'blur' },
          ],
          fengmian: [
          ],
          bianqu: [
          ],
          tianci: [
          ],
          yuyan: [
          ],
          zhuanji: [
          ],
          shizhang: [
          ],
          faxingfang: [
          ],
          faxingriqi: [
          ],
          geci: [
          ],
          songfile: [
            { required: true, message: '音乐文件不能为空', trigger: 'blur' },
          ],
          zhanghao: [
          ],
          nicheng: [
          ],
          sfsh: [
          ],
          shhf: [
          ],
          clicktime: [
          ],
        },
      };
    },
    computed: {



    },
    components: {
    },
    created() {
	  //this.bg();
      let type = this.$route.query.type ? this.$route.query.type : '';
      this.init(type);
      this.baseUrl = this.$config.baseUrl;
      this.ruleForm.faxingriqi = this.getCurDate()
    },
    methods: {
      getMakeZero(s) {
          return s < 10 ? '0' + s : s;
      },
      // 下载
      download(file){
        window.open(`${file}`)
      },
      // 初始化
      init(type) {
        this.type = type;
        if(type=='cross'){
          var obj = JSON.parse(localStorage.getItem('crossObj'));
          for (var o in obj){
            if(o=='songname'){
              this.ruleForm.songname = obj[o];
              this.ro.songname = true;
              continue;
            }
            if(o=='singer'){
              this.ruleForm.singer = obj[o];
              this.ro.singer = true;
              continue;
            }
            if(o=='yinleleixing'){
              this.ruleForm.yinleleixing = obj[o];
              this.ro.yinleleixing = true;
              continue;
            }
            if(o=='fengmian'){
              this.ruleForm.fengmian = obj[o].split(",")[0];
              this.ro.fengmian = true;
              continue;
            }
            if(o=='bianqu'){
              this.ruleForm.bianqu = obj[o];
              this.ro.bianqu = true;
              continue;
            }
            if(o=='tianci'){
              this.ruleForm.tianci = obj[o];
              this.ro.tianci = true;
              continue;
            }
            if(o=='yuyan'){
              this.ruleForm.yuyan = obj[o];
              this.ro.yuyan = true;
              continue;
            }
            if(o=='zhuanji'){
              this.ruleForm.zhuanji = obj[o];
              this.ro.zhuanji = true;
              continue;
            }
            if(o=='shizhang'){
              this.ruleForm.shizhang = obj[o];
              this.ro.shizhang = true;
              continue;
            }
            if(o=='faxingfang'){
              this.ruleForm.faxingfang = obj[o];
              this.ro.faxingfang = true;
              continue;
            }
            if(o=='faxingriqi'){
              this.ruleForm.faxingriqi = obj[o];
              this.ro.faxingriqi = true;
              continue;
            }
            if(o=='geci'){
              this.ruleForm.geci = obj[o];
              this.ro.geci = true;
              continue;
            }
            if(o=='songfile'){
              this.ruleForm.songfile = obj[o];
              this.ro.songfile = true;
              continue;
            }
            if(o=='zhanghao'){
              this.ruleForm.zhanghao = obj[o];
              this.ro.zhanghao = true;
              continue;
            }
            if(o=='nicheng'){
              this.ruleForm.nicheng = obj[o];
              this.ro.nicheng = true;
              continue;
            }
            if(o=='clicktime'){
              this.ruleForm.clicktime = obj[o];
              this.ro.clicktime = true;
              continue;
            }
          }
        }
        // 获取用户信息
        this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            var json = res.data.data;
            if((json.zhanghao!=''&&json.zhanghao) || json.zhanghao==0){
                this.ruleForm.zhanghao = json.zhanghao
            }
            if((json.nicheng!=''&&json.nicheng) || json.nicheng==0){
                this.ruleForm.nicheng = json.nicheng
            }
          }
        });
        this.$http.get('option/geshou/geshou', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.singerOptions = res.data.data;
          }
        });
        this.$http.get('option/yinleleixing/yinleleixing', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.yinleleixingOptions = res.data.data;
          }
        });
      },

    // 多级联动参数
      // 多级联动参数
      info(id) {
        this.$http.get('yinlexinxi/detail/${id}', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.ruleForm = res.data.data;
          }
        });
      },
      // 提交
      onSubmit() {

        //更新跨表属性
        var crossuserid;
        var crossrefid;
        var crossoptnum;
        this.$refs["ruleForm"].validate(valid => {
          if(valid) {
            if(this.type=='cross'){
                 var statusColumnName = localStorage.getItem('statusColumnName');
                 var statusColumnValue = localStorage.getItem('statusColumnValue');
                 if(statusColumnName && statusColumnName!='') {
                     var obj = JSON.parse(localStorage.getItem('crossObj'));
                     if(!statusColumnName.startsWith("[")) {
                         for (var o in obj){
                             if(o==statusColumnName){
                                 obj[o] = statusColumnValue;
                             }
                         }
                         var table = localStorage.getItem('crossTable');
                         this.$http.post(table+'/update', obj).then(res => {});
                     } else {
                            crossuserid=Number(localStorage.getItem('userid'));
                            crossrefid=obj['id'];
                            crossoptnum=localStorage.getItem('statusColumnName');
                            crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
                     }
                 }
            }
            if(crossrefid && crossuserid) {
                 this.ruleForm.crossuserid=crossuserid;
                 this.ruleForm.crossrefid=crossrefid;
                 var params = {
                     page: 1,
                     limit: 10,
                     crossuserid:crossuserid,
                     crossrefid:crossrefid,
                 }
                 this.$http.get('yinlexinxi/list', {
                  params: params
                 }).then(res => {
                     if(res.data.data.total>=crossoptnum) {
                         this.$message({
                          message: localStorage.getItem('tips'),
                          type: 'success',
                          duration: 1500,
                         });
                          return false;
                     } else {
                         // 跨表计算


                          this.$http.post('yinlexinxi/add', this.ruleForm).then(res => {
                              if (res.data.code == 0) {
                                  this.$message({
                                      message: '操作成功',
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                          this.$router.go(-1);
                                      }
                                  });
                              } else {
                                  this.$message({
                                      message: res.data.msg,
                                      type: 'error',
                                      duration: 1500
                                  });
                              }
                          });
                     }
                 });
             } else {


                  this.$http.post('yinlexinxi/add', this.ruleForm).then(res => {
                     if (res.data.code == 0) {
                          this.$message({
                              message: '操作成功',
                              type: 'success',
                              duration: 1500,
                              onClose: () => {
                                  this.$router.go(-1);
                              }
                          });
                      } else {
                          this.$message({
                              message: res.data.msg,
                              type: 'error',
                              duration: 1500
                          });
                      }
                  });
             }
          }
        });
      },
      // 获取uuid
      getUUID () {
        return new Date().getTime();
      },
      // 返回
      back() {
        this.$router.go(-1);
      },
      fengmianUploadChange(fileUrls) {
          this.ruleForm.fengmian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
      },
      songfileUploadChange(fileUrls) {
          this.ruleForm.songfile = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
      },
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
	  padding: 0 10px 0 0;
	  color: #666;
	  font-weight: 500;
	  width: 80px;
	  font-size: 14px;
	  line-height: 40px;
	  text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
	  margin-left: 80px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
	  border: 1px solid #88d67e;
	  border-radius: 4px;
	  padding: 0 12px;
	  box-shadow: inset 0px 0px 48px 0px #def5db;
	  outline: none;
	  color: #666;
	  width: 400px;
	  font-size: 14px;
	  height: 40px;
	}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
	  border: 1px solid #88d67e;
	  border-radius: 4px;
	  padding: 0 10px;
	  box-shadow: inset 0px 0px 48px 0px #def5db;
	  outline: none;
	  color: #666;
	  width: 200px;
	  font-size: 14px;
	  height: 40px;
	}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
	  border: 1px solid #88d67e;
	  border-radius: 4px;
	  padding: 0 10px 0 30px;
	  box-shadow: inset 0px 0px 48px 0px #def5db;
	  outline: none;
	  color: #666;
	  width: 200px;
	  font-size: 14px;
	  height: 40px;
	}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
	  border: 1px solid #88d67e;
	  cursor: pointer;
	  border-radius: 6px;
	  box-shadow: inset 0px 0px 48px 0px #def5db;
	  color: #88d67e;
	  width: 200px;
	  font-size: 32px;
	  line-height: 120px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
	  border: 1px solid #88d67e;
	  cursor: pointer;
	  border-radius: 6px;
	  box-shadow: inset 0px 0px 48px 0px #def5db;
	  color: #88d67e;
	  width: 200px;
	  font-size: 32px;
	  line-height: 120px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
	  border: 1px solid #88d67e;
	  cursor: pointer;
	  border-radius: 6px;
	  box-shadow: inset 0px 0px 48px 0px #def5db;
	  color: #88d67e;
	  width: 200px;
	  font-size: 32px;
	  line-height: 120px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
	  border: 1px solid #88d67e;
	  border-radius: 4px;
	  padding: 12px;
	  box-shadow: inset 0px 0px 48px 0px #def5db;
	  outline: none;
	  color: #666;
	  width: 400px;
	  font-size: 14px;
	  height: 120px;
	}
</style>
