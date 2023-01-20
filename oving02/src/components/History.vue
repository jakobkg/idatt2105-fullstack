<script lang="ts">
import { useHistoryStore } from '@/stores/history';
import { mapState } from 'pinia';
import HistoryElement from './HistoryElement.vue';

export default {
    computed: {
        ...mapState(useHistoryStore, { history: 'data' })
    },
    components: {
        HistoryElement
    }
}
</script>

<template>
    <div class="history">
        <HistoryElement v-for="(entry, idx) in history" :entry="entry" :class="idx % 2 === 1 ? 'darker' : ''" />
    </div>
</template>

<style>
.history {
    width: max-content;
    display: flex;
    flex-direction: column;

    margin: 5px;
}

.darker {
    background-color: rgb(230, 230, 230);
}
</style>