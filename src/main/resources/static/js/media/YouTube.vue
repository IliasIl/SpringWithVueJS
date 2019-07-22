<template>
    <iframe width="560"
            height="315"
            :src="computedLink" frameborder="0"
            allow="accelerometer; autoplay;
            encrypted-media; gyroscope;
            picture-in-picture" allowfullscreen></iframe>
</template>

<script>
    export default {
        props: ['src'],
        name: "YouTube",
        computed: {
            computedLink() {
                let k = this.src.split('/');
                return `https://www.youtube.com/embed/${k[k.length - 1]}`
            }
        }

    }
</script>

<style scoped>

</style>