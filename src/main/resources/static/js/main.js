import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import App from 'pages/App.vue'
import store from 'store/store'
import router from 'router/router'
import {connect} from 'util/ws'
import 'vuetify/dist/vuetify.min.css'

if (valuesMas.profile) {
    connect()
}
Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)

})
