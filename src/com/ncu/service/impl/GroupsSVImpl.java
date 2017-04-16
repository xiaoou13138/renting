package com.ncu.service.impl;

import com.ncu.dao.interfaces.IGroupRenterRelDAO;
import com.ncu.dao.interfaces.IGroupsDAO;
import com.ncu.service.interfaces.IGroupRenterRelSV;
import com.ncu.service.interfaces.IGroupsSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.bean.GroupsBean;
import com.ncu.table.bean.GroupsRenterRelBean;
import com.ncu.table.ivalue.IGroupsRenterRelValue;
import com.ncu.table.ivalue.IGroupsValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.APPUtil;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.infinispan.commons.hash.Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Array;
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

    @Resource(name = "UserSVImpl")
    private IUserSV userSV;

    @Resource(name="GroupRenterRelSVImpl")
    IGroupRenterRelSV groupRenterRelSV;

    /**
     * 查询组定义的信息
     * @param groupsId
     * @return
     * @throws Exception
     */
    @Override
    public IGroupsValue queryGroupInfoByGroupsId(long groupsId) throws Exception {
        if(groupsId !=0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IGroupsValue.S_GroupId,groupsId,condition,params,false);
            SQLCon.connectSQL(IGroupsValue.S_DelFlag,1L,condition,params,false);
            List<IGroupsValue> list = groupsDAO.queryGroupsInfoByCondition(condition.toString(),params,-1,-1);
            if(list !=null && list.size()>0){
                return  list.get(0);
            }
        }
        return null;
    }

    /**
     * 保存组定义信息
     * @param value
     * @throws Exception
     */
    @Override
    public void save(IGroupsValue value) throws Exception {
        groupsDAO.save(value);
    }

    /**
     * controller
     * 查询组所有信息
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap queryGroupInfoByUserId(long userId,int begin,int end) throws Exception{
        HashMap rtnMap = new HashMap();
        List rtnList = new ArrayList();
        if(userId != 0){
            //首先查关系表
            List<IGroupsRenterRelValue> groupsRenterRelValueList = groupRenterRelSV.queryGroupRenterRelByUserIdAndRole(userId,-1,begin,end);
            long count = groupRenterRelSV.queryGroupRenterRelCountByUserIdAndRole(userId,-1);
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
                    IGroupsValue groupsValue =queryGroupInfoByGroupsId(groupsId);
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
     * 根据组的ID查询成员的信息
     * @param groupId
     * @return
     * @throws Exception
     */
    public List queryUserInfoByGroupId(long groupId,int begin,int end) throws Exception{
        List rtnList = new ArrayList();
        if(groupId !=0){
            //先查询这个组里面的成员ID
            List<IGroupsRenterRelValue> groupsRenterRelValueList = groupRenterRelSV.queryGroupRenterRelInfoByGroupId(groupId,begin,end);
            if(groupsRenterRelValueList!=null){
                int length= groupsRenterRelValueList.size();
                for(int i =0;i<length;i++){
                    long userId = groupsRenterRelValueList.get(i).getRenterId();
                    IUserValue userValue = userSV.queryUserInfoByUserId(userId);
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

    /**
     * 根据组的名称查询组的信息
     * @param groupName
     * @throws Exception
     */
    public IGroupsValue queryGroupInfoByName(String groupName) throws Exception{
        if(StringUtils.isNotBlank(groupName)){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IGroupsValue.S_GroupName,groupName,condition,params,false);
            List<IGroupsValue> list = groupsDAO.queryGroupsInfoByCondition(condition.toString(),params,-1,-1);
            if(list != null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }



    /**
     * 用户创建一个组
     * @param userId
     * @param json
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveGroup(long userId,JSONObject json) throws Exception{
        String groupName = json.getString("groupName");
        String address = APPUtil.getSafeStringFromJSONObject(json,"address");
        String groupInfor = APPUtil.getSafeStringFromJSONObject(json,"groupInfor");
        String groupNumber = json.getString("groupNumber");

        GroupsBean bean = new GroupsBean();
        bean.setGroupName(groupName);
        bean.setCurrentNumber(1L);
        bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        bean.setDelFlag(1L);
        bean.setCurrentNumber(1L);
        bean.setGroupAddress(address);
        bean.setGroupInfor(groupInfor);
        bean.setGroupNumber(Long.parseLong(groupNumber));
        groupsDAO.save(bean);

        groupRenterRelSV.saveAddGroupInfo(userId,bean.getGroupId(),1L);
    }



    /**
     * 标准服务
     *  根据输入的搜索的内容查询组的信息
     * @param searchContent 搜索的内容 可能组的名称 也可能是组的ID
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IGroupsValue> queryGroupInfoByGroupNameOrGroupId(String searchContent,int begin,int end) throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        if(StringUtils.isNotBlank(searchContent)){
            if(StringUtils.isNumeric(searchContent)){
                SQLCon.connectSQL(IGroupsValue.S_GroupId,Long.parseLong(searchContent),condition,params,false);
            }else{
                SQLCon.connectSQL(IGroupsValue.S_GroupName,searchContent,condition,params,true);
            }
        }
        return groupsDAO.queryGroupsInfoByCondition(condition.toString(),params,begin,end);
    }

    /**
     * 标准服务
     * 查询分页需要的总数
     * @param searchContent
     * @return
     * @throws Exception
     */
    public long queryGroupInfoCountByGroupNameOrGroupId(String searchContent) throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        if(StringUtils.isNotBlank(searchContent)){
            if(StringUtils.isNumeric(searchContent)){
                SQLCon.connectSQL(IGroupsValue.S_GroupId,Long.parseLong(searchContent),condition,params,false);
            }else{
                SQLCon.connectSQL(IGroupsValue.S_GroupName,searchContent,condition,params,true);
            }
        }
        return groupsDAO.getCountByCondition(condition.toString(),params);
    }

    /**
     * 查找组的信息
     * @param searchContent
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryGroupInfoForController(String searchContent,int begin,int end) throws Exception{
        HashMap rtnMap =  new HashMap();
        ArrayList list = new ArrayList();
        long count = 0;
        List<IGroupsValue> groupsValueList = queryGroupInfoByGroupNameOrGroupId(searchContent,begin,end);
        if(groupsValueList != null){
            int length = groupsValueList.size();
            for(int i=0;i<length;i++){
                HashMap map = new HashMap();
                map.put("groupName",groupsValueList.get(i).getGroupName());
                map.put("groupAddress",groupsValueList.get(i).getGroupAddress());
                map.put("groupId",groupsValueList.get(i).getGroupId());
                map.put("groupNum",groupsValueList.get(i).getGroupNumber());
                map.put("currentNum",groupsValueList.get(i).getCurrentNumber());
                map.put("infor",groupsValueList.get(i).getGroupInfor());
                list.add(map);
            }
            count = queryGroupInfoCountByGroupNameOrGroupId(searchContent);
        }
        rtnMap.put("groupList",list);
        rtnMap.put("count",count);
        return rtnMap;
    }

    /**
     * 用户预约时展现的组的信息
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryAppointmentGroup(long userId,int begin,int end) throws Exception{
        HashMap rtnMap =  new HashMap();
        ArrayList list = new ArrayList();
        List<IGroupsRenterRelValue> groupsRenterRelValueList = groupRenterRelSV.queryGroupRenterRelByUserIdAndRole(userId,1L,begin,end);
        long count = count = groupRenterRelSV.queryGroupRenterRelCountByUserIdAndRole(userId,1L);
        if(groupsRenterRelValueList != null){
            int length = groupsRenterRelValueList.size();
            for(int i=0;i<length;i++){
                IGroupsValue groupsValue = queryGroupInfoByGroupsId(groupsRenterRelValueList.get(i).getGroupId());
                if(groupsValue != null){
                    HashMap map = new HashMap();
                    map.put("groupName",groupsValue.getGroupName());
                    map.put("groupAddress",groupsValue.getGroupAddress());
                    map.put("groupId",groupsValue.getGroupId());
                    map.put("groupId1",groupsValue.getGroupId());
                    map.put("groupNum",groupsValue.getGroupNumber());
                    map.put("currentNum",groupsValue.getCurrentNumber());
                    map.put("infor",groupsValue.getGroupInfor());
                    list.add(map);
                }
            }
        }
        rtnMap.put("groupList",list);
        rtnMap.put("count",count);
        return rtnMap;
    }


}
