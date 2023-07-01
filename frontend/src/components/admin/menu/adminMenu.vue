<template>
<div>
    <el-menu
            default-active="/admin"
            class="el-menu-vertical-demo"
            background-color="#112f50"
            text-color="#fff"
            active-text-color="#ffd04b"
            router :collapse="isCollapse">
        <el-menu-item>
            <span slot="title">CET报考系统后台</span>
        </el-menu-item>
        <el-menu-item index="/admin">
            <i class="el-icon-menu"></i>
            <span slot="title">首页</span>
        </el-menu-item>
        <el-submenu index="/admin/adminStudent">
            <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span>学生管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="/admin/adminStudent/studentList">
                    <i class="el-icon-document"></i>
                    <span slot="title">学生列表</span>
                </el-menu-item>
                <el-menu-item index="/admin/adminStudent/studentInfo">
                    <i class="el-icon-circle-plus"></i>
                    <span slot="title">学生信息</span>
                </el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-submenu index="/admin/adminTeacher">
            <template slot="title">
                <i class="el-icon-s-custom"></i>
                <span>教师管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="/admin/adminTeacher/teacherList">
                    <i class="el-icon-document"></i>
                    <span slot="title">教师列表</span>
                </el-menu-item>
                <el-menu-item index="/admin/adminTeacher/teacherInfo">
                    <i class="el-icon-circle-plus"></i>
                    <span slot="title">教师信息</span>
                </el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-submenu index="/admin/adminExam">
            <template slot="title">
                <i class="el-icon-location"></i>
                <span>考场管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="/admin/adminExam/examList">
                    <i class="el-icon-document"></i>
                    <span slot="title">考场列表</span>
                </el-menu-item>
                <el-menu-item index="/admin/adminExam/examInfo">
                    <i class="el-icon-circle-plus"></i>
                    <span slot="title">考场信息</span>
                </el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="/login">
            <i class="el-icon-s-home"></i>
            <span slot="title">返回首页</span>
        </el-menu-item>
    </el-menu>
</div>
</template>

<script>
export default {
    props:['isCollapse'],
}
</script>

<style lang="less" scoped>
.el-menu{
  border-right: 0;

  /deep/ .is-active{
    background: #1e78bf !important;
    color: #fff !important;
  }
}

.el-menu-vertical-demo:not(.el-menu--collapse){
  width: 200px;
  min-height: 400px;
}
</style>