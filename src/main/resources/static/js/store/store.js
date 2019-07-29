import Vue from 'vue'
import Vuex from 'vuex'
import MessageApi from 'api/messages'
import commentApi from 'api/comments'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        messages: messagesString,
        profile: valuesMas.profile

    },
    getters: {

        sortedMessages: state => (state.messages || []).sort((a, b) => -(a.id - b.id))

    },
    mutations: {
        addMessagesMutations(state, message) {
            const index = state.messages.findIndex(a => a.id === message.id)
            if (index > -1) {

            } else {
                state.messages = [...state.messages, message]
            }
        },
        updateMessagesMutations(state, message) {
            const index = state.messages.findIndex(a => a.id === message.id)
            state.messages = [...state.messages.slice(0, index), message, ...state.messages.slice(index + 1)]
        },
        removeMessagesMutations(state, message) {
            const index = state.messages.findIndex(a => a.id === message.id)
            if (index > -1) {
                state.messages = [...state.messages.slice(0, index), ...state.messages.slice(index + 1)]
            }
        },
        addCommentsMutations(state, comment) {
            const index = state.messages.findIndex(a => a.id === comment.messageId)
            console.log(`${comment.message.id} text of the ${comment.messageId}`)
            const message = state.messages[index]


            if (message.comments) {
                if (message.comments.findIndex(a => a.id === comment.id) === -1) {
                    state.messages = [...state.messages.slice(0, index), {
                        ...message,
                        comments: [...message.comments, comment
                        ]
                    }, ...state.messages.slice(index + 1)]
                }
            } else {
                state.messages = [...state.messages.slice(0, index),
                    {...message, comments: [comment]}, ...state.messages.slice(index + 1)]
            }
        },

    },
    actions: {
        async addMessagesActions({commit, state}, message) {
            const result = await MessageApi.add(message)
            const data = await result.json()
            const index = state.messages.findIndex(a => a.id === data.id)
            if (index > -1) {
                commit('updateMessagesMutations', data)
            } else {
                commit('addMessagesMutations', data)
            }
        },
        async updateMessagesActions({commit, state}, message) {
            const result = await MessageApi.update(message)
            const data = await result.json()
            commit('updateMessagesMutations', data)
        },
        async removeMessagesActions({commit, state}, message) {
            const result = await MessageApi.remove(message.id)
            if (result.ok) {
                commit('removeMessagesMutations', message)
            }
        },
        async addCommentsActions({commit, state}, comment) {
            const result = await commentApi.add(comment)
            const data = await result.json()
            commit('addCommentsMutations', data)
        }

    }

})