package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IGroupsRenterRelValue;
import com.ncu.table.ivalue.IGroupsValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/1.
 */
public interface IGroupsSV {
    public void save(IGroupsValue value) throws Exception;

    /**
     * 根据组的ID去查找组的信息
     * @param groupsId
     * @return
     * @throws Exception
     */
    public IGroupsValue queryGroupInfoByGroupsId(String groupsId) throws Exception;

    /**
     * 根据用户的ID查询用户拥有的组
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap queryGroupInfoByUserId(long userId,int begin,int end) throws Exception;

    /**
     * 根据用户的ID查询用户的组的关系
     * @return
     * @throws Exception
     */
    public List<IGroupsRenterRelValue> queryGroupsRenterRelInfoByUserId(long userId, int begin, int end) throws Exception;
    public long queryGroupsRenterRelCountInfoByUserId(long userId) throws Exception;

    /**
     * 根据组的ID查询用户的信息
     * @param groupId
     * @return
     * @throws Exception
     */
    public List queryUserInfoByGroupId(String groupId, int begin, int end) throws Exception;


    /**
     * 根据组的ID查询成员的主键
     * @param groupId
     * @return
     * @throws Exception
     */
    public List<IGroupsRenterRelValue> queryGroupRenterRelInfoByGroupId(String groupId,int begin,int end)throws Exception;
}
