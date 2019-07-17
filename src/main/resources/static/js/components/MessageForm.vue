<template>
    <v-layout row>
        <v-text-field label="Новое сообщение"
                      placeholder="Введите сообщение"
                      v-model="text"/>
        <v-btn icon @click="save">
            <v-icon>save</v-icon>
        </v-btn>
    </v-layout>
</template>

<script>

    import {sendMessage} from 'util/ws'


    export default {
        props: ['messages', 'messageA'],
        data() {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            messageA(newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id;
            }

        },
        methods: {
            save() {
                sendMessage({id: this.id, text: this.text})
                this.id = ''
                this.text = ''
                /*
                const mes = {text: this.text};
                if (this.id) {
                    this.$resource('/message{/id}').update({id: this.id}, mes)
                        .then(result => result.json()
                            .then(res => {
                                    var index = getIndex(this.messages, this.id);
                                    this.messages.splice(index, 1, res);
                                    this.id = '';
                                    this.text = '';
                                }
                            ));

                } else {
                    this.$resource('/message{/id}').save({}, mes)
                        .then(result => result.json().then(data => this.messages.push(data)));
                    this.text = ''
                }*/
            }
        }
    }
</script>

<style>


</style>
