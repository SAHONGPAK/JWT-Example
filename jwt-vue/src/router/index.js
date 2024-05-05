import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/MainView.vue'

import UserView from '@/views/users/UserView.vue'
import LoginComponent from '@/components/users/LoginComponent.vue'
import SignUpComponent from '@/components/users/SignUpComponent.vue'
import MyPageComponent from '@/components/users/MyPageComponent.vue'

import BoardView from '@/views/boards/BoardView.vue'



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView
    },
    {
      path: '/user',
      name: 'user',
      component: UserView,
      children: [
        {
          path: 'login',
          name: 'login',
          component: LoginComponent
        },
        {
          path: 'signUp',
          name: 'signUp',
          component: SignUpComponent
        },
        {
          path: 'myPage',
          name: 'myPage',
          component: MyPageComponent
        }
      ]
    },
    {
      path: '/board',
      name: 'board',
      component: BoardView
    }
  ]
})

export default router
