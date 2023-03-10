<script lang="ts">
import type { PropType } from 'vue';

export default {
    props: {
        value: {
            type: String,
            required: true
        },
        callback: {
            type: Function as PropType<((payload: MouseEvent) => void)>,
            required: true
        }
    }
}
</script>

<template>
    <button @click="callback">{{ value }}</button>
</template>

<style>

</style>