<script lang="ts">
import axios from "axios";
import router from "../router/index"

type LoginAPIData = {
    status: boolean
}

export default {
    data() {
        return {
            username: "",
            password: ""
        }
    },
    methods: {
        login(e: MouseEvent) {
            e.preventDefault();
            axios.get<LoginAPIData>("http://localhost:3000/login")
                .then(response => {
                    if (response.data.status) {
                        router.push("home")
                    } else {
                        // Login failure, alert user to check username/password
                    }
                }).catch((rejection: PromiseRejectionEvent) => {
                    // API failure, notify user that something is wrong and it might
                    // be network related and not a wrong username/password
                })
        }
    }
}
</script>

<template>
    <main>
        <h1 class="green">Please log in</h1>
        <form name="login">
            <label for="username">Username:</label>
            <input type="text" placeholder="Username" v-model="username">
            <label for="password">Password:</label>
            <input type="password" placeholder="Password" v-model="password">
            <button v-on:click="login">Log In</button>
        </form>
    </main>
</template>

<style scoped lang="scss">
main {
    text-align: center;
}

h1 {
    align-self: center;
    justify-self: center;
}

form {
    display: grid;
    grid-template-columns: max-content 1fr;
    padding-inline: 10%;

    label {
        padding-right: 25px;
    }

    input {
        max-width: 50%;
    }
}
</style>