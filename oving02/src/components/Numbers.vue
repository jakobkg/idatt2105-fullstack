<script setup>
import { useDisplayDataStore } from '../stores/displaydata';
import Button from './Button.vue';

const tegn = ["7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ","];
const store = useDisplayDataStore();
</script>

<template>
    <div class="numpad">
        <Button 
            v-for="tegn in tegn"
            :tekst="tegn"
            :style="{ gridColumn: tegn === '0' ? '1 / 3' : '' }"
            @click="store.insert(tegn)" />
    </div>
</template>

<style scoped>
.numpad {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
}
</style>