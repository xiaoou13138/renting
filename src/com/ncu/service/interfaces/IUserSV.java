package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IUserValue;
import net.sf.json.JSONArray;
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
	public IUserValue queryUserInfoByUserId(long userId)  throws Exception;

	/**
	 * 根据用户的cookie校验用户
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public HashMap checkUserInfoByCookie(Cookie cookies[]) throws Exception;


	/**
	 * 根据用户页面传进来的数据保存用户信息
	 * @param viewObject
	 * @throws Exception
	 */
	public void saveUserInfoByViewData(JSONObject viewObject)throws  Exception;

	/**
	 * 修改个人信息时获取的信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public JSONObject queryUserInfoByForEditView(long userId)throws Exception;

	/**
	 * 更新用户信息
	 * @param object
	 * @throws Exception
	 */
	public void updateUserInfo(long userId,JSONObject object)throws Exception;

	/**
	 * 改变用户的类型
	 * @param userId
	 * @param userType
	 * @throws Exception
	 */
	public void changeUserType(long userId,String userType)throws Exception;

	/**
	 * 查询用户的信息
	 * @param searchContent
	 * @param searchType
	 * @param begin
	 * @param end
	 * @return
	 */
	public HashMap queryUserForControllerByCondition(String searchContent,int searchType,int begin,int end)throws Exception;

	/**
	 * 查询用户信息
	 * @param searchContent
	 * @param searchType
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<IUserValue> queryUserByCondition(String searchContent,int searchType,int begin,int end) throws Exception ;
	public long queryUserCountByCondition(String searchContent,int searchType) throws Exception ;

	/**
	 * 删除用户信息
	 * @param userArray
	 * @throws Exception
	 */
	public void deleteUser(JSONArray userArray)throws Exception;




}
