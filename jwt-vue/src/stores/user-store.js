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
            console.log(response)
        })
    }

    const logout = async () => {
        await axios.post("/auth/logout")
        .then( (response) => {

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
})
