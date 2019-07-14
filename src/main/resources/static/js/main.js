import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import {connect} from './util/ws'


Vue.use(VueResource)
if (valuesMas.profile) {
    connect()
}
new Vue({
    el: '#app',
    render: a => a(App)
})
