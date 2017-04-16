package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IPostMessageRelDAO;
import com.ncu.table.engine.PostMessageRelEngine;
import com.ncu.table.ivalue.IPostMessageRelValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/13.
 */
@Repository("PostMessageRelDAOImpl")
public class PostMessageRelDAOImpl implements IPostMessageRelDAO {
    @Autowired
    PostMessageRelEngine postMessageRelEngine;

    public void save(IPostMessageRelValue value) throws Exception{
        postMessageRelEngine.save(value);
    }
    public List<IPostMessageRelValue> queryPostMessageRelByCondition(String condition, HashMap params, int begib, int end) throws Exception{
        return postMessageRelEngine.queryByCondition(condition,params,begib,end);
    }
    public long queryPostMessageRelCountByCondition(String condition, HashMap params) throws Exception{
        return postMessageRelEngine.queryCountByCondition(condition,params);
    }

}
