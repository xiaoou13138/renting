package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IPictureValue;

public interface IPictureDAO {
	/**
	 * 根据图片的的ID来查询
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public IPictureValue getPictureByPictureId(long pictureId) throws Exception;

}
