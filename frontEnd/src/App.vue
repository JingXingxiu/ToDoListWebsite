<script setup>
import Title from './components/Title.vue';
import { useAuthStore } from './stores/auth';

</script>

<template>
  <div>
    <!-- Title,登录前是欢迎标语，登录后可以切换下面的router-view（在setting和todolist之间） -->
    <Title></Title>
    <!-- 展示todolist或setting -->
    <router-view></router-view>
  </div>
</template>

<style scoped>

</style>

