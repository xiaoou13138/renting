package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IPostValue;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/11.
 */
public interface IPostDAO {
    public void save(IPostValue value) throws Exception;
    public void delete(IPostValue value) throws Exception;
    public long queryPostInfoCountByCondition(String condtion, HashMap params) throws Exception;
    public List<IPostValue> queryPostInfoByCondition(String condtion, HashMap params, int begin, int end) throws Exception;
}
