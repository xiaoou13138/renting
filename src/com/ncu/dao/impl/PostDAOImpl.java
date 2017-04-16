package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IPostDAO;
import com.ncu.table.engine.PostEngine;
import com.ncu.table.ivalue.IPostValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/11.
 */
@Repository("PostDAOImpl")
public class PostDAOImpl implements IPostDAO {
    @Autowired
    private PostEngine postEngine;
    @Override
    public void delete(IPostValue value) throws Exception {
        postEngine.delete(value);
    }

    @Override
    public List<IPostValue> queryPostInfoByCondition(String condtion, HashMap params, int begin, int end) throws Exception {
        return postEngine.queryByCondition(condtion,params,begin,end);
    }

    @Override
    public long queryPostInfoCountByCondition(String condtion, HashMap params) throws Exception {
        return postEngine.queryCountByCondition(condtion,params);
    }

    @Override
    public void save(IPostValue value) throws Exception {
        postEngine.save(value);
    }
}
