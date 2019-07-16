import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import {connect} from 'util/ws'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify)
Vue.use(VueResource)
if (valuesMas.profile) {
    connect()
}
new Vue({
    el: '#app',
    render: a => a(App)
})
