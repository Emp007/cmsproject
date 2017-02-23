package com.cms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Column {
    private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   
    @NotNull
    private String width;
     
    private String contentType;
        
    public Column() {
    	
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
