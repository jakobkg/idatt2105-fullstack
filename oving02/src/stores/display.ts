import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useDisplayStore = defineStore('display', {
  state: () => {
    return {
      data: ref("")
    }
  },
  actions: {
    insert(symbol: string) {
      this.data += symbol;
    }
  }
})
