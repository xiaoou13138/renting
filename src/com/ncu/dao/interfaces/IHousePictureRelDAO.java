package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IHousePictureRelValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IHousePictureRelDAO {
	/**
	 * 根据房间的ID来查询主照片d
	 * @return
	 * @throws Exception
	 */
	public List<IHousePictureRelValue> getHousePictureRelByCondition(String condition, HashMap params, int begin, int end) throws Exception;
	public void save(IHousePictureRelValue value) throws  Exception;
	public long getHousePictureRelCountByCondition(String condition, HashMap params)throws Exception;
	public void deleteHousePictureRel(IHousePictureRelValue value)throws Exception;
}
