<script lang="ts">
import Calculator from '../components/Calculator.vue';
import { useTokenStore } from '@/stores/token';
import { mapState } from 'pinia';

export default {
  setup() {
    const tokenStore = useTokenStore();

    return {
      tokenStore
    }
  },
  computed: {
    ...mapState(useTokenStore, ["token"])
  },
  components: {
    Calculator
  }
}
</script>

<template>
  <main>
    <Calculator v-if="token.length > 0" />
    <div v-else>
      <h1>Ikke innlogget</h1>  
      <RouterLink to="/login">Klikk her for å gå til innlogging</RouterLink>
    </div>
  </main>
</template>

<style scoped></style>