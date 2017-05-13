package com.ncu.service.impl;

import com.ncu.dao.interfaces.IHouseCollectDAO;
import com.ncu.service.interfaces.IHouseCollectSV;
import com.ncu.table.bean.HouseCollectBean;
import com.ncu.table.ivalue.IHouseCollectValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zuowy on 2017/4/14.
 */
@Service("HouseCollectSVImpl")
public class HouseCollectSVImpl implements IHouseCollectSV {
    @Resource(name="HouseCollectDAOImpl")
    private IHouseCollectDAO houseCollectDAO;
    /**
     * 根据用户ID和房子ID保存收藏信息
     * @param userId
     * @param houseId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCollectInfoByUserIdAndHouseId(long userId,long houseId) throws Exception{
        if(userId >0 && houseId >0){
            //先查询是否已经收藏过
            IHouseCollectValue value = queryCollectInfoByUserIdAndHouseId(userId,houseId);
            if(value == null){
                HouseCollectBean bean = new HouseCollectBean();
                bean.setCollectDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
                bean.setCollectorId(userId);
                bean.setHouseId(houseId);
                houseCollectDAO.save(bean);
            }
        }
    }

    /**
     * 查询收藏的信息
     * @param userId
     * @param houseId
     * @return
     * @throws Exception
     */
    public IHouseCollectValue queryCollectInfoByUserIdAndHouseId(long userId, long houseId) throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        if(userId > 0){
            SQLCon.connectSQL(IHouseCollectValue.S_CollectorId,userId,condition,params,false);
        }
        if(houseId > 0){
            SQLCon.connectSQL(IHouseCollectValue.S_HouseId,houseId,condition,params,false);
        }
        List<IHouseCollectValue> list = houseCollectDAO.queryHouseCollectByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 删除收藏
     * @param userId
     * @param houseId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteHouseCollectByUserIdAndHouseId(long userId, long houseId) throws Exception{
        IHouseCollectValue houseCollectValue = queryCollectInfoByUserIdAndHouseId(userId, houseId);
        if(houseCollectValue != null){
            houseCollectDAO.delete(houseCollectValue);
        }
    }

    /**
     * 查询用户收藏的房子
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<IHouseCollectValue> queryCollectHouseByUserId(long userId,int begin,int end) throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IHouseCollectValue.S_CollectorId,userId,condition,params,false);
        return houseCollectDAO.queryHouseCollectByCondition(condition.toString(),params,begin,end);
    }
    public long queryCollectHouseCountByUserId(long userId) throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IHouseCollectValue.S_CollectorId,userId,condition,params,false);
        return houseCollectDAO.queryHouseCollectCountByCondition(condition.toString(),params);
    }
}
