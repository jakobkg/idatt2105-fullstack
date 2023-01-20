<script lang="ts">
import Button from './Button.vue';
import { useDisplayStore } from '@/stores/display';
import { useHistoryStore } from '@/stores/history';
import { mapActions } from 'pinia';

export default {
    data() {
        return {
            buttons: "+-*/".split("")
        }
    },
    components: {
        Button
    },
    methods: {
        ...mapActions(useHistoryStore, ['getLatestAns']),
        ...mapActions(useDisplayStore, ['insert', 'clear', 'remove', 'calculate']),
    }
}
</script>

<template>
    <div class="operations">
        <Button value="DEL" :callback="(_) => { remove(); }" />
        <Button value="C" :callback="(_) => { clear(); }" />

        <Button v-for="text in buttons" :value="text" :callback="(_) => { insert(text); }" />

        <Button value="Ans" :callback="(_) => { insert(getLatestAns().toString()); }" />
        <Button value="=" :callback="(_) => { calculate(); }" />
    </div>
</template>

<style scoped lang="scss">
.operations {
    $gap: 5px;

    max-width: max-content;
    display: grid;
    grid-template-columns: repeat(2, 1fr);

    gap: $gap;
    margin-top: $gap;
    margin-left: $gap;

    button {
        min-width: 3em;
        height: 2em;
    }

}
</style>