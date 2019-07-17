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
                <message-list :messages="messages"/>
            </v-container>
        </v-content>
    </v-app>
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
                    } else {
                        this.messages.push(data)
                    }
                }
            ),
            addHandler(data => {
                    let index = getIndex(this.messages, data.id)
                    this.messages.splice(index, 1);

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