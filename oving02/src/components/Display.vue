<script lang="ts">
import { useDisplayStore } from '@/stores/display';
import { mapState } from 'pinia';
export default {
computed: {
    ...mapState(useDisplayStore, ['data'])
}
}
</script>

<template>
    <input type="text" readonly :value="data">
</template>

<style scoped lang="scss">
input {
    text-align: right;
}
</style>