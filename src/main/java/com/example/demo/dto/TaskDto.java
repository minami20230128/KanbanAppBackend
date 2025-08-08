package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.Status;

public class TaskDto {
	private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate dueDate;
    private Status status;
    
	public TaskDto(int id, String title, LocalDate startDate, LocalDate dueDate, Status status) {
		this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
