package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ncu.table.bean.UserBean;
import com.ncu.table.engine.UserEngine;
import com.ncu.table.ivalue.IUserValue;
@Repository("UserDAO")
public class UserDAOImpl {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private UserEngine engine;
	/**
	 * 保存用户信息
	 * @param value
	 */
	public void save(IUserValue value){
		hibernateTemplate.save(value);
	}

	/**
	 * 根据条件和参数来查询表
	 * @param condition 条件
	 * @param params 参数
	 * @return
	 * @throws Exception
	 */
	public List<IUserValue> queryUserInfoByCondition(String condition , HashMap<String,String> params) throws Exception{
		return (List<IUserValue>) engine.queryByCondition(condition, params,-1,-1);
	}

	/**
	 * 根据条件和参数来查询表
	 * @param condition
	 * @param params
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<IUserValue> queryUserInfoByCondition(String condition , HashMap<String,String> params,int begin,int end) throws Exception{
		return (List<IUserValue>) engine.queryByCondition(condition, params,begin,end);
	}

	public long queryUserCountByCondition(String condition , HashMap<String,String> params)throws Exception{
		return engine.queryCountByCondition(condition,params);
	}



}
