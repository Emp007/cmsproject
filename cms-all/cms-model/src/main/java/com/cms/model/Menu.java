package com.cms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "menu")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu implements Serializable {
	private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   
    @NotNull
    private Long hostId;
    
    @NotNull
    private String hostName;
    
    @NotNull
    private Long pageId;
    
    @NotNull
    private String pageName;
    
    @NotNull
    private String childPageName;
    
    @NotNull
    private Long parentsId;
    
    @NotNull
    private Long childId;
    
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Date createdDate;
   
    private String createdBy;
   
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Date updatedDate;
  
    private String updatedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public Long getParentsId() {
		return parentsId;
	}

	public void setParentsId(Long parentsId) {
		this.parentsId = parentsId;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getChildPageName() {
		return childPageName;
	}

	public void setChildPageName(String childPageName) {
		this.childPageName = childPageName;
	}
   
}