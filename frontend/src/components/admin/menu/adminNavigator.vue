<template>
    <div class="adminNavigator">
        <div class="open">
            <span v-if="!isCollapse" class="iconfont icon-right-indent" @click="changeMenu"></span>
            <span v-if="isCollapse" class="iconfont icon-left-indent" @click="changeMenu"></span>
        </div>
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
.adminNavigator{
    height: 50px;
    background: #1e78bf;
    color: #fff;
    line-height: 50px;
    .open{
        .iconfont{
            font-size: 22px;
            cursor: pointer;
        }
    }
}
</style>