package com.cms.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cms.app.dao.UserLogDao;
import com.cms.model.CMSUser;
import com.cms.model.UserLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by manishsanger on 03/11/16.
 */
@Service("userLogService")
public class UserLogServiceImpl implements UserLogService {
    private final static Logger logger = LoggerFactory.getLogger(UserLogServiceImpl.class);

    @Autowired
    private UserLogDao userLogDao;

    @Override
    public UserLog get(long id) {
        return null;
    }

    @Override
    public UserLog save(UserLog entity) {

        if (entity == null) {
            logger.info("User log data cannot be null/empty");
            throw new IllegalArgumentException("User log cannot be null/empty");
        }

        if (entity.getClientId() <= 0) {
            logger.info("Client Id cannot be null/empty");
            throw new IllegalArgumentException("Client Id cannot be null/empty");
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date date = new Date();
            String strDate = dateFormat.format(date);
            Date currentDate = dateFormat.parse(strDate);
            entity.setDate(currentDate);

            //Current year
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            entity.setYear(year);

            //Current month Name
            String monthName = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            entity.setMonthName(monthName);

            //Current month
            Integer month = Calendar.getInstance().get(Calendar.MONTH);
            entity.setMonth(month);

            //Current Week
            Integer week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
            entity.setWeek(week);

            //Set Current User Id
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CMSUser scbUser = (CMSUser)authentication.getPrincipal();

            entity.setUserId(scbUser.getId());

            return userLogDao.save(entity);
        } catch (Exception e) {
            String msg = String.format("Error while saving User log for user Id : %d",
                    entity.getUserId());
            logger.info(msg);
            throw new IllegalArgumentException(msg);
        }

    }

    @Override
    public UserLog update(long id, UserLog entity) {
        return null;
    }
    

   
}