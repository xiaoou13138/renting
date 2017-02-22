package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ncu.table.bean.ConfigBean;
import com.ncu.table.engine.ConfigEngine;
import com.ncu.table.ivalue.IConfigValue;
import com.ncu.util.SQLCon;

@Repository
public class ConfigDAOImpl {
	public List<ConfigBean> queryConfigByCodeType(String codeType) throws Exception{
		StringBuilder condition = new StringBuilder();
		HashMap<String,String> params  = new HashMap<String, String>();
		SQLCon.connectSQL(IConfigValue.S_CodeType, codeType, condition, params, false);
		return (List<ConfigBean>) ConfigEngine.queryByCondition(condition.toString(), params, -1, -1);
	}
}
