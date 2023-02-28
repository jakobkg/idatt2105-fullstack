import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
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
      let operands: Array<Number> = [];
      let shouldBreak: boolean = false;
      let request: CalculationRequest = { operands: [], operator: "+" };
      const expression = this.data;

      for (const operator of ["+", "-", "/", "*"]) {
        if (this.data.includes(operator)) {
          shouldBreak = true;
          
          for (const token of this.data.split(operator)) {
            if (Number.isNaN(Number.parseFloat(token))) {
              continue;
            }
            
            operands.push(Number.parseFloat(token));
          }
          
          if (shouldBreak) {
            request = { operator: operator as Operator, operands: operands };
            break;
          }
        }
      }

      var config = {
        method: "post",
        maxBodyLength: Infinity,
        url: "http://localhost:8080/calc",
        headers: {
          "Content-Type": "application/json",
        },
        data: request,
      };

      axios(config)
        .then(function (response) {
          console.log(JSON.stringify(response.data));
          if (response.data.status === "ERR") {
            document.getElementsByClassName("warning")[0].innerHTML = "Ugyldig regnestykke :(";
          } else {
            useHistoryStore().insert({expression: expression, result: response.data.result});
            document.getElementsByClassName("warning")[0].innerHTML = "";
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
});
