<template>
    <div class="dashboard">
        <h1>后台管理首页</h1>
        <div class="statistics">
            <div class="card">
                <h2>用户数量</h2>
                <p>{{ userCount }}</p>
            </div>
            <div class="card">
                <h2>订单数量</h2>
                <p>{{ orderCount }}</p>
            </div>
            <!-- 更多统计数据卡片 -->
        </div>
        <div class="latest-dynamic">
            <h2>最新动态</h2>
            <ul>
                <li v-for="dynamic in latestDynamics" :key="dynamic.id">
                    {{ dynamic.title }}
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: 'adminHome',
    data() {
        return {
            userCount: 0,
            orderCount: 0,
            latestDynamics: []
        };
    },
    mounted() {
        // 在这里可以进行数据请求，获取统计数据和最新动态
        // 模拟数据加载
        setTimeout(() => {
            this.userCount = 100;
            this.orderCount = 50;
            this.latestDynamics = [
                { id: 1, title: '系统更新' },
                { id: 2, title: '新增功能上线' },
                { id: 3, title: '用户反馈处理' }
            ];
        }, 1000);
    }
};

</script>
<style scoped>
.dashboard {
    padding: 20px;
}

.statistics {
    display: flex;
}

.card {
    flex: 1;
    padding: 20px;
    background-color: #f5f5f5;
    margin-right: 10px;
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
</style>