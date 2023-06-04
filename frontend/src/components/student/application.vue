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
                    <td>报名状态</td>
                    <td>费用</td>
                    <td>支付状态</td>
                    <td>报名操作</td>
                </tr>
                <tr>
                    <td>六级口语</td>
                    <td>
                        <span v-if = "this.applyData[0].jstate == 2 || this.applyData[0].jstate == 1">已报名</span>
                        <span v-if = "this.applyData[0].jstate == 0">未报名</span>
                    </td>
                    <td>
                        <span>{{this.applyData[0].famount}}</span>
                    </td>
                    <td>
                        <span v-if = "this.applyData[0].jstate == 0">未报名</span>
                        <span v-if = "this.applyData[0].jstate == 1" @click="nowSubject = 1;pay()">点击支付</span>
                        <span v-if = "this.applyData[0].jstate == 2">已支付</span>
                    </td>
                    <td>
                        <span v-if = "this.applyData[0].jstate == 0" @click="nowSubject = 1;toApply = true">报名</span>
                        <span v-if = "this.applyData[0].jstate == 1" @click="nowSubject = 1;toCancel = true">撤销报名</span>
                        <span v-if = "this.applyData[0].jstate == 2">已支付，无法撤销</span>
                    </td>
                </tr>
                <tr>
                    <td>六级笔试</td>
                    <td>
                        <span v-if = "this.applyData[1].jstate == 2 || this.applyData[1].jstate == 1">已报名</span>
                        <span v-if = "this.applyData[1].jstate == 0">未报名</span>
                    </td>
                    <td>
                        <span>{{this.applyData[1].famount}}</span>
                    </td>
                    <td>
                        <span v-if = "this.applyData[1].jstate == 0">未报名</span>
                        <span v-if = "this.applyData[1].jstate == 1">未支付</span>
                        <span v-if = "this.applyData[1].jstate == 2">已支付</span>
                    </td>
                    <td>
                        <span v-if = "this.applyData[1].jstate == 0" @click="nowSubject = 2;toApply = true">报名</span>
                        <span v-if = "this.applyData[1].jstate == 1" @click="nowSubject = 2;toCancel = true">撤销报名</span>
                        <span v-if = "this.applyData[1].jstate == 2">已支付，无法撤销</span>
                    </td>
                </tr>
            </table>
        </div>
        <el-dialog title='提交报名信息' center :visible.sync='toApply'>
            确定要报名<span v-if = "nowSubject == 1">六级口语</span><span v-if = "nowSubject == 2">六级笔试</span>考试吗？<br/>
            <el-button @click = "toApply = false;apply()">确定</el-button>
            <el-button @click = "toApply = false">取消</el-button>
        </el-dialog>
        <el-dialog title='报名成功' center :visible.sync='succApply'>
            您已成功报名<span v-if = "nowSubject == 1">六级口语</span><span v-if = "nowSubject == 2">六级笔试</span>考试！<br/>
            <el-button @click = "succApply = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='报名失败' center :visible.sync='failApply'>
            报名<span v-if = "nowSubject == 1">六级口语</span><span v-if = "nowSubject == 2">六级笔试</span>考试失败！<br/>
            <el-button @click = "failApply = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='撤销报名' center :visible.sync='toCancel'>
            确定要撤销对<span v-if = "nowSubject == 1">六级口语</span><span v-if = "nowSubject == 2">六级笔试</span>考试的报名吗？<br/>
            <el-button @click = "toCancel = false;Cancel()">确定</el-button>
            <el-button @click = "toCancel = false">取消</el-button>
        </el-dialog>
        <el-dialog title='撤销成功' center :visible.sync='succCancel'>
            您已成功撤销对<span v-if = "nowSubject == 1">六级口语</span><span v-if = "nowSubject == 2">六级笔试</span>考试的报名！<br/>
            <el-button @click = "succCancel = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='撤销失败' center :visible.sync='failCancel'>
            对<span v-if = "nowSubject == 1">六级口语</span><span v-if = "nowSubject == 2">六级笔试</span>考试的报名撤销失败！<br/>
            <el-button @click = "failCancel = false">关闭</el-button>
        </el-dialog>
    </div>
  </template>
  
  <script>
  
  export default {
    data() {
      return {
        nowSubject: 0,
        toApply: false,
        succApply: false,
        failApply: false,
        toCancel: false,
        succCancel: false,
        failCancel: false,
        userInfo: {
            uname : 'xuechen',
            uuid : 1,
            urole: 0,
        },
        userData: {uuid:null,suniversity : null,scollege:null,uname:null,usex:null,udoctype:null,udocno:null,
        sgrade:null,sclass:null,smajority:null,sno:null,ubirth:null,utype:null},

        applyData: [
            {jstate : 1,famount:30},
            {jstate : 0,famount:25}
        ]
      }
    },
    created() { //cookies都没加
        //this.getCookies()
        this.getUserData()
        //this.getApplyData()
    },
    methods: {
        getCookies() {  //获取cookie
            this.userInfo.name = this.$cookies.get("uname")
            this.userInfo.id = this.$cookies.get("uuid")
            this.userInfo.role = this.$cookies.get("urole")
        },
        getUserData(){
            console.log(this.userInfo.uname)
            console.log(this.userInfo.uuid)
            this.$axios({
                url: 'http://127.0.0.1:8081/api/studentData',
                method: 'post',
                data: this.userInfo
            }).then(res => {
                this.userData = res.data.data
            })
        },
        getApplyData(){
            this.$axios({url: '/api/score',method: 'post',data: this.userInfo.id}).then(res => {
                this.applyData = res.data.data
            })
        },
        apply(){
            this.$axios({url: '/api/score',method: 'post',data: {uuid:this.userInfo.id, apply:this.nowSubject}}).then(res => {
                if(res.data.data){
                    getApplyData()
                    succApply = true
                }
                else{
                    failApply = true
                }
            })
        },
        cancel(){
            this.$axios({url: '/api/score',method: 'post',data: {uuid:this.userInfo.id, apply:this.nowSubject}}).then(res => {
                if(res.data.data){
                    getApplyData()
                    succCancel = true
                }
                else{
                    failCancel = true
                }
            })
        },
        pay(){
            this.$router.push({path: '/payment', query: {nowSubject: this.nowSubject}})
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
  