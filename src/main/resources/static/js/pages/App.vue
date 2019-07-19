<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title class="tols">IliterVue&nbsp;<v-icon>face</v-icon>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
            <span v-if="!profile">
                <span>Войти через </span>&nbsp;<a href="/login">Google</a>
            </span>
        </v-toolbar>

        <v-content>
            <v-container v-if="profile">
                <message-list/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import MessageList from 'components/MessageList.vue'
    import {addHandler} from 'util/ws'
    import {mapState, mapMutations} from 'vuex'

    export default {
        components: {
            MessageList
        },
        computed:  mapState(['profile']),
        methods: {
            ...mapMutations(['addMessagesMutations', 'updateMessagesMutations', 'removeMessagesMutations'])
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
        }
    }
</script>

<style>
    .tols {
        color: indigo;
    }
</style>