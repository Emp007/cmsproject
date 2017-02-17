package com.cms.admin.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.service.UserService;
import com.cms.model.User;

import java.util.List;

@Controller
@RequestMapping(value = "admin/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private final static String USER_VIEW_NAME = "admin/user_view";
    private final static String USER_UPDATE_NAME = "admin/user_update";
    public final static String ISSAVE = "issave";

    private final static String USER_NEW_USER = "admin/user_new";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ModelAndView getClientView(@RequestParam(required = false) boolean success) {
        ModelAndView mav = new ModelAndView(USER_VIEW_NAME);
        List<User> userList = null;
        try {
            userList = userService.getAllUsers();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject(ISSAVE, success);
        mav.addObject("users", userList);
        return mav;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("id") long id) {

        ModelAndView mav = new ModelAndView(USER_UPDATE_NAME);
        User user = null;
        try {
            user = userService.getUser(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "newUser", method = RequestMethod.GET)
    public ModelAndView addNewUser(@RequestParam(required = false) boolean success) {
        ModelAndView mav = new ModelAndView(USER_NEW_USER);
        User user = null;
        try {
            user = userService.getUser(1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("id") long id, @RequestParam("name") String name,
                             @RequestParam("email") String email, @RequestParam("loginId") String loginId, @RequestParam("parentId") String parentId, @RequestParam("staffId") String staffId, @RequestParam("authorities") String authorities, @RequestParam("salesForce") String salesForce, @RequestParam("status") String status) {
        ModelAndView mav = new ModelAndView(USER_UPDATE_NAME);
        User user = new User();
        Boolean bool = false;

        try {
            user.setId(id);
            user.setName(name);
            user.setEmail(email);
            user.setLoginId(loginId);
            user.setAuthorities(authorities);
            user.setParentId(parentId);
            user.setStaffId(staffId);
            //System.out.println(id+","+name+","+email+","+authorities+","+parentId+","+staffId);
            User saveUser = userService.Update(user);
            if (saveUser != null) {
                bool = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject("user", user);
        return new ModelAndView("redirect:" + "get?success=" + bool);
        //return mav;
    }

    @RequestMapping(value = "saveNewUser", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@RequestParam("name") String name,
                                    @RequestParam("email") String email, @RequestParam("loginId") String loginId, @RequestParam("parentId") String parentId, @RequestParam("staffId") String staffId, @RequestParam("authorities") String authorities, @RequestParam("salesForce") String salesForceID) {
        ModelAndView mav = new ModelAndView(USER_UPDATE_NAME);
        User user = new User();
        Boolean bool = false;

        try {
            user.setName(name);
            user.setEmail(email);
            user.setAuthorities(authorities);
            user.setParentId(parentId);
            user.setStaffId(staffId);
            user.setLoginId(loginId);
            User saveUser = userService.Update(user);
            if (saveUser != null) {
                bool = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject("user", user);
        return new ModelAndView("redirect:" + "get?success=" + bool);
        //return mav;
    }

}
