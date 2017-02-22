package com.ncu.table.ivalue;

import java.util.Date;

public interface IHouseValue{
	public final static String S_HouseId = "HOUSE_ID";
	public final static String S_HouseName = "HOUSE_NAME";
	public final static String S_HouseDsc = "HOUSE_DSC";
	public final static String S_HouseMoney = "HOUSE_MONEY";
	public final static String S_DelFlag = "DEL_FLAG";
	public final static String S_CreateDate = "CREATE_DATE";
	public final static String S_ModifyDate = "MODIFY_DATE";
	public final static String S_OperId = "OPER_ID";
	
	public void setHouseId(long value);
	public void setHouseName(String value);
	public void setHouseDsc(String value);
	public void setHouseMoney(float value);
	public void setDelFlag(int value);
	public void setCreateDate(Date value);
	public void setModifyDate(Date value);
	public void setOperId(long value);
	
	public long getHouseId();
	public String getHouseName();
	public String getHouseDsc();
	public float getHouseMoney();
	public int getDelFlag();
	public Date getCreateDate();
	public Date getModifyDate();
	public long getOperId();

}
