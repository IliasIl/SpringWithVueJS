<template>
    <v-container>
        <v-layout justify-space-around>
            <v-flex :xs6="!$vuetify.breakpoint.xsOnly">
                <div class="title mb-3">User profile</div>
                <v-layout justify-space-between row>
                    <v-flex class="px-1">
                        <v-img class="pic" :src="profile.userpic"></v-img>
                    </v-flex>
                    <v-flex class="px-1">

                        <!--<v-list>-->
                            <!--<v-list-tile>-->
                                <!--<v-list-tile-content>-->
                                    <!--<v-list-tile-title class="title">{{profile.name}}</v-list-tile-title>-->
                                    <!--<v-list-tile-subtitle>{{profile.email}}</v-list-tile-subtitle>-->
                                <!--</v-list-tile-content>-->
                            <!--</v-list-tile>-->
                            <!--<v-list-tile>-->
                                <!--<v-list-tile-content>-->
                                    <!--<v-list-tile-text>{{profile.lastVisit}}</v-list-tile-text>-->
                                    <!--<v-list-tile-text>{{profile.gender}}</v-list-tile-text>-->
                                    <!--<v-list-tile-text>{{profile.locale}}</v-list-tile-text>-->
                                <!--</v-list-tile-content>-->
                            <!--</v-list-tile>-->
                        <!--</v-list>-->

                        <v-layout column>
                            <v-flex>{{profile.name}}</v-flex>
                            <v-flex>{{profile.email}}</v-flex>
                            <v-flex>{{profile.lastVisit}}</v-flex>
                            <v-flex>{{profile.gender}}</v-flex>
                            <v-flex>{{profile.locale}}</v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        computed: mapState(['profile']),
        name: "Profile"
    }
</script>

<style scoped>
    .pic {
        width: 100px;
        height: auto;
    }
</style>