
<template>
    <div class="all">
        <h2>学生列表</h2>
        <el-table :data="tableInfo" border>
            <el-table-column fixed="left" prop="uuid" label="用户Id" width="180">
            </el-table-column>
            <el-table-column prop="suniversity" label="大学名称" width="120"></el-table-column>
            <el-table-column prop="scollege" label="学院名称" width="200"></el-table-column>
            <el-table-column prop="smajority" label="专业名称" width="200"></el-table-column>
            <el-table-column prop="sno" label="学号" width="100"></el-table-column>
            <el-table-column prop="sclass" label="班级" width="120"></el-table-column>
            <el-table-column prop="sgrade" label="年级" width="120"></el-table-column>
            <el-table-column label="操作" width="120">
                <template v-slot:scope>
                    <el-button
                        size="mini"
                        @click="checkGrade(scope.$index, scope.row)"
                        >编辑</el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        @click="deleteById(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination.current"
            :page-sizes="[6, 10]"
            :page-size="pagination.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total"
            class="page">
        </el-pagination>
        <!-- 编辑对话框-->
        <el-dialog
            title="编辑试卷信息"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
            <section class="update">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="用户Id">
                        <el-input v-model="form.uuid"></el-input>
                    </el-form-item>
                    <el-form-item label="大学名称">
                        <el-input v-model="form.suniversity"></el-input>
                    </el-form-item>
                    <el-form-item label="学院名称">
                        <el-input v-model="form.scollege"></el-input>
                    </el-form-item>
                    <el-form-item label="专业名称">
                        <el-input v-model="form.smajority"></el-input>
                    </el-form-item>
                    <el-form-item label="学号">
                        <el-input v-model="form.sno"></el-input>
                    </el-form-item>
                    <el-form-item label="班级">
                        <el-input v-model="form.sclass"></el-input>
                    </el-form-item>
                    <el-form-item label="年级">
                        <el-input v-model="form.sgrade"></el-input>
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
            tableInfo:[{
                    uuid: 123456,
                    suniversity: '南开大学',
                    scollege: '计算机学院',
                    smajority:'计算机科学与技术',
                    sno: 2010139,
                    sclass: '计科三班',
                    sgrade: 2020,
                },
                {
                    uuid: 123456,
                    suniversity: '南开大学',
                    scollege: '计算机学院',
                    smajority:'计算机科学与技术',
                    sno: 2010139,
                    sclass: '计科三班',
                    sgrade: 2020
                }
            ]
        };
    },
    created() {
        //this.getStudentInfo();
    },
    methods: {
        /*getStudentInfo() {
            //分页查询所有试卷信息
            this.$axios(`/api/students/${this.pagination.current}/${this.pagination.size}`).then(res => {
                this.pagination = res.data.data;
            }).catch(error => {});
        },*/
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
        checkGrade(studentId) { //修改学生信息
            this.dialogVisible = true
            this.$axios(`/api/student/${studentId}`).then(res => {
                this.form = res.data.data
            })
        },
        deleteById(studentId) { //删除当前学生
            this.$confirm("确定删除当前学生吗？删除后无法恢复","Warning",{
                confirmButtonText: '确定删除',
                cancelButtonText: '算了,留着吧',
                type: 'danger'
            }).then(()=> { //确认删除
                this.$axios({
                    url: `/api/student/${studentId}`,
                    method: 'delete',
                }).then(res => {
                    this.getStudentInfo()
                })
            }).catch(() => {

            })
        },
        submit() { //提交更改
            this.dialogVisible = false
            this.$axios({
                url: '/api/student',
                method: 'put',
                data: {
                    ...this.form
                }
            }).then(res => {
                console.log(res)
                if(res.data.code ==200) {
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    })
                }
                this.getStudentInfo()
            })
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
