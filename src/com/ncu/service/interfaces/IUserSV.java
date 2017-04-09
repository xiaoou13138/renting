package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IUserValue;
import net.sf.json.JSONObject;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IUserSV {
	/**
	 * 校验用户的信息
	 * @param code 用户账号
	 * @return 通过校验:true
	 * @throws Exception
	 */
	public HashMap checkUserInfo(String code, String password) throws Exception;

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

	/**
	 * 根据用户账号和密码获得用户的信息
	 *
	 */
	public IUserValue getUserInfoByCodeAndPassword(String code ,String password) throws Exception;

	/**
	 * 根据多个用户id查询用户的信息
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	public List<IUserValue> queryUserInfoByUserIds(ArrayList userIdList) throws Exception;

	/**
	 * 根据用户的主键查询用户的信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public IUserValue queryUserInfoByUserId(String userId)  throws Exception;

	/**
	 * 根据用户的cookie校验用户
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public HashMap checkUserInfoByCookie(Cookie cookies[]) throws Exception;

	/**
	 * 根据用户的主键查询修改用户信息页面的信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap getEditViewInitData(String userId) throws Exception;


	/**
	 * 根据用户页面传进来的数据保存用户信息
	 * @param viewObject
	 * @throws Exception
	 */
	public void saveUserInfoByViewData(JSONObject viewObject)throws  Exception;



}
