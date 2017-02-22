package com.ncu.table.engine;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncu.table.bean.ConfigBean;
import com.ncu.util.BeanUtil;
@Repository
public class ConfigEngine{
	public static List<?> queryByCondition(String condition , HashMap<String,String> params,int beginPage ,int endPage) throws Exception{
		return BeanUtil.queryByConditionBase(condition, params, beginPage, endPage, ConfigBean.beanClass.getSimpleName());
	}
}
