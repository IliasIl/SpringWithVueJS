<template>
    <v-list-tile>
        <v-list-tile-avatar size="35px"><v-img :src="comment.author.userpic"></v-img></v-list-tile-avatar>
        <v-list-tile-content>
            <v-list-tile-title>
                {{comment.text}}
            </v-list-tile-title>
            <v-list-tile-sub-title>{{comment.author.name}}</v-list-tile-sub-title>
        </v-list-tile-content>
    </v-list-tile>
</template>

<script>
    export default {
        name: "CommentsItem",
        props: ['comment']
    }
</script>

<style scoped>

</style>