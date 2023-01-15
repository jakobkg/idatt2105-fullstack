import { defineStore } from "pinia";
import { ref } from "vue";

export const useCalculationLogStore = defineStore("calculationlog", {
    state: () => {
        return {
            calculations: ref([
                {
                    expression: "1+2",
                    result: "3"
                }
            ])
        }
    },
    actions: {
        insert(expression, result) {
            this.calculations.push({ expression, result })
        },
        clear() {
            this.log = [];
        },
    }
})