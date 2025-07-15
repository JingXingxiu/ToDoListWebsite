import { defineStore } from "pinia";
import axios from "axios";
export const useToDoListStore = defineStore('toDoList', {
  state: () => ({
    token: localStorage.getItem('token')?.trim() || '',
    toDoLists: [], // 状态名称改为复数
    flag:true,
  }),
  actions: {
        async fetchTodos() {
        const { data } = await axios.get('http://localhost:8080/api/todolist/fetch', {
          headers: { Authorization: `Bearer ${this.token}` }
        });
        if (data.status !== 200) throw new Error(data.errorMessage);
        this.toDoLists = data.toDoLists;
    },

    async addTodo(title, description, completed) {
        const { data } = await axios.post('http://localhost:8080/api/todolist/add', {
          title,
          description,
          completed
        }, {
          headers: { Authorization: `Bearer ${this.token}` }
        });
        if (data.status !== 201) throw new Error(data.errorMessage);
        let uniqueID=data.uniqueID;
        this.toDoLists.push({
            title,
            description,
            completed,
            uniqueID:data.uniqueID
        }); // 将新待办项添加到列表
    },
    
    async updateTodo(uniqueID, title, description, completed) {
        const { data } = await axios.put('http://localhost:8080/api/todolist/update', {
          uniqueID,
          title,
          description,
          completed
        }, {
          headers: { Authorization: `Bearer ${this.token}` }
        });
        if (data.status !== 201) throw new Error(data.errorMessage);
        
        // 更新本地列表
        const index = this.toDoLists.findIndex(todo => todo.uniqueID === uniqueID);
        if (index !== -1) {
          this.toDoLists[index] = { ...this.toDoLists[index], title, description, completed };
        }
    },
    
    async deleteTodo(uniqueID) {
        const { data } = await axios.delete('http://localhost:8080/api/todolist/delete', {
          headers: { Authorization: `Bearer ${this.token}` },
          data:{uniqueID}
        });
        
        if (data.status !== 204) throw new Error(data.errorMessage);
        // 从本地列表删除
        this.toDoLists = this.toDoLists.filter(todo => todo.uniqueID !== uniqueID);
    },

    async updateUser(type,change){
        const {data}=await axios.put('http://localhost:8080/api/todolist/setting',{
            headers:{ Authorization: `Bearer ${this.token}`},
            type,
            change
        });
        if(data.statue!==201) throw new Error(data.errorMeassage);
    }
  }
});

//       前端逻辑解析
//       ​状态管理​：
//       todos: 存储所有待办事项的数组（响应式）
//       newTodo: 存储新待办事项的输入内容
//       loading: 控制加载状态的布尔值
//       error: 存储错误信息
//       token: 从 localStorage 获取的认证令牌
//       ​核心功能​：
//       ​初始化加载​：onMounted 钩子自动调用 fetchTodos
//       ​获取列表​：fetchTodos 发送 GET 请求获取所有待办事项
//       ​添加事项​：addTodo 发送 POST 请求并更新本地列表
//       ​切换状态​：toggleTodo 发送 PATCH 请求更新完成状态
//       ​删除事项​：deleteTodo 发送 DELETE 请求并过滤本地列表
//       ​UI 交互​：
//       输入框支持回车键提交
//       每个事项显示复选框、文本和删除按钮
//       已完成事项会有特殊样式（.completed）

//修改：