package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IHousePictureRelDAO;
import com.ncu.table.engine.HousePictureRelEngine;
import com.ncu.table.ivalue.IHousePictureRelValue;
import com.ncu.util.SQLCon;
@Repository("HousePictureRelDAOImpl")
public class HousePictureRelDAOImpl implements IHousePictureRelDAO{
	@Autowired
	HousePictureRelEngine housePictureRelEngine;
	public List<IHousePictureRelValue> getHousePictureRelByCondition(String condition, HashMap params, int begin, int end)
			throws Exception {
		return  housePictureRelEngine.queryByCondition(condition, params, -1, -1);
	}
	public void save(IHousePictureRelValue value) throws  Exception{
		housePictureRelEngine.save(value);
	}

	public long getHousePictureRelCountByCondition(String condition, HashMap params)throws Exception{
		return  housePictureRelEngine.queryCountByCondition(condition, params);
	}
	public void deleteHousePictureRel(IHousePictureRelValue value)throws Exception{
		housePictureRelEngine.delete(value);
	}

}
