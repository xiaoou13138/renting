package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IMessageValue;

import java.util.HashMap;
import java.util.List;
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
    public void saveMessageByUserIdAndContent(long userId,long postId,String content,long isPrivate,long receiverId) throws Exception;

    /**
     * 查询私信信息
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IMessageValue> queryMessageByUserId(long userId,int begin,int end) throws Exception;

    /**
     * 查询数量
     * @param userId
     * @return
     * @throws Exception
     */
    public long queryMessageCountByUserId(long userId) throws Exception;
    /**
     * 查询私信信息
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryMessageByUserIdForController(long userId,int begin,int end) throws Exception;

    /**
     * 删除消息
     * @param messageId
     * @throws Exception
     */
    public void deleteMessage(long messageId)throws Exception;

    /**
     * 查询评论信息
     * @param postId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IMessageValue> queryMessageByPostId(long postId,int begin,int end)throws Exception;
    public long queryMessageCountByPostId(long postId)throws Exception;


}
