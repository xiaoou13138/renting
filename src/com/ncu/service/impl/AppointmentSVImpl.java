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
     * @param entityId
     * @param houseId
     * @param appointmentType
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAppointmentInfo(long entityId,long houseId,long appointmentType) throws Exception{
        IAppointmentValue  appointmentValue = queryAppointment(entityId,houseId,appointmentType);
        if(appointmentValue != null){
            throw new Exception("此用户已经预约过!");
        }
        if(entityId >0 && houseId >0){
            AppointmentBean bean = new AppointmentBean();
            bean.setHouseId(houseId);
            bean.setDelFlag(1L);
            bean.setOrderDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            bean.setRenterId(entityId);
            bean.setRenterType(appointmentType);
            appointmentDAO.save(bean);
        }
    }

    /**
     * 查询预约信息
     * @param userId
     * @param houseId
     * @param appointmentType
     * @return
     * @throws Exception
     */
    public IAppointmentValue queryAppointment(long userId, long houseId, long appointmentType)throws Exception{
        if(userId > 0 && houseId >0 ){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IAppointmentValue.S_RenterId,userId,condition,params,false);
            SQLCon.connectSQL(IAppointmentValue.S_HouseId,houseId,condition,params,false);
            SQLCon.connectSQL(IAppointmentValue.S_RenterType,appointmentType,condition,params,false);
            List<IAppointmentValue> list = appointmentDAO.queryAppointmentByCondition(condition.toString(),params,-1,-1);
            if(list != null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

}
