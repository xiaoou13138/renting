package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IApplyGroupMessageValue;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/4/10.
 */
public interface IApplyGroupMessageSV {
    /**
     * 保存用户申请加入团
     * @param userId
     * @param groupId
     * @throws Exception
     */
    public void saveApplyGroupMessageForController(long userId,long groupId) throws Exception;

    /**
     * 标准服务
     * 根据用户的Id查询用户待审核的消息
     * @param userId
     * @return
     * @throws Exception
     */
    public List<IApplyGroupMessageValue> queryApplyGroupMessage(long userId) throws Exception;

    /**
     * contoller
     * 根据用户的ID查询用户待审核的数据
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap queryApplyGroupMessageForController(long userId)  throws Exception;

    /**
     * 审核加团申请
     * @param applyId
     * @param dealType accept是申请 refuse是拒绝
     * @throws Exception
     */
    public void dealMessage(long applyId,String dealType,String content)  throws Exception;

    /**
     * 根据applyId查询带批准的信息
     * @param applyId
     * @throws Exception
     */
    public IApplyGroupMessageValue queryApplyGroupMessageByApplyId(long applyId) throws Exception;

    /**
     * 查询申请信息
     * @param userId 申请人
     * @param acceptUserId 被申请人
     * @param groupId 申请的组
     * @return
     * @throws Exception
     */
    public IApplyGroupMessageValue queryApplyGroupMessage(long userId,long acceptUserId,long groupId)throws Exception;

    /**
     * 查询用户带审核消息的数量
     * @param userId
     * @return
     * @throws Exception
     */
    public long queryApplyMessageCountByUserId(long userId)throws Exception;

}
