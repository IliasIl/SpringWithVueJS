<template>
    <div style="position: relative; width: 300px;">
        <message-form :messageA="message" :messages="messages"></message-form>
        <message-row
                v-for="message in messages"
                :message="message"
                :editMes="editMes"
                :messages="messages"
                :key="message.id"
                :deleteMes="deleteMes">
        </message-row>
    </div>
</template>

<script>
    import MessageForm from 'components/MessageForm.vue'
    import MessageRow from 'components/MessageRow.vue'

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
        props: ['messages'],
        methods: {
            editMes(message) {
                this.message = message
            },
            deleteMes(message){
                this.$resource('/message{/id}').remove({id: message.id}).then(result => {
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
