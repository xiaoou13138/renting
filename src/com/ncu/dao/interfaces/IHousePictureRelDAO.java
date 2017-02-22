package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IHousePictureRelValue;

public interface IHousePictureRelDAO {
	/**
	 * 根据房间的ID来查询主照片
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public IHousePictureRelValue getMainRelByHouseId(long houseId) throws Exception;

}
