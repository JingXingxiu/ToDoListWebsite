import { defineStore } from "pinia";
import axios from 'axios';

export const useAuthStore=defineStore('auth',{
    state:()=>({
        // username:null,       ???
        token: localStorage.getItem('token')?.trim()||null,
        isLogin:false
    }),
    actions:{
        async login(username,password){
            const {data} =await axios.post('http://localhost:8080/api/user/login',{username,password});
            if(data.status!=200) throw new Error(data.message);
            this.token = data.token;
            localStorage.setItem('token', data.token);
            this.isLogin=true;
            return data;
        },
        async register(username,password){
            const {data}=await axios.post('http://localhost:8080/api/user/register',{username,password});
            if(data.status!=200) throw new Error(data.message);
            return data;
        }
    }
})