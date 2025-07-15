<script setup>
import { ref } from 'vue';
import { useToDoListStore } from '../stores/todolist';

const store = useToDoListStore();

// 创建三个独立的输入值
const username = ref('');
const email = ref('');
const password = ref('');

// 直接调用store中已实现的updateUser方法
const updateSetting = (type, value) => {
  store.updateUser(type, value);
  
  // 更新后清空对应输入框
  if (type === 'username') username.value = '';
  if (type === 'email') email.value = '';
  if (type === 'password') password.value = '';
};
</script>

<template>
  <div class="settings-container">
    <h2>用户设置</h2>
    
    <div class="setting-item">
      <div class="input-group">
        <input 
          type="text" 
          v-model="username" 
          placeholder="新用户名"
          @keyup.enter="updateSetting('username', username)"
        >
        <button @click="updateSetting('username', username)">
          更新用户名
        </button>
      </div>
    </div>
    
    <div class="setting-item">
      <div class="input-group">
        <input 
          type="email" 
          v-model="email" 
          placeholder="新邮箱"
          @keyup.enter="updateSetting('email', email)"
        >
        <button @click="updateSetting('email', email)">
          更新邮箱
        </button>
      </div>
    </div>
    
    <div class="setting-item">
      <div class="input-group">
        <input 
          type="password" 
          v-model="password" 
          placeholder="新密码"
          @keyup.enter="updateSetting('password', password)"
        >
        <button @click="updateSetting('password', password)">
          更新密码
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.settings-container {
  max-width: 500px;
  margin: 20px auto;
  padding: 20px;
  background: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #2c3e50;
}

.setting-item {
  margin-bottom: 15px;
}

.input-group {
  display: flex;
  gap: 10px;
}

input {
  flex: 1;
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

button {
  padding: 10px 15px;
  background: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background 0.2s;
}

button:hover {
  background: #2980b9;
}
</style>