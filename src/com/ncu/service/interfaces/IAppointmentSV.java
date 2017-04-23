package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IAppointmentValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/14.
 */
public interface IAppointmentSV {
    /**
     * 保存预约信息
     * @param userId
     * @param houseId
     * @param appointmentType
     * @throws Exception
     */
    public void saveAppointmentInfo(long userId,long groupId,long houseId,long appointmentType) throws Exception;

    /**
     * 查询预约信息
     * @param userId
     * @param houseId
     * @param appointmentType
     * @return
     * @throws Exception
     */
    public IAppointmentValue queryAppointment(long userId,long groupId,long houseId,long appointmentType)throws Exception;

    /**
     * 查询用户预约的房子
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IAppointmentValue> queryAppointmentHouseByUserId(long userId,int begin,int end) throws Exception;
    public long queryAppointmentHouseCountByUserId(long userId) throws Exception;

    /**
     * 查询房子的预约的数量有多少
     * @param houseId
     * @return
     * @throws Exception
     */
    public long queryAppointmentCountByHouseId(long houseId)throws Exception;

    /**
     * 查询房子的预约信息
     * @param houseId
     * @return
     * @throws Exception
     */
    public List<IAppointmentValue> queryAppointmentByHouseId(long houseId,int begin,int end)throws Exception;

    /**
     * 查询房子的预约信息
     * @param houseId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryAppointmentByHouseIdForController(long houseId, int begin, int end)throws Exception;

    /**
     * 删除预约信息(完整流程)
     * @param appointmentId
     * @throws Exception
     */
    public void deleteAppointmentForController(long userd,long appointmentId,String content)throws Exception;

    /**
     * 查询预约信息
     * @param appointmentId 预约信息主键
     * @return
     * @throws Exception
     */
    public IAppointmentValue queryAppointmentById(long appointmentId)throws Exception;
}
