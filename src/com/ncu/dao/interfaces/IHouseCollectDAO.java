package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IHouseCollectValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/14.
 */
public interface IHouseCollectDAO {
    public void save(IHouseCollectValue value) throws Exception;
    public List<IHouseCollectValue> queryHouseCollectByCondition(String condition, HashMap params,int begin,int end) throws Exception;
    public long queryHouseCollectCountByCondition(String condition, HashMap params)throws Exception;
    public void delete(IHouseCollectValue value) throws Exception;
}
