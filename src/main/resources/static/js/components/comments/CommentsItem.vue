<template>
    <v-list-tile>
        <link-profile :author="comment.author"/>
        <v-list-tile-content>
            <v-list-tile-title>
                <span>{{comment.text}}</span>
            </v-list-tile-title>
        </v-list-tile-content>
    </v-list-tile>
</template>

<script>
    import LinkProfile from "components/LinkProfile.vue";

    export default {
        name: "CommentsItem",
        components: {LinkProfile},
        props: ['comment']
    }
</script>

<style scoped>

</style>