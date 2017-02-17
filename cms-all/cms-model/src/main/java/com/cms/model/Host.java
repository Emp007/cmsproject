package com.cms.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.io.Serializable;
import java.util.Date;


@Document(collection = "host")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Host implements Serializable {

    private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String hostName;

    @NotNull
    private String status;

    @NotNull
    private String type;

    @NotNull
    private String startDate;
    
    @NotNull
    private String expireDate;
    
    @NotNull
    private String alias;
    
    @NotNull
    private String indexpageURL;
    
    
    public long getTempletcount() {
		return templetcount;
	}

	public void setTempletcount(long templetcount) {
		this.templetcount = templetcount;
	}

	public long getPagecount() {
		return pagecount;
	}

	public void setPagecount(long pagecount) {
		this.pagecount = pagecount;
	}

	private long templetcount;
    
    
    private long pagecount;

    
    private String createdBy;
    
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Date createDate;
   
    private String updateBy;
    
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Date updateDate;
  
    public Host() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIndexpageURL() {
		return indexpageURL;
	}

	public void setIndexpageURL(String indexpageURL) {
		this.indexpageURL = indexpageURL;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}