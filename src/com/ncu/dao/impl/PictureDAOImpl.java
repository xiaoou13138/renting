package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IPictureDAO;
import com.ncu.table.engine.PictureEngine;
import com.ncu.table.ivalue.IPictureValue;
import com.ncu.util.SQLCon;
@Repository("PictureDAOImpl")
public class PictureDAOImpl implements IPictureDAO{

	/**
	 * 根据图片的的ID来查询
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	@Override
	public IPictureValue getPictureByPictureId(long pictureId) throws Exception {
		StringBuilder condition = new StringBuilder("");
		HashMap<String, String > params = new HashMap<String, String>();
		SQLCon.connectSQL(IPictureValue.S_PictureId, String.valueOf(pictureId), condition, params, false);
		List<IPictureValue> values = (List<IPictureValue>) PictureEngine.queryByCondition(condition.toString(), params, -1, -1);
		if(values != null && values.size()>0){
			return values.get(0);
		}else{
			return null;
		}
	}

}
