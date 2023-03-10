<script lang="ts">
import { useFeedbackStore } from '@/stores/feedback';
import { mapWritableState } from 'pinia';
import axios from "axios";

export default {
    data: () => {
        return {
            feedback: "",
            statusmessage: "",
        }
    },
    computed: {
        ...mapWritableState(useFeedbackStore, ['name', 'email']),
        allFilled(): boolean {
            return this.name.trim().length > 0
                && this.email.trim().length > 0
                && this.feedback.trim().length > 0
                && this.verifyEmail
        },
        verifyEmail(): boolean {
            const at_split = this.email.split('@');

            if (at_split.length != 2) {
                return false
            }

            if ((at_split[1].match(/\./g) || []).length < 1) {
                return false;
            }

            const dot_split = this.email.split('.');

            if (dot_split[dot_split.length - 1].length < 2) {
                return false
            }

            return true;
        }
    },
    methods: {
        submit(_: MouseEvent): void {
            axios.post("http://localhost:3000/feedback", {
                name: this.name,
                email: this.email,
                feedback: this.feedback
            }).then(response => {
                if (response.status >= 200 && response.status < 300) {
                    this.statusmessage = "Takk for din tilbakemelding";
                }
            }).catch((reason) => {
                this.statusmessage = "Noe gikk galt ved levering, serveren sa: " + reason;
            })
        }
    }
}

</script>

<template>
    <main>
        <form>
            <label for="name">Navn</label>
            <input id="name" v-model="name" placeholder="Ditt navn" />
            <label for="email">Email</label>
            <input v-bind:style="!verifyEmail ? { 'border': '2px solid red' } : {}" id="email" v-model="email"
                placeholder="Din email" type="email" />
            <label for="feedback">Melding</label>
            <textarea id="feedback" v-model="feedback" placeholder="Din tilbakemelding" rows="5" cols="25" />
        </form>
        <button v-bind:disabled="!allFilled" @click="submit">Send</button>
        <p>{{ statusmessage }}</p>
    </main>
</template>

<style scoped>
main {
    display: flex;
    flex-direction: column;
}
</style>