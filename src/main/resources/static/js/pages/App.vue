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
    export default {
        components: {
            MessageList
        },
        data() {
            return {
                profile: valuesMas.profile,
                messages: valuesMas.messages
            }
        }
    }
</script>

<style>

</style>