package com.springboot.webapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("papu");
        model.addAttribute("todos",todos);
        return "listTodos";
    }

    @RequestMapping(value= "add-todos", method = RequestMethod.GET)
    public String showNewTodos(ModelMap model) {
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo",todo);
        return "todo";
    }   
    
    @RequestMapping(value= "add-todos", method = RequestMethod.POST)
    public String addNewTodos(ModelMap model, Todo todo) {
        String username = (String)model.get("name");
        todoService.addTodos(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
        return "redirect:list-todos";
    }  
}
