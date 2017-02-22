package com.ncu.table.engine;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

import com.ncu.table.bean.ConfigBean;
import com.ncu.table.bean.UserBean;
import com.ncu.util.BeanUtil;


@Service
public class UserEngine{

	public static List<?> queryByCondition(String condition , HashMap<String,String> params,int beginPage ,int endPage) throws Exception{
		return BeanUtil.queryByConditionBase(condition, params, beginPage, endPage, UserBean.beanClass.getSimpleName());
	}

}
