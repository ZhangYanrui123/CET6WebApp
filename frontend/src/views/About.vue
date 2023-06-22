<template>
    <div class="blackbg">
        <header class="main-header container">
            <router-link to="/" class="logo">HOME</router-link>
        </header>
        <section>
            <div class="box">
                <h1 data-jarallax-element="0 200" class="abouth1">This is our Team!</h1>
            </div>
        </section>
        <section>
            <div class="box">
                <!-- y x-->
                <h2 data-jarallax-element="0 -200" class="abouth2">CXY: Cui Xingyu(Leader)</h2>
                <div class="container">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="@/assets/img/pic1.jpeg">
                    </div>
                    <div class="content" data-jarallax-element="-200 0">
                        <p>派大星是海绵宝宝的最好朋友，也是动画中的一位重要角色。他是一个懒散而幼稚的星形生物，经常和海绵宝宝一起展开各种冒险。派大星有着粉红色身体和愚蠢但善良的个性，常常搞笑、天真无邪，给观众带来欢乐和笑声。</p>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <div class="box">
                <h2 data-jarallax-element="0 200" class="abouth2">CY: Cha Yi(Worker)</h2>
                <div class="container">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="@/assets/img/pic2.jpeg">
                    </div>
                    <div class="content" data-jarallax-element="-200 0">
                        <p>海绵宝宝是这个动画的主角，他是一只住在海底城镇——比基尼底的海绵，他有一个方形的身体。海绵宝宝积极乐观、善良友好，对世界充满好奇和热情，常常陷入各种有趣和搞笑的冒险中。他是比基尼底最快乐的居民之一，同时也是观众喜爱的角色之一。</p>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <div class="box">
                <h2 data-jarallax-element="0 -200" class="abouth2">XC: Xue Chen(Worker)</h2>
                <div class="container">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="@/assets/img/pic3.jpg">
                    </div>
                    <div class="content" data-jarallax-element="-200 0">
                        <p>珊迪是一只来自德克萨斯州的狐松鼠，她是海绵宝宝的好朋友之一。珊迪是一位机智和勇敢的角色，她穿着太空服，并在水下的究极生物圈中生活。她善于运动和格斗，并在很多时候给海绵宝宝提供力量和帮助。</p>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <div class="box">
                <h2 data-jarallax-element="0 200" class="abouth2">ZYR: Zhang Yanrui(Worker)</h2>
                <div class="container">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="@/assets/img/pic4.jpeg">
                    </div>
                    <div class="content" data-jarallax-element="-200 0">
                        <p>章鱼哥是海绵宝宝的机器工，他住在海绵宝宝的邻居中。他是一个不太友好和自命不凡的角色，对于海绵宝宝和派大星的愚蠢行为常常感到烦躁。章鱼哥喜欢音乐、艺术和文化活动，但对周围的噪音和干扰没有耐性。</p>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="box">
                <h1 data-jarallax-element="100 0"  class="abouth1">Welcome to our CET System!</h1>
            </div>
        </section>
    </div>
</template>

<script>
import {jarallax} from "jarallax";
import('@/assets/js/jarallax.js')
import('@/assets/js/jarallax-element.js')

export default {
    name: "About"
}

import('@/assets/css/aboutpage.css')
import('@/assets/css/jarallax.css')

jarallax(document.querySelectorAll('.jarallax'), {
    speed: 0.6
});

</script>

<style scoped>

</style>