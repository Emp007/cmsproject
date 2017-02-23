package com.cms.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Document(collection = "header")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header implements Serializable {

	private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String headerName;
         
    private long hostId;
    
    private String hostName;
    
    private long templetId;
     
    private String headerContent;

	@DateTimeFormat(iso=ISO.DATE_TIME)
    private Date createdDate;
   
    private String createdBy;
   
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Date updatedDate;
  
    private String updatedBy;
   

    public Header() {
    
    }
    
    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getHeaderName() {
		return headerName;
	}


	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}


	public long getHostId() {
		return hostId;
	}


	public void setHostId(long hostId) {
		this.hostId = hostId;
	}


	public String getHostName() {
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	public long getTempletId() {
		return templetId;
	}


	public void setTempletId(long templetId) {
		this.templetId = templetId;
	}


	
	public String getHeaderContent() {
		return headerContent;
	}


	public void setHeaderContent(String headerContent) {
		this.headerContent = headerContent;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}