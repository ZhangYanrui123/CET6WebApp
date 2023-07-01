
<template>
    <div class="all">
        <h2>考场</h2>
        <el-table :data="this.pagination.records" border>
            <el-table-column fixed="left"  prop="cuniversity" label="学校" width="250"></el-table-column>
            <el-table-column prop="cclassroom" label="教室" width="250"></el-table-column>
            <el-table-column prop="copen" label="开放时间" width="250"></el-table-column>
            <el-table-column prop="cclose" label="关闭时间" width="250"></el-table-column>
            <el-table-column label="操作" width="200">
                <template slot-scope="scope">
                    <span><el-button
                        size="mini"
                        @click="checkGrade(scope.$index, scope.row)"
                    >编辑</el-button></span>
                    <span><el-button
                        size="mini"
                        type="danger"
                        @click="deleteById(scope.$index, scope.row)"
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
                    {
                        cuniversity: '南开大学',
                        cclassroom: 'B402',
                        copen: '2023-06-04',
                        cclose: '2023-06-06'
                    }, {
                        cuniversity: '南开大学',
                        cclassroom: 'B403',
                        copen: '2023-06-04',
                        cclose: '2023-06-16'
                    }, {
                        cuniversity: '南开大学',
                        cclassroom: 'C129',
                        copen: '2023-07-02',
                        cclose: '2023-07-20'
                    }
                ] // 初始化为一个空数组
            },
            dialogVisible: false, //对话框
            form: {// 保存点击以后当前试卷的信息
            },
            tableInfo:[{
                cuniversity: '南开大学',
                cclass: 'B402',
                copen: '2023-7-1',
                cclose: '2023-7-2'
            }, {
                cuniversity: '南开大学',
                cclass: 'B403',
                copen: '2023-7-1',
                cclose: '2023-7-2'
            }
            ]
        };
    },
    created() {
        //this.getStudentInfo();
    },
    methods: {
        getExamInfo() {
            //分页查询所有试卷信息
            this.$axios({
                url: "http://127.0.0.1:8081/api/student/allStudents",
                method: "post",
            }).then((res) => {
                this.pagination.records = res.data.data;
                console.log(this.pagination.records)
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
