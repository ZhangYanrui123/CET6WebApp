<template>
  <div class="dashboard">
    <div class="statistics">
      <div class="card">
        <h2>学生数量</h2>
        <p>{{ studentCount }}</p>
      </div>
      <div class="card">
        <h2>教师数量</h2>
        <p>{{ teacherCount }}</p>
      </div>
      <div class="card">
        <h2>考试数量</h2>
        <p>{{ examCount }}</p>
      </div>
    </div>
    <div id="china-map"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import chinaGeoJSON from './china.json';

export default {
  name: 'adminHome',
  data() {
    return {
      studentCount: 0,
      teacherCount: 0,
      examCount: 0,
      latestDynamics: []
    };
  },
  mounted() {
    setTimeout(() => {
      this.studentCount = 200;
      this.teacherCount = 20;
      this.examCount = 5;
      this.latestDynamics = [
        { id: 1, title: '系统更新' },
        { id: 2, title: '新增功能上线' },
        { id: 3, title: '用户反馈处理' }
      ];

      const chart = echarts.init(document.getElementById('china-map'));

      echarts.registerMap('china', chinaGeoJSON);

      const data = [
        { name: '北京', value: 50 },
        { name: '天津', value: 100 },
        { name: '上海', value: 30 },
        { name: '广东', value: 20 },
        // 更多省份的数据...
      ];

      const option = {
        title: {
          text: '各省份注册学生数量',
          left: 'left',
          textStyle: {
            fontWeight: 'bold',
            fontSize: 24
          }
        },
        tooltip: {
          trigger: 'item'
        },
        visualMap: {
          min: 0,
          max: 100,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],
          calculable: true,
          inRange: {
            color: ['#00b0ff', '#001c6b'] // 蓝色渐变
          }
        },
        series: [
          {
            name: '学生数量',
            type: 'map',
            map: 'china',
            roam: false,
            itemStyle: {
              emphasis: { label: { show: true } }
            },
            data
          }
        ],
        textStyle: {
          color: '#fff'
        },

      };

      chart.setOption(option);
    }, 100);
  }
};
</script>

<style scoped>
.dashboard {
  padding: 20px;
  background-color: rgba(238, 238, 238, 0.57); /* 设置整体背景色 */
}

.statistics {
  display: flex;
}


.card {
  flex: 1;
  padding: 20px;
  background: linear-gradient(135deg, #64b5f6, #1976d2);
  margin-right: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: #fff;
}

.latest-dynamic {
  margin-top: 20px;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 10px;
}

#china-map {
  margin-top: 30px;
  height: 600px; /* 增大地图高度 */
  width: 100%; /* 设置地图宽度为100% */
}
</style>
