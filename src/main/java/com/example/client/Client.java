package com.example.client;

import com.example.todoservice.Todo;
import com.example.todoservice.TodoService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class Client {
    public static void main(String[] args) throws Exception {
        URL wsdlURL = new URL("http://localhost:8080/todo-service/TodoService?wsdl");
        QName qname = new QName("http://todoservice.example.com/", "TodoService");

        Service service = Service.create(wsdlURL, qname);
        TodoService todoService = service.getPort(TodoService.class);

        todoService.addTodo(new Todo(1, "Write SOAP service"));
        todoService.addTodo(new Todo(2, "Test client"));

        List<Todo> todos = todoService.getTodos();
        for (Todo t : todos) {
            System.out.println(t.getId() + ": " + t.getTask());
        }
    }
}
