package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IHouseFacilityRelValue;
import java.util.List;
/**
 * Created by xiaoou on 2017/3/30.
 */
public interface IHouseFacilityRelSV {
    public void save(IHouseFacilityRelValue value) throws Exception;
    public List<IHouseFacilityRelValue> queryHouseFacilityRelByHouseId(long houseId) throws Exception;

    public void saveFacilityHouseRel(long houseId,String codeType) throws Exception;
}
