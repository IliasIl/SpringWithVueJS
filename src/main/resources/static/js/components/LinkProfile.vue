<template>
    <router-link :to="`/user/${author.id}`" class="px-2">
        <v-avatar v-if="author && author.userpic"
                  :size="`${size || 30}px`">
            <v-img :src="author.userpic" :alt=" author.name"/>
        </v-avatar>
        <v-avatar :size="`${size || 30}px`" color="indigo" v-else>
            <v-icon dark>account_circle</v-icon>
        </v-avatar>
        <span class="pl-2"> {{compMessage}}</span>
    </router-link>
</template>

<script>
    export default {
        name: "LinkProfile",
        props: ['author', 'size'],
        computed: {
            compMessage() {
                return this.author ? this.author.name : 'unknown'
            }
        }
    }
</script>

<style scoped>

</style>