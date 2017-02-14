package com.ncu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncu.dao.impl.ConfigDAOImpl;
import com.ncu.service.interfaces.IConfigSV;
import com.ncu.table.bean.ConfigBean;

@Service
public class ConfigSVImpl implements IConfigSV{
	@Autowired
	private ConfigDAOImpl dao;
	
	/**
	 * 根据codeType区查询配置表的数据
	 * @param codeType
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ConfigBean> queryConfigByCodeType(String codeType) throws Exception{
		return dao.queryConfigByCodeType(codeType);
	}
}
