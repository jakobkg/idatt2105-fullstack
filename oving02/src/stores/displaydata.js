import { defineStore } from "pinia";
import { ref } from "vue";
import { useCalculationLogStore } from "./calculationlog";



export const useDisplayDataStore = defineStore("displaydata", {
    state: () => {
        return {
            displaydata: ref("")
        }
    },
    actions: {
        insert(symbol) {
            this.displaydata = this.displaydata + symbol;
        },
        remove() {
            this.displaydata = this.displaydata.slice(0, -1);
        },
        clear() {
            this.displaydata = "";
        },
        setData(data) {
            this.displaydata = data;
        },
        calculate() {
            let result;
            try {
                result = eval(this.displaydata).toString()
            } catch (e) {
                console.log("oida")
            }

            useCalculationLogStore().insert(this.displaydata, result);
            this.displaydata = result;
        }
    }
})