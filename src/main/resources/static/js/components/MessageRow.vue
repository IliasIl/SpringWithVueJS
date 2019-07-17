<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <i>{{message.id}}</i>
            {{message.text}}
        </v-card-text>
        <v-card-actions>
            <v-btn small round @click="edit">Редактировать</v-btn>
            <v-btn icon value="X" @click="del">
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import {deleteMessage} from "util/ws";

    export default {
        props: ['messages', 'message', 'editMes', 'deleteMes'],
        methods: {
            del() {
                deleteMessage(this.message)
            },
            edit() {
                this.editMes(this.message);
            }
        }
    }
</script>

<style>

</style>


