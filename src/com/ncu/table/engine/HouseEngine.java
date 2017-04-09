package com.ncu.table.engine;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ncu.table.bean.HouseBean;
import com.ncu.util.beanUtil.BeanUtil;

@Repository
public class HouseEngine{
  @Autowired
  BeanUtil beanUtil;
  public List queryByCondition(String condition , HashMap<String,String> params,int beginPage ,int endPage) throws Exception{
    return beanUtil.queryByCondition(condition, params, beginPage, endPage, HouseBean.beanClass.getSimpleName());
  }
  public void save(Object value) throws Exception{
    beanUtil.save(value);
  }

  public long getCount(String condition , HashMap<String,String> params) throws Exception{
    return beanUtil.getCount(condition,params,HouseBean.beanClass.getSimpleName());
  }
}