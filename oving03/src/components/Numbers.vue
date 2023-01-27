<script lang="ts">
import Button from './Button.vue';
import { useDisplayStore } from '@/stores/display';
import { mapActions } from 'pinia';

export default {
    data() {
        return {
            numbers: "7894561230.".split("")
        }
    },
    components: {
        Button
    },
    methods: {
        ...mapActions(useDisplayStore, ['insert'])
    }
}
</script>

<template>
    <div class="numbers">
        <Button
            v-for="number in numbers"
            :value="number"
            :callback="() => { insert(number) }"
            :style="number === '0' ? {gridColumn: 'span 2'} : {}" />
    </div>
</template>

<style scoped lang="scss">
.numbers {
    $gap: 5px;
    
    max-width: max-content;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: $gap;
    margin-top: $gap;

    button {
        min-width: 3em;
        height: 2em;
    }
}
</style>