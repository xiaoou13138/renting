package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IHouseFacilityRelValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/3/30.
 */
public interface IHouseFacilityRelDAO {

    public List<IHouseFacilityRelValue> queryHouseFacilityRelByCondition(String condition, HashMap params,int begin,int end) throws Exception;
    public void save(IHouseFacilityRelValue value) throws  Exception;
}
