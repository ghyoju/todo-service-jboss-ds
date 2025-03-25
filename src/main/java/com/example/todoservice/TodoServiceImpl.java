package com.example.todoservice;

import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javax.sql.DataSource;
import java.util.List;

@WebService(
    serviceName = "TodoService",
    portName = "TodoPort",
    targetNamespace = "http://todoservice.example.com/",
    endpointInterface = "com.example.todoservice.TodoService",
    wsdlLocation = "WEB-INF/wsdl/TodoService.wsdl"
)
public class TodoServiceImpl implements TodoService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource(lookup = "java:/TodoDS")
    private DataSource dataSource;

    @Override
    @Transactional
    public String addTodo(String task) {
        Todo todo = new Todo();
        todo.setTask(task);
        em.persist(todo);
        return "Task added with ID: " + todo.getId();
    }

    @Override
    @Transactional
    public String getTodos() {
        List<Todo> todos = em.createQuery("SELECT t FROM Todo t", Todo.class)
                           .getResultList();
        return todos.toString();
    }
    
    @Override
    @Transactional
    public String getTodo(long id) {
        Todo todo = em.find(Todo.class, id);
        if (todo == null) {
            return "Todo with ID " + id + " not found";
        }
        return todo.toString();
    }
}