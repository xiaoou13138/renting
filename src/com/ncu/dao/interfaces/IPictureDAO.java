package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IPictureValue;

import java.util.HashMap;
import java.util.List;

public interface IPictureDAO {
	/**
	 * 根据图片的的ID来查询
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<IPictureValue> getPictureByCondition(String condition, HashMap params, int begin, int end) throws Exception;
	public void save(IPictureValue value)throws  Exception;

}
