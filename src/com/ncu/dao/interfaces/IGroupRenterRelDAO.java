package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IGroupsRenterRelValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/1.
 */
public interface IGroupRenterRelDAO {
    public void save(IGroupsRenterRelValue value) throws Exception;
    public List<IGroupsRenterRelValue> queryGroupsRenterRelInfoByCondition(String condition, HashMap params,int begin,int end) throws Exception;
    public void delete(IGroupsRenterRelValue value) throws Exception;
    public long queryGroupsRenterRelCountByCondition(String condition, HashMap params)throws Exception;
}
