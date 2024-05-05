<script setup>
    import { RouterView } from 'vue-router'
    import HeaderView from '@/views/includes/HeaderView.vue'
</script>

<template>
    <v-layout>
        <HeaderView />

        <RouterView />
    </v-layout>
</template>

<style scoped>
</style>