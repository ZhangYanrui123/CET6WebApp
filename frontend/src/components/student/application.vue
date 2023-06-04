<template>
    <div class="application">
        <ul class="top">
            <span class="order">报名信息</span> 
        </ul>
        <div class = "tables">
            <table class="userData" border = "1">
                <tr>
                    <td>报名学校：</td>
                    <td colspan="5"><span>{{this.userData.suniversity}}</span></td>
                </tr>
                <tr>
                    <td>所属学院：</td>
                    <td colspan="5"><span>{{this.userData.college}}</span></td>
                </tr>
                <tr>
                    <td colspan="6"></td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td class = "double"><span>{{this.userData.uname}}</span></td>
                    <td>性别：</td>
                    <td class = "double"><span>{{this.userData.usex}}</span></td>
                </tr>
                <tr>
                    <td>证件类型：</td>
                    <td class = "double"><span>{{this.userData.udoctype}}</span></td>
                    <td>证件号码：</td>
                    <td class = "double"><span>{{this.userData.udocno}}</span></td>
                </tr>
                <tr>
                    <td>年级：</td>
                    <td class = "double"><span>{{this.userData.sgrade}}</span></td>
                    <td>班级：</td>
                    <td class = "double"><span>{{this.userData.sclass}}</span></td>
                </tr>
                <tr>
                    <td>专业：</td>
                    <td class = "double"><span>{{this.userData.smajority}}</span></td>
                    <td>学号：</td>
                    <td class = "double"><span>{{this.userData.sno}}</span></td>
                </tr>
            </table>
            <table class="applyData" border = "1">
                <tr>
                    <td>科目</td>
                    <td>开始时间</td>
                    <td>结束时间</td>
                    <td>报名状态</td>
                    <td>费用</td>
                    <td>支付状态</td>
                    <td>报名操作</td>
                </tr>
                <tr v-for="(item,index) in this.applyData" :key="index">
                    <td>
                        <span v-if = "item.esubject == 1">六级笔试</span>
                        <span v-if = "item.esubject == 2">六级口语</span>
                    </td>
                    <td>
                        <span>{{item.ebegin}}</span>
                    </td>
                    <td>
                        <span>{{item.eend}}</span>
                    </td>
                    <td>
                        <span v-if = "item.jstate == 0">未报名</span>
                        <span v-if = "item.jstate == 1||item.jstate == 2">已报名</span>
                    </td>
                    <td>
                        <span>{{this.item.famount}}</span>
                    </td>
                    <td>
                        <span v-if = "item.jstate == 0">未报名</span>
                        <el-button v-if = "item.jstate == 1" @click="nowExam = index;pay()">点击支付</el-button>
                        <span v-if = "item.jstate == 2">已支付</span>
                    </td>
                    <td>
                        <el-button v-if = "item.jstate == 0" @click="nowExam = index;toApply = true">报名</el-button>
                        <el-button v-if = "item.jstate == 1" @click="nowExam = index;toCancel = true">撤销报名</el-button>
                        <span v-if = "item.jstate == 2">已支付，无法撤销</span>
                    </td>
                </tr>
            </table>
        </div>
        <el-dialog title='提交报名信息' center :visible.sync='toApply'>
            确定要报名<span v-if = "applyData[nowExam].eid == 1">六级口语</span><span v-if = "applyData[nowExam].eid == 2">六级笔试</span>考试吗？<br/>
            <el-button @click = "toApply = false;apply()">确定</el-button>
            <el-button @click = "toApply = false">取消</el-button>
        </el-dialog>
        <el-dialog title='报名成功' center :visible.sync='succApply'>
            您已成功报名<span v-if = "applyData[nowExam].eid == 1">六级口语</span><span v-if = "applyData[nowExam].eid == 2">六级笔试</span>考试！<br/>
            <el-button @click = "succApply = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='报名失败' center :visible.sync='failApply'>
            报名<span v-if = "applyData[nowExam].eid == 1">六级口语</span><span v-if = "applyData[nowExam].eid == 2">六级笔试</span>考试失败！<br/>
            <el-button @click = "failApply = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='撤销报名' center :visible.sync='toCancel'>
            确定要撤销对<span v-if = "applyData[nowExam].eid == 1">六级口语</span><span v-if = "applyData[nowExam].eid == 2">六级笔试</span>考试的报名吗？<br/>
            <el-button @click = "toCancel = false;Cancel()">确定</el-button>
            <el-button @click = "toCancel = false">取消</el-button>
        </el-dialog>
        <el-dialog title='撤销成功' center :visible.sync='succCancel'>
            您已成功撤销对<span v-if = "applyData[nowExam].eid == 1">六级口语</span><span v-if = "applyData[nowExam].eid == 2">六级笔试</span>考试的报名！<br/>
            <el-button @click = "succCancel = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='撤销失败' center :visible.sync='failCancel'>
            对<span v-if = "applyData[nowExam].eid == 1">六级口语</span><span v-if = "applyData[nowExam].eid == 2">六级笔试</span>考试的报名撤销失败！<br/>
            <el-button @click = "failCancel = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='付款成功' center :visible.sync='succPay'>
            您已成功付款！
            <el-button @click = "succPay = false">关闭</el-button>
        </el-dialog>
    </div>
  </template>
  
  <script>
  
  export default {
    data() {
      return {
        nowExam: 0,
        toApply: false,
        succApply: false,
        failApply: false,
        toCancel: false,
        succCancel: false,
        failCancel: false,
        succPay:false,
        user: {
            uname : null,
            uuid : null
        },
        userData: {},
        applyData: []
      }
    },
    created() { 
        getCookies()
        getUserData()
        getApplyData()
    },
    methods: {
        getCookies() {  //获取cookie
            this.user.uname = this.$cookies.get("uname")
            this.user.uuid = this.$cookies.get("uuid")
        },
        getUserData(){
            //获取用户信息，需要学生用户除了密码和qstate的所有属性
            this.$axios({url: '/api/score',method: 'post',data: this.user.id}).then(res => { 
                this.userData = res.data.data
            })
        },
        getApplyData(){
            //获取用户可报名信息，需要考试eid，科目，开始时间，结束时间，报名状态和金额，
            this.$axios({url: '/api/score',method: 'post',data: this.user.id}).then(res => { 
                this.applyData = res.data.data
            })
        },
        apply(){
            //提交用户报名信息，包含id和eid
            this.$axios({url: '/api/score',method: 'post',data: {uuid:this.user.id, eid:this.applyData[nowExam].eid}}).then(res => {
                if(res.data.data){
                    getApplyData()
                    this.succApply = true
                }
                else{
                    this.failApply = true
                }
            })
        },
        cancel(){
            //提交用户撤销报名信息，包含id和eid
            this.$axios({url: '/api/score',method: 'post',data: {uuid:this.user.id, eid:this.applyData[nowExam].eid}}).then(res => {
                if(res.data.data){
                    getApplyData()
                    this.succCancel = true
                }
                else{
                    this.failCancel = true
                }
            })
        },
        pay(){
            //进入交费页面
            //this.$router.push({path: '/payment', query: {applyData[nowExam].eid: this.applyData[nowExam].eid}})
            //提交用户交费信息，成功就完事了
            this.$axios({url: '/api/score',method: 'post',data: {uuid:this.user.id, eid:this.applyData[nowExam].eid}}).then(res => {
                if(res.data.data){
                    getApplyData()
                    this.succPay = true
                }
                else{
                }
            })
        }
    }
  }
  </script>
  
  <style lang="less" scoped>
  ul{
    list-style-type:none
  }
  .application .top {
    border-bottom: 1px solid #eee;
    margin-bottom: 20px;
    display: flex;
  }
  .application .top .order {
    margin: 20px;
  }
  .application {
    width: 90%;
    height: 530px;
    margin: 0 auto;
    background-color: #dda6cb17;
  }
  .application .tables{
    display: flex;
    flex-direction:column;
    align-items: center; 
    justify-content: center;
  }
  .application .tables table{
    border-collapse: collapse;
  }
  .tables * {
  margin: 20px 0;
}
  .application .tables td{
    height : 30px;
    width : 120px;
  }
  .application .tables .double{
    width : 240px;
  }
  </style>
  