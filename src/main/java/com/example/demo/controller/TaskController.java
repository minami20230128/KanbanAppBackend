package com.example.demo.controller;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Task;
import com.example.demo.input.TaskInput;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.findAll();
	}

	@PostMapping("/new")
	public ResponseEntity<Void> saveNewTask(@Validated @RequestBody TaskInput taskInput) {
		var newTask = new Task();
		newTask.setTitle(taskInput.getTitle());
		newTask.setStartDate(taskInput.getStartDate());
		newTask.setDueDate(taskInput.getDueDate());
		newTask.setCondition(taskInput.getCondition());
		newTask.setStatus(taskInput.getStatus());
		newTask.setCreatedAt(LocalDateTime.now());

		taskService.create(newTask);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable int id) {
		return taskService.findById(id).map(task -> ResponseEntity.ok(task))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public void editTask(@PathVariable int id, @RequestBody TaskInput taskInput) {
		var updatedTask = new Task();
		updatedTask.setTitle(taskInput.getTitle());
		updatedTask.setStartDate(taskInput.getStartDate());
		updatedTask.setDueDate(taskInput.getDueDate());
		updatedTask.setCondition(taskInput.getCondition());
		updatedTask.setStatus(taskInput.getStatus());
		updatedTask.setUpdatedAt(LocalDateTime.now());

		taskService.update(id, updatedTask);
	}
}
