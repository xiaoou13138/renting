package com.ncu.service.impl;

import com.ncu.dao.interfaces.IMessageNoticeQueueDAO;
import com.ncu.service.interfaces.IMessageNoticeQueueSV;
import com.ncu.table.bean.MessageNoticeQueueBean;
import com.ncu.table.ivalue.IMessageNoticeQueueValue;
import com.ncu.util.SQLCon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/21.
 */
@Service("MessageNoticeQueueSVImpl")
public class MessageNoticeQueueSVImpl implements IMessageNoticeQueueSV {
    @Resource(name="MessageNoticeQueueDAOImpl")
    private IMessageNoticeQueueDAO messageNoticeQueueDAO;

    /**
     * 通知数量+1
     * @param userId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNotice(long userId)throws Exception{
        IMessageNoticeQueueValue messageNoticeQueueValue = queryMessageNoticeQueue(userId);
        if(messageNoticeQueueValue != null ){
            throw new Exception("用户不合法:"+userId);
        }
        messageNoticeQueueValue.setMessageNum(messageNoticeQueueValue.getMessageNum()+1);
        messageNoticeQueueDAO.save(messageNoticeQueueValue);
    }

    /**
     * 查询通知表
     * @param userId
     * @return
     * @throws Exception
     */
    public IMessageNoticeQueueValue queryMessageNoticeQueue(long userId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params =  new HashMap();
        SQLCon.connectSQL(IMessageNoticeQueueValue.S_UserId,userId,condition,params,false);
        List<IMessageNoticeQueueValue> list = messageNoticeQueueDAO.queryMessageNoticeQueueByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size()>0){
            return list.get(0)
;        }
        return null;
    }

    /**
     * 查询用户未读私信数量
     * @param userId
     * @return
     * @throws Exception
     */
    public long getMessageNum(long userId)throws Exception{
        IMessageNoticeQueueValue messageNoticeQueueValue = queryMessageNoticeQueue(userId);
        if(messageNoticeQueueValue == null){
            return 0L;
        }
        return messageNoticeQueueValue.getMessageNum();
    }

    /**
     * 保存通知表
     * @param userId
     * @param num
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMessageNoticeQueue(long userId,long num,boolean isNew)throws Exception{
        IMessageNoticeQueueValue value;
        if(isNew){
            value = new MessageNoticeQueueBean();
            value.setMessageNum(0L);
            value.setUserId(userId);
        }else{
            value = queryMessageNoticeQueue(userId);
            value.setMessageNum(num);
        }
       messageNoticeQueueDAO.save(value);
    }

}
