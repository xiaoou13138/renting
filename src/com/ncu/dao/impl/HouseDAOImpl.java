package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IHouseDAO;
import com.ncu.table.engine.HouseEngine;
import com.ncu.table.ivalue.IHouseValue;
@Repository("HouseDAOImpl")
public class HouseDAOImpl implements IHouseDAO{

	@Override
	public List<IHouseValue> getHouseInfoByCondition(String condition,
			HashMap<String, String> params, int beginPage, int endPage)
			throws Exception {
		return (List<IHouseValue>) HouseEngine.queryByCondition(condition, params, beginPage, endPage);
	}
	

	

}
