package com.ncu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.bean.User;
import com.ncu.dao.UserDAO;

@Service("UserSV")
public class UserSV {
	@Autowired
	private UserDAO userDAO;
	public UserSV(){
		
		
	}
	@Transactional
	public void saveUser(){
	   User user = new User(); 
	   user.setUserId(2);
	   user.setDelFlag("1");
	   user.setcode("12");
	   SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	   String dateStr = "2002-07-11 11:27:13";
	   Date date = null;
	   try {
		date = ss.parse(dateStr);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   user.setCreateDate(date);
	   user.setPassword("123");
	   userDAO.save(user);
	}

}
