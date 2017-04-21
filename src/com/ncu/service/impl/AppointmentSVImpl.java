package com.ncu.service.impl;

import com.ncu.dao.interfaces.IAppointmentDAO;
import com.ncu.service.interfaces.IAppointmentSV;
import com.ncu.table.bean.AppointmentBean;
import com.ncu.table.ivalue.IAppointmentValue;
import com.ncu.table.ivalue.IGroupsRenterRelValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/14.
 */
@Service("AppointmentSVImpl")
public class AppointmentSVImpl implements IAppointmentSV {
    @Resource(name="AppointmentDAOImpl")
    private IAppointmentDAO appointmentDAO;

    /**
     * 保存预约信息
     * @param userId
     * @param houseId
     * @param appointmentType
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAppointmentInfo(long userId,long groupId,long houseId,long appointmentType) throws Exception{
        IAppointmentValue  appointmentValue = queryAppointment(userId,groupId,houseId,appointmentType);
        if(appointmentValue != null){
            throw new Exception("此用户已经预约过!");
        }
        AppointmentBean bean = new AppointmentBean();
        bean.setHouseId(houseId);
        bean.setDelFlag(1L);
        bean.setOrderDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        bean.setRenterId(userId);
        bean.setGroupId(groupId);
        bean.setRenterType(appointmentType);
        appointmentDAO.save(bean);
    }

    /**
     * 查询预约信息
     * @param userId
     * @param houseId
     * @param appointmentType
     * @return
     * @throws Exception
     */
    public IAppointmentValue queryAppointment(long userId,long groupId, long houseId, long appointmentType)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IAppointmentValue.S_RenterType,appointmentType,condition,params,false);
        if(userId > 0){
            SQLCon.connectSQL(IAppointmentValue.S_RenterId,userId,condition,params,false);
        }
        if(houseId > 0 ){
            SQLCon.connectSQL(IAppointmentValue.S_HouseId,houseId,condition,params,false);
        }
        if(groupId > 0 ){
            SQLCon.connectSQL(IAppointmentValue.S_GroupId,groupId,condition,params,false);
        }
        List<IAppointmentValue> list = appointmentDAO.queryAppointmentByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询用户预约的房子
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IAppointmentValue> queryAppointmentHouseByUserId(long userId,int begin,int end) throws Exception{
        if( userId > 0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IAppointmentValue.S_RenterId,userId,condition,params,false);
            SQLCon.connectSQL(IAppointmentValue.S_DelFlag,1L,condition,params,false);
            return appointmentDAO.queryAppointmentByCondition(condition.toString(),params,-1,-1);
        }
        return null;
    }
    public long queryAppointmentHouseCountByUserId(long userId) throws Exception{
        if( userId > 0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IAppointmentValue.S_RenterId,userId,condition,params,false);
            SQLCon.connectSQL(IAppointmentValue.S_DelFlag,1L,condition,params,false);
            return appointmentDAO.queryAppointmentCountByCondition(condition.toString(),params);
        }
        return 0L;
    }


}
