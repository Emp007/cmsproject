package com.cms.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "meta")
@JsonInclude(JsonInclude.Include.NON_NULL)

	
	public class Meta implements Serializable {

		private static final long serialVersionUID = -5925102261368544877L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    
	    private String hostName;
	    
		private long hostId;
	    
	    private long templetId;
	    
	    private String discription;
	    
	    private String pageName;
	    private String metaContent;
	    
	    private String title;
	    

		

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getMetaContent() {
			return metaContent;
		}

		public void setMetaContent(String metaContent) {
			this.metaContent = metaContent;
		}

		
		public String getHostName() {
			return hostName;
		}

		public void setHostName(String hostName) {
			this.hostName = hostName;
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

		public String getDiscription() {
			return discription;
		}

		public void setDiscription(String discription) {
			this.discription = discription;
		}

		public String getPageName() {
			return pageName;
		}

		public void setPageName(String pageName) {
			this.pageName = pageName;
		}
	    
	    
	    
	    
}

		