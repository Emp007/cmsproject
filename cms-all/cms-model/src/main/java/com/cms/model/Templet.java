package com.cms.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "templet")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Templet implements Serializable {

    private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long hostId;
    
    @NotNull
    private String templetName;
    
    private String herosURL;
    
    
    public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private String description;
    private String isContainsHeader;
    private String headerType;
    private String isContainsFooter;
    private String footerType;
    private String bodyRowNo;
    private String bodyRowType;
    private String templetContent;
    private long count;
    
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private String createdDate;
    
    
    private String createdBy;
    
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private String modifiedDate;
    
    
    private String modifiedBy;
    

    public Templet() {
    }

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

    
	public long getHostId() {
		return hostId;
	}

	public void setHostId(long hostId) {
		this.hostId = hostId;
	}

	public String getTempletName() {
		return templetName;
	}


	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIsContainsHeader() {
		return isContainsHeader;
	}

	public void setIsContainsHeader(String isContainsHeader) {
		this.isContainsHeader = isContainsHeader;
	}

	public String getHeaderType() {
		return headerType;
	}

	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}

	public String getIsContainsFooter() {
		return isContainsFooter;
	}

	public void setIsContainsFooter(String isContainsFooter) {
		this.isContainsFooter = isContainsFooter;
	}

	public String getFooterType() {
		return footerType;
	}

	public void setFooterType(String footerType) {
		this.footerType = footerType;
	}

	public String getBodyRowNo() {
		return bodyRowNo;
	}

	public void setBodyRowNo(String bodyRowNo) {
		this.bodyRowNo = bodyRowNo;
	}

	public String getBodyRowType() {
		return bodyRowType;
	}

	public void setBodyRowType(String bodyRowType) {
		this.bodyRowType = bodyRowType;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getTempletContent() {
		return templetContent;
	}

	public void setTempletContent(String templetContent) {
		this.templetContent = templetContent;
	}

	public String getHerosURL() {
		return herosURL;
	}

	public void setHerosURL(String herosURL) {
		this.herosURL = herosURL;
	}
	
	
	
    
}