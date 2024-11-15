import { createRouter, createWebHistory } from 'vue-router'
import Lab3 from '../components/Lab3.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Lab3
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
