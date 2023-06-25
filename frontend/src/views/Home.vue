<template>
    <div class="blurred-wrapper">
        <header class="main-header homeContainer">
            <router-link to="about" class="homeLogo">ABOUT</router-link>
            <router-link to="login" class="homeBtn sign-up homea">Sign up</router-link>
        </header>

        <section class="hero homeContainer">
            <div class="homeContentWrapper">
                <h5 class="tagline">More than just a website</h5>
                <h1 class="homeTitle homeh1">
                    CET6 Registration Examination System<span>.</span>
                </h1>
                <p class="homeMessage">
                    Make educating and studying easier
                </p>
                <div style="margin-bottom: 50px">
                </div>
                <router-link to="about" class="homeBtn ctk homea">Lear more</router-link>
            </div>
        </section>
    </div>
</template>

<script>
export default {

}

</script>

<style>
/*
⚡ MAKE SURE TO SUBSCRIBE PROGRAMMER CLOUD ⚡
*/

.homeh1{
    font-size: 5em;
    color: hotpink;
    margin-bottom: 20px;
    font-family: Arial, Helvetica, sans-serif;
}

.homea {
    text-decoration: none;
    display: inline-block;
}

.blurred-wrapper {
    height: 100vh;
    background: url(@/assets/img/background.png) center/cover;
    position: relative;
    z-index: 1;
    overflow: hidden;
    animation: hue 10s infinite alternate;
    font-family: sans-serif;
    color: #fff;
}

.blurred-wrapper::after {
    content: "";
    width: 100%;
    height: 100%;
    background: inherit;
    position: absolute;
    top: 0;
    left: 0;
    z-index: -1;
    filter: blur(2rem);
    transform: scale(1.15);
}

@keyframes hue {
    from {
        filter: hue-rotate(0);
    }
    to {
        filter: hue-rotate(360deg);
    }
}

.main-header {
    height: 12rem;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.homeContainer {
    max-width: 120rem;
    margin: 0 auto;
    padding: 0 1.5rem;
}

.homeLogo {
    color: #fff;
    text-transform: uppercase;
    font-size: 2.5rem;
    font-weight: bold;
}

.homeBtn {
    padding: 1rem 2.5rem;
    border: 2px solid rgba(255, 255, 255, 0.5);
    border-radius: 2.5rem;
    color: #fff;
    text-transform: uppercase; /*字体大写*/
    text-decoration: none; /*字体增加装饰：去除下划线*/
    font-family: sans-serif; /*非衬线体*/
    box-sizing: border-box; /*盒模型大小规则*/
    background: linear-gradient(
        90deg,#03a9f4, #f441a5, #ffeb3b,
        #03a9f4, #f441a5, #ffeb3b, #03a9f4); /*渐变背景*/
    z-index: 1; /*层叠定位*/
    background-size: 400%;
}

.homeBtn:hover{
    animation: homeAnimate 8s linear infinite alternate; /*动画: 名称 时间 线性 循环 播放完回退播放*/
}

.homeBtn::before{ /*之前添加*/
    content: ''; /*内容*/
    top:-5px; /*当设置对立的2个定位属性时，元素的大小将由对立的大小决定*/
    left: -5px;
    right: -5px;
    bottom: -5px; /*当设置对立的2个定位属性时，元素的大小将由对立的大小决定*/
    z-index: -1;
    background: linear-gradient(
        90deg,#03a9f4, #f441a5, #ffeb3b, #03a9f4,
        #f441a5, #ffeb3b, #03a9f4);
    border-radius: 40px;
    background-size: 400%;
    filter: blur(20px); /*过渡：模糊*/
    opacity: 0; /*透明度*/
    transition: 1s; /*过渡时间*/
}
.homeBtn:hover::before{
    filter: blur(20px);
    opacity: 1;
    animation: homeAnimate 8s linear infinite; /*注意动画名称统一*/
}

.hero {
    display: flex;
    align-items: center;
    height: calc(100vh - 12rem);
}

.homeContentWrapper {
    max-width: 40rem;
    transform: translateY(-4rem);
}

.tagline {
    text-transform: uppercase;
    font-size: 1rem;
    font-weight: 100;
    margin-bottom: 1rem;
}

.homeTitle {
    font-size: 2.5rem;
    margin-bottom: 1.5rem;
}

.homeMessage {
    font-size: 1.4rem;
    margin-bottom: 3rem;
}

.cta {
    background-color: #000;
}

@media screen and (min-width: 768px) {
    .homeBtn {
        padding: 1.5rem 3rem;
    }
    .homeContentWrapper {
        max-width: 70rem;
    }
    .tagline {
        font-size: 1.6rem;
    }
    .homeTitle {
        font-size: 6rem;
    }
    .homeMessage {
        font-size: 2rem;
    }
}


@keyframes homeAnimate{
    0%{
        background-position: 0%; /*修改背景定位，实现渐变色炫光*/
    }
    50%{
        background-position: 100%;
    }
    100%{
        background-position: 0%;
    }
}
</style>