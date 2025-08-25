package com.springboot.webapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

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
    public String addNewTodos(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }

        String username = (String)model.get("name");
        todoService.addTodos(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
        return "redirect:list-todos";
    }  

    @RequestMapping("delete-todos")
    public String deleteTodo(@RequestParam int id) {
        
        todoService.deleteById(id);
        return "redirect:list-todos";
    }   

    @RequestMapping(value= "update-todos", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }  

    @RequestMapping(value= "update-todos", method = RequestMethod.POST)
    public String updateNewTodos(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }

        String username = (String)model.get("name");
        todo.setUsername(username);
        todoService.updateTodos(todo);
        return "redirect:list-todos";
    }  
}
