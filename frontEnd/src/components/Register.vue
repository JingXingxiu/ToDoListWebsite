<script setup>
import {reactive} from 'vue';
import {useRouter} from 'vue-router';
import {useAuthStore} from '../stores/auth'

const user = reactive({
  username: '',
  password: ''
});

const router = useRouter();
const authStore = useAuthStore();

const register = async function() {
  try {
    await authStore.register(user.username, user.password);
    alert('注册成功!');
    router.push('/login');
  } catch(error) {
    console.error(error);
    alert('注册失败: ' + error.message);
  }
}
</script>

<template>
  <div class="register-container">
    <div class="register-card">
      <div class="register-header">
        <h1>用户注册</h1>
        <p>创建您的ToDoList账户</p>
      </div>
      
      <form @submit.prevent="register" class="register-form">
        <div class="form-group">
          <label for="username">用户名</label>
          <input 
            id="username" 
            name="username" 
            type="text" 
            v-model="user.username"
            placeholder="请输入用户名"
            required
          >
          <i class="fas fa-user"></i>
        </div>
        
        <div class="form-group">
          <label for="password">密码</label>
          <input 
            id="password" 
            name="password" 
            type="password" 
            v-model="user.password"
            placeholder="请输入密码"
            required
          >
          <i class="fas fa-lock"></i>
        </div>
        
        <button type="submit" class="register-btn">
          注册账号
          <i class="fas fa-arrow-right"></i>
        </button>
      </form>
      
      <div class="login-link">
        已有账号? <a @click="router.push('/login')">立即登录</a>
      </div>
    </div>
  </div>
</template>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #6e8efb, #a777e3);
  padding: 20px;
}

.register-card {
  width: 100%;
  max-width: 450px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.15);
  overflow: hidden;
  padding: 40px 30px;
}

.register-header {
  text-align: center;
  margin-bottom: 30px;
}

.register-header h1 {
  color: #2c3e50;
  font-size: 28px;
  margin-bottom: 10px;
}

.register-header p {
  color: #7f8c8d;
  font-size: 16px;
}

.register-form {
  margin-bottom: 25px;
}

.form-group {
  position: relative;
  margin-bottom: 25px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #2c3e50;
  font-weight: 500;
  font-size: 14px;
}

.form-group input {
  width: 100%;
  padding: 14px 15px 14px 45px;
  border: 2px solid #e0e6ed;
  border-radius: 10px;
  font-size: 16px;
  transition: all 0.3s ease;
}

.form-group input:focus {
  border-color: #3498db;
  box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
  outline: none;
}

.form-group i {
  position: absolute;
  left: 15px;
  top: 42px;
  color: #7f8c8d;
  font-size: 18px;
}

.register-btn {
  width: 100%;
  padding: 14px;
  background: linear-gradient(to right, #3498db, #2ecc71);
  color: white;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

.register-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 7px 15px rgba(52, 152, 219, 0.4);
}

.register-btn:active {
  transform: translateY(1px);
}

.login-link {
  text-align: center;
  color: #7f8c8d;
  font-size: 14px;
}

.login-link a {
  color: #3498db;
  text-decoration: none;
  cursor: pointer;
  font-weight: 500;
  transition: color 0.2s;
}

.login-link a:hover {
  color: #2980b9;
  text-decoration: underline;
}

@media (max-width: 480px) {
  .register-card {
    padding: 30px 20px;
  }
  
  .register-header h1 {
    font-size: 24px;
  }
  
  .form-group input {
    padding: 12px 15px 12px 40px;
  }
  
  .form-group i {
    top: 40px;
    font-size: 16px;
  }
}
</style>