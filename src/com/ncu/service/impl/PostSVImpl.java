package com.ncu.service.impl;

import com.ncu.dao.interfaces.IPostDAO;
import com.ncu.service.interfaces.*;
import com.ncu.table.bean.PostBean;
import com.ncu.table.ivalue.*;
import com.ncu.util.APPUtil;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/11.
 */
@Service("PostSVImpl")
public class PostSVImpl implements IPostSV {
    @Resource(name="PostDAOImpl")
    private IPostDAO postDAO;

    @Resource(name="PostPictureRelSVImpl")
    private IPostPictureRelSV postPictureRelSV;

    @Resource(name="PictureSVImpl")
    private IPictureSV pictureSV;

    @Resource(name="PostMessageRelSVImpl")
    private IPostMessageRelSV postMessageRelSV;

    @Resource(name="MessageSVImpl")
    private IMessageSV messageSV;

    @Resource(name="UserSVImpl")
    private IUserSV userSV;

    /**
     * 根据页面传进来的数据保存帖子
     * @param object
     * @param userId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public long savePostInfoForController(JSONObject object, long userId) throws Exception{
        String cardTitle = APPUtil.getSafeStringFromJSONObject(object,"cardTitle");
        long houseLimit = APPUtil.getSafeLongParamFromJSONObject(object,"houseLimit");
        String sexLimit = APPUtil.getSafeStringFromJSONObject(object,"sexLimit");
        String cardContent = APPUtil.getSafeStringFromJSONObject(object,"cardContent");
        if(userId == 0){
            throw new Exception("用户请先登录");
        }
        PostBean bean = new PostBean();
        bean.setContent(cardContent);
        bean.setCrateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        bean.setDelFlag(1L);
        bean.setHostId(userId);
        if(houseLimit!=0){
            bean.setIsNoHouse(houseLimit);
        }
        bean.setPostTitle(cardTitle);
        bean.setSexLimit(sexLimit);
        postDAO.save(bean);
        return bean.getPostId();
    }

    /**
     * 根据传进来的条件查询帖子
     * @param searchCondition
     * @return
     * @throws Exception
     */
    public HashMap queryPostInfoForController(JSONObject searchCondition) throws Exception{
        HashMap rtnMap = new HashMap();

        StringBuilder condition = new StringBuilder("");
        HashMap params = new HashMap();


        String title = APPUtil.getSafeStringFromJSONObject(searchCondition,"title");
        long houseLimit = APPUtil.getSafeLongParamFromJSONObject(searchCondition,"houseLimit");
        String sexLimit = APPUtil.getSafeStringFromJSONObject(searchCondition,"sexLimit");
        String begin = APPUtil.getSafeStringFromJSONObject(searchCondition,"begin");
        String end = APPUtil.getSafeStringFromJSONObject(searchCondition,"end");
        if(StringUtils.isNotBlank(title)){
            SQLCon.connectSQL(IPostValue.S_PostTitle, title, condition, params, true);
        }
        if(houseLimit !=0){
            SQLCon.connectSQL(IPostValue.S_IsNoHouse, houseLimit, condition, params, false);
        }
        if(StringUtils.isNotBlank(sexLimit)){
            SQLCon.connectSQL(IPostValue.S_SexLimit, sexLimit, condition, params, false);
        }
        SQLCon.connectSQL(IPostValue.S_DelFlag, 1L, condition, params, false);
        long count = postDAO.queryPostInfoCountByCondition(condition.toString(),params);
        rtnMap.put("count",count);
        List<IPostValue> postList = postDAO.queryPostInfoByCondition(condition.toString(),params,Integer.parseInt(begin),Integer.parseInt(end));
        if(postList != null && postList.size()>0){
            ArrayList rtnList = new ArrayList();
            int length = postList.size();
            for(int i = 0;i < length;i++){
                IPostValue value = postList.get(i);
                HashMap map = new HashMap();
                map.put("title",value.getPostTitle());
                map.put("content",value.getContent());
                map.put("postId",value.getPostId());
                map.put("createDate",TimeUtil.formatTimeyyyyMMddhhmmss(value.getCrateDate()));
                map.put("hostId",value.getHostId());
                if(value.getIsNoHouse() != null){
                    map.put("houseLimit",value.getIsNoHouse());
                }
                map.put("sexLimit",value.getSexLimit());
                List<IPostPictureRelValue> pictureList = postPictureRelSV.queryPostPictureRelInfoByPostId(value.getPostId());
                if(pictureList != null && pictureList.size()>0){
                    int pictureListlength = pictureList.size();
                    ArrayList rtnPictureList = new ArrayList();
                    for(int j= 0;j<pictureListlength;j++){
                        long pictureId = pictureList.get(j).getPostPictureId();
                        IPictureValue pictureValue = pictureSV.queryPictureInfoByPictureId(pictureId);
                        rtnPictureList.add(pictureValue.getPicturePath());
                    }
                    map.put("pictureList",rtnPictureList);
                }
                //查找帖子关联的图片

                rtnList.add(map);
            }
            rtnMap.put("postBarList",rtnList);
        }
        return rtnMap;
    }

    /**
     * 根据帖子的主键帖子所有的信息（包括帖子和帖子之下的评论）
     * @param postId
     * @return
     * @throws Exception
     */
    public HashMap queryPostBarDetailForController(long postId,int begin,int end) throws Exception{
        HashMap rtnMap = new HashMap();
        //先查询帖子的信息
        IPostValue  postValue = queryPostInfoByPostId(postId);
        if(postValue != null){
            if(begin == 0){
                HashMap postMap = new HashMap();
                postMap.put("postId",postValue.getPostId());
                postMap.put("title",postValue.getPostTitle());
                postMap.put("hostId",postValue.getHostId());
                postMap.put("hostId",postValue.getHostId());
                if(postValue.getIsNoHouse() != null){
                    postMap.put("houseLimit",postValue.getIsNoHouse());
                }
                postMap.put("sexLimit",postValue.getSexLimit());
                postMap.put("createDate",postValue.getCrateDate());
                postMap.put("content",postValue.getContent());
                rtnMap.put("head",postMap);
            }
            //查询帖下面的评论的内容
            List<IPostMessageRelValue> postMessageRelValueList = postMessageRelSV.queryMessageRelByPostId(postId,begin,end);
            long count = postMessageRelSV.queryMessageRelCountByPostId(postId);
            rtnMap.put("count",count);
            if(postMessageRelValueList != null & postMessageRelValueList.size()>0){
                ArrayList cardList = new ArrayList();
                int length = postMessageRelValueList.size();
                for(int i= 0;i<length;i++){
                    IPostMessageRelValue postMessageRelValue = postMessageRelValueList.get(i);
                    long messageId = postMessageRelValue.getMessageId();
                    IMessageValue messageValue = messageSV.queryMessageByMessageId(messageId);
                    if(messageValue != null){
                        HashMap messageMap = new HashMap();
                        messageMap.put("content",messageValue.getContent());
                        messageMap.put("sendDate",messageValue.getSenderDate());
                        long senderId = messageValue.getSenderId();
                        //查询用户的信息
                        IUserValue userValue = userSV.queryUserInfoByUserId(senderId);
                        if(userValue != null){
                            messageMap.put("senderName",userValue.getUserName());
                            messageMap.put("senderId",userValue.getUserName());
                        }
                        cardList.add(messageMap);
                    }
                }
                rtnMap.put("cardList",cardList);
            }

        }
        return rtnMap;
    }

    /**
     * 根据帖子的主键查询帖子的信息
     * @param postId
     * @return
     * @throws Exception
     */
    public IPostValue queryPostInfoByPostId(long postId) throws Exception{
        if( postId >0){
            StringBuilder condition = new StringBuilder("");
            HashMap params = new HashMap();
            SQLCon.connectSQL(IPostValue.S_PostId, postId, condition, params, false);
            SQLCon.connectSQL(IPostValue.S_DelFlag, 1L, condition, params, false);
            List<IPostValue> list = postDAO.queryPostInfoByCondition(condition.toString(),params,-1,-1);
            if(list != null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }
}
