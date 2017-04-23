package com.ncu.dao.impl;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.table.bean.MessageBean;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.util.beanUtil.BeanUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by xiaoou on 2017/3/23.
 */
@Repository("CommonDAOImpl")
public class CommonDAOImpl implements ICommonDAO {
    @Autowired
    private  HibernateTemplate template;



    public List commonQuery(final String sql, ParamsDefine[] paramsDefine) throws Exception {
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

    public List commonQuery(final String sql, ParamsDefine[] paramsDefine,int begin,int end) throws Exception {
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
                //判断分页 开始
                if(begin==-1^end==-1){
                    throw new HibernateException("分页错误");
                }
                if(begin != -1){
                    query.setFirstResult(begin);
                    query.setMaxResults(end);
                }
                return query.list();
            }
        });
    }
    public long getCount(final String sqlI, ParamsDefine[] paramsDefine) throws Exception {
        final String sql = "select count(1) "+sqlI;
        return(long) template.execute(new HibernateCallback() {
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

                List list =  query.list();
                if(list != null && list.size()>0){
                    long countObject= (long)list.get(0);
                    return countObject;
                }
                return 0L;
            }
        });
    }
}
