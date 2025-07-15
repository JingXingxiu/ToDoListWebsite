<script setup>
import { ref, reactive, onMounted } from 'vue';
import { useToDoListStore } from '../stores/todolist';

const store = useToDoListStore();
const loading = ref(false);
const error = ref('');
const editIndex = ref(-1); // 当前正在编辑的索引
const editTodo = reactive({
  title: "",
  description: "",
  completed: 0
});

const newTodo = reactive({
  title: "",
  description: "",
  completed: 0
});

function clearNewTodo() {
  newTodo.title = '';
  newTodo.description = '';
  newTodo.completed = 0;
}

const fetchTodos = async () => {
  try {
    loading.value = true;
    await store.fetchTodos();
    error.value = '';
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};

const addTodo = async () => {
  if (!newTodo.title.trim()) {
    error.value = '标题不能为空';
    return;
  }
  
  try {
    loading.value = true;
    await store.addTodo(newTodo.title, newTodo.description, Number(newTodo.completed));
    clearNewTodo();
    error.value = '';
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};

const startEdit = (index) => {
  const todo = store.toDoLists[index];
  editIndex.value = index;
  editTodo.title = todo.title;
  editTodo.description = todo.description;
  editTodo.completed = todo.completed;
};

const cancelEdit = () => {
  editIndex.value = -1;
};

const updateTodo = async (index) => {
  const todo = store.toDoLists[index];
  
  try {
    loading.value = true;
    await store.updateTodo(
      todo.uniqueID,
      editTodo.title,
      editTodo.description,
      editTodo.completed
    );
    editIndex.value = -1;
    error.value = '';
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};

const deleteTodo = async (uniqueID) => {
  try {
    loading.value = true;
    await store.deleteTodo(uniqueID);
    error.value = '';
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};

// 初始化时加载数据
onMounted(fetchTodos);
</script>

<template>
  <div class="todo-container">
    <h1>我的待办事项</h1>
    
    <!-- 添加新待办事项 -->
    <div class="todo-input">
      <div>
        <input v-model="newTodo.title" placeholder="任务标题" />
        <textarea v-model="newTodo.description" placeholder="任务描述"></textarea>
        <label>
          <input type="checkbox" v-model="newTodo.completed" true-value=1 false-value=0 />
          已完成
        </label>
      </div>
      <button @click="addTodo">添加任务</button>
    </div>

    <!-- 加载状态 -->
    <div v-if="loading" class="loading">加载中...</div>

    <!-- 错误提示 -->
    <div v-if="error" class="error">{{ error }}</div>

    <!-- 任务列表 -->
    <ul class="todo-list" v-if="store.toDoLists.length">
      <li v-for="(todo, index) in store.toDoLists" :key="todo.uniqueID">
        <template v-if="editIndex === index">
          <div class="edit-form">
            <input v-model="editTodo.title" />
            <textarea v-model="editTodo.description"></textarea>
            <label>
              <input type="checkbox" v-model="editTodo.completed" true-value="1" false-value="0" />
              已完成
            </label>
            <div class="edit-actions">
              <button @click="updateTodo(index)">保存</button>
              <button @click="cancelEdit">取消</button>
            </div>
          </div>
        </template>
        <template v-else>
          <div class="todo-item">
            <div class="todo-content">
              <h3 :class="{ completed: todo.completed === 1 }">{{ todo.title }}</h3>
              <p v-if="todo.description">{{ todo.description }}</p>
            </div>
            <div class="todo-actions">
              <span class="status">{{ todo.completed === 1 ? '已完成' : '未完成' }}</span>
              <button @click="startEdit(index)">编辑</button>
              <button @click="deleteTodo(todo.uniqueID)">删除</button>
            </div>
          </div>
        </template>
      </li>
    </ul>
    
    <div v-else class="empty-list">
      <p>暂无待办事项，请添加您的第一项任务！</p>
    </div>
  </div>
</template>

<style scoped>
.todo-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 30px;
}

.todo-input {
  background: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.todo-input input, 
.todo-input textarea {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
}

.todo-input textarea {
  min-height: 80px;
  resize: vertical;
}

.todo-input button {
  background-color: #42b983;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s;
}

.todo-input button:hover {
  background-color: #3aa373;
}

.todo-list {
  list-style: none;
  padding: 0;
}

.todo-list li {
  background: white;
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 10px;
  box-shadow: 0 1px 3px rgba(0,0,0,0.1);
}

.todo-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.todo-content h3 {
  margin: 0 0 8px 0;
  font-size: 18px;
}

.todo-content p {
  margin: 0;
  color: #666;
}

.completed {
  text-decoration: line-through;
  color: #999;
}

.todo-actions {
  display: flex;
  align-items: center;
  gap: 10px;
}

.status {
  font-size: 14px;
  color: #42b983;
  font-weight: bold;
}

.edit-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.edit-actions {
  display: flex;
  gap: 10px;
  margin-top: 10px;
}

button {
  padding: 6px 12px;
  border-radius: 4px;
  border: none;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s;
}

button:first-of-type {
  background-color: #3498db;
  color: white;
}

button:first-of-type:hover {
  background-color: #2980b9;
}

button:last-of-type {
  background-color: #ff4757;
  color: white;
}

button:last-of-type:hover {
  background-color: #ff2e43;
}

.loading,
.error,
.empty-list {
  text-align: center;
  padding: 20px;
  font-size: 16px;
}

.error {
  color: #ff4757;
  background: #fff0f1;
  border-radius: 4px;
  margin: 10px 0;
}
</style>