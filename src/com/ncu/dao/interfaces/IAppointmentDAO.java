package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IAppointmentValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by zuowy on 2017/4/14.
 */
public interface IAppointmentDAO {
    public void save(IAppointmentValue value) throws Exception;
    public List<IAppointmentValue> queryAppointmentByCondition(String condition, HashMap params,int begin,int end)throws Exception;
    public long queryAppointmentCountByCondition(String condition, HashMap params)throws Exception;
}
