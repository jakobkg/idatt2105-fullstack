<script setup>
import { useCalculationLogStore } from '../stores/calculationlog';

const history = useCalculationLogStore();
</script>

<template>
    <ul>
        <li v-for="calculation in history.calculations">{{ calculation.expression }} = {{ calculation.result }}</li>
    </ul>
</template>