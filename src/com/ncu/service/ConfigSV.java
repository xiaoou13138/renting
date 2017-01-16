package com.ncu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncu.bean.ConfigBean;
import com.ncu.dao.ConfigDAO;

@Service
public class ConfigSV {
	@Autowired
	private ConfigDAO dao;
	public List<ConfigBean> queryConfigByCodeType(String codeType){
		return dao.queryConfigByCodeType(codeType);
	}
	public ConfigSV(){
		
	}

}
