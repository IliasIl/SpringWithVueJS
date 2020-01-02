<template>
    <v-container>
        <v-layout justify-space-around>
            <v-flex :xs6="!$vuetify.breakpoint.xsOnly">
                <div class="title mb-3">User profile</div>
                <v-layout justify-space-between row>
                    <v-flex class="px-1">
                        <v-img class="pic" :src="profile.userpic"></v-img>
                    </v-flex>
                    <v-flex class="px-1">

                        <!--<v-list>-->
                        <!--<v-list-tile>-->
                        <!--<v-list-tile-content>-->
                        <!--<v-list-tile-title class="title">{{profile.name}}</v-list-tile-title>-->
                        <!--<v-list-tile-subtitle>{{profile.email}}</v-list-tile-subtitle>-->
                        <!--</v-list-tile-content>-->
                        <!--</v-list-tile>-->
                        <!--<v-list-tile>-->
                        <!--<v-list-tile-content>-->
                        <!--<v-list-tile-text>{{profile.lastVisit}}</v-list-tile-text>-->
                        <!--<v-list-tile-text>{{profile.gender}}</v-list-tile-text>-->
                        <!--<v-list-tile-text>{{profile.locale}}</v-list-tile-text>-->
                        <!--</v-list-tile-content>-->
                        <!--</v-list-tile>-->
                        <!--</v-list>-->

                        <v-layout column>
                            <v-flex>{{profile.name}}</v-flex>
                            <v-flex>{{profile.lastVisit}}</v-flex>
                            <v-flex>{{profile.gender}}</v-flex>
                            <v-flex>{{profile.locale}}</v-flex>

                            <router-link v-if="myProfile" :to="`/subscribers/${profile.id}`">
                                <span>Подписчики: {{profile.subscribers && profile.subscribers.length}}</span>
                            </router-link>
                            <v-flex v-else><span>Подписчики:&nbsp;</span>{{profile.subscribers &&
                                profile.subscribers.length}}
                            </v-flex>

                            <v-flex>
                                <span>Подписки:&nbsp;</span>{{profile.subscription && profile.subscription.length}}
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
                <v-btn v-if="!myProfile" @click="changeSubscribs">
                    {{isSubscribe ? 'Отписаться': 'Подписаться'}}
                </v-btn>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import profileApi from 'api/profile'

    export default {
        data() {
            return {
                profile: {}
            }
        },
        name: "Profile",
        watch: {
            '$route'() {
                this.reloadProfile()
            }
        },
        computed: {
            isSubscribe() {
                return this.profile.subscribers &&
                    this.profile.subscribers.find(subscriber => subscriber.subscriberId === this.$store.state.profile.id)

            },
            myProfile() {
                return !this.$route.params.id || this.$route.params.id === this.$store.state.profile.id
            }
        },
        methods: {
            async reloadProfile() {
                const id = this.$route.params.id || this.$store.state.profile.id
                const result = await profileApi.get(id)
                this.profile = await result.json()
            },
            async changeSubscribs() {
                const id = this.profile.id
                const result = await profileApi.editSubscribe(id)
                this.profile = await result.json()
            }
        },
        beforeMount() {
            this.reloadProfile()
        }
    }
</script>

<style scoped>
    .pic {
        width: 100px;
        height: auto;
    }
</style>