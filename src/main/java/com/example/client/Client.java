package com.example.client;

public class Client {
    public static void main(String[] args) {
        TodoService service = new TodoService();
        TodoService port = service.getTodoServicePort();

        System.out.println(port.ping());

        Todo todo = new Todo();
        todo.setTitle("Buy milk");
        todo.setDescription("2% fat");

        port.addTodo(todo);
        System.out.println("Todos: " + port.getTodos().size());
    }
}
