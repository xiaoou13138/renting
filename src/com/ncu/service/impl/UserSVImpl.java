package com.ncu.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.dao.impl.UserDAOImpl;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.SQLCon;

@Service("UserSVImpl")
public class UserSVImpl implements IUserSV {
	@Autowired
	private UserDAOImpl dao;
	
    /**
     * 保存用户的信息
     * @param value 用户信息的数据容器
     */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(IUserValue value) throws Exception{
		if(value != null){
			dao.save(value);
		}
	}
	
	/**
	 * 根据账号和密码校验用户的信息
	 * @param code 用户账号
	 * @return 通过校验:true
	 * @throws Exception
	 */
	@Override
	public boolean checkUserInfo(String code,String password) throws Exception {
		IUserValue value = getUserInfoByCode(code);
		if(value != null &&value.getPassword().equals(password) ){
			return true;
		}
		return false;
	}
	/**
	 * 根据用户的账号查找用户的信息
	 * @param code 用户账号
	 * @return 用户信息容器
	 * @throws Exception
	 */
	@Override
	public IUserValue getUserInfoByCode(String code) throws Exception {
		if(StringUtils.isNotBlank(code)){
			StringBuilder condition = new StringBuilder("");
			HashMap<String,String> params = new HashMap<String, String>();
			SQLCon.connectSQL(IUserValue.S_Code, code, condition, params, false);
			List<IUserValue> iUserValues = dao.queryUserInfoByCondition(condition.toString(), params);
			if(iUserValues!=null){
				if(iUserValues.size()==1){
					return iUserValues.get(0);
				}else if(iUserValues.size()>1){
					throw new Exception("该账号异常");
				}
			}
		}
		return null;
	}

	/**
	 * 根据用户code判是否已经存在了这样的账号
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkHasEqualCode(String code) throws Exception {
		if(StringUtils.isNotBlank(code)){
			IUserValue value = getUserInfoByCode(code);
			if(value == null){
				return false;
			}
		}
		return true;
	}

}
