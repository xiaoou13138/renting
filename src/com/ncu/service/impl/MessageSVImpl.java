package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.dao.interfaces.IMessageDAO;
import com.ncu.service.interfaces.IMessageNoticeQueueSV;
import com.ncu.service.interfaces.IMessageSV;
import com.ncu.service.interfaces.IPostMessageRelSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.bean.MessageBean;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.table.ivalue.IMessageValue;
import com.ncu.table.ivalue.IPostValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by xiaoou on 2017/4/13.
 */
@Service("MessageSVImpl")
public class MessageSVImpl implements IMessageSV {
    @Resource(name="MessageDAOImpl")
    private IMessageDAO messageDAO;

    @Resource(name="PostMessageRelSVImpl")
    private IPostMessageRelSV postMessageRelSV;

    @Resource(name="UserSVImpl")
    private IUserSV userSV;

    @Resource(name="CommonDAOImpl")
    private ICommonDAO commonDAO;

    @Resource(name="MessageNoticeQueueSVImpl")
    private IMessageNoticeQueueSV messageNoticeQueueSV;
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
     * @param userId 发送人主键
     * @param postId 贴纸主键 如果是私信 0
     * @param content 私信内容
     * @param isPrivate 是否是私人
     * @param receiverId 接收人主键 如果是帖子 0
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMessageByUserIdAndContent(long userId,long postId,String content,long isPrivate,long receiverId) throws Exception{
        MessageBean bean = new MessageBean();

        bean.setContent(content);
        bean.setDelFlag(1L);
        bean.setIsPrivate(isPrivate);
        bean.setSenderId(userId);
        bean.setSenderDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        if(isPrivate == 0L){
            bean.setPostId(postId);
            messageDAO.save(bean);
            postMessageRelSV.savePostMessageRelByPostIdAndMessageId(postId,bean.getMessageId());
        }else{
            bean.setReceiverId(receiverId);
            messageDAO.save(bean);
            messageNoticeQueueSV.addNotice(receiverId);
        }
        //消息通知

    }


    /**
     * 查询私信信息
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IMessageValue> queryMessageByUserId(long userId,int begin,int end) throws Exception{
        String sql = "from MessageBean a where a.receiverId = :userId and delFlag=1 order by 1 desc";
        ArrayList<ParamsDefine> paramList = new ArrayList();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setColName("userId");
        paramsDefine.setParamVal(userId);
        paramsDefine.setIsList(false);
        paramList.add(paramsDefine);
        return commonDAO.commonQuery(sql,paramList.toArray(new ParamsDefine[0]),begin,end);
    }

    /**
     * 查询数量
     * @param userId
     * @return
     * @throws Exception
     */
    public long queryMessageCountByUserId(long userId) throws Exception{
        String sql = "from MessageBean a where a.receiverId = :userId and delFlag=1 order by 1 desc";
        ArrayList<ParamsDefine> paramList = new ArrayList();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setColName("userId");
        paramsDefine.setParamVal(userId);
        paramsDefine.setIsList(false);
        paramList.add(paramsDefine);
        return commonDAO.getCount(sql,paramList.toArray(new ParamsDefine[0]));
    }
    /**
     * 查询私信信息
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryMessageByUserIdForController(long userId,int begin,int end) throws Exception{
        HashMap rtnMap = new HashMap();

        List<IMessageValue> messageValueList = queryMessageByUserId(userId,begin,end);
        long count = queryMessageCountByUserId(userId);
        rtnMap.put("count",count);
        if(messageValueList != null && messageValueList.size()>0){
            int length = messageValueList.size();
            ArrayList rtnList= new ArrayList();
            for(int i =  0;i<length;i++){
                IMessageValue messageValue = messageValueList.get(i);
                HashMap map = new HashMap();
                IUserValue userValue = userSV.queryUserInfoByUserId(messageValue.getSenderId());
                if(messageValue.getPostId() != null){
                    map.put("postBarId",messageValue.getPostId());
                }
                map.put("content",messageValue.getContent());
                map.put("friendId",messageValue.getSenderId());
                map.put("friendName",userValue.getUserName());
                map.put("createTime",TimeUtil.formatTimeyyyyMMddhhmmss(messageValue.getSenderDate()));
                rtnList.add(map);
            }
            rtnMap.put("contentList",rtnList);
        }
        return rtnMap;
    }

    /**
     * 删除消息
     * @param messageId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMessage(long messageId)throws Exception{
        IMessageValue messageValue = queryMessageByMessageId(messageId);
        if(messageValue == null){
            throw new Exception("信息不存在");
        }
        messageValue.setDelFlag(0L);
        messageDAO.save(messageValue);
    }

    /**
     * 查询评论信息
     * @param postId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IMessageValue> queryMessageByPostId(long postId,int begin,int end)throws Exception{
        List<IMessageValue> rtnList = new ArrayList<>();
        String sql = "from MessageBean a,PostMessageRelBean b where a.messageId = b.messageId and b.postId =:postId and a.delFlag=1";
        ArrayList<ParamsDefine> paramList = new ArrayList();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setColName("postId");
        paramsDefine.setParamVal(postId);
        paramsDefine.setIsList(false);
        paramList.add(paramsDefine);
        Object obj =  commonDAO.commonQuery(sql,paramList.toArray(new ParamsDefine[0]),begin,end);
        if(obj != null){
            ArrayList list = (ArrayList)obj;
            for(int i = 0;i<list.size();i++){
                Object object[] = (Object[]) list.get(i);
                IMessageValue messageValue = (IMessageValue)object[0];
                rtnList.add(messageValue);
            }
            return rtnList;
        }
        return null;
    }
    public long queryMessageCountByPostId(long postId)throws Exception{
        String sql = "from MessageBean a,PostMessageRelBean b where a.messageId = b.messageId and b.postId =:postId and a.delFlag=1";
        ArrayList<ParamsDefine> paramList = new ArrayList();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setColName("postId");
        paramsDefine.setParamVal(postId);
        paramsDefine.setIsList(false);
        paramList.add(paramsDefine);
        return commonDAO.getCount(sql,paramList.toArray(new ParamsDefine[0]));
    }
}
