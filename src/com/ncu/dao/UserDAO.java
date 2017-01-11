package com.ncu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.ncu.bean.User;
@Repository("UserDAO")
public class UserDAO {
	@Autowired
	HibernateTemplate hibernateTemplate;
	public void save(User user){
		hibernateTemplate.save(user);
	}
}
