package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IGroupsDAO;
import com.ncu.table.engine.GroupsEngine;
import com.ncu.table.ivalue.IGroupsValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/1.
 */
@Repository("GroupsDAOImpl")
public class GroupsDAOImpl implements IGroupsDAO{
    @Autowired
    GroupsEngine groupsEngine;
    @Override
    public List<IGroupsValue> queryGroupsInfoByCondition(String condtion, HashMap params, int begin, int end) throws Exception {
        return groupsEngine.queryByCondition(condtion,params,begin,end);
    }

    @Override
    public void save(IGroupsValue value) throws Exception {
        groupsEngine.save(value);
    }
    public long getCountByCondition(String condition,HashMap  params)throws  Exception{
        return groupsEngine.queryCountByCondition(condition,params);
    }
}
