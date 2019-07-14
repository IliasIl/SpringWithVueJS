<template>
    <div>
        <div v-if="!profile">Необходимо войти через <a href="/login">Google</a></div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <message-list :messages="messages"/>
        </div>
    </div>
</template>

<script>
    import MessageList from 'components/MessageList.vue'
    import {addHandler} from 'util/ws'
    import {getIndex} from 'util/collections'

    export default {
        components: {
            MessageList
        },
        data() {
            return {
                profile: valuesMas.profile,
                messages: valuesMas.comp
            }
        },
        created() {
            addHandler(data => {
                    let index = getIndex(this.messages, data.id)
                    if (index > -1) {
                    this.messages.splice(index, 1, data)
                    } else{
                        this.messages.push(data)
                    }
                }
            )
        }
    }
</script>

<style>
</style>