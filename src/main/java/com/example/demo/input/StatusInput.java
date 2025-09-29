package com.example.demo.input;

import com.example.demo.entity.Status;

public class StatusInput {
	private Status status;
	
	private Long version;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
