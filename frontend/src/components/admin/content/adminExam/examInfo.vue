<!-- 添加学生 -->
<template>
    <section class="add">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="学校">
                <el-input v-model="form.cuniversity"></el-input>
            </el-form-item>
            <el-form-item label="教室">
                <el-input v-model="form.cclassroom"></el-input>
            </el-form-item>
            <el-form-item label="开始时间">
                <el-date-picker
                    v-model="form.copen"
                    type="datetime"
                    placeholder="选择日期和时刻"
                    style="width: 100%;"
                ></el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间">
                <el-date-picker
                    v-model="form.cclose"
                    type="datetime"
                    placeholder="选择日期和时刻"
                    style="width: 100%;"
                ></el-date-picker>
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
                cuniversity: "",
                cclassroom: "",
                copen:"",
                close:""
            }
        };
    },
    methods: {
        onSubmit() { //数据提交
            this.$axios({
                url: 'http://127.0.0.1:8081/api/admin/addClassroom',
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
                    this.$router.push({path: '/admin/adminExam/examList'})
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

