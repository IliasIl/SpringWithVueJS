function getIndex(list, el) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === el) {
            return i;
        }
    }
    return -1;
};

var res = Vue.resource('/message{/id}');

Vue.component('message-form', {
    props: ['messages', 'messageA'],
    data: function () {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        messageA: function (newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }

    },
    template: '<div>' +
        '<input type="text" placeholder="Введите сообщение" v-model="text"/>' +
        '<input type="button" @click="save" value="Сохранить"/>' +
        '</div>',
    methods: {
        save: function () {
            var mes = {text: this.text};
            if (this.id) {
                res.update({id: this.id}, mes)
                    .then(result => result.json()
                        .then(res => {
                                var index = getIndex(this.messages, this.id);
                                this.messages.splice(index, 1, res);
                                this.id = '';
                                this.text = '';
                            }
                        ));

            } else {
                res.save({}, mes)
                    .then(result => result.json().then(data => this.messages.push(data)));
                this.text = '';
            }
        }
    }
});

Vue.component('message-row', {
    props: ['messages', 'message', 'editMes'],
    template: '<div><i>{{message.id}}</i> {{message.text}}' +
        '<span style="position: absolute; right: 0px;" >' +
        '<input type="button" value="Edit" @click="edit"/>' +
        '<input type="button" value="X" @click="del"/>' +
        '</span>' +
        '</div>',
    methods: {
        del: function () {
            res.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1);
                }
            });
        },
        edit: function () {
            this.editMes(this.message);
        }
    }
});

Vue.component('message-list', {
    data: function () {
        return {
            message: null
        }
    },
    props: ['messages'],
    template: '<div style="position: relative; width: 300px;">' +
        '<message-form :messageA="message" :messages="messages"></message-form>' +
        '<message-row' +
        ' v-for="message in messages" :message="message" ' +
        ':editMes="editMes" :messages="messages" :key="message.id">' +
        '</message-row>' +
        '</div>',
    methods: {
        editMes: function (message) {
            this.message = message;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<div>' +
        '<div v-if="!profile">Необходимо войти через <a href="/login">Google</a></div>' +
        '<div v-else>' +
        '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>' +
        '<message-list :messages="messages"/>' +
        '</div>' +
        '</div>',
    data: {
        profile: valuesMas.profile,
        messages: valuesMas.messages
    },
    created: function () {
    }
});
