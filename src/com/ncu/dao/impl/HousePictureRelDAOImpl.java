package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IHousePictureRelDAO;
import com.ncu.table.engine.HousePictureRelEngine;
import com.ncu.table.ivalue.IHousePictureRelValue;
import com.ncu.util.SQLCon;
@Repository("HousePictureRelDAOImpl")
public class HousePictureRelDAOImpl implements IHousePictureRelDAO{

	/**
	 * 根据房间的ID来查询主照片
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	@Override
	public IHousePictureRelValue getMainRelByHouseId(long houseId)
			throws Exception {
		StringBuilder condition  = new StringBuilder("");
		HashMap<String, String> params = new HashMap<String, String>();
		SQLCon.connectSQL(IHousePictureRelValue.S_HouseId, String.valueOf(houseId), condition, params, false);
		SQLCon.connectSQL(IHousePictureRelValue.S_DelFlag, "1", condition, params, false);
		SQLCon.connectSQL(IHousePictureRelValue.S_RelType, "MAIN", condition, params, false);
		List<IHousePictureRelValue>list = (List<IHousePictureRelValue>) HousePictureRelEngine.queryByCondition(condition.toString(), params, -1, -1);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
