package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IPictureDAO;
import com.ncu.table.engine.PictureEngine;
import com.ncu.table.ivalue.IPictureValue;
import com.ncu.util.SQLCon;
@Repository("PictureDAOImpl")
public class PictureDAOImpl implements IPictureDAO{
	@Autowired
	PictureEngine pictureEngine;
	/**
	 * 根据图片的的ID来查询
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<IPictureValue> getPictureByCondition(String condition, HashMap params, int begin, int end) throws Exception {
		return  pictureEngine.queryByCondition(condition, params, -1, -1);
	}
	@Override
	public void save(IPictureValue value) throws Exception {
		pictureEngine.save(value);
	}
}
