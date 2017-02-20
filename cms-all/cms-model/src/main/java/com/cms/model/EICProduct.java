package com.cms.model;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "eic_product")
public class EICProduct implements Serializable {



  /**
	 * 
	 */
  private static final long serialVersionUID = 7742538563819729880L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "product_category_id")
  private long productCategoryId;

  @Column(name = "ref_id")
  private String refId;

  @Column(name = "title", length = 100)
  private String title;

  @Column(name = "tag", length = 4000)
  private String tag;

  @Column(name = "product_url")
  private String productUrl;

  @Column(name = "thumb_nail", length = 200)
  private String thumbNail;

  @Column(name = "last_update", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdate;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getProductCategoryId() {
    return productCategoryId;
  }

  public void setProductCategoryId(long productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

  public String getRefId() {
    return refId;
  }

  public void setRefId(String refId) {
    this.refId = refId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getProductUrl() {
    return productUrl;
  }

  public void setProductUrl(String productUrl) {
    this.productUrl = productUrl;
  }

  public String getThumbNail() {
    return thumbNail;
  }

  public void setThumbNail(String thumbNail) {
    this.thumbNail = thumbNail;
  }

  public Date getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}
