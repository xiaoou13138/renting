package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IMessageValue;
import java.util.List;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/13.
 */
public interface IMessageDAO {
    public void save(IMessageValue value) throws Exception;
    public List<IMessageValue> queryMessageByCondition(String condtion, HashMap params,int begin,int end) throws Exception;
}
