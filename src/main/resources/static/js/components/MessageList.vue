<template>
    <v-layout align-space-around justify-start column>
        <message-form :messageA="message" :messages="messages"></message-form>
        <message-row
                v-for="message in sortedMessages"
                :message="message"
                :editMes="editMes"
                :messages="messages"
                :key="message.id"
                :deleteMes="deleteMes">
        </message-row>
    </v-layout>
</template>

<script>
    import MessageForm from 'components/MessageForm.vue'
    import MessageRow from 'components/MessageRow.vue'
    import messageApi from 'api/messages'

    export default {
        components: {
            MessageForm,
            MessageRow
        },
        data() {
            return {
                message: null
            }
        },
        computed: {
            sortedMessages() {
                return this.messages.sort((a, b) => -(a.id - b.id))
            }
        },
        props: ['messages'],
        methods: {
            editMes(message) {
                this.message = message
            },
            deleteMes(message) {
                messageApi.remove(message.id).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(message), 1)
                    }
                })
            }
        }
    }

</script>

<style>


</style>
