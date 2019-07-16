<template>
    <div><i>{{message.id}}</i> {{message.text}}
        <span style="position: absolute; right: 0px;">
        <input type="button" value="Edit" @click="edit"/>
        <input type="button" value="X" @click="del"/>
        </span>
    </div>
</template>

<script>
    import {deleteMessage} from "util/ws";

    export default {
        props: ['messages', 'message', 'editMes', 'deleteMes'],
        methods: {
            del() {
               deleteMessage(this.message)
            },
            edit() {
                this.editMes(this.message);
            }
        }
    }
</script>

<style>

</style>


