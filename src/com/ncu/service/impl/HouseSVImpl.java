package com.ncu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ncu.cache.StaticDataCache;
import com.ncu.service.interfaces.*;
import com.ncu.table.bean.HouseBean;
import com.ncu.table.ivalue.*;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ncu.dao.interfaces.IHouseDAO;
import com.ncu.dao.interfaces.IHousePictureRelDAO;
import com.ncu.dao.interfaces.IPictureDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("HouseSVImpl")
public class HouseSVImpl implements IHouseSV{
	@Resource(name="HouseDAOImpl")
	private IHouseDAO houseDAO;
	@Resource(name="PictureSVImpl")
	private IPictureSV pictureSV;
	@Resource(name="HousePictureRelSVImpl")
	private IHousePictureRelSV housePictureRelSV;//房屋的图片
	@Resource(name="HouseFacilityRelSVImpl")
	private IHouseFacilityRelSV houseFacilityRelSV;
	@Resource(name="StaticDataCache")
	private StaticDataCache cache;
	@Resource(name="UserSVImpl")
	private IUserSV userSV;
	@Resource(name="HouseCollectSVImpl")
	private IHouseCollectSV houseCollectSV;
	@Resource(name="AppointmentSVImpl")
	private IAppointmentSV appointmentSV;


	/**
	 * 查询页面展示的信息
	 * @param condition
	 * @param beginI
	 * @param endI
	 * @return
	 * @throws Exception
	 */
	public HashMap queryHouseInfoByCondition(Map condition, String beginI, String endI) throws  Exception{
		HashMap rtnMap = new HashMap();
		int begin = Integer.parseInt(beginI);
		int end = Integer.parseInt(endI);
		Object searchContent = condition.get("searchContent");


		ArrayList rtnList = new ArrayList();
		StringBuilder condtionB = new StringBuilder();
		HashMap params = new HashMap();
		if(searchContent !=null){
			SQLCon.connectSQL(IHouseValue.S_HouseName,searchContent,condtionB,params,true);
		}

		List<IHouseValue> houseList = houseDAO.getHouseInfoByCondition(condtionB.toString(),params,begin,end);
		long count = houseDAO.getCount(condtionB.toString(),params);
		if(houseList != null){
			int length = houseList.size();
			for(int i = 0;i<length;i++){
				HashMap map = new HashMap();
				map.put("houseId",houseList.get(i).getHouseId());//房源ID
				map.put("landlord_id",houseList.get(i).getLandlordId());//房东ID
				map.put("houseName",houseList.get(i).getHouseName());//
				map.put("houseType",houseList.get(i).getHouseType());
				map.put("houseArea",houseList.get(i).getHouseArea());
				map.put("houseAddress",houseList.get(i).getHouseAddress());
				map.put("information",houseList.get(i).getInformation());
				map.put("money",houseList.get(i).getMoney());

				//查询房屋带有的设备
				long houseId = houseList.get(i).getHouseId();
				List<IHouseFacilityRelValue> houseFacilityRelValueList = houseFacilityRelSV.queryHouseFacilityRelByHouseId(houseId);
				if(houseFacilityRelValueList!= null){
					ArrayList facilityList = new ArrayList();
					int relLength = houseFacilityRelValueList.size();
					for(int j =0;j<relLength;j++){
						facilityList.add(houseFacilityRelValueList.get(j).getCodeType());
					}
					map.put("facility",facilityList);
				}
				//查询房子对应的图片
				IPictureValue pictureValue = pictureSV.queryMainPictureByHouseId(houseId);
				if(pictureValue !=null){
					map.put("mainPicture",pictureValue.getPicturePath());
				}
				rtnList.add(map);
			}

		}
		rtnMap.put("houseView",rtnList);
		rtnMap.put("count",count);
		return rtnMap;
	}

	/**
	 * 根据用户的主键和查询类型查询房子的信息
	 * @param userId
	 * @param queryType 1是收藏的房子  2是预约的房子
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public HashMap queryHouseInfoByUserIdAndQueryType(long userId,int queryType,int begin,int end) throws Exception{
		HashMap rtnMap = new HashMap();
		if(userId >0){
			List list = null;
			long count = 0;

			if(queryType ==1){
				list = houseCollectSV.queryCollectHouseByUserId(userId,begin,end);
				count = houseCollectSV.queryCollectHouseCountByUserId(userId);
			}else if(queryType ==2){
				list = appointmentSV.queryAppointmentHouseByUserId(userId,begin,end);
				count = appointmentSV.queryAppointmentHouseCountByUserId(userId);
			}
			if(list  != null && list.size()>0){
				ArrayList rtnList = new ArrayList();
				int length = list.size();
				for(int i = 0;i<length;i++){
					long houseId = 0;
					long appointmentType = 0;
					if(queryType ==1 ){
						IHouseCollectValue houseCollectValue = (IHouseCollectValue)list.get(i);
						houseId =houseCollectValue.getHouseId();
					}else{
						IAppointmentValue appointmentValue = (IAppointmentValue)list.get(i);
						houseId = appointmentValue.getHouseId();
						appointmentType = appointmentValue.getRenterType();
					}
					IHouseValue houseValue = queryHouseDefInfoByHouseId(houseId);
					if(houseValue != null){
						HashMap map = new HashMap();
						map.put("houseId",houseValue.getHouseId());//房源ID
						map.put("landlord_id",houseValue.getLandlordId());//房东ID
						map.put("houseName",houseValue.getHouseName());//
						map.put("houseType",houseValue.getHouseType());
						map.put("houseArea",houseValue.getHouseArea());
						map.put("houseAddress",houseValue.getHouseAddress());
						map.put("information",houseValue.getInformation());
						map.put("money",houseValue.getMoney());
						if(appointmentType != 0){
							map.put("appointmentType",appointmentType);
						}


						//查询房屋带有的设备
						List<IHouseFacilityRelValue> houseFacilityRelValueList = houseFacilityRelSV.queryHouseFacilityRelByHouseId(houseId);
						if(houseFacilityRelValueList!= null){
							ArrayList facilityList = new ArrayList();
							int relLength = houseFacilityRelValueList.size();
							for(int j =0;j<relLength;j++){
								facilityList.add(houseFacilityRelValueList.get(j).getCodeType());
							}
							map.put("facility",facilityList);
						}
						//查询房子对应的图片
						IPictureValue pictureValue = pictureSV.queryMainPictureByHouseId(houseId);
						if(pictureValue !=null){
							map.put("mainPicture",pictureValue.getPicturePath());
						}
						rtnList.add(map);
						rtnMap.put("houseView",rtnList);
						rtnMap.put("count",count);
					}
				}
			}
		}

		return rtnMap;
	}

	/***
	 * 保存房源定义表
	 * @param value
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(IHouseValue value) throws Exception {
		houseDAO.save(value);
	}

	/**
	 * 发布房源信息
	 * @param houseInfoObject
	 * @param userId
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUpLoadHouseInfo(JSONObject houseInfoObject,long userId,ArrayList pictureList) throws Exception{
		houseInfoObject.get("facility");
		long houseId = 0;
		if(userId > 0){
			HouseBean houseBean = new HouseBean();
			houseBean.setDelFlag(1L);
			houseBean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
			houseBean.setHouseArea(Long.parseLong(houseInfoObject.getString("houseArea")));
			houseBean.setHouseName(houseInfoObject.getString("houseName"));
			houseBean.setHouseType(houseInfoObject.getString("houseType"));
			houseBean.setInformation(houseInfoObject.getString("information"));
			houseBean.setLandlordId(userId);
			houseBean.setMoney(Long.parseLong(houseInfoObject.getString("price")));
			houseBean.setHouseAddress(houseInfoObject.getString("address"));
			houseBean.setDepositType(houseInfoObject.getString("depositType"));
			houseDAO.save(houseBean);
			houseId = houseBean.getHouseId();
			//保存图片和房源的关系
			if(pictureList !=null && pictureList.size()>0){
				int length  = pictureList.size();
				for(int i = 0;i<length;i++){
					HashMap map = (HashMap) pictureList.get(i);
					long pictureId = (long)map.get("PICTURE");
					String type = (String)map.get("TYPE");
					housePictureRelSV.savePictureRelByUserIdAndTypeAndPictureId(houseId,pictureId,type);
				}
			}

			//保存房子和设备的信息
			if(houseInfoObject.containsKey("facility")){
				JSONArray facilityArray = houseInfoObject.getJSONArray("facility");
				if(facilityArray.size()>0){
					int length = facilityArray.size();
					for(int i = 0;i<length;i++){
						String str = facilityArray.getString(i);
						houseFacilityRelSV.saveFacilityHouseRel(houseId,str);
					}
				}
			}


		}



	}
	/**
	 * 根据房子的ID查询房源详细信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public IHouseValue queryHouseDefInfoByHouseId(long houseId) throws Exception{
		if(houseId >0){
			StringBuilder condition = new StringBuilder();
			HashMap params = new HashMap();
			SQLCon.connectSQL(IHouseValue.S_HouseId,houseId,condition,params,false);
			SQLCon.connectSQL(IHouseValue.S_DelFlag,1L,condition,params,false);
			List<IHouseValue> list = houseDAO.getHouseInfoByCondition(condition.toString(),params,-1,-1);
			if(list != null  && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}


	/**
	 * 查询房源详细信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public HashMap queryDetailsByHouseId(long userId,long houseId) throws Exception{
		HashMap detailMap = new HashMap();
		//先查询房源定义表
		IHouseValue houseValue = queryHouseDefInfoByHouseId(houseId);
		if(houseValue !=null){
			detailMap.put("houseId",houseValue.getHouseId());//房源ID
			detailMap.put("landlord_id",houseValue.getLandlordId());//房东ID
			detailMap.put("houseName",houseValue.getHouseName());//
			detailMap.put("houseType",houseValue.getHouseType());
			detailMap.put("houseArea",houseValue.getHouseArea());
			detailMap.put("houseAddress",houseValue.getHouseAddress());
			detailMap.put("information",houseValue.getInformation());
			detailMap.put("money",houseValue.getMoney());
		}
		//查询收藏的信息,是否收藏过
		boolean collect = false;
		if(userId >0){
			IHouseCollectValue houseCollectValue = houseCollectSV.queryCollectInfoByUserIdAndHouseId(userId,houseId);
			if(houseCollectValue != null){
				collect = true;
			}
		}
		detailMap.put("collect",collect);
		//
		//房子的房东的信息
		long landlordId = houseValue.getLandlordId();
		//获取房东的手机号码
		IUserValue userValue = userSV.queryUserInfoByUserId(landlordId);
		if(userValue == null){
			throw new Exception("房子没有所有者");
		}
		detailMap.put("phone",userValue.getUserPhone());
		//查询房子和照片的联系
		List<IHousePictureRelValue> housePictureRelValueList = housePictureRelSV.queryHousePictureRelByHouseId(houseId);
		if(housePictureRelValueList !=null  && housePictureRelValueList.size()>0){
			ArrayList pictureList = new ArrayList();
			int length = housePictureRelValueList.size();
			for(int i = 0;i<length;i++){
				long pictureId = housePictureRelValueList.get(i).getHousePictureId();
				IPictureValue pictureValue = pictureSV.queryPictureInfoByPictureId(pictureId);
				if(pictureValue != null){
					pictureList.add(pictureValue.getPicturePath());
				}

			}
			detailMap.put("pictures",pictureList);//把照片的信息放到map中

			//查询设备的信息
			List<IHouseFacilityRelValue> houseFacilityRelValueList = houseFacilityRelSV.queryHouseFacilityRelByHouseId(houseId);
			if(houseFacilityRelValueList !=null && houseFacilityRelValueList.size()>0){
				ArrayList facilityList = new ArrayList();
				length = houseFacilityRelValueList.size();
				for(int i=0;i<length;i++){
					String codeType =  houseFacilityRelValueList.get(i).getCodeType();
					String codeName = cache.getCodeNameByCodeType(codeType);
					if(codeName != null){
						facilityList.add(codeName);
					}else{
						facilityList.add("没有找到设备的定义");
					}

				}
				detailMap.put("facility",facilityList);
			}
		}
		return  detailMap;
	}

	/**
	 * 查询房东的房源信息
	 * @param landlordId
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public HashMap queryHouseInfoByLandlordIdForController(long landlordId,int begin,int end) throws Exception{
		HashMap rtnMap = new HashMap();
		List<IHouseValue> list =  queryHouseInfoByLandlordId(landlordId,begin,end);
		long count = queryHouseInfoCountByLandlordId(landlordId);
		if(list != null && list.size()>0){
			ArrayList rtnList= new ArrayList();
			int length = list.size();
			for(int i = 0;i<length;i++){
				IHouseValue houseValue =list.get(i);
				HashMap map =  new HashMap();
				map.put("houseId",houseValue.getHouseId());
				map.put("houseName",houseValue.getHouseName());
				long houseId = houseValue.getHouseId();
				//查询房子对应的图片
				IPictureValue pictureValue = pictureSV.queryMainPictureByHouseId(houseId);
				if(pictureValue !=null){
					map.put("mainPicture",pictureValue.getPicturePath());
				}
				//查询房子的预约信息有多少
				long appointmentCount = appointmentSV.queryAppointmentCountByHouseId(houseValue.getHouseId());
				map.put("appointmentCount",appointmentCount);
				rtnList.add(map);
			}
			rtnMap.put("houseView",rtnList);
			rtnMap.put("count",count);
		}

		return rtnMap;
	}

	/**
	 * 查询房子信息
	 * @param landlordId
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<IHouseValue> queryHouseInfoByLandlordId(long landlordId,int begin,int end) throws Exception{
		StringBuilder condition = new StringBuilder();
		HashMap params = new HashMap();
		if(landlordId>0){
			SQLCon.connectSQL(IHouseValue.S_LandlordId,landlordId,condition,params,false);
		}
		SQLCon.connectSQL(IHouseValue.S_DelFlag,1L,condition,params,false);
		return houseDAO.getHouseInfoByCondition(condition.toString(),params,begin,end);
	}

	/**
	 * 查询数量
	 * @param landlordId
	 * @return
	 * @throws Exception
	 */
	public long queryHouseInfoCountByLandlordId(long landlordId) throws Exception{
		StringBuilder condition = new StringBuilder();
		HashMap params = new HashMap();
		if(landlordId>0){
			SQLCon.connectSQL(IHouseValue.S_LandlordId,landlordId,condition,params,false);
		}
		SQLCon.connectSQL(IHouseValue.S_DelFlag,1L,condition,params,false);
		return houseDAO.getCount(condition.toString(),params);
	}

	/**
	 * 查询房子的信息
	 * @param userId
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public IHouseValue queryHouseByUserIdAndHouseId(long userId,long houseId)  throws Exception{
		StringBuilder condition = new StringBuilder();
		HashMap params = new HashMap();
		if(userId > 0){
			SQLCon.connectSQL(IHouseValue.S_LandlordId,userId,condition,params,false);
		}
		if( houseId > 0){
			SQLCon.connectSQL(IHouseValue.S_HouseId,houseId,condition,params,false);
		}
		List<IHouseValue> list = houseDAO.getHouseInfoByCondition(condition.toString(),params,-1,-1);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 删除房子的信息
	 * @param userId
	 * @param houseId
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delHouseInfoByUserIdAndHouseId(long userId,long houseId)  throws Exception{
		IHouseValue houseValue = queryHouseByUserIdAndHouseId(userId, houseId);
		if(houseValue == null){
			throw new Exception("用户没有对应的房源");
		}
		houseValue.setDelFlag(0L);
		houseDAO.save(houseValue);
	}
}
