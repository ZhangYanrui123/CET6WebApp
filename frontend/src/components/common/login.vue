<template>
    <div id="login">
      <el-row class="main-container">
        <el-col :lg="8" :xs="16" :md="10" :span="10">
          <div class="bottom">
            <div class="container">
              <p class="title">六级报考系统登录</p>
              <el-form label-width="80px">
                <el-form-item label="用户名">
                  <el-input v-model="user.uname" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input v-model="user.password" placeholder="请输入密码" type='password'></el-input>
                </el-form-item>
                <div class="submit">
                  <el-button type="primary" class="row-login" @click="login()">登录</el-button>
                </div>
                <div class="options">
                  <div class="register">
                    <span><a href="javascript:;" @click = "register">注册账号</a></span>
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
        user:{
          uname:null,
          password:null
        }
      }
    },
    methods: {
      login() {
        console.log("登录中");
        this.$axios({
          url: `/api/login`,
          method: 'post',
          data: this.user
        }).then(res=>{
          let userData = res.data.data
          if(userData != null) {
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
          }
          if(resData == null) { //错误提示
            this.$message({
              showClose: true,
              type: 'error',
              message: '用户名或者密码错误'
            })
          }
        })
      },
      register(){
        this.$router.push({path: '/register'})
      }
    }
  }
  </script>
  
  <style lang="less" scoped>
  .remind {
    border-radius: 4px;
    padding: 10px 20px;
    display: flex;
    position: fixed;
    right: 20px;
    bottom: 50%;
    flex-direction: column;
    color: #606266;
    background-color: #fff;
    border-left: 4px solid #409eff;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)
  }
  .container {
    margin-bottom: 32px;
  }
  .container .el-radio-group {
    margin: 30px 0px;
  }
  a:link {
    color:#ff962a;
    text-decoration:none;
  }
  #login {
    font-size: 14px;
    color: #000;
    background-color: #fff;
  }
  #login .main-container {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  #login .main-container .top {
    margin-top: 100px;
    font-size: 30px;
    color: #ff962a;
    display: flex;
    justify-content: center;
  }
  #login .top .icon-kaoshi {
    font-size: 80px;
  }
  #login .top .title {
    margin-top: 20px;
  }
  #login .bottom {
    display:flex;
    justify-content: center;
    background-color:#fff;
    border-radius: 5px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  }
  #login .bottom .title {
    text-align: center;
    font-size: 30px;
  }
  .bottom .container .title {
    margin: 30px 0px;;
  }
  .bottom .submit .row-login {
    width: 100%;
    background-color: #04468b;
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
    color: #ff962a;
    display: flex;
    justify-content: space-between;
  }
  .bottom .options > a {
    color: #ff962a;
  }
  .bottom .options .register span:nth-child(1) {
    color: #8C8C8C;
  }
  </style>
  