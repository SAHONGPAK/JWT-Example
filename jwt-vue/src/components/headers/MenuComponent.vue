<script setup>
    import { ref } from 'vue'
    import { RouterLink, useRouter } from 'vue-router'
    import { useUserStore } from '@/stores/user-store'

    const router = useRouter()
    const userStore = useUserStore()

    const menuList = ref([
        {name: "로그인", routeName: "login"},
        {name: "회원가입", routeName: "signUp"},
        {name: "게시판", routeName: "board"},
        {name: "마이페이지", routeName: "myPage"},
        {name: "로그아웃", routeName: "logout"},
    ])

    const logout = async () => {
        await userStore.logout()
        //router.push('/')
    }

</script>

<template>
    <v-app-bar-nav-icon icon="mdi-menu" />

    <v-app-bar-title
        text="JWT Example"
        class="v-col-3"
        @click="$router.push({name: 'main'})"
    />

    <v-list class="v-col-8">
        <v-row class="justify-end">
            <template v-for="menu in menuList" :key="menu.name">
                <v-list-item>
                    <template v-if="menu.routeName === 'logout'">
                        <RouterLink to="/" @click.prevent="logout">{{ menu.name }}</RouterLink>
                    </template>
                    <template v-else>
                        <RouterLink :to="{name: menu.routeName }">{{ menu.name }}</RouterLink>
                    </template>
                </v-list-item>
            </template>
        </v-row>
    </v-list>
</template>

<style scoped>
</style>