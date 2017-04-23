package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IMessageNoticeQueueValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/21.
 */
public interface IMessageNoticeQueueDAO  {
    public void save(IMessageNoticeQueueValue value)throws Exception;
    public List<IMessageNoticeQueueValue> queryMessageNoticeQueueByCondition(String condition, HashMap params,int begin,int end)throws Exception;
}
