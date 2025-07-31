package com.example.demo.input;

import java.time.LocalDate;

import com.example.demo.entity.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskInput {
	@NotBlank
	@Size(max = 128)
	private String title;

	private LocalDate startDate;

	private LocalDate dueDate;

	private String condition;

	private String memo;

	private Status status;

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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
