<script setup>
const props = defineProps({
    tekst: {
        type: String,
        required: true
    }
});
</script>

<template>
    <button>{{ tekst }}</button>
</template>

<style>
button {
    background-color: aqua;
    min-width: 3em;
    height: 2em;
    border-radius: 3px;
}
</style>