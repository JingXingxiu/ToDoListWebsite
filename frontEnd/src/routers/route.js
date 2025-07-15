import {createRouter,createWebHashHistory} from 'vue-router'

import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import ToDoList from '../components/ToDoList.vue';
import Setting from '../components/Setting.vue';
const router=createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:'/',
            redirect:'/login'
        },
        {
            path:'/login',
            component:Login,
            name:'login'
        },
        {
            path:'/register',
            component:Register,
            name:'register'
        },
        {
            path:'/todolist',
            component:ToDoList,
            name:'ToDoList'
        },
        {
            path:'/setting',
            component:Setting,
            name:'setting'
        }
    ],
});


export default router;