<template>
    <v-card>
        <v-flex v-if="type==='image'" xs6 offset-sm3>
            <a :href="message.link">
                <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="1.75"></v-img>
                <v-layout justify-center>Источник</v-layout>
            </a>
        </v-flex>

        <v-flex v-if="type==='href'" xs6 offset-sm3>
            <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="2.75"></v-img>
            <v-card-title>
                <a :href="message.link">{{message.linkTitle || message.link}}&nbsp;</a>
                <div v-if="message.linkDescription">{{message.linkDescription}}</div>
            </v-card-title>
        </v-flex>

        <v-flex v-if="type==='youtube'" xs6 offset-sm3>
            <v-layout class="" justify-center column>
                <v-flex>
                    <you-tube :src="message.link"></you-tube>
                </v-flex>
                <v-flex offset-sm5>
                    <a :href="message.link">Источник</a>
                </v-flex>
            </v-layout>
        </v-flex>
    </v-card>

</template>

<script>
    import YouTube from "media/YouTube.vue";

    export default {
        components: {YouTube},
        props: ['message'],
        component: {
            YouTube
        },
        data() {
            return {
                type: 'href'
            }
        },
        name: "Media",
        beforeMount() {
            if (this.message.link.indexOf('youtu') > -1) {
                this.type = 'youtube'
            } else if (this.message.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
                this.type = 'image'
            } else {
                this.type = 'href'
            }

        }
    }
</script>

<style scoped>

</style>