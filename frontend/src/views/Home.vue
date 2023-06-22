<template>
    <div class="blurred-wrapper">
        <header class="main-header container">
            <router-link to="about" class="logo">ABOUT</router-link>
            <router-link to="login" class="btn sign-up homea">Sign up</router-link>
        </header>

        <section class="hero container">
            <div class="content-wrapper">
                <h5 class="tagline">More than just a website</h5>
                <h1 class="title homeh1">
                    CET6 Registration Examination System<span>.</span>
                </h1>
                <p class="message">
                    Make educating and studying easier
                </p>
                <div style="margin-bottom: 50px">
                </div>
                <router-link to="about" class="btn ctk homea">Lear more</router-link>
            </div>
        </section>
    </div>
</template>

<script>
export default {

}

import "@/assets/css/homepage.css"
</script>

<style>

</style>