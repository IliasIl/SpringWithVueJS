import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'pages/MessageList.vue'
import Profile from 'pages/Profile.vue'
import Auth from 'pages/Auth.vue'
import SubscriberList from 'pages/SubscribersList.vue'


Vue.use(VueRouter)

const routes = [
    {path: '/', component: MessageList},
    {path: '/auth', component: Auth},
    {path: '/user/:id?', component: Profile},
    {path: '/subscribers/:id', component: SubscriberList},
    {path: '*', component: MessageList}

]

export default new VueRouter({
    mode: 'history',
    routes
})