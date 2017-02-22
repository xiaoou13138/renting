package com.ncu.table.engine;


import java.util.HashMap;
import java.util.List;

import com.ncu.table.bean.ConfigBean;
import com.ncu.table.bean.HouseBean;
import com.ncu.util.BeanUtil;

public class HouseEngine{
	public static List<?> queryByCondition(String condition , HashMap<String,String> params,int beginPage ,int endPage) throws Exception{
		return BeanUtil.queryByConditionBase(condition, params, beginPage, endPage, HouseBean.beanClass.getSimpleName());
	}
}
