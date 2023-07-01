<!-- 添加学生 -->
<template>
    <section class="add">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="姓名">
                <el-input v-model="form.uname"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-input v-model="form.usex"></el-input>
            </el-form-item>
            <el-form-item label="学院">
                <el-input v-model="form.scollege"></el-input>
            </el-form-item>
            <el-form-item label="所属专业">
                <el-input v-model="form.smajority"></el-input>
            </el-form-item>
            <el-form-item label="年级">
                <el-input v-model="form.sgrade"></el-input>
            </el-form-item>
            <el-form-item label="班级">
                <el-input v-model="form.sclass"></el-input>
            </el-form-item>
            <el-form-item label="电话号码">
                <el-input v-model="form.utel"></el-input>
            </el-form-item>
            <el-form-item label="身份证号">
                <el-input v-model="form.udocno"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="form.umail"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="form.upwd"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit()">立即创建</el-button>
                <el-button type="text" @click="cancel()">取消</el-button>
            </el-form-item>
        </el-form>
    </section>
</template>

<script>
export default {
    name: 'studentInfo',
    data() {
        return {
            form: { //表单数据初始化
                suniversity: "南开大学",
                uname: null,
                sgrade: null,
                smajority: null,
                sclass: null,
                scollege: null,
                utel: null,
                umail: null,
                upwd: null,
                udocno: null,
                usex: null,
                role: 2
            }
        };
    },
    methods: {
        onSubmit() { //数据提交
            this.$axios({
                url: 'http://127.0.0.1:8081/api/student/addStudent',
                method: 'post',
                data: {
                    ...this.form
                }
            }).then(res => {
                if(res.data.code == 200) {
                    this.$message({
                        message: '数据添加成功',
                        type: 'success'
                    })
                    this.$router.push({path: '/admin/adminStudent/studentList'})
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

