package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IGroupRenterRelDAO;
import com.ncu.table.engine.GroupsRenterRelEngine;
import com.ncu.table.ivalue.IGroupsRenterRelValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/1.
 */
@Repository("GroupRenterRelDAOImpl")
public class GroupRenterRelDAOImpl implements IGroupRenterRelDAO {
    @Autowired
    GroupsRenterRelEngine groupsRenterRelEngine;
    @Override
    public List<IGroupsRenterRelValue> queryGroupsRenterRelInfoByCondition(String condition, HashMap params, int begin, int end) throws Exception {
        return groupsRenterRelEngine.queryByCondition(condition,params,begin,end);
    }

    @Override
    public void save(IGroupsRenterRelValue value) throws Exception {
        groupsRenterRelEngine.save(value);
    }
}

