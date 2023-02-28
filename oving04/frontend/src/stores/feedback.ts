import { defineStore } from "pinia";

export const useFeedbackStore = defineStore("feedback", {
  state: () => {
    return {
      name: "",
      email: ""
    };
  },
  actions: {
    setName(name: string) {
        this.name = name
    },
    setEmail(email: string) {
        this.email = email
    }
  },
});
