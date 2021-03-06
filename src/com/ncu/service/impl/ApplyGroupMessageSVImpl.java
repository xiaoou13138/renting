package com.ncu.service.impl;

import com.ncu.dao.interfaces.IApplyGroupMessageDAO;
import com.ncu.service.interfaces.*;
import com.ncu.table.bean.ApplyGroupMessageBean;
import com.ncu.table.ivalue.IApplyGroupMessageValue;
import com.ncu.table.ivalue.IGroupsRenterRelValue;
import com.ncu.table.ivalue.IGroupsValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.infinispan.commons.hash.Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/4/10.
 */
@Service("ApplyGroupMessageSVImpl")
public class ApplyGroupMessageSVImpl implements IApplyGroupMessageSV {
    @Resource(name="ApplyGroupMessageDAOImpl")
    private IApplyGroupMessageDAO applyGroupMessageDAO;

    @Resource(name="GroupRenterRelSVImpl")
    private IGroupRenterRelSV groupRenterRelSV;

    @Resource(name="UserSVImpl")
    private IUserSV userSV;

    @Resource(name="GroupsSVImpl")
    private IGroupsSV groupsSV;

    @Resource(name="MessageSVImpl")
    private IMessageSV messageSV;

    /**
     * 查询用户是否有需要推送的申请的消息
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<IApplyGroupMessageValue> queryApplyGroupMessage(long userId) throws Exception {
        if(userId != 0){
            StringBuilder condition = new StringBuilder();
            HashMap params =  new HashMap();
            SQLCon.connectSQL(IApplyGroupMessageValue.S_AcceptApplyUserId,userId,condition,params,false);
            return applyGroupMessageDAO.queryApplyGroupMessageInfoByCondition(condition.toString(),params,-1,-1);
        }
        return null;
    }

    /**
     * 申请加入指定团
     * @param userId
     * @param groupId
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveApplyGroupMessageForController(long userId, long groupId) throws Exception {
        //首先先查出创建团的人是谁
        if(userId != 0&& groupId !=0){
            //查询用户是否已经在组里面
            IGroupsValue groupsValue = groupsSV.queryGroupInfoByGroupsId(groupId);
            if(groupsValue ==null ){
                throw new Exception("组不存在");
            }
            if(groupsValue.getCurrentNumber()>=groupsValue.getGroupNumber()){
                throw new Exception("该组人数已满，不能加入");
            }
            IGroupsRenterRelValue value =  groupRenterRelSV.queryGroupRenterRelByUserIdAndGroupId(userId,groupId);
            if(value != null){
                throw new Exception("您已经加入该组");
            }

            IGroupsRenterRelValue groupsRenterRelValue = groupRenterRelSV.queryGroupHeader(groupId);
            if(groupsRenterRelValue != null){
                long acceptUserId = groupsRenterRelValue.getRenterId();
                //查询是否已经申请过
                IApplyGroupMessageValue applyGroupMessageValue = queryApplyGroupMessage(userId,acceptUserId,groupId);
                if(applyGroupMessageValue != null){
                    throw new Exception("不能重复申请加入组");
                }
                ApplyGroupMessageBean bean = new ApplyGroupMessageBean();
                bean.setAcceptApplyUserId(acceptUserId);
                bean.setApplyGroupId(groupId);
                bean.setApplyUserId(userId);
                bean.setState(1L);
                bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
                applyGroupMessageDAO.save(bean);
            }else{
                throw new Exception("该组没有组长");
            }
        }

    }
    /**
     * contoller
     * 根据用户的ID查询用户待审核的数据
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap queryApplyGroupMessageForController(long userId) throws Exception{
        HashMap rtnMap = new HashMap();
        ArrayList rtnList = new ArrayList();
        if(userId !=0){
            List<IApplyGroupMessageValue> list = queryApplyGroupMessage(userId);
            if(list != null && list.size()>0){
                int length = list.size();
                for(int i= 0;i<length;i++){
                    HashMap map = new HashMap();
                    IApplyGroupMessageValue value = list.get(i);
                    map.put("applyId",value.getApplyId());
                    map.put("applyUserId",value.getApplyUserId());
                    map.put("groupId",value.getApplyGroupId());
                    map.put("state",value.getState());
                    // 查询用户的名称和组的名称
                    IUserValue userValue =userSV.queryUserInfoByUserId(value.getApplyUserId());
                    if(userValue != null){
                        map.put("applyUserName",userValue.getUserName());
                    }
                    IGroupsValue groupsValue = groupsSV.queryGroupInfoByGroupsId(value.getApplyGroupId());
                    if(groupsValue != null){
                        map.put("groupName",groupsValue.getGroupName());
                    }
                    rtnList.add(map);
                    //查询组的名称
                }
            }
            rtnMap.put("messageList",rtnList);
        }
        return rtnMap;
    }

    /**
     * 审核加团申请
     * @param applyId
     * @param dealType accept是申请 refuse是拒绝
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void dealMessage(long applyId,String dealType,String content)  throws Exception{
        //先查出是否有这条申请的数据
        IApplyGroupMessageValue applyGroupMessageValue =  queryApplyGroupMessageByApplyId(applyId);
        if(applyGroupMessageValue != null){
            if("accept".equals(dealType)){
                applyGroupMessageValue.setState(2L);//2是批准
                groupRenterRelSV.saveAddGroupInfo(applyGroupMessageValue.getApplyUserId(),applyGroupMessageValue.getApplyGroupId(),2L);
            }else if("refuse".equals(dealType)){
                applyGroupMessageValue.setState(3L);//3是拒绝
                //发送拒绝的消息
                if(StringUtils.isBlank(content)){
                    content = "你的入组申请被拒绝，组长没有写理由。如有问题可直接回复组长";
                }
                long sendUserId = applyGroupMessageValue.getAcceptApplyUserId();
                long acceptId = applyGroupMessageValue.getApplyUserId();
                messageSV.saveMessageByUserIdAndContent(sendUserId,0,content,1,acceptId);
            }else{
                throw new Exception("传入的dealType:"+dealType+"参数错误");
            }
            applyGroupMessageDAO.save(applyGroupMessageValue);
        }
    }
    /**
     * 根据applyId查询带批准的信息
     * @param applyId
     * @throws Exception
     */
    public IApplyGroupMessageValue queryApplyGroupMessageByApplyId(long applyId) throws Exception{
        if(applyId !=0){
            StringBuilder condition = new StringBuilder();
            HashMap params =  new HashMap();
            SQLCon.connectSQL(IApplyGroupMessageValue.S_ApplyId,applyId,condition,params,false);
            List<IApplyGroupMessageValue> list= applyGroupMessageDAO.queryApplyGroupMessageInfoByCondition(condition.toString(),params,-1,-1);
            if(list != null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

    /**
     * 查询申请信息
     * @param userId 申请人
     * @param acceptUserId 被申请人
     * @param groupId 申请的组
     * @return
     * @throws Exception
     */
    public IApplyGroupMessageValue queryApplyGroupMessage(long userId,long acceptUserId,long groupId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params =  new HashMap();
        SQLCon.connectSQL(IApplyGroupMessageValue.S_ApplyUserId,userId,condition,params,false);
        SQLCon.connectSQL(IApplyGroupMessageValue.S_AcceptApplyUserId,acceptUserId,condition,params,false);
        SQLCon.connectSQL(IApplyGroupMessageValue.S_ApplyGroupId,groupId,condition,params,false);
        SQLCon.connectSQL(IApplyGroupMessageValue.S_State,1L,condition,params,false);
        List<IApplyGroupMessageValue> list= applyGroupMessageDAO.queryApplyGroupMessageInfoByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询用户带审核消息的数量
     * @param userId
     * @return
     * @throws Exception
     */
    public long queryApplyMessageCountByUserId(long userId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params =  new HashMap();
        SQLCon.connectSQL(IApplyGroupMessageValue.S_AcceptApplyUserId,userId,condition,params,false);
        SQLCon.connectSQL(IApplyGroupMessageValue.S_State,1L,condition,params,false);
        return applyGroupMessageDAO.queryApplyGroupMessageCountByCondition(condition.toString(),params);
    }
}
