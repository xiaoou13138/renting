package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IUserValue;

public interface IUserSV {
	/**
	 * 校验用户的信息
	 * @param code 用户账号
	 * @return 通过校验:true
	 * @throws Exception
	 */
	public boolean checkUserInfo(String code,String password) throws Exception;
	
	/**
	 * 保存用户的信息
	 * @param value 用户信息容器
	 * @throws Exception
	 */
	public void save(IUserValue value) throws Exception; 
	
	/**
	 * 根据用户的账号查找用户的信息
	 * @param code 用户账号
	 * @return 用户信息容器
	 * @throws Exception
	 */
	public IUserValue getUserInfoByCode(String code) throws Exception;
	
	/**
	 * 根据用户code判是否已经存在了这样的账号
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public boolean checkHasEqualCode(String code) throws Exception;
	
}
