package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IHouseFacilityRelValue;
import com.ncu.table.ivalue.IHouseValue;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IHouseSV {
	public HashMap queryHouseInfoByCondtion(Map condition, String begin, String end) throws  Exception;
	public void save(IHouseValue value) throws Exception;
	public void saveUpLoadHouseInfo(JSONObject houseInfoObject,long userId,ArrayList pictureList) throws Exception;

	/**
	 * 查询房源详细信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public HashMap queryDetailsByHouseId(String houseId) throws Exception;

	/**
	 * 根据房子的ID查询房源信息定义表
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public IHouseValue queryHouseDefInfoByHouseId(String houseId) throws Exception;


}
