import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import AppGame from '../views/AppGame.vue'
import WebApp from '../views/WebApp.vue'
import Description from '../views/Description.vue'



const routes = [
  {
    path: '/',
    name: 'Home',
    components: {
      body: Home,
    }
  },
  {
    path: '/appgame',
    name: 'AppGame',
    components: {
      body: AppGame,
    }
  }, {
    path: '/webapp',
    name: 'WebApp',
    components: {
      body: WebApp,
    }
  },
  {
    path: '/:id',
    name: 'Description',
    components: {
      body: Description,
    },
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  mode: 'history',
})

export default router
