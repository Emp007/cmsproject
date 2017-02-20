package com.cms.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Row {
    private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   
    @NotNull
    private String height;
     
    private List<Column> row;
        
    public Row() {
    	
    }
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public List<Column> getRow() {
		return row;
	}

	public void setRow(List<Column> row) {
		this.row = row;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
