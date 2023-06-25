<template>
    <div class="blackbg">
        <header class="main-header homeContainer">
            <router-link to="/" class="homeLogo">HOME</router-link>
        </header>
        <aboutSection>
            <div class="box">
                <h1 data-jarallax-element="0 200" class="abouth1">This is our Team!</h1>
            </div>
        </aboutSection>
        <aboutSection>
            <div class="box">
                <!-- y x-->
                <h2 data-jarallax-element="0 -200" class="abouth2">CXY: Cui Xingyu(Leader)</h2>
                <div class="aboutContainer">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="@/assets/img/pic1.jpeg">
                    </div>
                    <div class="aboutContent" data-jarallax-element="-200 0">
                        <p>派大星是海绵宝宝的最好朋友，也是动画中的一位重要角色。他是一个懒散而幼稚的星形生物，经常和海绵宝宝一起展开各种冒险。派大星有着粉红色身体和愚蠢但善良的个性，常常搞笑、天真无邪，给观众带来欢乐和笑声。</p>
                    </div>
                </div>
            </div>
        </aboutSection>

        <aboutSection>
            <div class="box">
                <h2 data-jarallax-element="0 200" class="abouth2">CY: Cha Yi(Worker)</h2>
                <div class="aboutContainer">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="@/assets/img/pic2.jpeg">
                    </div>
                    <div class="aboutContent" data-jarallax-element="-200 0">
                        <p>海绵宝宝是这个动画的主角，他是一只住在海底城镇——比基尼底的海绵，他有一个方形的身体。海绵宝宝积极乐观、善良友好，对世界充满好奇和热情，常常陷入各种有趣和搞笑的冒险中。他是比基尼底最快乐的居民之一，同时也是观众喜爱的角色之一。</p>
                    </div>
                </div>
            </div>
        </aboutSection>

        <aboutSection>
            <div class="box">
                <h2 data-jarallax-element="0 -200" class="abouth2">XC: Xue Chen(Worker)</h2>
                <div class="aboutContainer">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="@/assets/img/pic3.jpg">
                    </div>
                    <div class="aboutContent" data-jarallax-element="-200 0">
                        <p>珊迪是一只来自德克萨斯州的狐松鼠，她是海绵宝宝的好朋友之一。珊迪是一位机智和勇敢的角色，她穿着太空服，并在水下的究极生物圈中生活。她善于运动和格斗，并在很多时候给海绵宝宝提供力量和帮助。</p>
                    </div>
                </div>
            </div>
        </aboutSection>

        <aboutSection>
            <div class="box">
                <h2 data-jarallax-element="0 200" class="abouth2">ZYR: Zhang Yanrui(Worker)</h2>
                <div class="aboutContainer">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="@/assets/img/pic4.jpeg">
                    </div>
                    <div class="aboutContent" data-jarallax-element="-200 0">
                        <p>章鱼哥是海绵宝宝的机器工，他住在海绵宝宝的邻居中。他是一个不太友好和自命不凡的角色，对于海绵宝宝和派大星的愚蠢行为常常感到烦躁。章鱼哥喜欢音乐、艺术和文化活动，但对周围的噪音和干扰没有耐性。</p>
                    </div>
                </div>
            </div>
        </aboutSection>
        <aboutSection>
            <div class="box">
                <h1 data-jarallax-element="100 0"  class="abouth1">Welcome to our CET System!</h1>
            </div>
        </aboutSection>
    </div>
</template>

<script>
import {jarallax} from "jarallax";
import('@/assets/js/jarallax.js')
import('@/assets/js/jarallax-element.js')

export default {
    name: "About"
}

import('@/assets/css/jarallax.css')

jarallax(document.querySelectorAll('.jarallax'), {
    speed: 0.6
});

</script>

<style scoped>

@import url('https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap');


.blackbg {
    background: #111;
    padding-bottom: 50px;
    overflow-x: hidden;

    box-sizing: border-box;
    font-family: 'poppins', sans-serif;
    font-size: 18px;
}

.blackbg:before {
    content: 'Parallax';
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 16em;
    font-weight: 700;
    color: transparent;
    -webkit-text-stroke: 1px #555;
}

aboutSection {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}

.abouth1{

    font-size: 5em;
    color: hotpink;
    margin-bottom: 20px;
    font-family: Arial, Helvetica, sans-serif;
}

aboutSection .abouth2 {
    margin-left: 200px;
    font-size: 4em;
    color: mediumpurple;
    margin-bottom: 20px;
}

aboutSection .aboutContainer {
    position: relative;
    width: 1200px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

aboutSection:nth-child(even) .aboutContainer {
    flex-direction: row-reverse;
}

aboutSection .aboutContainer .imgBx {
    position: relative;
    width: 800px;
    height: 500px;
    overflow: hidden;
    box-shadow: 0 25px 35px rgba(0, 0, 0, 0.1);
}

aboutSection .aboutContainer .imgBx img {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}

aboutSection .aboutContainer .aboutContent {
    position: absolute;
    right: 0;
    margin-top: 200px;
    margin-bottom: 40px;
    padding: 40px;
    background: #333;
    z-index: 1;
    max-width: 400px;
    color: #fff;
    box-shadow: 0 25px 35px rgba(0, 0, 0, 0.1);
    border-bottom: 6px solid #4ac7ff;
}

aboutSection:nth-child(even) .aboutContainer .aboutContent {
    left: 0;
}
</style>