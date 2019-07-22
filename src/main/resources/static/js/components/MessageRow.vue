<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <i>{{message.id}}</i>
            {{message.text}}
        </v-card-text>
        <v-container v-if="message.link">
            <media :message="message"></media>
        </v-container>

        <v-card-actions>
            <v-btn small round @click="edit">Редактировать</v-btn>
            <v-btn icon value="X" @click="del">
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import media from 'media/Media.vue'
    export default {
        components: {
          media
        },
        props: ['message', 'editMes', 'deleteMes'],
        methods: {
            del() {
                this.deleteMes(this.message)
            },
            edit() {
                this.editMes(this.message);
            }
        }
    }
</script>

<style>

</style>


