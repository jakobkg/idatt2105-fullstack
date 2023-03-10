<script lang="ts">
import Numbers from './Numbers.vue';
import Operations from './Operations.vue';

export default {
    components: {
        Numbers,
        Operations
    }
}
</script>

<template>
    <div class="keypad">
        <Numbers />
        <Operations />
    </div>
</template>

<style>
.keypad {
    display:flex;
    width: 100%;
}
</style>
