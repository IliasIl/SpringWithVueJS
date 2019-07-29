<template>
    <v-list two-line>
        <v-subheader>
            Comments
        </v-subheader>
        <template v-for="(item, index) in comments">
            <v-divider v-if="index>0" :key="index"/>
            <comment-item :comment="item" :key="'item'+index"/>
        </template>
    </v-list>
</template>

<script>
    import CommentItem from 'components/comments/CommentsItem.vue'
    import CommentForm from 'components/comments/CommentsForm.vue'
    export default {
        components: {
          CommentItem, CommentForm
        },
        name: "CommentsList",
        props: ['comments', 'messageId']

    }
</script>

<style scoped>

</style>