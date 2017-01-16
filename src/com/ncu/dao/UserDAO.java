package com.ncu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.ncu.bean.UserBean;
@Repository("UserDAO")
public class UserDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	public void save(UserBean user){
		hibernateTemplate.save(user);
	}
}
