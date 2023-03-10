<script lang="ts">
import router from '@/router';
import { useTokenStore } from '@/stores/token';
import { useHistoryStore } from '@/stores/history';

export default {
    setup() {
        const tokenStore = useTokenStore();
        const historyStore = useHistoryStore();
        return {
            tokenStore,
            historyStore
        }
    },
    data() {
        return {
            username: "",
            password: "",
            statusMsg: "",
        }
    },
    methods: {
        async login() {
            await this.tokenStore.getToken(this.username, this.password);

            if (this.tokenStore.token.length != 0 ) {
                this.historyStore.getFromBackend();
                router.push("/");
            } else {
                this.statusMsg = "Kunne ikke logge inn";
            }
        }
    }
}
</script>

<template>
    <main>
        <div class="login-modal">
            <h1>Logg inn</h1>
            <div class="loginfield">
                <label for="username">Brukernavn: </label>
                <input v-model="username" name="username" type="text">
            </div>
            <div class="loginfield">
                <label for="password">Passord: </label>
                <input v-model="password" name="password" type="password">
            </div>
            <div class="loginfield">
                <button @click="login">Logg inn</button>
            </div>
            <p>{{ statusMsg }}</p>
        </div>
    </main>
</template>

<style lang="scss">
main {
    min-width: 90%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
}

.login-modal {
    padding: 1rem;
    border: 1px solid black;
    border-radius: 1rem;
}

.loginfield {
    display:flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;

    padding-bottom: 1rem;

    label {
        min-width: 13rem;
    }
}
</style>