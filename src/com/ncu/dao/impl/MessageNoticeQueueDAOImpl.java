package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IMessageNoticeQueueDAO;
import com.ncu.table.engine.MessageNoticeQueueEngine;
import com.ncu.table.ivalue.IMessageNoticeQueueValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/21.
 */
@Repository("MessageNoticeQueueDAOImpl")
public class MessageNoticeQueueDAOImpl implements IMessageNoticeQueueDAO {
    @Autowired
    private MessageNoticeQueueEngine messageNoticeQueueEngine;
    public void save(IMessageNoticeQueueValue value)throws Exception{
        messageNoticeQueueEngine.save(value);
    }
    public List<IMessageNoticeQueueValue> queryMessageNoticeQueueByCondition(String condition, HashMap params, int begin, int end)throws Exception{
        return messageNoticeQueueEngine.queryByCondition(condition,params,begin,end);
    }

}
