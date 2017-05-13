package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IHouseFacilityRelValue;
import com.ncu.table.ivalue.IHouseValue;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IHouseSV {
	public HashMap queryHouseInfoByCondition(String searchContent, int begin, int end) throws  Exception;
	public void save(IHouseValue value) throws Exception;
	public void saveUpLoadHouseInfo(JSONObject houseInfoObject,long userId,ArrayList mainPictureList,ArrayList normalPictureList,int actionType) throws Exception;

	/**
	 * 查询房源详细信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public HashMap queryDetailsByHouseId(long userId,long houseId) throws Exception;

	/**
	 * 根据房子的ID查询房源信息定义表
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public IHouseValue queryHouseDefInfoByHouseId(long houseId) throws Exception;

	/**
	 * 根据用户的主键和查询类型查询房子的信息
	 * @param userId
	 * @param queryType 1是收藏的房子  2是预约的房子
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public HashMap queryHouseInfoByUserIdAndQueryType(long userId,int queryType,int begin,int end) throws Exception;

	/**
	 * 查询房东的房源信息
	 * @param landlordId
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public HashMap queryHouseInfoByLandlordIdForController(long landlordId,int begin,int end) throws Exception;

	/**
	 * 查询房子的信息
	 * @param landlordId
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<IHouseValue> queryHouseInfoByLandlordId(long landlordId,int begin,int end) throws Exception;
	public long queryHouseInfoCountByLandlordId(long landlordId) throws Exception;

	/**
	 * 删除房子的信息
	 * @param userId
	 * @param houseId
	 * @throws Exception
	 */
	public void delHouseInfoByUserIdAndHouseId(long userId,long houseId)  throws Exception;

	/**
	 * 查询房子的信息
	 * @param userId
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public IHouseValue queryHouseByUserIdAndHouseId(long userId,long houseId)  throws Exception;

	/**
	 * 查询房子的所有信息（修改页面需要）
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public HashMap queryAllHouseInfoByHouseId(long houseId) throws Exception;

	/**
	 * 根据房子主键删除房子信息
	 * @param houseId
	 * @throws Exception
	 */
	public void deleteHouseByHouseId(long houseId)throws Exception;

	public List<IHouseValue> queryHouseInfoBySearchContent(String searchContent,int begin,int end)throws Exception;
	public long queryHouseCountBySearchContent(String searchContent)throws Exception;

}
