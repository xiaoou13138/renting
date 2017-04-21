package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IAppointmentValue;
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
}
