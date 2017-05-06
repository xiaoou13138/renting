package com.ncu.service.impl;

import com.ncu.dao.interfaces.IHouseFacilityRelDAO;
import com.ncu.service.interfaces.IHouseFacilityRelSV;
import com.ncu.table.bean.HouseFacilityRelBean;
import com.ncu.table.ivalue.IHouseFacilityRelValue;
import com.ncu.util.SQLCon;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/30.
 */
@Service("HouseFacilityRelSVImpl")
public class HouseFacilityRelSVImpl implements IHouseFacilityRelSV {
    @Autowired
    @Qualifier("HouseFacilityRelDAOImpl")
    private IHouseFacilityRelDAO houseFacilityRelDAO;

    /**
     * 根据房子的主键查找相关的设备信息
     * @param houseId
     * @return
     * @throws Exception
     */
    public List<IHouseFacilityRelValue> queryHouseFacilityRelByHouseId(long houseId) throws Exception {
        if(houseId >0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IHouseFacilityRelValue.S_HouseId,houseId,condition,params,false);
            return houseFacilityRelDAO.queryHouseFacilityRelByCondition(condition.toString(),params,-1,-1);
        }
        return null;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(IHouseFacilityRelValue value) throws Exception {
        houseFacilityRelDAO.save(value);
    }

    /**
     * 保存房子和设备的关系
     * @param houseId
     * @param codeType
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveFacilityHouseRel(long houseId, String codeType) throws Exception {
        HouseFacilityRelBean bean = new HouseFacilityRelBean();
        bean.setHouseId(houseId);
        bean.setCodeType(codeType);
        houseFacilityRelDAO.save(bean);
    }

    /**
     * 查询房子和设备的关系
     * @param houseId
     * @return
     * @throws Exception
     */
    public JSONArray queryHouseFacilityRelListByHouseId(long houseId)throws Exception{
        JSONArray facilityList = new JSONArray();
        List<IHouseFacilityRelValue> houseFacilityRelValueList = queryHouseFacilityRelByHouseId(houseId);
        if(houseFacilityRelValueList!= null){
            int relLength = houseFacilityRelValueList.size();
            for(int j =0;j<relLength;j++){
                facilityList.add(houseFacilityRelValueList.get(j).getCodeType());
            }
        }
        return facilityList;
    }

    /**
     * 删除房子和设备的关系
     * @param value
     * @throws Exception
     */
    public void deleteHouseFacilityRel(IHouseFacilityRelValue value)throws Exception{
        houseFacilityRelDAO.deleteHouseFacilityRel(value);
    }
}
