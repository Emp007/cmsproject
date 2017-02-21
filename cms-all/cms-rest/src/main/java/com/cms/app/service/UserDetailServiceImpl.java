package com.cms.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cms.app.dao.UserDao;
import com.cms.app.exception.CMSException;
import com.cms.app.util.DateTimeUtil;
import com.cms.model.Constants;
import com.cms.model.CMSUser;
import com.cms.model.User;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserDao userDao;
  
  @Autowired
  private DateTimeUtil dateTimeUtil;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	 /* User user1 =new User();
	  user1.setAuthorities("admin@gmail.com ,ROLE_ADMIN");
	  user1.setEmail("admin@gmail.com");
	  user1.setLoginId("admin@gmail.com");
	  user1.setName("viru");
	  user1.setParentId("50084");
	  user1.setStaffId("50084");
	  userDao.save(user1);*/
   
	  User user = userDao.findOneByLoginId(username);

    if (user == null) {
      logger.info(String.format("No user found with user name : %s", username));
      throw new UsernameNotFoundException(String.format("No user found with user name : %s",
          username));
    }

    return new CMSUser(user.getId(), user.getName(), Constants.DUMMY_PASSWORD, user.getEmail(),
            AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities()),
            user.getStaffId());
      }

  public User getUserByUserId(Long id) {
    if (StringUtils.isEmpty(id)) {
      logger.info("User id cannot be null/empty");
      throw new IllegalArgumentException("Staff id cannot be null/empty");
    }

    try {
      return userDao.findOne(id);
    } catch (Exception e) {
      String msg = String.format("Error while getting user by user id : %s", id);
      logger.error(msg, e);
      throw new CMSException(msg, e);
    }
  }

  public List<User> getAllUsers() {

    try {
      List<User> users = (List<User>) userDao.findAll();
      return users;
    } catch (Exception e) {
      String msg = String.format("Error while getting all users by admin");
      logger.error(msg, e);
      throw new CMSException(msg, e);
    }
  }


  public User getUserByStaffId(String staffId) {

    if (StringUtils.isEmpty(staffId)) {
      logger.info("Staff id cannot be null/empty");
      throw new IllegalArgumentException("Staff id cannot be null/empty");
    }

    try {
      return userDao.findOneByStaffId(staffId);
    } catch (Exception e) {
      String msg = String.format("Error while getting user by staff id : %s", staffId);
      logger.error(msg, e);
      throw new CMSException(msg, e);
    }

  }

  public List<User> getAllRMUsersByParentId(String parentId) {

    if (StringUtils.isEmpty(parentId)) {
      logger.info("Parent id cannot be null/empty");
      throw new IllegalArgumentException("Parent id cannot be null/empty");
    }

    try {
      User user = this.getUserByStaffId(parentId);
      if (user == null) {
        logger.error("No user found by given staffId");
        throw new IllegalArgumentException("No user found by given staffId");
      }

      List<User> users = new ArrayList<>();

      if (user.getAuthorities().contains(Constants.ROLE_SRM)) {
        users.addAll(userDao.findAllByParentId(parentId));
      } else if (user.getAuthorities().contains(Constants.ROLE_SEGMENT_HEAD)) {
        List<User> srmUsers = userDao.findAllByParentId(parentId);
        srmUsers.forEach(srmUser -> {
          users.addAll(userDao.findAllByParentId(srmUser.getStaffId()));
        });
      }
      return users;
    } catch (Exception e) {
      String msg = String.format("Error while getting user by parent id : %s", parentId);
      logger.error(msg, e);
      throw new CMSException(msg, e);
    }

  }


  public List<User> getUsersByParentId(String parentId) {

    if (StringUtils.isEmpty(parentId)) {
      logger.info("Parent id cannot be null/empty");
      throw new IllegalArgumentException("Parent id cannot be null/empty");
    }

    try {
      return userDao.findAllByParentId(parentId);
    } catch (Exception e) {
      String msg = String.format("Error while getting user by parent id : %s", parentId);
      logger.error(msg, e);
      throw new CMSException(msg, e);
    }

  }

  public User save(User updateUser) {

    if (updateUser == null) {
      logger.info("User can not be empty or null");
      throw new IllegalArgumentException("User can not be empty or null");
    }
    try {
    	setDateTimeWithUserName(updateUser);
    	return userDao.save(updateUser);
    } catch (Exception e) {
      String msg = String.format("Error while  updating by user id : %s", updateUser.getId());
      logger.error(msg, e);
      throw new CMSException(msg, e);
    }
  }
  
  /*public Long  getDateRange() {
	  return userDao.getDateRange();
  }*/
  
  public int count(){
	  return (int) userDao.count();
  }
  
  public void setDateTimeWithUserName(User user){
	  
	  user.setCreatedDate(dateTimeUtil.currentDateTime());
	  user.setUpdatedDate(dateTimeUtil.currentDateTime());
	  user.setCreatedBy("virendra");
	  user.setUpdatedBy("Virendra");
  }
 
  
}
