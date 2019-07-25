<template>
    <v-container>
        <v-layout row>
            <v-text-field label="Новый комментарий"
                          placeholder="Комментарий" v-model="text" @keyup.enter="save"/>
            <v-btn @click="save" icon>
                <v-icon>save</v-icon>
            </v-btn>
        </v-layout>
    </v-container>

</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        props: ['messageId'],
        data() {
            return {
                text: ''
            }
        },
        name: "CommentsForm",
        methods: {
            ...mapActions(['addCommentsActions']),
            save() {
                console.log('commentForm')
                this.addCommentsActions({
                    text: this.text,
                    message: {
                        id: this.messageId
                    }
                })
                this.text = ''
            }
        }

    }
</script>

<style scoped>

</style>