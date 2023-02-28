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
    <p class="warning"></p>
    <input type="text" readonly :value="data">
</template>

<style scoped lang="scss">
input {
    text-align: right;
    width: 100%;
    padding: 0;
}
</style>