import axios from "axios";
import { defineStore } from "pinia";

export const useTokenStore = defineStore("token", {
  state: () => {
    return {
      token: "",
      loggedInAs: "",
    };
  },
  persist: {
    storage: localStorage
  },
  actions: {
    async getToken(username: string, password: string) {
      const config = {
        headers: {
          "content-type": "application/json",
        },
      };

      let response = await axios.post(
        import.meta.env.VITE_BACKEND_URL + "/token",
        JSON.stringify({ username, password }),
        config,
      );

      if (
        response.data != null &&
        response.data != undefined &&
        response.data != ""
      ) {
        this.token = response.data;
        this.loggedInAs = username;
      }
    },
  },
});
