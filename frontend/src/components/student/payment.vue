<template>
    <div class="payment">
        <ul class="top">
            <span class="order">微信支付</span> 
            <img src = "./qr.png">
        </ul>
        <el-dialog title='支付成功' center :visible.sync='pay'>
            您已成功支付<br/>
            将在？？？s后自动跳转回报名页面<br/>
            <!-- 如果能做的话，倒计时截止后自动跳转 -->
            <el-button @click = "succApply = false">关闭</el-button>
        </el-dialog>
    </div>
  </template>
  
  <script>
  
  export default {
    data() {
      return {
        nowSubject: 0,
        pay: false,
        userInfo: {
            name : null,
            id : null
        }
      }
    },
    created() { //cookies都没加
        this.nowSubject = this.$route.query.eid
        this.getCookies()
        // getPaymentMethod() 如果有的话，获取付款二维码
    },
    methods: {
        getCookies() {  //获取cookie
            this.userInfo.name = this.$cookies.get("uname")
            this.userInfo.id = this.$cookies.get("uuid")
        },
    }
  }
  </script>
  
  <style lang="less" scoped>
  img{
    height : 100px;
    width : 100px
  }
 .payment ul{
    display: flex;
    flex-direction:column;
    align-items: center; 
    justify-content: center;
    vertical-align : middle;
    margin: 20%;
 }
 .payment ul *{
  padding: 5px;
}
  </style>
  