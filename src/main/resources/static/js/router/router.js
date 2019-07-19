import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'pages/MessageList.vue'
import Profile from 'pages/Profile.vue'
import Auth from 'pages/Auth.vue'

Vue.use(VueRouter)

const routes = [
    {path: '/', component: MessageList},
    {path: '/auth', component: Auth},
    {path: '*', component: MessageList},
    {path: '/profile', component: Profile}


]

export default new VueRouter({
    mode: 'history',
    routes
})