
<template>
    <div class="all">
        <h2>学生列表</h2>
        <el-table :data="this.pagination.records" border>
            <el-table-column fixed="left"  prop="suniversity" label="大学名称" width="120"></el-table-column>
            <el-table-column prop="scollege" label="学院名称" width="150"></el-table-column>
            <el-table-column prop="smajority" label="专业名称" width="200"></el-table-column>
            <el-table-column prop="sno" label="学号" width="100"></el-table-column>
            <el-table-column prop="uname" label="姓名" width="100"></el-table-column>
            <el-table-column prop="sclass" label="班级" width="120"></el-table-column>
            <el-table-column prop="sgrade" label="年级" width="120"></el-table-column>
            <el-table-column prop="utel" label="联系电话" width="140"></el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <span><el-button
                        size="mini"
                        @click="checkGrade(scope.$index, scope.row)"
                    >编辑</el-button></span>
                    <span><el-button
                        size="mini"
                        type="danger"
                        @click="deleteStudent(scope.$index, scope.row)"
                    >删除</el-button></span>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination.current"
            :page-sizes="[5, 10]"
            :page-size="pagination.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total"
            class="page">
        </el-pagination>
        <!-- 编辑对话框-->
        <el-dialog
            title="编辑学生信息"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
            <section class="update">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="大学名称">
                        <el-input v-model="form.suniversity" value="this.diagInfo.suniversity"></el-input>
                    </el-form-item>
                    <el-form-item label="学院名称">
                        <el-input v-model="form.scollege"></el-input>
                    </el-form-item>
                    <el-form-item label="专业名称">
                        <el-input v-model="form.smajority"></el-input>
                    </el-form-item>
                    <el-form-item label="学号">
                        <el-input v-model="form.sno" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="姓名">
                        <el-input v-model="form.uname" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="班级">
                        <el-input v-model="form.sclass"></el-input>
                    </el-form-item>
                    <el-form-item label="年级">
                        <el-input v-model="form.sgrade"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话">
                        <el-input v-model="form.utel"></el-input>
                    </el-form-item>
                </el-form>
            </section>
            <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
            pagination: {
                //分页后的考试信息
                current: 1, //当前页
                total: null, //记录条数
                size: 6, //每页条数
                records: [
                ] // 初始化为一个空数组
            },
            dialogVisible: false, //对话框
            form: {// 保存点击以后当前试卷的信息
            },
        };
    },
    created() {
        this.getStudentInfo();
    },
    methods: {
        getStudentInfo() {
            //分页查询所有试卷信息
            this.$axios({
                url: "http://127.0.0.1:8081/api/student/allStudents",
                method: "post",
            }).then((res) => {
                this.pagination.records = res.data.data;
            }).catch((error) => {
                console.log(error);
            });
        },
        //改变当前记录条数
        handleSizeChange(val) {
            this.pagination.size = val;
            this.getStudentInfo();
        },
        //改变当前页码，重新发送请求
        handleCurrentChange(val) {
            this.pagination.current = val;
            this.getStudentInfo();
        },
        checkGrade(index, row) { //修改学生信息
            this.form = row;
            this.dialogVisible = true;
        },
        deleteStudent(index, row) { //删除当前学生
            this.$confirm("确定删除当前学生吗？删除后无法恢复","Warning",{
                confirmButtonText: '确定删除',
                cancelButtonText: '算了,留着吧',
                type: 'danger'
            }).then(()=> { //确认删除
                console.log(row.uname)
                console.log(row.sno)
                this.$axios.post('http://127.0.0.1:8081/api/admin/deleteStudent',{
                        uname: row.uname,
                        sno: row.sno
                    }).then(response => {
                    if (response.status === 200) {
                        // 请求成功的处理逻辑
                        this.$message.success('删除成功');  // 弹出成功提示消息
                        this.getStudentInfo();  // 刷新页面数据，你可以根据具体情况自定义刷新页面的方法
                    } else {
                        // 请求成功但返回的状态码不是200的处理逻辑
                        this.$message.error('请求失败');
                    }
                }).catch(error => {
                    // 请求错误的处理逻辑
                    this.$message.error('请求发生错误');
                });
            }).catch(() => {

            })
        },
        submit() { //提交更改
            console.log(this.form)
            this.$axios({
                url: 'http://127.0.0.1:8081/api/admin/updateStudent',
                method: 'post',
                data: {
                    ...this.form
                }}).then(response => {
                if (response.status === 200) {
                    // 请求成功的处理逻辑
                    this.$message.success('更新成功');  // 弹出成功提示消息
                    this.dialogVisible = false;  // 关闭当前对话框，你可以根据具体情况自定义关闭对话框的方法
                    this.getStudentInfo();  // 刷新页面数据，你可以根据具体情况自定义刷新页面的方法
                } else {
                    // 请求成功但返回的状态码不是200的处理逻辑
                    this.$message.error('请求失败');
                }
            }).catch(error => {
                // 请求错误的处理逻辑
                this.$message.error('请求发生错误');
            });
        },
        handleClose(done) { //关闭提醒
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                }).catch(_ => {});
        },
    }
};
</script>
<style lang="less" scoped>
.all {
    padding: 0 40px;
    .page {
        margin-top: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .edit {
        margin-left: 20px;
    }
    .el-table tr {
        background-color: #dd5862 !important;
    }
}
.el-table .warning-row {
    background: #000 !important;
}

.el-table .success-row {
    background: #dd5862;
}
</style>
