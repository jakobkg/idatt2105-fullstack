<script setup>
import Calculator from './components/Calculator.vue'
import History from './components/History.vue';
</script>

<template>
  <main>
    <Calculator />
    <History />
  </main>
</template>

<style scoped>
main {
  max-width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
