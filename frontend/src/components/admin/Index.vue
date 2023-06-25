<template>
  <div class="adminLayout">
      <div class="adminMenu">
          <Menu :isCollapse="isCollapse"></Menu>
      </div>
      <div class="adminContent" :class="{small:isCollapse}">
          <Content @changeShow="changeShow" :isCollapes="isCollapse"></Content>
      </div>
  </div>
</template>

<script>
import Menu from './menu/adminMenu.vue'
import Content from './content/adminRight.vue'
export default {
    name: "Index.vue",
    components:{
        Menu,
        Content
    },
    data(){
        return{
            isCollapse:false
        }
    },
    methods:{
        changeShow(){
            this.isCollapse=!this.isCollapse;
        }
    }
}
</script>

<style lang="less" scoped>
.adminLayout{
  //display: flex;
  .adminMenu{
    //width: 200px;
    background: #112f50;
    position: fixed;
    left: 0;
    top: 0;
    bottom: 0;
  }
  .adminContent{
    padding-left: 200px;
  }
  .small{
    padding-left: 64px;
  }
}
</style>