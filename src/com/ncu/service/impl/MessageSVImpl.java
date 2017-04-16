package com.ncu.service.impl;

import com.ncu.dao.interfaces.IMessageDAO;
import com.ncu.service.interfaces.IMessageSV;
import com.ncu.service.interfaces.IPostMessageRelSV;
import com.ncu.table.bean.MessageBean;
import com.ncu.table.ivalue.IMessageValue;
import com.ncu.table.ivalue.IPostValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/13.
 */
@Service("MessageSVImpl")
public class MessageSVImpl implements IMessageSV {
    @Resource(name="MessageDAOImpl")
    private IMessageDAO messageDAO;

    @Resource(name="PostMessageRelSVImpl")
    private IPostMessageRelSV postMessageRelSV;

    /**
     * 根据消息的主键查询消息的信息
     * @param messageId
     * @return
     * @throws Exception
     */
    public IMessageValue queryMessageByMessageId(long messageId) throws Exception{
        if(messageId > 0){
            StringBuilder condition = new StringBuilder("");
            HashMap params = new HashMap();
            SQLCon.connectSQL(IMessageValue.S_MessageId, messageId, condition, params, false);
            List<IMessageValue> list = messageDAO.queryMessageByCondition(condition.toString(),params,-1,-1);
            if(list != null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }
    /**
     * 保存帖子评内容
     * @param userId
     * @param content
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMessageByUserIdAndContent(long userId,long postId,String content) throws Exception{
        if(userId > 0 && StringUtils.isNotBlank(content)){
            MessageBean bean = new MessageBean();
            bean.setPostId(postId);
            bean.setContent(content);
            bean.setDelFlag(1L);
            bean.setIsPrivate(0L);
            bean.setSenderId(userId);
            bean.setSenderDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            messageDAO.save(bean);

            postMessageRelSV.savePostMessageRelByPostIdAndMessageId(postId,bean.getMessageId());
        }
    }

}
