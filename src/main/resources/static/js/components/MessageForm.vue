<template>
    <v-layout row>
        <v-text-field label="Новое сообщение"
                      placeholder="Введите сообщение"
                      v-model="text"
                      @keyup.enter="save"/>
        <v-btn icon @click="save">
            <v-icon>save</v-icon>
        </v-btn>
    </v-layout>
</template>
<script>
    import {mapActions} from 'vuex'

    export default {
        props: ['messageA'],
        data() {
            return {
                text: '',
                id: null
            }
        },
        watch: {
            messageA(newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id;
            }

        },
        methods: {
            ...mapActions(['addMessagesActions', 'updateMessagesActions']),
            save() {
                const mes = {
                    id: this.id,
                    text: this.text
                };
                if (this.id) {
                    this.updateMessagesActions(mes)
                } else {
                    this.addMessagesActions(mes)
                }
                this.id = null
                this.text = ''
            }
        }
    }
</script>

<style>


</style>
