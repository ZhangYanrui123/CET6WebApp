<template>
    <div class="adminNavigator">
        <div class="open">
            <span v-if="!isCollapse" class="iconfont icon-right-indent" @click="changeMenu"></span>
            <span v-if="isCollapse" class="iconfont icon-left-indent" @click="changeMenu"></span>
        </div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/admin' }" class="custom-color">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/admin/adminStudent/studentList' }" class="custom-color">学生中心</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/admin/adminTeacher/teacherList' }" class="custom-color">教师中心</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/admin/adminExam/examList' }" class="custom-color">考场中心</el-breadcrumb-item>
            <el-breadcrumb-item>...</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
</template>

<script>
export default {
    name: "adminNavigator.vue",
    props:['isCollapse'],
    methods:{
        changeMenu(){
            this.$emit('changeShow')
        }
    }
}
</script>

<style scoped>

.custom-color {
  color: #ccc;
}

.adminNavigator{
    //height: 50px;
    background: #1e78bf;
    color: #fff;
    line-height: 50px;
    padding-bottom: 15px;
    .open{
        .iconfont{
            font-size: 22px;
            cursor: pointer;
        }
    }
}

</style>