<script setup>
import {reactive,ref} from 'vue';
import {useRouter} from 'vue-router';
import {useAuthStore} from '../stores/auth'

const user = reactive({
  username: '',
  password: ''
});

const tipInformation = ref('');
const router = useRouter();
const authStore = useAuthStore();

const login = async function() {
  try {
    const data = await authStore.login(user.username, user.password);
    alert("登录成功");
    router.push('/todolist');
  } catch(error) {
    alert("登录失败");
    tipInformation.value = error.message || "用户名或密码错误";
  }
}
</script>

<template>
  <div class="login-container">
    <div class="login-card">
      <div class="login-header">
        <div class="logo">
          <i class="fas fa-tasks"></i>
          <span>ToDoList</span>
        </div>
        <h1>用户登录</h1>
        <p>欢迎回来，请登录您的账户</p>
      </div>
      
      <form @submit.prevent="login" class="login-form">
        <div class="form-group">
          <label for="username">用户名</label>
          <div class="input-with-icon">
            <i class="fas fa-user"></i>
            <input 
              id="username" 
              name="username" 
              type="text" 
              v-model="user.username"
              placeholder="请输入用户名"
              required
            >
          </div>
        </div>
        
        <div class="form-group">
          <label for="password">密码</label>
          <div class="input-with-icon">
            <i class="fas fa-lock"></i>
            <input 
              id="password" 
              name="password" 
              type="password" 
              v-model="user.password"
              placeholder="请输入密码"
              required
            >
          </div>
        </div>
        
        <div v-if="tipInformation" class="error-tip">
          <i class="fas fa-exclamation-circle"></i> {{ tipInformation }}
        </div>
        
        <button type="submit" class="login-btn">
          登录账号
          <i class="fas fa-arrow-right"></i>
        </button>
      </form>
      
      <div class="register-link">
        没有账号？<a @click="router.push('/register')">立即注册</a>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #1e5799, #207cca);
  padding: 20px;
}

.login-card {
  width: 100%;
  max-width: 450px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  padding: 40px 35px;
}

.logo {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  margin-bottom: 20px;
  color: #3498db;
  font-size: 24px;
  font-weight: 600;
}

.logo i {
  font-size: 28px;
}

.login-header {
  text-align: center;
  margin-bottom: 30px;
}

.login-header h1 {
  color: #2c3e50;
  font-size: 28px;
  margin-bottom: 10px;
}

.login-header p {
  color: #7f8c8d;
  font-size: 16px;
}

.login-form {
  margin-bottom: 25px;
}

.form-group {
  margin-bottom: 25px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #2c3e50;
  font-weight: 500;
  font-size: 14px;
}

.input-with-icon {
  position: relative;
}

.input-with-icon i {
  position: absolute;
  left: 15px;
  top: 50%;
  transform: translateY(-50%);
  color: #7f8c8d;
  font-size: 18px;
}

.input-with-icon input {
  width: 100%;
  padding: 14px 15px 14px 45px;
  border: 2px solid #e0e6ed;
  border-radius: 10px;
  font-size: 16px;
  transition: all 0.3s ease;
}

.input-with-icon input:focus {
  border-color: #3498db;
  box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
  outline: none;
}

.error-tip {
  background: #fef3f2;
  color: #f04438;
  padding: 12px 15px;
  border-radius: 8px;
  margin: 15px 0;
  font-size: 14px;
  display: flex;
  align-items: center;
  gap: 8px;
}

.login-btn {
  width: 100%;
  padding: 14px;
  background: linear-gradient(to right, #3498db, #2980b9);
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

.login-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 7px 15px rgba(52, 152, 219, 0.4);
}

.login-btn:active {
  transform: translateY(1px);
}

.register-link {
  text-align: center;
  color: #7f8c8d;
  font-size: 14px;
}

.register-link a {
  color: #3498db;
  text-decoration: none;
  cursor: pointer;
  font-weight: 500;
  transition: color 0.2s;
}

.register-link a:hover {
  color: #2980b9;
  text-decoration: underline;
}

@media (max-width: 480px) {
  .login-card {
    padding: 30px 20px;
  }
  
  .login-header h1 {
    font-size: 24px;
  }
  
  .input-with-icon input {
    padding: 12px 15px 12px 40px;
  }
}
</style>