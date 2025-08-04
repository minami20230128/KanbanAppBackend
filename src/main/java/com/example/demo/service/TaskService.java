package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Status;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public Task create(Task task) {
		return taskRepository.save(task);
	}

	public Optional<Task> findById(Integer id) {
		return taskRepository.findById(id);
	}

	public Optional<Task> update(Integer id, Task updatedTask) {
		return taskRepository.findById(id).map(task -> {
			task.setTitle(updatedTask.getTitle());
			task.setStartDate(updatedTask.getStartDate());
			task.setDueDate(updatedTask.getDueDate());
			task.setCondition(updatedTask.getCondition());
			task.setMemo(updatedTask.getMemo());
			task.setStatus(updatedTask.getStatus());
			task.setUpdatedAt(updatedTask.getUpdatedAt());
			return taskRepository.save(task);
		});
	}
	
	public Optional<Task> updateStatus(int id, Status status) {
		return taskRepository.findById(id).map(task -> {
			task.setStatus(status);
			return taskRepository.save(task);
		});
	}
}
