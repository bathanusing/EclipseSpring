package com.firstAplication.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.firstAplication.springboot.web.model.Todo;
import com.firstAplication.springboot.web.services.TodoServices;

@Controller
@SessionAttributes("name")
public class TodoController {
	// Injected automatically es decir injeccion de dependencias
	@Autowired
	TodoServices services;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodoList(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", services.retrieveTodos(name));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		return (String) model.get("name");
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "", new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String RedireccionAlistaTodos(@Valid Todo todo, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "todo";
		}
		String name = getLoggedInUserName(model);
		services.addTodo(name, todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		if (id == 1)
			throw new RuntimeException();
		services.deleteTodo(id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = services.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "todo";
		}
		// Todo todoRetrieve=services.retrieveTodo(todo.getId());
		String name = getLoggedInUserName(model);
		todo.setUser(name);
		services.updateTodo(todo);

		model.put("todo", todo);
		return "redirect:/list-todos";
	}
}
