package com.cms.app.dao;


//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cms.model.User;

import java.util.List;


public interface UserDao extends MongoRepository<User, Long> {

  public User findByEmail(String email);

  public User findByName(String name);

  public User findOneByStaffId(String staffId);

  public List<User> findAllByParentId(String parentId);

  //@Query("SELECT u FROM User u WHERE u.staffId IN (?1)")
  //public List<User> getAllRMByParentId(String parentId);

  public User findOneByLoginId(String loginId);
  
 // @Query(value = "SELECT sum(TIMESTAMPDIFF(SECOND, srm_approve_timestamp,srm_submission_timestamp)) FROM sherlock.moa_plan where srm_approve_timestamp >= '2016-01-01 00:00:00' AND srm_approve_timestamp < '2016-11-04 12:08:16'", nativeQuery = true)
 // public Long getDateRange();

}
