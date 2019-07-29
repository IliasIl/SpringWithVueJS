<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <div class="pb-2">
                <v-avatar v-if="message.author && message.author.userpic"
                          size="36px">
                    <v-img :src="message.author.userpic" :alt="message.author.name"/>
                </v-avatar>
                <v-avatar size="35px" color="indigo" v-else>
                    <v-icon dark>account_circle</v-icon>
                </v-avatar>
                <span class="pl-2"> {{compMessage}}</span>
            </div>
            <div class="mx-5">
                {{message.text}}
            </div>
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
        <comment-list :message-id="message.id" :comments="message.comments"/>
        <comment-form :message-id="message.id"/>
    </v-card>
</template>

<script>
    import media from 'media/Media.vue'
    import CommentList from 'components/comments/CommentsList.vue'
    import CommentForm from 'components/comments/CommentsForm.vue'

    export default {
        components: {
            media, CommentList, CommentForm
        },
        props: ['message', 'editMes', 'deleteMes'],
        methods: {
            del() {
                this.deleteMes(this.message)
            },
            edit() {
                this.editMes(this.message);
            }
        },
        computed: {
            compMessage() {
                return this.message.author ? this.message.author.name : 'unknown'
            }
        }
    }
</script>

<style>

</style>


