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
import jakarta.persistence.OptimisticLockException;

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
	    
	 // バージョンを比較
	    if (!task.getVersion().equals(input.getVersion())) {
	        throw new OptimisticLockException("version mismatch: expected=" 
	                                          + input.getVersion() + " actual=" + task.getVersion());
	    }
	    
	    task.setTitle(input.getTitle());
	    task.setStartDate(input.getStartDate());
	    task.setDueDate(input.getDueDate());
	    task.setCondition(input.getCondition());
	    task.setMemo(input.getMemo());
	    task.setUpdatedAt(LocalDateTime.now());
	    return taskRepository.save(task);
	}

	
	@Transactional
	public Task updateStatus(int id, Status status, Long version) {
	    Task task = taskRepository.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("タスクが見つかりません: " + id));

	    // バージョンを比較
	    if (!task.getVersion().equals(version)) {
	        throw new OptimisticLockException("version mismatch: expected=" 
	                                          + version + " actual=" + task.getVersion());
	    }

	    task.setStatus(status);
	    return taskRepository.save(task); // 保存時に version が自動で +1 される
	}
	
	@Transactional
	public void delete(int id, Long version) {
	    Task task = taskRepository.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("タスクが見つかりません: " + id));

	    // バージョンを比較
	    if (!task.getVersion().equals(version)) {
	        throw new OptimisticLockException("version mismatch: expected=" 
	                                          + version + " actual=" + task.getVersion());
	    }

	    taskRepository.delete(task); // 削除時も version チェックが効く
	}
}
