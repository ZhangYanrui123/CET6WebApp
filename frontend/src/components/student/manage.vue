<template>
    <div class="manage">
        <ul class="top">
            <span class="order">个人信息</span> 
        </ul>
        <div class = "tables">
            <table class="userData">
                <tr>
                    <td>用户类型：</td>
                    <td colspan="5"><span>{{this.userData.utype}}</span></td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td class = "double"><span>{{this.userData.uname}}</span></td>
                    <td>性别：</td>
                    <td class = "double"><span>{{this.userData.usex}}</span></td>
                </tr>
                <tr>
                    <td>生日：</td>
                    <td colspan="5"><span>{{this.userData.ubirth}}</span></td>
                </tr>
                <tr>
                    <td>联系方式：</td>
                    <td class = "double"><span>{{this.userData.utel}}</span></td>
                    <td>邮箱：</td>
                    <td class = "double"><span>{{this.userData.umail}}</span></td>
                </tr>
                <tr>
                    <td>证件类型：</td>
                    <td class = "double"><span>{{this.userData.udoctype}}</span></td>
                    <td>证件号码：</td>
                    <td class = "double"><span>{{this.userData.udocno}}</span></td>
                </tr>
                <tr>
                    <td>所属学校：</td>
                    <td colspan="5"><span>{{this.userData.suniversity}}</span></td>
                </tr>
                <tr>
                    <td>所属学院：</td>
                    <td colspan="5"><span>{{this.userData.college}}</span></td>
                </tr>
                <tr>
                    <td>专业：</td>
                    <td class = "double"><span>{{this.userData.smajority}}</span></td>
                    <td>学号：</td>
                    <td class = "double"><span>{{this.userData.sno}}</span></td>
                </tr>
                <tr>
                    <td>年级：</td>
                    <td class = "double"><span>{{this.userData.sgrade}}</span></td>
                    <td>班级：</td>
                    <td class = "double"><span>{{this.userData.sclass}}</span></td>
                </tr>
            </table>
        </div>
        <div class = "buttons">
                <el-button @click = "nowEdit=1;editUser = true">修改信息</el-button> 
                <el-button @click = "nowEdit=2;editPassword = true">修改密码</el-button>
        </div>
        <el-dialog title='修改信息' center :visible.sync='editUser'>
            <el-form label-width="70px">
                <el-form-item label="姓名">
                    <el-input v-model="editData.uname" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="editData.usex">
                        <el-radio :label="true">男</el-radio>
                        <el-radio :label="false">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="生日">
                    <el-date-picker v-model="editData.ubirth" type="date" placeholder="请输入内容" value-format="yyyy-mm-dd"></el-date-picker>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-input v-model="editData.utel" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱">
                    <el-input v-model="editData.umail" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="证件类型">
                    <el-select v-model="editData.udoctype">
                        <el-option label="身份证" value="0"></el-option>
                        <el-option label="护照" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="证件号码">
                    <el-input v-model="editData.udocno" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="大学名称">
                    <el-input v-model="editData.suniversity" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="学院名称">
                    <el-input v-model="editData.scollege" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="专业名称">
                    <el-input v-model="editData.smajority" placeholder="请输入内容"></el-input>
                </el-form-item> 
                <el-form-item label="学号">
                    <el-input v-model="editData.sno" type="number" onKeypress="return(/^[0-9]*$/.test(String.fromCharCode(event.keyCode)))" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="班级">
                    <el-input v-model="editData.sclass" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="年级">
                    <el-date-picker v-model="editData.sgrade" type="year" placeholder="请输入内容" value-format="yyyy"></el-date-picker>
                </el-form-item>
            </el-form>
            <el-button @click = "editUser = false;edit()">确定</el-button>
            <el-button @click = "editUser = false">取消</el-button>
        </el-dialog>
        <el-dialog title='修改成功' center :visible.sync='success'>
            修改<span v-if = "nowEdit == 1">信息</span><span v-if = "nowEdit == 2">密码</span>成功！<br/>
            <el-button @click = "success = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='修改失败' center :visible.sync='fail'>
            修改<span v-if = "nowEdit == 1">信息</span><span v-if = "nowEdit == 2">密码</span>失败！<br/>
            <el-button @click = "fail = false">关闭</el-button>
        </el-dialog>
        <el-dialog title='修改密码' center :visible.sync='editPassword'>
            <el-input v-model="upwd" type="password" placeholder="原密码"> </el-input>
            <el-input v-model="password" type="password" placeholder="新密码"> </el-input>
            <el-button @click = "editPassword = false;passwordEdit()">确定</el-button>
            <el-button @click = "editPassword = false">取消</el-button>
        </el-dialog>
    </div>
  </template>
  
  <script>
  
  import axios from "axios";

  export default {
    data() {
      return {
        nowEdit: 0,
        success: false,
        fail: false,
        editUser:false,
        editPassword:false,
        userInfo: {
            name : 'xuechen',
            id : 0,
            role: 0,
        },
        userData: {uuid:null,suniversity : "nku",scollege:"jn",uname:"name",usex:"sex",udoctype:"sfz",udocno:"xxxxxxxxxxxxxxxxxx",
        sgrade:"20",sclass:"2",smajority:"jsjkxyjs",sno:"2011111",ubirth:"2023/6/4",usex:true,},
        editData: {uuid:null,suniversity : "nku",scollege:"jn",uname:"name",usex:"sex",udoctype:"sfz",udocno:"xxxxxxxxxxxxxxxxxx",
        sgrade:"20",sclass:"1",smajority:"jsjkxyjs",sno:"2011111",ubirth:"2023/6/4",usex:true,},
        password:null,
        upwd:null
      }
    },
<<<<<<< HEAD
    created() { 
        //this.getCookies()
        //this.getUserData()
=======
    created() { //cookies都没加
        this.getCookies()
        console.log('vue created')
        console.log(this.userInfo.name)
        this.getUserData()
>>>>>>> 62fad54ac64d8d3ba35e0e55ed3fa4265be44589
        this.resetEditData()
    },
    methods: {
        getCookies() {  //获取cookie
            this.userInfo.name = this.$cookies.get("uname")
            this.userInfo.id = this.$cookies.get("uuid")
            this.userInfo.role = this.$cookies.get("urole")
        },
        getUserData(){
<<<<<<< HEAD
            this.$axios({url: '/api/score',method: 'post',data: {uuid: this.userInfo.id}}).then(res => {
=======
            this.$axios({url: 'http://127.0.0.1:8081/api/user',method: 'post',data: this.userInfo}).then(res => {
>>>>>>> 62fad54ac64d8d3ba35e0e55ed3fa4265be44589
                this.userData = res.data.data
            })
        },
        resetEditData(){
            for(var key in this.userData){
                this.editData[key] = this.userData[key]
            }
        },
        edit(){
            this.$axios({url: '/api/score',method: 'post',data: this.editData}).then(res => {
                if(res.data.data){
                    getUserData()
                    success = true
                }
                else{
                    fail = true
                }
            })
        },
        passwordEdit(){
            this.$axios({url: '/api/score',method: 'post',data: {uuid:this.userInfo.uuid, upwd:this.upwd, password:this.password}}).then(res => {
                if(res.data.data){
                    success = true
                }
                else{
                    fail = true
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
  .manage .top {
    border-bottom: 1px solid #eee;
    margin-bottom: 20px;
    display: flex;
  }
  .manage .top .order {
    margin: 20px;
  }
  .manage {
    width: 90%;
    margin: 0 auto;
    background-color: #dda6cb17;
  }
  .manage .tables{
    display: flex;
    flex-direction:column;
    align-items: center; 
    justify-content: center;
  }
  .manage .tables table{
    border-collapse: collapse;
    border-top : 1px solid;
    border-bottom : 1px solid;
  }
  .tables * {
  margin: 20px 0;
}
  .manage .tables td{
    height : 50px;
    width : 120px;
    border-top : 1px solid #ada5a54f;
    border-bottom : 1px solid #ada5a54f;
  }
  .manage .tables .double{
    width : 240px;
  }
  .manage .buttons{
    padding-bottom: 30px;
    padding-top: 10px;
  }
  .manage .buttons *{
    margin: 0 40px;
  }
  </style>
  