package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IHouseDAO;
import com.ncu.table.engine.HouseEngine;
import com.ncu.table.ivalue.IHouseValue;
@Repository("HouseDAOImpl")
public class HouseDAOImpl implements IHouseDAO{
	@Autowired
	HouseEngine houseEngine;
	@Override
	public List<IHouseValue> getHouseInfoByCondition(String condition,
			HashMap params, int beginPage, int endPage)
			throws Exception {
		return (List<IHouseValue>) houseEngine.queryByCondition(condition, params, beginPage, endPage);
	}
	public long getCount(String condition, HashMap params)throws Exception{
		return houseEngine.queryCountByCondition(condition,params);
	}

	public void save(IHouseValue value) throws Exception{
		houseEngine.save(value);
	}
	

}
