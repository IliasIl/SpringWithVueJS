<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title class="tols">IliterVue&nbsp;<v-icon>face</v-icon>
            </v-toolbar-title>
            <v-btn round v-if="profile" @click="showMessages" flat :disabled="$route.path==='/'">Messages</v-btn>
            <v-spacer></v-spacer>
            <v-btn round v-if="profile" @click="showProfile" flat :disabled="$route.path==='/profile'">{{profile.name}}</v-btn>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-toolbar>

        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import {addHandler} from 'util/ws'
    import {mapMutations, mapState} from 'vuex'

    export default {
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addMessagesMutations', 'updateMessagesMutations', 'removeMessagesMutations']),
            showMessages (){this.$router.push('/')},
            showProfile (){this.$router.push('/profile')}

        },
        created() {
            addHandler(data => {
                    if (data.objectType === 'MESSAGE') {
                        switch (data.eventClass) {
                            case 'CREATE':
                                this.addMessagesMutations(data.payload)
                                break
                            case 'UPDATE':
                                this.updateMessagesMutations(data.payload)
                                break
                            case 'REMOVE':
                                this.removeMessagesMutations(data.payload)
                                break
                            default:
                                console.error(`Misunderstand type ${data.objectType}`)
                        }

                    } else {
                        console.error(`ERROR in handler: "${data.objectType}"`)
                    }
                }
            )
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }

        }
    }
</script>

<style>
    .tols {
        color: indigo;
    }
</style>