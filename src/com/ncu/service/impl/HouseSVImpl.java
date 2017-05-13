package com.ncu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ncu.cache.StaticDataCache;
import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.service.interfaces.*;
import com.ncu.table.bean.HouseBean;
import com.ncu.table.bean.ParamsDefine;
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
	@Resource(name="CommonDAOImpl")
	private ICommonDAO commonDAO;



	/**
	 * 查询页面展示的信息
	 * @param searchContent
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public HashMap queryHouseInfoByCondition(String searchContent, int begin, int end) throws  Exception{
		HashMap rtnMap = new HashMap();
		List<IHouseValue> houseList = queryHouseInfoBySearchContent(searchContent,begin,end);
		long count = queryHouseCountBySearchContent(searchContent);
		rtnMap.put("count",count);
		if(houseList != null){
			ArrayList rtnList = new ArrayList();
			int length = houseList.size();
			for(int i = 0;i<length;i++){
				HashMap map = new HashMap();
				map.put("houseId",houseList.get(i).getHouseId());//房源ID
				map.put("landlord_id",houseList.get(i).getLandlordId());//房东ID
				map.put("houseName",houseList.get(i).getHouseName());//
				map.put("houseType",houseList.get(i).getHouseType());
				map.put("houseArea",houseList.get(i).getHouseArea());
				map.put("houseAddress",houseList.get(i).getProvince()+houseList.get(i).getCity()+houseList.get(i).getDetailAddress());
				if(houseList.get(i).getInformation() !=null){
					map.put("information",houseList.get(i).getInformation());
				}
				map.put("money",houseList.get(i).getMoney());

				//查询房屋带有的设备
				long houseId = houseList.get(i).getHouseId();
				List facilityList = houseFacilityRelSV.queryHouseFacilityRelListByHouseId(houseId);
				if(facilityList != null && facilityList.size()>0){
					map.put("facility",facilityList);
				}
				//查询房子对应的图片
				IPictureValue pictureValue = pictureSV.queryMainPictureByHouseId(houseId);
				if(pictureValue !=null){
					map.put("mainPicture",pictureValue.getPicturePath());
				}
				rtnList.add(map);
			}
			rtnMap.put("houseView",rtnList);
		}

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
					long appointmentId= 0;
					if(queryType ==1 ){
						IHouseCollectValue houseCollectValue = (IHouseCollectValue)list.get(i);
						houseId =houseCollectValue.getHouseId();
					}else{
						IAppointmentValue appointmentValue = (IAppointmentValue)list.get(i);
						houseId = appointmentValue.getHouseId();
						appointmentType = appointmentValue.getRenterType();
						appointmentId = appointmentValue.getOrderId();
					}
					IHouseValue houseValue = queryHouseDefInfoByHouseId(houseId);
					if(houseValue != null){
						HashMap map = new HashMap();
						map.put("houseId",houseValue.getHouseId());//房源ID
						map.put("landlord_id",houseValue.getLandlordId());//房东ID
						map.put("houseName",houseValue.getHouseName());//
						map.put("houseType",houseValue.getHouseType());
						map.put("houseArea",houseValue.getHouseArea());
						map.put("houseAddress",houseValue.getProvince()+houseValue.getCity()+houseValue.getDetailAddress());
						if(houseValue.getInformation() != null){
							map.put("information",houseValue.getInformation());
						}
						map.put("money",houseValue.getMoney());
						if(appointmentType != 0){
							map.put("appointmentType",appointmentType);
						}
						map.put("appointmentId",appointmentId);


						//查询房屋带有的设备
						List facilityList = houseFacilityRelSV.queryHouseFacilityRelListByHouseId(houseId);
						if(facilityList != null && facilityList.size()>0){
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
	public void saveUpLoadHouseInfo(JSONObject houseInfoObject,long userId,ArrayList mainPictureList,ArrayList normalPictureList,int actionType) throws Exception{
		IHouseValue houseValue = null;
		if(actionType == 1){
			houseValue = new HouseBean();
			houseValue.setDelFlag(1L);
			houseValue.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
			houseValue.setLandlordId(userId);
			userSV.changeUserType(userId,"land lord");
		}else if(actionType ==2 ){
			long houseId = houseInfoObject.getLong("houseId");
			houseValue = queryHouseDefInfoByHouseId(houseId);

			//查询房子和设备的关系
			List<IHouseFacilityRelValue> list = houseFacilityRelSV.queryHouseFacilityRelByHouseId(houseId);
			if(list != null && list.size()>0){
				int length = list.size();
				for(int i= 0;i<length;i++){
					IHouseFacilityRelValue houseFacilityRelValue = list.get(i);
					houseFacilityRelSV.deleteHouseFacilityRel(houseFacilityRelValue);
				}
			}


		}
		houseValue.setRentType(houseInfoObject.getString("rentingType"));
		houseValue.setHouseArea(Long.parseLong(houseInfoObject.getString("houseArea")));
		houseValue.setHouseName(houseInfoObject.getString("houseName"));
		houseValue.setHouseType(houseInfoObject.getString("houseType"));
		if(houseInfoObject.containsKey("information")){
			houseValue.setInformation(houseInfoObject.getString("information"));
		}
		houseValue.setMoney(Long.parseLong(houseInfoObject.getString("money")));
		houseValue.setProvince(houseInfoObject.getString("province"));
		houseValue.setCity(houseInfoObject.getString("city"));
		houseValue.setDetailAddress(houseInfoObject.getString("detailAddress"));
		houseValue.setRoom(houseInfoObject.getLong("room"));
		houseValue.setHall(houseInfoObject.getLong("hall"));
		houseValue.setToilet(houseInfoObject.getLong("toilet"));
		houseValue.setDepositType(houseInfoObject.getString("depositType"));

		houseDAO.save(houseValue);
		long houseId =  houseValue.getHouseId();
		//保存图片和房源的关系
		if(mainPictureList !=null && mainPictureList.size()>0){
			int length  = mainPictureList.size();
			for(int i = 0;i<length;i++){
				long pictureId = (long)mainPictureList.get(i);
				housePictureRelSV.savePictureRelByUserIdAndTypeAndPictureId(houseId,pictureId,"MAIN");
			}
		}
		if(normalPictureList !=null && normalPictureList.size()>0){
			int length  = normalPictureList.size();
			for(int i = 0;i<length;i++){
				long pictureId = (long)normalPictureList.get(i);
				housePictureRelSV.savePictureRelByUserIdAndTypeAndPictureId(houseId,pictureId,"NORMAL");
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
			detailMap.put("rentType",houseValue.getRentType());
			detailMap.put("houseAddress",houseValue.getProvince()+houseValue.getCity()+houseValue.getDetailAddress());
			if(houseValue.getInformation() != null){
				detailMap.put("information",houseValue.getInformation());
			}

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

	/**
	 * 查询房子的所有信息（修改页面需要）
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public HashMap queryAllHouseInfoByHouseId(long houseId) throws Exception{
		HashMap rtnMap = new HashMap();
		IHouseValue houseValue = queryHouseDefInfoByHouseId(houseId);
		if(houseValue != null){
			rtnMap.put("houseId",houseValue.getHouseId());//房源ID
			rtnMap.put("landlord_id",houseValue.getLandlordId());//房东ID
			rtnMap.put("houseName",houseValue.getHouseName());//
			rtnMap.put("houseType",houseValue.getHouseType());
			rtnMap.put("houseArea",houseValue.getHouseArea());
			rtnMap.put("province",houseValue.getProvince());
			rtnMap.put("city",houseValue.getCity());
			rtnMap.put("detailAddress",houseValue.getDetailAddress());
			rtnMap.put("room",houseValue.getRoom());
			rtnMap.put("hall",houseValue.getHall());
			rtnMap.put("toilet",houseValue.getToilet());
			if(houseValue.getInformation() != null){
				rtnMap.put("information",houseValue.getInformation());
			}
			rtnMap.put("money",houseValue.getMoney());
			rtnMap.put("depositType",houseValue.getDepositType());
			//查询房屋带有的设备
			JSONArray facilityList = houseFacilityRelSV.queryHouseFacilityRelListByHouseId(houseId);
			if(facilityList != null && facilityList.size()>0){
				rtnMap.put("facility",facilityList);
			}

		}
		return rtnMap;
	}

	/**
	 * 根据房子主键删除房子信息
	 * @param houseId
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteHouseByHouseId(long houseId)throws Exception{
		IHouseValue houseValue = queryHouseDefInfoByHouseId(houseId);
		if(houseValue == null){
			throw new Exception("房子不存在");
		}
		houseValue.setDelFlag(0L);
		houseDAO.save(houseValue);
	}


	public List<IHouseValue> queryHouseInfoBySearchContent(String searchContent,int begin,int end)throws Exception{
		String sql ="";
		ArrayList<ParamsDefine> paramsDefineArrayList = new ArrayList<>();
		if(StringUtils.isNotBlank(searchContent)){
			sql  = "from HouseBean a where a.houseName like:searchContent or a.province like:searchContent"
					+" or a.city like :searchContent or a.detailAddress like :searchContent"
					+" or a.houseType like :searchContent";
			paramsDefineArrayList = new ArrayList<>();
			ParamsDefine paramsDefine = new ParamsDefine();
			paramsDefine.setColName("searchContent");
			paramsDefine.setIsList(false);
			paramsDefine.setParamVal('%'+searchContent+'%');
			paramsDefineArrayList.add(paramsDefine);
		}else{
			sql =" from HouseBean";
		}
		return commonDAO.commonQuery(sql,paramsDefineArrayList.toArray(new ParamsDefine[0]),begin,end);

	}
	public long queryHouseCountBySearchContent(String searchContent)throws Exception{
		String sql ="";
		ArrayList<ParamsDefine> paramsDefineArrayList = new ArrayList<>();
		if(StringUtils.isNotBlank(searchContent)){
			sql  = "from HouseBean a where a.houseName like:searchContent or a.province like:searchContent"
					+" or a.city like :searchContent or a.detailAddress like :searchContent"
					+" or a.houseType like :searchContent";
			paramsDefineArrayList = new ArrayList<>();
			ParamsDefine paramsDefine = new ParamsDefine();
			paramsDefine.setColName("searchContent");
			paramsDefine.setIsList(false);
			paramsDefine.setParamVal('%'+searchContent+'%');
			paramsDefineArrayList.add(paramsDefine);
		}else{
			sql =" from HouseBean";
		}
		return commonDAO.getCount(sql,paramsDefineArrayList.toArray(new ParamsDefine[0]));
	}

}
