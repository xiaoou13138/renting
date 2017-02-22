package com.ncu.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service("BeanUtil")
public class BeanUtil {
	private static HibernateTemplate hibernateTemplate;
	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		BeanUtil.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * 根据条件查询信息
	 * @param condition
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static List<?> queryByConditionBase(String condition , HashMap<String,String> params,int beginPage ,int endPage,String beanName) throws Exception{
		if(condition == null){
			condition = "";
		}
		if(params == null){
			params = new HashMap<String, String>();
		}
		condition = condition.toUpperCase();//查询条件
		StringBuilder builder = new StringBuilder(" FROM " + beanName + " WHERE 1=1" + condition);
		ArrayList<String> list = new ArrayList<String>();//参数
		int indexColon = 0;//:的位置
		int indexAnd = 0;//AND的位置
		String temp = "";//key
		String tempValue = "";//value
		
		//判断分页 开始
		if(beginPage==-1^endPage==-1){
			throw new Exception("分页错误");
		}
		//判断分页 结束
		
		while(true){
			indexColon = builder.indexOf(":");
			if(indexColon!=-1){
				 indexAnd = builder.indexOf("AND" , indexColon);
				 if(indexAnd != -1){
					 temp = builder.substring(indexColon+1, indexAnd).trim();
					 tempValue = params.get(temp);
					 if(StringUtils.isEmpty(tempValue)){
						 throw new Exception("在参数集合中没有\""+temp+"\"对应的值");
					 }
					 builder.replace(indexColon, indexAnd, "?");
					 list.add(tempValue);
				 }else{
					 temp = builder.substring(indexColon+1).trim();
					 tempValue = params.get(temp);
					 if(StringUtils.isEmpty(tempValue)){
						 throw new Exception("在参数集合中没有\""+temp+"\"对应的值");
					 }
					 builder.replace(indexColon, builder.length(), "?");
					 list.add(tempValue);
				 }
				 if(beginPage!=-1){
					 builder.append("LIMIT"+beginPage+","+endPage);
				 }
			}else{
				break;
			}
		}
		return hibernateTemplate.find(builder.toString(), (Object[])list.toArray(new String[0]));
	}
	
	
	/**
	 * 给bean初始化主键
	 */
	public static ArrayList<String> initPK(Class<?> beanClass){
		ArrayList<String> primaryKey = new ArrayList<String>();
        Field[] field = beanClass.getDeclaredFields();  
        if(field != null){  
            for(Field fie : field){  
                if(!fie.isAccessible()){  
                    fie.setAccessible(true);  
                }  
                Id annon = fie.getAnnotation(Id.class);  
                if(annon!=null){
                	primaryKey.add(fie.getName());
                }
            }  
        }
        return primaryKey;
    }
}
