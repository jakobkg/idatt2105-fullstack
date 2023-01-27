<script lang="ts">
import Display from './Display.vue';
import Keypad from './Keypad.vue';
import History from './History.vue'
import "@fontsource/chivo-mono"

export default {
    components: {
        Keypad,
        Display,
        History
    }
}
</script>

<template>
    <div class="calculator">
        <Display />
        <Keypad />
    </div>
    <History />
</template>

<style scoped lang="scss">
main {
    font-family: "Chivo Mono", monospace;
}

button {
    font: inherit;
}

input {
    font: inherit;
}
</style>