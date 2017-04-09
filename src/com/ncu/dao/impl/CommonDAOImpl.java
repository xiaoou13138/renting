package com.ncu.dao.impl;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.util.beanUtil.BeanUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaoou on 2017/3/23.
 */
@Repository("CommonDAOImpl")
public class CommonDAOImpl implements ICommonDAO {
    @Autowired
    private HibernateTemplate template;



    public List commonQuery(String sql, ParamsDefine[] paramsDefine) throws Exception {
       return(List) template.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(sql);
               for(int i = 0;i<paramsDefine.length;i++){
                   if(paramsDefine[i].getIsList()){
                       query.setParameterList(paramsDefine[i].getColName(),paramsDefine[i].getParamListVal());
                   }else{
                       query.setParameter(paramsDefine[i].getColName(),paramsDefine[i].getParamVal());
                   }
               }
                return query.list();
            }
        });
    }
}
