import SockJS from 'sockjs-client'
import {Stomp} from '@stomp/stompjs'

var stompClient = null
const handlers = []

export function connect() {
    //  const socket = new SockJS('/gs-guide-websocket')
    stompClient = Stomp.over(function () {
        return new SockJS('/gs-guide-websocket')
    })
    stompClient.debug= () => {}
    stompClient.connect({}, frame => {
        console.log('connect to' + frame)
        stompClient.subscribe('/topic/activity', message => {
            handlers[0](JSON.parse(message.body))
        })
        stompClient.subscribe('/topic/delete', message => {
            handlers[1](JSON.parse(message.body))
        })
    })
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect()
    }
    console.log("disconnected")
}

export function sendMessage(message) {
    stompClient.send("/app/changeMes", {}, JSON.stringify(message))
}

export function deleteMessage(message) {
    stompClient.send("/app/deleteMes", {}, JSON.stringify(message))
}