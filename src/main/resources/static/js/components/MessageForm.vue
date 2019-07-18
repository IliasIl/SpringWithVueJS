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
    import messageApi from 'api/messages'

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
                const mes = {
                    id: this.id,
                    text: this.text
                };
                if (this.id) {
                    messageApi.update(mes)
                        .then(result => result.json()
                            .then(res => {
                                    let index = this.messages.findIndex(elem => elem.id === res.id)
                                    this.messages.splice(index, 1, res);
                                }
                            ));

                } else {
                    messageApi.add(mes)
                        .then(result => result.json().then(data => {
                            let index = this.messages.findIndex(elem => elem.id === data.id);
                            if (index > -1) {
                                this.messages.splice(index, 1, data)
                            } else {
                                this.messages.push(data)
                            }
                        }));
                }
                this.id = ''
                this.text = ''
            }
        }
    }
</script>

<style>


</style>
