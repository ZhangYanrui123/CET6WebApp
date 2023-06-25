<template>
  <div>
      <Navigator @changeShow="changeShow" :isCollapse="isCollapse"></Navigator>
      <router-view></router-view>
  </div>
</template>

<script>
import Navigator from '../menu/adminNavigator.vue'

export default {
    name: "Index",
    props:['isCollapse'],
    components:{
        Navigator,
    },
    methods:{
      changeShow() {
          this.$emit('changeShow');
      }
    }
}
</script>

<style scoped>

</style>