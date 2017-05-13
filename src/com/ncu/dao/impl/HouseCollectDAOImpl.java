package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IHouseCollectDAO;
import com.ncu.table.engine.HouseCollectEngine;
import com.ncu.table.ivalue.IHouseCollectValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/4/14.
 */
@Repository("HouseCollectDAOImpl")
public class HouseCollectDAOImpl implements IHouseCollectDAO {
    @Autowired
    private HouseCollectEngine houseCollectEngine;

    public void save(IHouseCollectValue value) throws Exception{
        houseCollectEngine.save(value);
    }
    public List<IHouseCollectValue> queryHouseCollectByCondition(String condition, HashMap params, int begin, int end) throws Exception{
        return houseCollectEngine.queryByCondition(condition,params,begin,end);
    }
    public long queryHouseCollectCountByCondition(String condition, HashMap params)throws Exception{
        return houseCollectEngine.queryCountByCondition(condition,params);
    }
    public void delete(IHouseCollectValue value) throws Exception{
        houseCollectEngine.delete(value);
    }
}
