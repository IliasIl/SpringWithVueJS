<template>
    <div>
        <input type="text" placeholder="Введите сообщение" v-model="text"/>
        <input type="button" @click="save" value="Сохранить"/>
    </div>
</template>

<script>
    function getIndex(list, el) {
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === el) {
                return i
            }
        }
        return -1
    }

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
                }
            }
        }
    }
</script>

<style>


</style>
