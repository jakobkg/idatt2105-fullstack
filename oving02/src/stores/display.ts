import { ref } from "vue";
import { defineStore } from "pinia";
import { useHistoryStore } from "./history";

export const useDisplayStore = defineStore("display", {
  state: () => {
    return {
      data: ref(""),
    };
  },
  actions: {
    insert(symbol: string) {
      this.data += symbol;
    },
    remove() {
      this.data = this.data.slice(0, -1);
    },
    clear() {
      this.data = "";
    },
    calculate() {
      try {
        const result = Number(Number(eval(this.data)).toPrecision(4));
        const expression = this.data;

        if ([undefined, null, NaN, Infinity].includes(result)) {
          throw new Error("fysjda");
        }

        useHistoryStore().insert({ expression: expression, result: result });

        this.data = result.toString();
      } catch (error) {
        console.warn("Nei og nei");
      }
    },
  },
});
