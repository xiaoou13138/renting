package com.ncu.util.beanUtil;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.*;

@Service("BeanUtil")
public class BeanUtil {
	@Autowired
	private HibernateTemplate template;
	
	/**
	 * 根据条件查询信息
	 * @param condition
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryByCondition(String condition, HashMap params,int begin,int end,String beanName) throws Exception{
		final String finalCondition = "from "+beanName+" where 1=1 "+condition;
		return(List) template.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(finalCondition);
				Iterator<Map.Entry> iterator = params.entrySet().iterator();
				while(iterator.hasNext()){
					Map.Entry entry = iterator.next();
					String key = (String)entry.getKey();
					Object value = entry.getValue();
					if(value instanceof ArrayList){
						query.setParameterList(key,(ArrayList)value);
					}else{
						query.setParameter(key,value);
					}
				}
				//判断分页 开始
				if(begin==-1^end==-1){
					throw new HibernateException("分页错误");
				}
				if(begin != -1){
					query.setFirstResult(begin);
					query.setMaxResults(end);
				}
				List list = query.list();
				return list;
			}
		});
	}

	public long getCount(String condition, HashMap params,String beanName)throws Exception{
		final String finalCondition = "select count(1) from "+beanName+" where 1=1 "+condition;
		return(long) template.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(finalCondition);
				Iterator<Map.Entry> iterator = params.entrySet().iterator();
				while(iterator.hasNext()){
					Map.Entry entry = iterator.next();
					String key = (String)entry.getKey();
					Object value = entry.getValue();
					if(value instanceof ArrayList){
						query.setParameterList(key,(ArrayList)value);
					}else{
						query.setParameter(key,value);
					}
				}

				List list = query.list();
				if(list != null && list.size()>0){
					long countObject= (long)list.get(0);
					return countObject;
				}
				return 0L;
			}
		});
	}

	public void save(Object value){
		template.save(value);
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
