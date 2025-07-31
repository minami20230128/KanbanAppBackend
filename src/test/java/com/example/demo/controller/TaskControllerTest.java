package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Task;
import com.example.demo.input.TaskInput;
import com.example.demo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private TaskService taskService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetAllTasks() throws Exception {
		Task task = new Task();
		task.setId(1);
		task.setTitle("Test Task");
		task.setStatus(0);

		Mockito.when(taskService.findAll()).thenReturn(List.of(task));

		mockMvc.perform(get("/tasks")).andExpect(status().isOk()).andExpect(jsonPath("$[0].title").value("Test Task"));
	}

	@Test
	void testSaveNewTask() throws Exception {
		TaskInput input = new TaskInput();
		input.setTitle("New Task");
		input.setStartDate(LocalDate.now());
		input.setDueDate(LocalDate.now().plusDays(3));
		input.setCondition("完了条件");
		input.setStatus(1);

		mockMvc.perform(post("/tasks/new").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input))).andExpect(status().isOk());

		Mockito.verify(taskService).create(any(Task.class));
	}

	@Test
	void testGetTaskFound() throws Exception {
		Task task = new Task();
		task.setId(1);
		task.setTitle("Task A");

		Mockito.when(taskService.findById(1)).thenReturn(Optional.of(task));

		mockMvc.perform(get("/tasks/1")).andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Task A"));
	}

	@Test
	void testGetTaskNotFound() throws Exception {
		Mockito.when(taskService.findById(999)).thenReturn(Optional.empty());

		mockMvc.perform(get("/tasks/999")).andExpect(status().isNotFound());
	}

	@Test
	void testEditTask() throws Exception {
		TaskInput input = new TaskInput();
		input.setTitle("Updated Task");
		input.setStartDate(LocalDate.now());
		input.setDueDate(LocalDate.now().plusDays(5));
		input.setCondition("新しい条件");
		input.setStatus(2);

		mockMvc.perform(
				put("/tasks/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(input)))
				.andExpect(status().isOk());

		Mockito.verify(taskService).update(eq(1), any(Task.class));
	}
}
