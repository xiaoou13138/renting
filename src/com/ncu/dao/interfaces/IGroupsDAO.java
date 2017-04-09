package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IGroupsValue;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/1.
 */
public interface IGroupsDAO {
    public void save(IGroupsValue value) throws Exception;
    public List<IGroupsValue> queryGroupsInfoByCondition(String condtion, HashMap params,int begin,int end) throws  Exception;
}
