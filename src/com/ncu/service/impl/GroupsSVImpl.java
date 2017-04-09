package com.ncu.service.impl;

import com.ncu.dao.interfaces.IGroupRenterRelDAO;
import com.ncu.dao.interfaces.IGroupsDAO;
import com.ncu.service.interfaces.IGroupsSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.ivalue.IGroupsRenterRelValue;
import com.ncu.table.ivalue.IGroupsValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.SQLCon;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/1.
 */
@Service("GroupsSVImpl")
public class GroupsSVImpl implements IGroupsSV{
    @Resource(name = "GroupsDAOImpl")
    private IGroupsDAO groupsDAO;

    @Resource(name = "GroupRenterRelDAOImpl")
    private IGroupRenterRelDAO groupRenterRelDAO;

    @Resource(name = "UserSVImpl")
    private IUserSV userSV;

    /**
     * 根据组的ID查询组的信息
     * @param groupsId
     * @return
     * @throws Exception
     */
    @Override
    public IGroupsValue queryGroupInfoByGroupsId(String groupsId) throws Exception {
        if(StringUtils.isNotBlank(groupsId)){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IGroupsValue.S_GroupId,Long.parseLong(groupsId),condition,params,false);
            SQLCon.connectSQL(IGroupsValue.S_DelFlag,1L,condition,params,false);
            List<IGroupsValue> list = groupsDAO.queryGroupsInfoByCondition(condition.toString(),params,-1,-1);
            if(list !=null && list.size()>0){
                return  list.get(0);
            }
        }
        return null;
    }

    @Override
    public void save(IGroupsValue value) throws Exception {
        groupsDAO.save(value);
    }

    /**
     * 根据用户的ID查询用户拥有的组
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap queryGroupInfoByUserId(long userId,int begin,int end) throws Exception{
        HashMap rtnMap = new HashMap();
        List rtnList = new ArrayList();
        if(userId != -1){
            //首先查关系表
            List<IGroupsRenterRelValue> groupsRenterRelValueList = queryGroupsRenterRelInfoByUserId(userId,begin,end);
            long count = queryGroupsRenterRelCountInfoByUserId(userId);
            rtnMap.put("count",count);
            if(groupsRenterRelValueList!= null){
                int length = groupsRenterRelValueList.size();
                for(int i = 0;i<length;i++){
                    long groupsId = groupsRenterRelValueList.get(i).getGroupId();
                    long roleType = groupsRenterRelValueList.get(i).getRole();
                    String roleTypeStr ="";
                    if(roleType==0){
                        roleTypeStr ="组长";
                    }else{
                        roleTypeStr ="组员";
                    }
                    //查询组的信息
                    IGroupsValue groupsValue =queryGroupInfoByGroupsId(String.valueOf(groupsId));
                    if(groupsValue != null ){
                        HashMap map = new HashMap();
                        map.put("groupId",groupsValue.getGroupId());
                        map.put("groupName",groupsValue.getGroupName());
                        map.put("userTypeInGroup",roleTypeStr);
                        map.put("groupAddress",groupsValue.getGroupAddress());
                        map.put("groupNum",groupsValue.getGroupNumber());
                        map.put("currentNum",groupsValue.getCurrentNumber());
                        map.put("infor",groupsValue.getGroupInfor());
                        rtnList.add(map);
                    }

                }
            }
        }
        rtnMap.put("groupList",rtnList);
        return rtnMap;
    }

    /**
     * 根据用户的ID查询用户的组的关系
     * @return
     * @throws Exception
     */
    public List<IGroupsRenterRelValue> queryGroupsRenterRelInfoByUserId(long userId,int begin,int end) throws Exception {
        if(userId != -1){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IGroupsRenterRelValue.S_RenterId,userId,condition,params,false);
            return groupRenterRelDAO.queryGroupsRenterRelInfoByCondition(condition.toString(),params,begin,end);
        }
        return null;
    }
    public long queryGroupsRenterRelCountInfoByUserId(long userId) throws Exception{
        if(userId != -1){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IGroupsRenterRelValue.S_RenterId,userId,condition,params,false);
            List<IGroupsRenterRelValue> list = groupRenterRelDAO.queryGroupsRenterRelInfoByCondition(condition.toString(),params,-1,-1);
            if(list != null){
                return list.size();
            }
        }
        return 0;
    }

    /**
     * 根据组的ID查询成员的信息
     * @param groupId
     * @return
     * @throws Exception
     */
    public List queryUserInfoByGroupId(String groupId,int begin,int end) throws Exception{
        List rtnList = new ArrayList();
        if(StringUtils.isNotBlank(groupId)){
            //先查询这个组里面的成员ID
            List<IGroupsRenterRelValue> groupsRenterRelValueList = queryGroupRenterRelInfoByGroupId(groupId,begin,end);
            if(groupsRenterRelValueList!=null){
                int length= groupsRenterRelValueList.size();
                for(int i =0;i<length;i++){
                    long userId = groupsRenterRelValueList.get(i).getRenterId();
                    IUserValue userValue = userSV.queryUserInfoByUserId(String.valueOf(userId));
                    HashMap map = new HashMap();
                    long role = groupsRenterRelValueList.get(i).getRole();
                    String roleStr = "";
                    if(role ==0 ){
                        roleStr = "组长";
                    }else{
                        roleStr = "组员";
                    }
                    map.put("userId",userValue.getUserId());
                    map.put("userTypeInGroup",roleStr);
                    map.put("name",userValue.getUserName());
                    map.put("sex",userValue.getUserSex());
                    map.put("age",userValue.getUserAge());
                    map.put("phoneNum",userValue.getUserPhone());
                    rtnList.add(map);
                }
            }
        }
        return rtnList;
    }

    public List<IGroupsRenterRelValue> queryGroupRenterRelInfoByGroupId(String groupId,int begin,int end)throws Exception{
        if(StringUtils.isNotBlank(groupId)){
            //先查询这个组里面的成员ID
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IGroupsRenterRelValue.S_GroupId,groupId,condition,params,false);
            return groupRenterRelDAO.queryGroupsRenterRelInfoByCondition(condition.toString(),params,begin,end);
        }
        return null;
    }
}
