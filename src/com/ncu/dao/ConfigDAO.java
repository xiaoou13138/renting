package com.ncu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ncu.bean.ConfigBean;

@Repository
public class ConfigDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unchecked")
	public List<ConfigBean> queryConfigByCodeType(String codeType){
		return (List<ConfigBean>) hibernateTemplate.find("from ConfigBean where code_type=?", codeType);
	}
}
