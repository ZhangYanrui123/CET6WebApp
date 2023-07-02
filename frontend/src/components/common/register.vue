<template>
    <div class="register">
      <el-row class="main-container">
        <el-col :lg="8" :xs="16" :md="10" :span="10">
          <div class="bottom">
            <div class="container">
              <p class="title">六级报考系统注册</p>
              <el-form label-width="80px">
                <el-form-item label="姓名">
                  <el-input v-model="userData.uname" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input v-model="userData.password" placeholder="请输入密码" type='password'></el-input>
                </el-form-item>
                <el-form-item label="确认密码">
                  <el-input v-model="confirmPassword" placeholder="请输入密码" type='password'></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="userData.usex">
                        <el-radio :label=0>男</el-radio>
                        <el-radio :label=1>女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="生日">
                    <el-date-picker v-model="userData.ubirth" type="date" placeholder="请输入内容" value-format="yyyy-mm-dd"></el-date-picker>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-input v-model="userData.utel" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱">
                    <el-input v-model="userData.umail" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="证件类型">
                    <el-select v-model="userData.udoctype">
                        <el-option label="身份证" value="0"></el-option>
                        <el-option label="护照" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="证件号码">
                    <el-input v-model="userData.udocno" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="大学名称">
                    <el-input v-model="userData.suniversity" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="学院名称">
                    <el-input v-model="userData.scollege" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="专业名称">
                    <el-input v-model="userData.smajority" placeholder="请输入内容"></el-input>
                </el-form-item> 
                <el-form-item label="学号">
                    <el-input v-model="userData.sno" type="number" onKeypress="return(/^[0-9]*$/.test(String.fromCharCode(event.keyCode)))" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="班级">
                    <el-input v-model="userData.sclass" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="年级">
                    <el-date-picker v-model="userData.sgrade" type="year" placeholder="请输入内容" value-format="yyyy"></el-date-picker>
                </el-form-item>
                <div class="submit">
                  <el-button type="primary" class="row-login" @click="register()">注册</el-button>
                </div>
                <div class="options">
                  <div class="login">
                    <span>已有账号？<a href="javascript:;" @click = "login">去登录</a></span>
                  </div>
                </div>
              </el-form>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </template>
  
  <script>
  export default {
    name: "login",
    data() {
      return {
        role: 0,
        userData:{},
        confirmPassword: null,
      }
    },
    methods: {
      register(){
        if(this.userData.password!=this.confirmPassword){
            this.$message("密码不一致",{
                type: 'error'
            })
        }
        else{
            this.$axios({url: 'http://127.0.0.1:8081/api/register',method: 'post',data: this.userData}).then(res => {
                if(res.data.data){
                    let userData = res.data.data
                    this.$cookies.set("uname", userData.uname)
                    this.$cookies.set("uuid", userData.uuid)
                    this.$cookies.set("role", userData.role)
                    switch(userData.role) {
                        case "1":  //管理员
                            this.$router.push({path: '/index' })
                            break
                        case "2": //学生
                            this.$router.push({path: '/student' })
                            break
                        case "3": //教师
                            this.$router.push({path: '/teacher'})
                            break
                    }
                    this.$message("注册成功",{
                        type: 'info'
                    })
                }
                else{
                    this.$message("注册失败",{
                        type: 'error'
                    })
                }
            })
        }
      },
      login(){
        this.$router.push({path: '/login'})
      }
    }
  }
  </script>
  
  <style lang="less" scoped>
  .container {
    margin-bottom: 32px;
  }
  .register {
    font-size: 14px;
    color: #000;
    background-color: #fff;
  }
  .register .main-container {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .register .bottom {
    display:flex;
    justify-content: center;
    background-color:#fff;
    border-radius: 5px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  }
  .register .bottom .title {
    text-align: center;
    font-size: 30px;
  }
  .bottom .container .title {
    margin: 30px 0px;;
  }
  .bottom .submit .row-login {
    width: 100%;
    background-color: #82048b;
    border-color: #04468b;
    margin: 20px 0px 10px 0px;
    padding: 15px 20px;
  }
  .bottom .submit {
    display: flex;
    justify-content: center;
  }
  .bottom .options {
    margin-bottom: 40px;
    color: #8C8C8C;
    display: flex;
    justify-content: space-between;
  }
  .bottom .options a {
    color: #ff962a;
  }
  </style>
  