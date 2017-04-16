package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IAppointmentValue;

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
    public void saveAppointmentInfo(long userId,long houseId,long appointmentType) throws Exception;

    /**
     * 查询预约信息
     * @param userId
     * @param houseId
     * @param appointmentType
     * @return
     * @throws Exception
     */
    public IAppointmentValue queryAppointment(long userId,long houseId,long appointmentType)throws Exception;
}
