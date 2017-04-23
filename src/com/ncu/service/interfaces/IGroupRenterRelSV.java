package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IGroupsRenterRelValue;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/10.
 */
public interface IGroupRenterRelSV {
    /**
     * 标准服务
     * 加入一个组
     * @param userId
     * @param groupId
     * @throws Exception
     */
    public void saveAddGroupInfo(long userId,long groupId,long role) throws Exception;

    /**
     * 根据团的主键查询团长是谁
     * @param groupId
     * @return
     * @throws Exception
     */
    public IGroupsRenterRelValue queryGroupHeader(long groupId) throws Exception;

    /**
     * 根据用户ID和组的ID查询groupRenterRel表
     * @param userId
     * @param groupId
     * @throws Exception
     */
    public IGroupsRenterRelValue queryGroupRenterRelByUserIdAndGroupId(long userId,long groupId) throws Exception;

    /**
     * 用户退出组
     * @param userId
     * @param groupId
     * @throws Exception
     */
    public void exitGroup(long userId,long groupId) throws Exception;

    /**
     * 根据组的ID查询成员的主键
     * @param groupId
     * @return
     * @throws Exception
     */
    public List<IGroupsRenterRelValue> queryGroupRenterRelInfoByGroupId(long groupId,int begin,int end)throws Exception;

    /**
     * 用户添加团
     * @param userId
     * @param groupId
     * @throws Exception
     */
    public void addGroup(long userId,long groupId) throws Exception;

    /**
     * 根据用户的ID查询用户的组的关系
     * @return
     * @throws Exception
     */
    public List<IGroupsRenterRelValue> queryGroupRenterRelByUserIdAndRole(long userId, long role,int begin, int end) throws Exception;
    public long queryGroupRenterRelCountByUserIdAndRole(long userId, long role) throws Exception;


}
