package com.cms.model;

import java.io.Serializable;
import java.util.List;

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
public class _Templet implements Serializable {

    private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotNull
    private long hostId;
    
    @NotNull
    private String templetName;
     
    private String description;
    
    @NotNull
    private String headerId;
    
    @NotNull
    private String footerId;
    
    @NotNull
    private HeroPanel heroPanel;
    
    @NotNull
    private List<Row> bodyRowtype;
   
    @NotNull
    private String createBy;
    
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private String createDate;
   
    private String updateBy;
    
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private String updateDate;
   

    public _Templet() {
    }

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getHostId() {
		return hostId;
	}

	public void setHostId(long hostId) {
		this.hostId = hostId;
	}

	public String getHeaderId() {
		return headerId;
	}

	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}

	public String getFooterId() {
		return footerId;
	}

	public void setFooterId(String footerId) {
		this.footerId = footerId;
	}

	public HeroPanel getHeroPanel() {
		return heroPanel;
	}

	public void setHeroPanel(HeroPanel heroPanel) {
		this.heroPanel = heroPanel;
	}

	public List<Row> getBodyRowtype() {
		return bodyRowtype;
	}

	public void setBodyRowtype(List<Row> bodyRowtype) {
		this.bodyRowtype = bodyRowtype;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
    
}