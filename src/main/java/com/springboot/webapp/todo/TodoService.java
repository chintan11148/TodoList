package com.springboot.webapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int todoCount = 0;

    static{
        todos.add(new Todo(++todoCount, "Udemy", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todoCount, "Udemy", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todoCount, "Udemy", "Learn MLOps", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodos(String username, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todoCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id){
        
        Predicate<? super Todo> predicate = todo -> todo.getId() == id; //lambda function (functional programming)
        todos.removeIf(predicate);
        --todoCount;
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id; //lambda function (functional programming)
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;        
    }

    public void updateTodos(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
