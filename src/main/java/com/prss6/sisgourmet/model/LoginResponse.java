package com.prss6.sisgourmet.model;

public class LoginResponse {
	
    private boolean success;
    private String message;
    private Long id;
    
    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
	public LoginResponse(boolean success, String message, Long id) {
        this.success = success;
        this.message = message;
        this.setId(id);
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

