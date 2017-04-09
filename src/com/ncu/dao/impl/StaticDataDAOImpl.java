package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IStaticDataDAO;
import com.ncu.table.engine.StaticDataEngine;
import com.ncu.table.ivalue.IStaticDataValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/27.
 */
@Repository("StaticDataDAOImpl")
public class StaticDataDAOImpl implements IStaticDataDAO {
    @Autowired
    StaticDataEngine staticDataEngine;
    @Override
    public List<IStaticDataValue> queryStaticDataInfoByCondition(String condition, HashMap params, int begin, int end) throws Exception {
        return staticDataEngine.queryByCondition(condition,params,begin,end);
    }
}
