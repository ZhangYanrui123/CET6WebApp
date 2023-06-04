//获取试卷并跳转到添加题库
<template>
  <div class="exam">
    <el-button @click="addData">添加数据</el-button>
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="id" label="试卷编号" width="180"></el-table-column>
      <el-table-column prop="ebegin" label="开始时间" width="200"></el-table-column>
      <el-table-column prop="eend" label="结束时间" width="200"></el-table-column>
      <el-table-column prop="subject" label="考试科目" width="180"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="add(scope.row.paperId,scope.row.source)" type="primary" size="small">编辑考卷</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[4, 8, 10, 20]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total" class="page">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {}, //保存点击以后当前试卷的信息
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 4, //每页条数
        records: [] // 初始化为一个空数组
      },
    }
  },
  created() {
    this.getExamInfo()
  },
  methods: {
    getExamInfo() { //分页查询所有试卷信息
      this.$axios(`/api/exams/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.pagination = res.data.data
      }).catch(error => {
      })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getExamInfo()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getExamInfo()
    },
    add(paperId,source) { //增加题库
      this.$router.push({path:'/addAnswerChildren',query: {paperId: paperId,subject:source}})
    },
    addData() {
      // 创建新的数据对象
      let newData = {
        id: "001",
        ebegin: "2023-05-15 10:00",
        eend: "2023-05-15 12:00",
        subject: "六级笔试",
      };
      // 将新数据添加到数据源中
      this.pagination.records.push(newData);
    }
  },
};
</script>
<style lang="less" scoped>
.exam {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit{
    margin-left: 20px;
  }
}
</style>
