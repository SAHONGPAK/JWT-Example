<script setup>
    import { ref } from 'vue'

    const userInfo =  ref({
        userEmail: '',
        currentUserPassword: '',
        newUserPassword: ''
    })

    const currentPasswordVisible = ref(false)
    const newPasswordVisible = ref(false)

    const modify = async () => {

    }

    const withdrawal = async () => {

    }

</script>

<template>
    <v-card
        class="myPage-form mx-auto pa-8"
        elevation="8"
        rounded="lg"
    >

        <div class="text-subtitle-1 text-medium-emphasis">계정</div>

        <v-text-field
            density="compact"
            prepend-inner-icon="mdi-email-outline"
            variant="outlined"
            readonly
            v-model="userInfo.userEmail"
        ></v-text-field>


        <div class="text-subtitle-1 text-medium-emphasis">이름</div>

        <v-text-field
            density="compact"
            prepend-inner-icon="mdi-account-tag"
            variant="outlined"
            readonly
            v-model="userInfo.userName"
        ></v-text-field>


        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
            현재 비밀번호
        </div>

        <v-text-field
            :append-inner-icon="currentPasswordVisible ? 'mdi-eye-off' : 'mdi-eye'"
            :type="currentPasswordVisible ? 'text' : 'password'"
            density="compact"
            placeholder="현재 비밀번호"
            prepend-inner-icon="mdi-lock-outline"
            variant="outlined"
            @click:append-inner="currentPasswordVisible = !currentPasswordVisible"
            v-model="userInfo.currentUserPassword"
        ></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
            새로운 비밀번호
        </div>

        <v-text-field
            :append-inner-icon="newPasswordVisible ? 'mdi-eye-off' : 'mdi-eye'"
            :type="newPasswordVisible ? 'text' : 'password'"
            density="compact"
            placeholder="새로운 비밀번호"
            prepend-inner-icon="mdi-lock-outline"
            variant="outlined"
            @click:append-inner="newPasswordVisible = !newPasswordVisible"
            v-model="userInfo.newUserPassword"
        ></v-text-field>

        <v-btn
            block
            class="mb-8"
            color="blue"
            size="large"
            variant="tonal"
            @click="modify"
        >
            수정하기
        </v-btn>

        <v-btn
            block
            class="mb-8"
            color="red"
            size="large"
            variant="tonal"
            @click="$router.push({name: 'main'})"
        >
            취소
        </v-btn>

        <v-btn
            block
            class="mb-8"
            color="red-darken-4"
            size="large"
            @click="withdrawal"
        >
            회원 탈퇴
        </v-btn>
    </v-card>
</template>

<style scoped>
</style>