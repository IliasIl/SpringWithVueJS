import Vue from 'vue'
import Vuex from 'vuex'
import MessageApi from 'api/messages'
import commentApi from 'api/comments'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        messages: messagesString,
        ...valuesMas
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
            const message = state.messages[index]
            if (index > -1) {
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
            }
        },
        loadMessagesInfo(state, message) {
            const defaultMessages = state.messages.concat(message).reduce((res, val) => {
                res[val.id] = val
                return res
            }, {})
            state.messages = Object.values(defaultMessages)
        },
        changeTotalPages(state, totalPages) {
            state.totalPages = totalPages
        },
        changeCurrentPage(state, currentPage) {
            state.currentPage = currentPage
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
        },
        async loadMessages({commit, state}) {
            if (state.totalPages !== state.currentPage) {
                const result = await MessageApi.page(state.currentPage + 1)
                const data = await result.json()
                console.log(`state.totalPages:=${state.totalPages} and state.currentPage:=${state.currentPage}`)
                commit('loadMessagesInfo', data.messages)
                commit('changeTotalPages', data.totalPages)
                commit('changeCurrentPage', Math.min(data.totalPages, data.currentPage))
            }
        }

    }

})