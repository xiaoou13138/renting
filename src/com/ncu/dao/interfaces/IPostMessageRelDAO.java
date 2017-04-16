package com.ncu.dao.interfaces;
import com.ncu.table.ivalue.IPostMessageRelValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/13.
 */
public interface IPostMessageRelDAO {
    public void save(IPostMessageRelValue value) throws Exception;
    public List<IPostMessageRelValue> queryPostMessageRelByCondition(String condition, HashMap params,int begib,int end) throws Exception;
    public long queryPostMessageRelCountByCondition(String condition, HashMap params) throws Exception;
}
