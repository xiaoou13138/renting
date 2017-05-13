package com.ncu.service.impl;

import com.ncu.cache.StaticDataCache;
import com.ncu.dao.interfaces.IAppointmentDAO;
import com.ncu.service.interfaces.IAppointmentSV;
import com.ncu.service.interfaces.IMessageSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.bean.AppointmentBean;
import com.ncu.table.ivalue.IAppointmentValue;
import com.ncu.table.ivalue.IGroupsRenterRelValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.infinispan.commons.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by zuowy on 2017/4/14.
 */
@Service("AppointmentSVImpl")
public class AppointmentSVImpl implements IAppointmentSV {
    @Resource(name="AppointmentDAOImpl")
    private IAppointmentDAO appointmentDAO;

    @Resource(name="UserSVImpl")
    private IUserSV userSV;

    @Resource(name="MessageSVImpl")
    private IMessageSV messageSV;

    @Autowired
    private StaticDataCache staticDataCache;

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

    /**
     * 查询房子的预约的数量有多少
     * @param houseId
     * @return
     * @throws Exception
     */
    public long queryAppointmentCountByHouseId(long houseId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IAppointmentValue.S_HouseId,houseId,condition,params,false);
        SQLCon.connectSQL(IAppointmentValue.S_DelFlag,1L,condition,params,false);
        return appointmentDAO.queryAppointmentCountByCondition(condition.toString(),params);
    }

    /**
     * 查询房子的预约信息
     * @param houseId
     * @return
     * @throws Exception
     */
    public List<IAppointmentValue> queryAppointmentByHouseId(long houseId,int begin,int end)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IAppointmentValue.S_HouseId,houseId,condition,params,false);
        SQLCon.connectSQL(IAppointmentValue.S_DelFlag,1L,condition,params,false);
        return appointmentDAO.queryAppointmentByCondition(condition.toString(),params,begin,end);
    }

    public HashMap queryAppointmentByHouseIdForController(long houseId,int begin,int end)throws Exception{
        HashMap rtnMap = new HashMap();
        List<IAppointmentValue> list = queryAppointmentByHouseId(houseId,begin,end);
        long count = queryAppointmentCountByHouseId(houseId);
        if(list !=  null && list.size()>0){
            int length = list.size();
            ArrayList rtnList = new ArrayList();
            for(int i = 0;i<length;i++){
                IAppointmentValue appointmentValue = list.get(i);
                HashMap map = new HashMap();
                long userId = appointmentValue.getRenterId();
                IUserValue userValue = userSV.queryUserInfoByUserId(userId);
                if(userValue == null){
                    throw new Exception("预约的用户不存在");
                }
                String userName = userValue.getUserName();
                long phone = userValue.getUserPhone();
                map.put("appointmentId",appointmentValue.getOrderId());


                map.put("appointmentUserName",userName);
                map.put("phone",phone);
                map.put("appointmentType",appointmentValue.getRenterType());
                map.put("appointmentTime",TimeUtil.formatTimeyyyyMMddhhmmss(appointmentValue.getOrderDate()));
                rtnList.add(map);
            }
            rtnMap.put("appointmentList",rtnList);
        }

        return rtnMap;
    }

    /**
     * 删除预约信息(完整流程)
     * @param appointmentId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAppointmentForController(long userId,long appointmentId,String content,int userType)throws Exception{
        IAppointmentValue appointmentValue = queryAppointmentById(appointmentId);
        if(appointmentValue == null){
            throw new Exception("预约信息不存在");
        }
        appointmentValue.setDelFlag(0L);
        appointmentDAO.save(appointmentValue);

        //发送一条私信消息
        if(userType == 1){
            if(StringUtils.isBlank(content)){
                content = "您的预约已经被房源上传者取消，具体原因房源上传者没有填写！如需了解，请拨打房源上传者号码";
            }
            messageSV.saveMessageByUserIdAndContent(userId,0,content,1L,appointmentValue.getRenterId());
        }
    }

    /**
     * 查询预约信息
     * @param appointmentId 预约信息主键
     * @return
     * @throws Exception
     */
    public IAppointmentValue queryAppointmentById(long appointmentId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IAppointmentValue.S_OrderId,appointmentId,condition,params,false);
        SQLCon.connectSQL(IAppointmentValue.S_DelFlag,1L,condition,params,false);
        List<IAppointmentValue> list =  appointmentDAO.queryAppointmentByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;

    }
}
