<script setup>
import Button from './Button.vue';
import { useDisplayDataStore } from '../stores/displaydata';

const operations = [ "*", "/", "+", "-" ];
const store = useDisplayDataStore();

</script>

<template>
    <div class="operations">
        <Button tekst="DEL" @click="store.remove()" />
        <Button tekst="C" @click="store.clear()" />
        <Button v-for="operation in operations" :tekst="operation" @click="store.insert(operation)" />
        <Button tekst="=" @click="store.calculate()" />
    </div>
</template>

<style scoped>
.operations {
    display: grid;

    grid-template-rows: 1fr 1fr 1fr 1fr;
    grid-template-columns: 1fr 1fr;
}
</style>