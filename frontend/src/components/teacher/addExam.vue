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
          <el-option label="六级笔试" value="2"></el-option>
          <el-option label="六级口语" value="1"></el-option>
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
    created() {
        this.getClassrooms();
    },
  methods: {
    getClassrooms(){
        this.$axios({
            url:"http://127.0.0.1:8081/api/classroom/getAllClassrooms",
            method: 'post',
        }).then(res => {
            const classrooms = res.data.data
            const options = [];
            const categories = {};
            classrooms.forEach(classroom=>{
               // 获取首字母作为分类标识
                const category = classroom.cclassroom[0];
                // 如果分类不存在，则创建一个新的分类对象
                if (!categories[category]) {
                    categories[category] = {
                        label: `公教${category}`,
                        options: []
                    };
                }
                // 将当前项添加到相应的分类的 options 数组中
                categories[category].options.push({
                    value: classroom.cclassroom,
                    label: classroom.cclassroom
                });
            })
            // 将分类对象中的值转换为最终的数组结构
            for (const category in categories) {
                options.push(categories[category]);
            }
            console.log(options);
            this.form.options = options;
        }).catch(error => {
            console.error(error);
        });
    },
    onSubmit() {
      this.$axios({
          url: 'http://127.0.0.1:8081/api/exam/addExam',
          method: 'post',
          data: this.form
      }).then(res => {
          if(res.data.code == 200){
              this.$message({
                  message: '添加成功',
                  type: 'success'
              })
          }
      })
    },
    cancel() { //取消按钮
      this.form = {}
    },
  }
};
</script>
<style lang="less" scoped>
.add {
  padding: 0px 40px;
  width: 400px;
}
</style>

