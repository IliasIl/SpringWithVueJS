import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
new Vue({
    el: '#app',
    render: a=> a(App)
})


/*
function getIndex(list, el) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === el) {
            return i;
        }
    }
    return -1;
};

var res = Vue.resource('/message{/id}');


*/
