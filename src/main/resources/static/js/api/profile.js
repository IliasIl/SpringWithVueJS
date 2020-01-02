import Vue from 'vue'

const profile = Vue.resource('/profile{/id}')

export default {
    get: id => profile.get({id}),
    editSubscribe: id => Vue.http.post(`/profile/subscribe-user/${id}`),
    allSubscribers: channelId => Vue.http.get(`/profile/get-subscribers/${channelId}`),
    changeStatus: subscriberId => Vue.http.post(`/profile/change-status/${subscriberId}`)
}