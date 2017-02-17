package com.cms.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "scheduler_state")
public class SchedulerState {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long id;

  @Column(name = "scheduler_name", unique = true)
  private String schedulerName;

  @Column(name = "state", length = 10485760)
  private String state;

  private Date createDate;

  private Date updateDate;


  public SchedulerState() {}


  public SchedulerState(String schedulerName, String state) {
    this.schedulerName = schedulerName;
    this.state = state;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }



  public String getSchedulerName() {
    return schedulerName;
  }

  public void setSchedulerName(String schedulerName) {
    this.schedulerName = schedulerName;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  @PrePersist
  protected void onCreate() {
    createDate = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updateDate = new Date();
  }


}
