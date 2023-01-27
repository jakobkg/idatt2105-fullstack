import { defineStore } from "pinia";

export const useHistoryStore = defineStore("history", {
  state: () => {
    return {
      data: [] as HistoryEntry[],
    };
  },
  actions: {
    insert(entry: HistoryEntry) {
      this.data.push(entry);
      console.log(entry);
    },
    getLatestAns(): number {
      return this.data[this.data.length - 1].result;
    },
  },
});
