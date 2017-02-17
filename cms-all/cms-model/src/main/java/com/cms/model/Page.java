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


@Document(collection = "page")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page implements Serializable {

    private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String pageName;

    private String pageURL;
     
    private HeroPanel heroPanel;
         
    private List<Row> bodyRowType;
     
    private String pageType;

    private String parentsId;
         
    private long hostId;
    
    private String hostName;
    
    private long templetId;
     
    private String templateContent;
    
    private long count;
    
    public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}


	@DateTimeFormat(iso=ISO.DATE_TIME)
    private Date createdDate;
   
    private String createdBy;
   
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Date updatedDate;
  
    private String updatedBy;
   

    public Page() {
    }


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getPageName() {
		return pageName;
	}


	public void setPageName(String pageName) {
		this.pageName = pageName;
	}


	public String getPageURL() {
		return pageURL;
	}


	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}


	public HeroPanel getHeroPanel() {
		return heroPanel;
	}


	public void setHeroPanel(HeroPanel heroPanel) {
		this.heroPanel = heroPanel;
	}


	public List<Row> getBodyRowType() {
		return bodyRowType;
	}


	public void setBodyRowType(List<Row> bodyRowType) {
		this.bodyRowType = bodyRowType;
	}


	public String getPageType() {
		return pageType;
	}


	public void setPageType(String pageType) {
		this.pageType = pageType;
	}


	public String getParentsId() {
		return parentsId;
	}


	public void setParentsId(String parentsId) {
		this.parentsId = parentsId;
	}


	public long getHostId() {
		return hostId;
	}


	public void setHostId(long hostId) {
		this.hostId = hostId;
	}


	public long getTempletId() {
		return templetId;
	}


	public void setTempletId(long templetId) {
		this.templetId = templetId;
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


	public String getTemplateContent() {
		return templateContent;
	}


	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}


	public String getHostName() {
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

   
	
}