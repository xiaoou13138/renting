package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IPostValue;
import net.sf.json.JSONObject;
import java.util.List;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/11.
 */
public interface IPostSV {
    /**
     * 根据页面传进来的数据保存帖子
     * @param object
     * @param userId
     * @throws Exception
     */
    public long savePostInfoForController(JSONObject object,long userId) throws Exception;

    /**
     * 根据传进来的条件查询帖子
     * @param condition
     * @return
     * @throws Exception
     */
    public HashMap queryPostInfoForController(JSONObject condition) throws Exception;

    /**
     * 根据帖子的主键帖子所有的信息（包括帖子和帖子之下的评论）
     * @param postId
     * @return
     * @throws Exception
     */
    public HashMap queryPostBarDetailForController(long postId,int begin,int end) throws Exception;

    /**
     * 根据帖子的主键查询帖子的信息
     * @param postId
     * @return
     * @throws Exception
     */
    public IPostValue queryPostInfoByPostId(long postId) throws Exception;

    /**
     * 删除帖子
     * @param postId
     * @throws Exception
     */
    public void deletePostInfoByPostId(long postId)throws Exception;

}
