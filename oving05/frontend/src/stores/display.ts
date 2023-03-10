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
      const expression = this.data;
      const request = { expression: expression };

      var config = {
        method: "post",
        maxBodyLength: Infinity,
        url: "http://localhost:2332/calc",
        headers: {
          "Content-Type": "application/json",
        },
        data: request,
      };

      axios(config)
        .then(function (response) {
          if (response.data.status !== "OK") {
            document.getElementsByClassName("warning")[0].innerHTML = response.data.status;
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
