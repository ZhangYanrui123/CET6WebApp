<template>
    <div class="scoreTable">
      <ul class="paper" v-loading="loading">
        <li class="item" v-for="(item,index) in pagination.records" :key="index">
          <div class="info">
            考试类型：<span v-show="item.esubject == 1">
                六级口语
            </span>
            <span v-show="item.esubject == 2">
                六级笔试
            </span>&nbsp;&nbsp;&nbsp;&nbsp;
            考试日期：<span>{{item.ebegin}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
            考试成绩：<span>{{item.gtotal}}</span>
          </div>
        </li>
      </ul>
      <el-dialog title='考场信息' center :visible.sync='open'>
      </el-dialog>
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[6]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </div>
    </div>
  </template>
  
  <script>
  
  export default {
    data() {
      return {
        loading: false,
        open: false,
        pagination: { //分页后的考试信息
          current: 1, //当前页
          total: null, //记录条数
          size: 6, //每页条数
          records: [{esubject : 2,ebegin : 2,eend : 3,gtotal : 100},{esubject : 1,ebegin : 2,eend : 3,gtotal : 50}]
        },
        uuid: null
      }
    },
    created() {
      this.getCookies()
      this.getScoreInfo()
    },
    methods: {
      getCookies() {  //获取cookie
            this.uuid = this.$cookies.get("uuid")
    },
      //获取当前所有成绩信息
      getScoreInfo() {
          this.uuid = 1
        this.$axios({
            url: 'http://127.0.0.1:8081/api/totalscore',
            method: 'post',
            data: {
              uuid:this.uuid,
              // current: this.pagination.current,
              // size: this.pagination.size
      }}).then(res => {
        this.pagination.records = res.data.data
      }).catch(error => {
        console.log(error)
      })
      },
      //改变当前记录条数
      handleSizeChange(val) {
        this.pagination.size = val
        this.getScoreInfo()
      },
      //改变当前页码，重新发送请求
      handleCurrentChange(val) {
        this.pagination.current = val
        this.getScoreInfo()
      }
    }
  }
  </script>
  
  <style lang="less" scoped>
  ul{
    list-style-type:none
  }
  .enter{
    border-radius: 4px;
    border: none;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    background-color: #3a69df;
    color: #fff;
  }
  .classroom{
    cursor: pointer;
    text-decoration: underline;
  }
  .pagination {
    padding: 20px 0px 30px 0px;
    .el-pagination {
      display: flex;
      justify-content: center;
    }
  }
  .paper {
    padding: 20px;
  }
  .paper .item a {
    color: #417ea1;
  }
  .paper .item {
    border-radius: 4px;
    border: 1px solid #eee;
    transition: all 0.6s ease;
  }
  .paper .item:hover {
    border: 1px solid #7e3ac2;
  }
  .paper .item .info {
    font-size: 20px;
    color: #000;
  }
  .paper .item span {
    color: #417ea1;
  }
  .paper * {
    margin: 10px 0;
  }
  .scoreTable .paper {
    justify-content: space-around;
    flex-wrap: wrap;
  }
  .scoreTable .top {
    border-bottom: 1px solid #eee;
    margin-bottom: 20px;
    display: flex;
  }
  .scoreTable .top .order {
    margin: 20px;
  }
  .scoreTable {
    width: 90%;
    margin: 0 auto;
    background-color: rgba(209, 166, 221, 0.09);
  }
  </style>
  