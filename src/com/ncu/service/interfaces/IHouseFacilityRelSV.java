package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IHouseFacilityRelValue;
import net.sf.json.JSONArray;

import java.util.List;
/**
 * Created by zuowy on 2017/3/30.
 */
public interface IHouseFacilityRelSV {
    public void save(IHouseFacilityRelValue value) throws Exception;
    public List<IHouseFacilityRelValue> queryHouseFacilityRelByHouseId(long houseId) throws Exception;

    /**
     * 保存房子和设备的关系
     * @param houseId
     * @param codeType
     * @throws Exception
     */
    public void saveFacilityHouseRel(long houseId,String codeType) throws Exception;

    /**
     * 查询房子和设备的关系
     * @param houseId
     * @return
     * @throws Exception
     */
    public JSONArray queryHouseFacilityRelListByHouseId(long houseId)throws Exception;

    /**
     * 删除房子和设备的关系
     * @param value
     * @throws Exception
     */
    public void deleteHouseFacilityRel(IHouseFacilityRelValue value)throws Exception;
}
