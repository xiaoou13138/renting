package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IHouseFacilityRelDAO;
import com.ncu.table.engine.HouseFacilityRelEngine;
import com.ncu.table.ivalue.IHouseFacilityRelValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/30.
 */
@Repository("HouseFacilityRelDAOImpl")
public class HouseFacilityRelDAOImpl implements IHouseFacilityRelDAO {
    @Autowired
    HouseFacilityRelEngine houseFacilityRelEngine;
    @Override
    public List<IHouseFacilityRelValue> queryHouseFacilityRelByCondition(String condition, HashMap params,int begin,int end) throws Exception {
        return houseFacilityRelEngine.queryByCondition(condition,params,begin,end);
    }

    @Override
    public void save(IHouseFacilityRelValue value) throws Exception {
        houseFacilityRelEngine.save(value);
    }
}
