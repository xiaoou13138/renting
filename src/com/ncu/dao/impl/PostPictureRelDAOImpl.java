package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IPostPictureRelDAO;
import com.ncu.table.engine.PostPictureRelEngine;
import com.ncu.table.ivalue.IPostPictureRelValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/4/12.
 */
@Repository("PostPictureRelDAOImpl")
public class PostPictureRelDAOImpl implements IPostPictureRelDAO{
    @Autowired
    PostPictureRelEngine postPictureRelEngine;

    @Override
    public List<IPostPictureRelValue> queryPostPicttureRelInfoByCondition(String condition, HashMap params, int begin, int end) throws Exception {
        return postPictureRelEngine.queryByCondition(condition,params,begin,end);
    }

    @Override
    public void save(IPostPictureRelValue value) throws Exception {
        postPictureRelEngine.save(value);
    }
}
