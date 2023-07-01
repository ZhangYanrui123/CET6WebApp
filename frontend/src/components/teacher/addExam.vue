<!-- 添加考试 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="考场教室">
          <el-select v-model="form.cname" label="考场" placeholder="请选择">
              <el-option-group
                      v-for="group in form.options"
                      :key="group.label"
                      :label="group.label">
                  <el-option
                          v-for="item in group.options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                  </el-option>
              </el-option-group>
          </el-select>
        </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="form.ebegin"
          type="datetime"
          placeholder="选择日期和时刻"
          style="width: 100%;"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="form.eend"
          type="datetime"
          placeholder="选择日期和时刻"
          style="width: 100%;"
        ></el-date-picker>
      </el-form-item>
      <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="考试科目">
        <el-select v-model="form.esubject">
          <el-option label="六级笔试" value="1"></el-option>
          <el-option label="六级口语" value="2"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">立即创建</el-button>
        <el-button type="text" @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      form: { //表单数据初始化
        cid: null,
        cname: null,
        ebegin: null,
        eend: null,
        subject: null,
        options: [{
            label: '公教B',
            options: [{
                value: 'B402',
                label: 'B402'
            }, {
                value: 'B403',
                label: 'B403'
            }]
        }, {
            label: '公教C',
            options: [{
                value: 'C129',
                label: 'C129'
            }]
        }],
        value: ''
      }
    };
  },
  methods: {
    formatTime(date) { //日期格式化
      let year = date.getFullYear()
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    onSubmit() {

      this.$axios({
          url: 'http://127.0.0.1:8081/api/exam/addExam',
          method: 'post',
          data: this.form
      }).then(res => {
          this.form = res.data.data
      })
    },
    cancel() { //取消按钮
      this.form = {}
    },
    watch: {
    'form.eend': function(newVal) {
      console.log('form.eend changed:', newVal);
    }
  }
    
  }
};
</script>
<style lang="less" scoped>
.add {
  padding: 0px 40px;
  width: 400px;
}
</style>

