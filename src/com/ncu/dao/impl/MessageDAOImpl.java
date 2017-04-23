package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IMessageDAO;
import com.ncu.table.engine.MessageEngine;
import com.ncu.table.ivalue.IMessageValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/13.
 */
@Repository("MessageDAOImpl")
public class MessageDAOImpl implements IMessageDAO {
    @Autowired
    MessageEngine messageEngine;
    public void save(IMessageValue value) throws Exception{
        messageEngine.save(value);
    }
    public List<IMessageValue> queryMessageByCondition(String condition, HashMap params, int begin, int end) throws Exception{
        return messageEngine.queryByCondition(condition,params,begin,end);
    }
    public long queryMessageCountByCondition(String condition, HashMap params) throws Exception{
        return messageEngine.queryCountByCondition(condition,params);
    }
}
