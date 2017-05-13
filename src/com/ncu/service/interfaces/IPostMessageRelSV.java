package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IPostMessageRelValue;
import java.util.List;
/**
 * Created by zuowy on 2017/4/13.
 */
public interface IPostMessageRelSV {
    /**
     * 根据帖子的信息查询帖子下面的评论
     * @param postId
     * @return
     * @throws Exception
     */
    public List<IPostMessageRelValue> queryMessageRelByPostId(long postId,int begin,int end) throws Exception;

    /**
     * 分页查询数量
     * @param postId
     * @return
     * @throws Exception
     */
    public long queryMessageRelCountByPostId(long postId) throws Exception;

    /**
     * 保存帖子和评论的关系
     * @param postId
     * @param messageId
     * @throws Exception
     */
    public void savePostMessageRelByPostIdAndMessageId(long postId,long messageId)throws Exception;
}
