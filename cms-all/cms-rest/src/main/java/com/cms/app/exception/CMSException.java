package com.cms.app.exception;

public class CMSException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CMSException() {
	}

	public CMSException(String message) {
		super(message);
	}

	public CMSException(Throwable cause) {
		super(cause);
	}

	public CMSException(String message, Throwable cause) {
		super(message, cause);
	}

	public CMSException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
