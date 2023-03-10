import axios, { type AxiosResponse } from "axios";
import { defineStore } from "pinia";
import { useTokenStore } from "./token";

type BackendHistoryData = {
  calculations: HistoryEntry[]
}

export const useHistoryStore = defineStore("history", {
  state: () => {
    return {
      data: [] as HistoryEntry[],
    };
  },

  actions: {
    insert(entry: HistoryEntry) {
      this.data.push(entry);
    },
    getLatestAns(): number {
      return this.data[this.data.length - 1].result;
    },
    getFromBackend() {
      const tokenStore = useTokenStore();
      if (tokenStore.token.length == 0) {
        return;
      }

      const config = {
        headers: {
          "Content-type": "application/json",
          "Authorization": "Bearer " + tokenStore.token,
        },
      };

      axios.get(
        import.meta.env.VITE_BACKEND_URL + "/history/" + tokenStore.loggedInAs,
        config,
      ).then((response: AxiosResponse<BackendHistoryData, any>) => {
        this.data = response.data.calculations;
      });
    },
  },
});
