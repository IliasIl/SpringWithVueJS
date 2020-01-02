<template>
    <v-container>
        <v-layout justify-align-center>
            <v-list v-for="item in subscribers" :key="item.subscriberId.id">
                <v-list-tile>
                    <link-profile :author="item.subscriberId" size="24"></link-profile>
                    <v-btn @click="changeStatus(item.subscriberId.id)">
                        {{item.active? 'Dismiss':'Approve'}}
                    </v-btn>
                </v-list-tile>
            </v-list>
        </v-layout>
    </v-container>
</template>

<script>
    import profileApi from 'api/profile'
    import {mapState} from 'vuex'
    import LinkProfile from 'components/LinkProfile.vue'

    export default {
        components: {
            LinkProfile
        },
        name: "SubscribersList",
        computed: mapState(['profile']),
        data() {
            return {
                subscribers: []
            }
        },
        watch: {
            '$route'() {
                this.reloadPage()
                console.log('sfv')
            }
        },
        methods: {
            async changeStatus(userId) {
                await profileApi.changeStatus(userId)

                const index = this.subscribers.findIndex(item => item.subscriberId.id === userId)
                const element = this.subscribers[index]
                this.subscribers = [...this.subscribers.slice(0, index),
                    {...element, active: !element.active},
                    ...this.subscribers.slice(index + 1)]

            },
            async reloadPage() {
                const resp = await profileApi.allSubscribers(this.profile.id)
                this.subscribers = await resp.json()
            }

        },
        beforeMount() {
            this.reloadPage()
        }
    }
</script>

<style scoped>

</style>