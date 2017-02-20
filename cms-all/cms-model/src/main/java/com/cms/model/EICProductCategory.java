package com.cms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eic_product_category")
public class EICProductCategory implements Serializable {


  private static final long serialVersionUID = 150041102334611503L;

  @Id
  private long id;

  @Column(name = "thumb_nail", length = 200)
  private String thumbNail;

  @Column(name = "category_name", length = 200)
  private String categoryName;

  @Column(name = "description", length = 4000)
  private String description;

  @Column(name = "category_fore_color", length = 7)
  private String categoryForeColor;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getThumbNail() {
    return thumbNail;
  }

  public void setThumbNail(String thumbNail) {
    this.thumbNail = thumbNail;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategoryForeColor() {
    return categoryForeColor;
  }

  public void setCategoryForeColor(String categoryForeColor) {
    this.categoryForeColor = categoryForeColor;
  }

}
