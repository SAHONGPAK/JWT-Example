<script setup>
    import { ref } from 'vue'

    const userInfo = ref({

    })

    const passwordVisible = ref(false)
    const passwordCheckVisible = ref(false)

    const signUp = async () => {

    }

</script>

<template>
    <v-card
        class="signUp-form mx-auto pa-8"
        elevation="8"
        rounded="lg"
    >

        <div class="text-subtitle-1 text-medium-emphasis">계정</div>

        <v-text-field
            density="compact"
            placeholder="이메일"
            prepend-inner-icon="mdi-email-outline"
            variant="outlined"
            v-model="userInfo.userEmail"
        />


        <div class="text-subtitle-1 text-medium-emphasis">이름</div>

        <v-text-field
            density="compact"
            placeholder="이름"
            prepend-inner-icon="mdi-account-tag"
            variant="outlined"
            v-model="userInfo.userName"
        />


        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
            비밀번호
        </div>

        <v-text-field
            :append-inner-icon="passwordVisible ? 'mdi-eye-off' : 'mdi-eye'"
            :type="passwordVisible ? 'text' : 'password'"
            density="compact"
            placeholder="비밀번호"
            prepend-inner-icon="mdi-lock-outline"
            variant="outlined"
            @click:append-inner="passwordVisible = !passwordVisible"
            v-model="userInfo.userPassword"
        />

        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
            비밀번호 확인
        </div>

        <v-text-field
            :append-inner-icon="passwordCheckVisible ? 'mdi-eye-off' : 'mdi-eye'"
            :type="passwordCheckVisible ? 'text' : 'password'"
            density="compact"
            placeholder="비밀번호 확인"
            prepend-inner-icon="mdi-lock-outline"
            variant="outlined"
            @click:append-inner="passwordCheckVisible = !passwordCheckVisible"
        />

        <v-btn
            block
            class="mb-8"
            color="green"
            size="large"
            variant="tonal"
            @click="signUp"
        >
            회원가입
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
    </v-card>
</template>

<style scoped>
</style>