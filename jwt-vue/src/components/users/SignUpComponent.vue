<script setup>
    import { ref, inject, computed } from 'vue'
    
    const axios = inject('axios')

    const userInfo = ref({
        userEmail: '',
        userName: '',
        userPassword: '',
        retryUserPassword: ''
    })

    const checkEmailMessage = ref('')
    const checkEmailMessageColor = ref('')

    const checkUserEmail = async () => {
        await axios.get(`/user/checkEmail/${userInfo.value.userEmail}`)
        .then( (response) => {
            checkEmailMessage.value = '사용할 수 있는 이메일 입니다.'
            checkEmailMessageColor.value = 'text-green'
        })
        .catch( (error) => {
            checkEmailMessage.value = '사용할 수 없는 이메일 입니다.'
            checkEmailMessageColor.value = 'text-red'
        })
    }

    const isSamePassword = computed( () => {
        if(userInfo.value.userPassword != userInfo.value.retryUserPassword) {
            return false;
        }
    })

    const passwordVisible = ref(false)
    const passwordCheckVisible = ref(false)

    const signUp = async () => {
        await axios.post('/user/signUp', userInfo.value)
        .then( (response) => {
            console.log(response)
        })
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
            @keyup="checkUserEmail"
        />
        <template v-if="checkEmailMessage !== ''">
            <span :class="checkEmailMessageColor">{{ checkEmailMessage }}</span>
        </template>


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
            v-model="userInfo.retryUserPassword"
        />

        <template v-if="isSamePassword === false">
            <span class="text-red">비밀번호가 일치하지 않습니다.</span>
        </template>

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