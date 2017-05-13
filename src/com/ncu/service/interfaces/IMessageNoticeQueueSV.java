package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IMessageNoticeQueueValue;

/**
 * Created by zuowy on 2017/4/21.
 */
public interface IMessageNoticeQueueSV {
    /**
     * 通知数量+1
     * @param userId
     * @throws Exception
     */
    public void addNotice(long userId)throws Exception;

    /**
     * 查询通知表
     * @param userId
     * @return
     * @throws Exception
     */
    public IMessageNoticeQueueValue queryMessageNoticeQueue(long userId)throws Exception;

    /**
     * 查询用户未读私信数量
     * @param userId
     * @return
     * @throws Exception
     */
    public long getMessageNum(long userId)throws Exception;

    /**
     * 保存通知表
     * @param userId
     * @param num
     * @throws Exception
     */
    public void saveMessageNoticeQueue(long userId,long num,boolean isNew)throws Exception;

    /**
     * 给所有用户都通知
     * @throws Exception
     */
    public void addNoticeAll()throws Exception;

}
