package com.ncu.service.impl;

import com.ncu.dao.interfaces.IGroupRenterRelDAO;
import com.ncu.service.interfaces.IGroupRenterRelSV;
import com.ncu.service.interfaces.IGroupsSV;
import com.ncu.table.bean.GroupsRenterRelBean;
import com.ncu.table.ivalue.IGroupsRenterRelValue;
import com.ncu.table.ivalue.IGroupsValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/10.
 */
@Service("GroupRenterRelSVImpl")
public class GroupRenterRelSVImpl implements IGroupRenterRelSV{
    @Resource(name="GroupRenterRelDAOImpl")
    IGroupRenterRelDAO groupRenterRelDAO;

    @Resource(name="GroupsSVImpl")
    private IGroupsSV groupsSV;
    /**
     * 标准服务
     * 加入一个组
     * @param userId
     * @param groupId
     * @param role 1是组长 2是组员
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAddGroupInfo(long userId,long groupId,long role) throws Exception{
        if(userId !=0 && groupId !=0){
            GroupsRenterRelBean bean = new GroupsRenterRelBean();
            bean.setRole(role);
            bean.setRenterId(userId);
            bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            bean.setGroupId(groupId);
            groupRenterRelDAO.save(bean);
        }else{
            throw new Exception("传入的参数错误");
        }

    }

    /**
     * 查询
     * @param groupId
     * @return
     * @throws Exception
     */
    public IGroupsRenterRelValue queryGroupHeader(long groupId) throws Exception{
        if(groupId != 0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IGroupsRenterRelValue.S_GroupId,groupId,condition,params,false);
            SQLCon.connectSQL(IGroupsRenterRelValue.S_Role,1L,condition,params,false);
            List<IGroupsRenterRelValue> list = groupRenterRelDAO.queryGroupsRenterRelInfoByCondition(condition.toString(),params,-1,-1);
            if(list != null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

    /**
     * 根据用户主键和角色查询相关的组
     * @param userId
     * @param role
     * @return
     * @throws Exception
     */
    public List<IGroupsRenterRelValue> queryGroupRenterRelByUserIdAndRole(long userId,long role,int begin,int end) throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        if(userId > 0){
            SQLCon.connectSQL(IGroupsRenterRelValue.S_RenterId,userId,condition,params,false);
        }
        if(role>0){
            SQLCon.connectSQL(IGroupsRenterRelValue.S_Role,role,condition,params,false);
        }
        return groupRenterRelDAO.queryGroupsRenterRelInfoByCondition(condition.toString(),params,begin,end);
    }
    //查询数量
    public long queryGroupRenterRelCountByUserIdAndRole(long userId,long role) throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        if(userId > 0){
            SQLCon.connectSQL(IGroupsRenterRelValue.S_RenterId,userId,condition,params,false);
        }
        if(role>0){
            SQLCon.connectSQL(IGroupsRenterRelValue.S_Role,role,condition,params,false);
        }
        return groupRenterRelDAO.queryGroupsRenterRelCountByCondition(condition.toString(),params);
    }

    /**
     * 用户退出组
     * @param userId
     * @param groupId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void exitGroup(long userId,long groupId) throws Exception{
        if(userId ==0 || groupId ==0){
            throw new Exception("用户退出组失败");
        }
        //先判断用户是否在组里面，然后退出
        IGroupsRenterRelValue groupsRenterRelValue = queryGroupRenterRelByUserIdAndGroupId(userId,groupId);
        if(groupsRenterRelValue != null){
            groupRenterRelDAO.delete(groupsRenterRelValue);
        }
    }


    /**
     * 根据用户的ID查询用户拥有的组
     * @param groupId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IGroupsRenterRelValue> queryGroupRenterRelInfoByGroupId(long groupId,int begin,int end)throws Exception{
        if(groupId !=0){
            //先查询这个组里面的成员ID
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IGroupsRenterRelValue.S_GroupId,groupId,condition,params,false);
            return groupRenterRelDAO.queryGroupsRenterRelInfoByCondition(condition.toString(),params,begin,end);
        }
        return null;
    }


    /**
     * 根据用户ID和组的ID查询groupRenterRel表
     * @param userId
     * @param groupId
     * @throws Exception
     */
    public IGroupsRenterRelValue queryGroupRenterRelByUserIdAndGroupId(long userId,long groupId) throws Exception{
        if(userId ==0 || groupId ==0){
            throw new Exception("参数不正确");
        }
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IGroupsRenterRelValue.S_GroupId,groupId,condition,params,false);
        SQLCon.connectSQL(IGroupsRenterRelValue.S_RenterId,userId,condition,params,false);
        List<IGroupsRenterRelValue> list = groupRenterRelDAO.queryGroupsRenterRelInfoByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size() == 1){
            return list.get(0);
        }
        return null;
    }
    /**
     * 用户加入团
     * @param userId
     * @param groupId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGroup(long userId,long groupId) throws Exception{
        if(userId == 0){
            throw new Exception("请先登录账号");
        }
        IGroupsValue groupsValue = groupsSV.queryGroupInfoByGroupsId(groupId);
        if(groupsValue != null){
            groupsValue.setCurrentNumber(groupsValue.getCurrentNumber()+1);
            groupsSV.save(groupsValue);
            GroupsRenterRelBean bean = new GroupsRenterRelBean();
            bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            bean.setGroupId(groupId);
            bean.setRenterId(userId);
            bean.setRole(2L);
            groupRenterRelDAO.save(bean);
        }else{
            throw new Exception("输入的组并不存在");
        }

    }


}
