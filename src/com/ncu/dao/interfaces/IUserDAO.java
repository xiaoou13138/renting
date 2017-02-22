package com.ncu.dao.interfaces;

import java.util.HashMap;
import java.util.List;

import com.ncu.table.ivalue.IUserValue;

public interface IUserDAO {
	
	/**
	 * 保存用户信息
	 * @param user
	 */
	public void save(IUserValue value);
	
	/**
	 * 根据条件和参数来查询表
	 * @param condition 条件
	 * @param params 参数
	 * @return
	 * @throws Exception
	 */
	public List<IUserValue> queryUserInfoByCondition(String condition , HashMap<String,String> params) throws Exception;
}
