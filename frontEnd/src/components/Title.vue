<script setup>
import { ref, watch } from 'vue';
import { useAuthStore } from '../stores/auth';
import { useRouter } from 'vue-router';

const router = useRouter();
const authStore = useAuthStore();
const isChecked = ref('Chinese');
const flag = ref(true);

watch(isChecked, (newVal) => {
  flag.value = newVal === "Chinese";
});

function changeTodoList() {
  router.push('/todolist');
}

function changeSetting() {
  router.push('/setting');
}
</script>

<template>
  <header class="app-header">
    <div class="header-content">
      <div class="logo">
        <i class="fas fa-tasks"></i>
        <span>ToDoList</span>
      </div>
      
      <div class="language-switcher">
        <label :class="{ active: isChecked === 'English' }">
          <input 
            type="radio" 
            name="language" 
            v-model="isChecked" 
            value="English"
          >
          English
        </label>
        <label :class="{ active: isChecked === 'Chinese' }">
          <input 
            type="radio" 
            name="language" 
            v-model="isChecked" 
            value="Chinese"
          >
          中文
        </label>
      </div>
    </div>
    
    <div class="welcome-section" v-if="!authStore.isLogin">
      <h1>{{ flag ? '欢迎来到ToDoList' : 'Welcome to the toDoList' }}</h1>
      <p>{{ flag 
          ? '这是一个用于记录你要做的和你已经做的的应用' 
          : 'It is a app to help you recode what you need to do and what you have done.' 
      }}</p>
    </div>
    
    <div class="navigation-buttons" v-else>
      <button class="nav-btn" @click="changeTodoList">
        <i class="fas fa-list"></i>
        {{ flag ? '事件清单' : 'ToDoList' }}
      </button>
      <button class="nav-btn" @click="changeSetting">
        <i class="fas fa-cog"></i>
        {{ flag ? '设置' : 'Setting' }}
      </button>
    </div>
  </header>
</template>

<style scoped>
.app-header {
  background: linear-gradient(to right, #3498db, #2c3e50);
  color: white;
  padding: 20px;
  border-radius: 0 0 15px 15px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.logo {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 1.5rem;
  font-weight: 600;
}

.logo i {
  font-size: 1.8rem;
}

.language-switcher {
  display: flex;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 30px;
  padding: 5px;
}

.language-switcher label {
  padding: 8px 16px;
  border-radius: 30px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.language-switcher label.active {
  background: white;
  color: #3498db;
  font-weight: 500;
}

.language-switcher input[type="radio"] {
  display: none;
}

.welcome-section {
  text-align: center;
  padding: 20px 0;
}

.welcome-section h1 {
  font-size: 2.2rem;
  margin-bottom: 15px;
  font-weight: 600;
}

.welcome-section p {
  font-size: 1.1rem;
  max-width: 600px;
  margin: 0 auto;
  line-height: 1.6;
}

.navigation-buttons {
  display: flex;
  justify-content: center;
  gap: 20px;
  padding: 15px 0;
}

.nav-btn {
  padding: 12px 30px;
  border-radius: 50px;
  background: rgba(255, 255, 255, 0.15);
  color: white;
  border: 2px solid rgba(255, 255, 255, 0.3);
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 8px;
}

.nav-btn:hover {
  background: rgba(255, 255, 255, 0.25);
  transform: translateY(-3px);
}

.nav-btn i {
  font-size: 1.1rem;
}

@media (max-width: 768px) {
  .header-content {
    flex-direction: column;
    gap: 15px;
  }
  
  .navigation-buttons {
    flex-direction: column;
    align-items: center;
  }
  
  .nav-btn {
    width: 100%;
    max-width: 300px;
    justify-content: center;
  }
  
  .welcome-section h1 {
    font-size: 1.8rem;
  }
  
  .welcome-section p {
    font-size: 1rem;
  }
}
</style>