<script lang="ts">
export default {
    data: () => {
        return {
            name: "",
            email: "",
            feedback: "",
            processing: false,
            received: false
        }
    },
    computed: {
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
            this.processing = true;

            if (!this.verifyEmail) {
                this.processing = false;
                return;
            }

            const payload = JSON.stringify({ name: this.name, email: this.email, message: this.feedback });

            fetch(import.meta.env.VITE_BACKEND_URL + "/feedback", {
                headers: { 'content-type': 'application/json' },
                method: "POST",
                body: payload
            }).then((_: Response) => {
                this.name = "";
                this.email = "";
                this.feedback = "";
                this.received = true;
            }, (reason) => {
                console.warn(reason);
            });

            this.processing = false;
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
        <button v-bind:disabled="!allFilled || processing" @click="submit">Send</button>
        <p v-bind:style="!received ? { 'display': 'none' } : {}">Takk!</p>
    </main>
</template>

<style scoped>
main {
    display: flex;
    flex-direction: column;
}
</style>