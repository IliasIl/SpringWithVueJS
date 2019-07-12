<template>
    <div><i>{{message.id}}</i> {{message.text}}
        <span style="position: absolute; right: 0px;">
        <input type="button" value="Edit" @click="edit"/>
        <input type="button" value="X" @click="del"/>
        </span>
    </div>
</template>

<script>
    export default {
        props: ['messages', 'message', 'editMes', 'deleteMes'],
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


