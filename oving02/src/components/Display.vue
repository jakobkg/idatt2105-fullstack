<script setup>
import { useDisplayDataStore } from "../stores/displaydata";

const store = useDisplayDataStore();

function filterInput(event) {
    const legalSymbols = "12342567890x/+-,".split("");

    store.setData(event.target.value.split("").filter(symbol => legalSymbols.includes(symbol)).join(""));
}

document.addEventListener("input", filterInput);

</script>

<template>
    <input v-bind:value="store.displaydata" />
</template>

<style scoped>
input {
    width: 100%;
    text-align: right;
    font-family: monospace;
}
</style>