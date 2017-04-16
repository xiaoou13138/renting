package com.ncu.service.impl;

import com.ncu.dao.interfaces.IPostMessageRelDAO;
import com.ncu.service.interfaces.IPostMessageRelSV;
import com.ncu.table.bean.PostMessageRelBean;
import com.ncu.table.ivalue.IPostMessageRelValue;
import com.ncu.table.ivalue.IPostValue;
import com.ncu.util.SQLCon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/13.
 */
@Service("PostMessageRelSVImpl")
public class PostMessageRelSVImpl implements IPostMessageRelSV {
    @Resource(name="PostMessageRelDAOImpl")
    IPostMessageRelDAO postMessageRelDAO;

    /**
     * 根据帖子的信息查询帖子下面的评论
     * @param postId
     * @return
     * @throws Exception
     */
    public List<IPostMessageRelValue> queryMessageRelByPostId(long postId,int begin,int end) throws Exception{
        if(postId >0 ){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IPostMessageRelValue.S_PostId, postId, condition, params, false);
            return postMessageRelDAO.queryPostMessageRelByCondition(condition.toString(),params,begin,end);
        }
        return null;
    }

    /**
     * 分页查询数量
     * @param postId
     * @return
     * @throws Exception
     */
    public long queryMessageRelCountByPostId(long postId) throws Exception{
        if(postId >0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IPostMessageRelValue.S_PostId, postId, condition, params, false);
            return postMessageRelDAO.queryPostMessageRelCountByCondition(condition.toString(),params);
        }
        return 0L;
    }
    /**
     * 保存帖子和评论的关系
     * @param postId
     * @param messageId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void savePostMessageRelByPostIdAndMessageId(long postId,long messageId)throws Exception{
        PostMessageRelBean bean = new PostMessageRelBean();
        bean.setPostId(postId);
        bean.setMessageId(messageId);
        postMessageRelDAO.save(bean);
    }

}
