package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IGroupsRenterRelValue;
import com.ncu.table.ivalue.IGroupsValue;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
/**
 * Created by zuowy on 2017/4/1.
 */
public interface IGroupsSV {
    public void save(IGroupsValue value) throws Exception;

    /**
     * 根据组的ID去查找组的信息
     * @param groupsId
     * @return
     * @throws Exception
     */
    public IGroupsValue queryGroupInfoByGroupsId(long groupsId) throws Exception;

    /**
     * 根据用户的ID查询用户拥有的组
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap queryGroupInfoByUserId(long userId,int begin,int end) throws Exception;



    /**
     * 根据组的ID查询用户的信息
     * @param groupId
     * @return
     * @throws Exception
     */
    public List queryUserInfoByGroupId(long groupId, int begin, int end) throws Exception;










    /**
     * 根据组的名称查询组的信息
     * @param groupName
     * @throws Exception
     */
    public IGroupsValue queryGroupInfoByName(String groupName) throws Exception;

    /**
     * 用户创建一个组
     * @param userId
     * @param json
     * @throws Exception
     */
    public void saveGroup(long userId, JSONObject json) throws Exception;

    /**
     * * 根据输入的搜索的内容查询组的信息
     * @param searchContent 搜索的内容 可能组的名称 也可能是组的ID
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IGroupsValue> queryGroupInfoByGroupNameOrGroupId(String searchContent,int begin,int end) throws Exception;

    /**
     * 查询分页需要的总数
     * @param searchContent
     * @return
     * @throws Exception
     */
    public long queryGroupInfoCountByGroupNameOrGroupId(String searchContent) throws Exception;

    /**
     * 给controller提供的根据查询条件查询组的信息服务
     * @param searchContent
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryGroupInfoForController(String searchContent,int begin,int end) throws Exception;

    /**
     * 用户预约时展现的组的信息
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryAppointmentGroup(long userId,int begin,int end) throws Exception;

    /**
     * 删除组
     * @param groupId
     * @throws Exception
     */
    public void deleteGroup(long groupId)throws Exception;

    /**
     * 组人数+1
     * @param groupId
     * @throws Exception
     */
    public void addGroupNum(long groupId)throws Exception;


}
