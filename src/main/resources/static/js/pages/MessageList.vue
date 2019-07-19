<template>
    <v-container>
        <v-layout align-space-around justify-start column>
            <message-form :messageA="message"></message-form>
            <message-row
                    v-for="message in sortedMessages"
                    :message="message"
                    :editMes="editMes"
                    :key="message.id"
                    :deleteMes="deleteMes">
            </message-row>
        </v-layout>
    </v-container>
</template>

<script>
    import MessageForm from '../components/MessageForm.vue'
    import MessageRow from '../components/MessageRow.vue'
    import {mapActions, mapGetters} from 'vuex'

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
        computed: mapGetters(['sortedMessages']),
        methods: {
            ...mapActions(['removeMessagesActions']),
            editMes(message) {
                this.message = message
            },
            deleteMes(message) {
                this.removeMessagesActions(message)
            }
        }
    }

</script>

<style>


</style>
