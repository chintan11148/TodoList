package com.springboot.webapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class TodoControllerJpa {

    private TodoRepository todoRepository;

    public TodoControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoggedInUsername(model);

        List<Todo> todos = todoRepository.findByUsername(username);
        model.addAttribute("todos",todos);
        return "listTodos";
    }

    @RequestMapping(value= "add-todos", method = RequestMethod.GET)
    public String showNewTodos(ModelMap model) {
        String username = getLoggedInUsername(model);
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo",todo); 
        return "todo";
    }   
    
    @RequestMapping(value= "add-todos", method = RequestMethod.POST)
    public String addNewTodos(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }

        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        todoRepository.save(todo);

        //todoService.addTodos(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }  

    @RequestMapping("delete-todos")
    public String deleteTodo(@RequestParam int id) {
        
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }   

    @RequestMapping(value= "update-todos", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo";
    }  

    @RequestMapping(value= "update-todos", method = RequestMethod.POST)
    public String updateNewTodos(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }

        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }  

    private String getLoggedInUsername(ModelMap model) {
        Authentication authentication = 
            SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();  
    }
}
