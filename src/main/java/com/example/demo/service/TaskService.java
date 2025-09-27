package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Status;
import com.example.demo.entity.Task;
import com.example.demo.input.TaskInput;
import com.example.demo.repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

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

	@Transactional
	public Task update(int id, TaskInput input) {
	    Task task = taskRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
	    
	    task.setTitle(input.getTitle());
	    task.setStartDate(input.getStartDate());
	    task.setDueDate(input.getDueDate());
	    task.setCondition(input.getCondition());
	    task.setMemo(input.getMemo());
	    task.setUpdatedAt(LocalDateTime.now());
	    return taskRepository.save(task);
	}

	
	public Optional<Task> updateStatus(int id, Status status) {
		return taskRepository.findById(id).map(task -> {
			task.setStatus(status);
			return taskRepository.save(task);
		});
	}
	
	public void delete(int id) {
		var task = taskRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("タスクが見つかりません: " + id));
        taskRepository.delete(task);
	}
}
