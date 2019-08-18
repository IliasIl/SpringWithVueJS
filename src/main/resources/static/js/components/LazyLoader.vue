<template>
    <span></span>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {

        name: "LazyLoader",
        methods: {
            ...mapActions(['loadMessages']),
        },
        mounted() {
            window.onscroll = () => {
                const el = document.documentElement
                const isBottomOfScreen = el.scrollTop + window.innerHeight === el.offsetHeight
                if (isBottomOfScreen) {
                    this.loadMessages()
                }
            }
        },
        beforeDestroy() {
            window.onscroll = null
        }
    }
</script>

<style scoped>

</style>