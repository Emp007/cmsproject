package com.cms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "heropanel")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeroPanel implements Serializable {

    private static final long serialVersionUID = -5925102261368544877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   
    @NotNull
    private String imageType;
    
    @NotNull
    private List<Image> images;
    
    @NotNull
    private String heroheight;
    
    @NotNull
    private String herowidth;
    
    @NotNull
    private String animationType;
    
    
    public HeroPanel() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}


	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getHeroheight() {
		return heroheight;
	}

	public void setHeroheight(String heroheight) {
		this.heroheight = heroheight;
	}

	public String getHerowidth() {
		return herowidth;
	}

	public void setHerowidth(String herowidth) {
		this.herowidth = herowidth;
	}

	
	public String getAnimationType() {
		return animationType;
	}

	public void setAnimationType(String animationType) {
		this.animationType = animationType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}