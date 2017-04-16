package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IMessageValue;

/**
 * Created by xiaoou on 2017/4/13.
 */
public interface IMessageSV {
    /**
     * 根据消息的主键查询消息的信息
     * @param messageId
     * @return
     * @throws Exception
     */
    public IMessageValue queryMessageByMessageId(long messageId) throws Exception;

    /**
     * 保存帖子评内容
     * @param userId
     * @param content
     * @throws Exception
     */
    public void saveMessageByUserIdAndContent(long userId,long postId,String content) throws Exception;
}
