package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IApplyGroupMessageDAO;
import com.ncu.dao.interfaces.IAppointmentDAO;
import com.ncu.table.engine.AppointmentEngine;
import com.ncu.table.ivalue.IAppointmentValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/4/14.
 */
@Repository("AppointmentDAOImpl")
public class AppointmentDAOImpl implements IAppointmentDAO {
    @Autowired
    private AppointmentEngine appointmentEngine;

    public void save(IAppointmentValue value) throws Exception{
        appointmentEngine.save(value);
    }
    public List<IAppointmentValue> queryAppointmentByCondition(String condition, HashMap params, int begin, int end)throws Exception{
        return appointmentEngine.queryByCondition(condition,params,begin,end);
    }
    public long queryAppointmentCountByCondition(String condition, HashMap params)throws Exception{
        return appointmentEngine.queryCountByCondition(condition,params);
    }
}
