package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IApplyGroupMessageDAO;
import com.ncu.table.engine.ApplyGroupMessageEngine;
import com.ncu.table.ivalue.IApplyGroupMessageValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/10.
 */
@Repository("ApplyGroupMessageDAOImpl")
public class ApplyGroupMessageDAOImpl implements IApplyGroupMessageDAO {
    @Autowired
    ApplyGroupMessageEngine applyGroupMessageEngine;

    @Override
    public List<IApplyGroupMessageValue> queryApplyGroupMessageInfoByCondition(String condition, HashMap params, int begin, int end) throws Exception {
        return applyGroupMessageEngine.queryByCondition(condition,params,begin,end);
    }

    @Override
    public void save(IApplyGroupMessageValue value) throws Exception {
        applyGroupMessageEngine.save(value);
    }
    public long queryApplyGroupMessageCountByCondition(String condition, HashMap params) throws Exception{
        return applyGroupMessageEngine.queryCountByCondition(condition,params);
    }
}
