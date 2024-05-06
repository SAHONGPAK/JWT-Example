import { ref , inject } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('userStore', () => {

    /**
     * State
     */
    const axios = inject('axios')


    /**
     * actions
    */
    const login = async (userInfo) => {
        await axios.post('/auth/login', userInfo, { withCredentials: true })
        .then( (response) => {
            axios.defaults.headers.common['Authorization'] = response.headers.authorization
        })
        .catch( (error) => {
            console.log(error)
        })
    }

    const logout = async () => {
        await axios.delete("/auth/logout", { withCredentials : true })
        .then( (response) => {
            axios.defaults.headers.common['Authorization'] = ''
        })
    }

    const signUp = async () => {
       await axios.post("/user/signUp")
       .then( (response) => {

       }) 
    }

    const withdrawal = async () => {
        await axios.delete('/user/withdrawal')
        .then( (response) => {

        })
    }

    return {
        login,
        logout,
        signUp,
        withdrawal
    }
})
