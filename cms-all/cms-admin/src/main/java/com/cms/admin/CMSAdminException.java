package com.cms.admin;

public class CMSAdminException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CMSAdminException() {
		super();
	}

	public CMSAdminException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CMSAdminException(String message, Throwable cause) {
		super(message, cause);
	}

	public CMSAdminException(String message) {
		super(message);
	}

	public CMSAdminException(Throwable cause) {
		super(cause);
	}

}
