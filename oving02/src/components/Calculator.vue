<script lang="ts">
import Display from './Display.vue';
import Keypad from './Keypad.vue';
import History from './History.vue'

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

<style lang="scss">
.calculator {
    display: flex;
    flex-direction: column;
}
</style>