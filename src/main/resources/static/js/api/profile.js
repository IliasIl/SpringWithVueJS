import Vue from 'vue'

const profile = Vue.resource('/profile{/id}')

export default {
    get: id => profile.get({id}),
    editSubscribe: id => Vue.http.post(`/profile/subscribe-user/${id}`)
}