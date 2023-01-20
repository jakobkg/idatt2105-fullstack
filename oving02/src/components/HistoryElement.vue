<script lang="ts">
import type { PropType } from 'vue';

export default {
    props: {
        entry: {
            type: Object as PropType<HistoryEntry>,
            required: true
        }
    }
}
</script>

<template>
    <div class="entry">
        <div>{{ entry.expression }} = {{ entry.result }}</div>
    </div>
</template>

<style>
.entry {
    width: max-content;
    height: 1.5em;
    max-height: 1.5em;
    overflow: hidden;
}
</style>