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
        <div class="expression">{{ entry.expression }}</div>
        <div class="result">{{ entry.result }}</div>
    </div>
</template>

<style lang="scss">
.entry {
    width: 100%;
}

.result {
    text-align: right;
}

.result::before {
    content: "= ";
}
</style>