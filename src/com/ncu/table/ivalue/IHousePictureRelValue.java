package com.ncu.table.ivalue;

import java.util.Date;

public interface IHousePictureRelValue{
	public final static String S_RelId = "REL_ID";
	public final static String S_RelType = "REL_TYPE";
	public final static String S_HouseId = "HOUSE_ID";
	public final static String S_RelPictureId = "REL_PICTURE_ID";
	public final static String S_DelFlag = "DEL_FLAG";
	public final static String S_CreateDate = "CREATE_DATE";
	public final static String S_ModifyDate = "MODIFY_DATE";
	public final static String S_OperId = "OPER_ID";

	public void setRelId(long value);
	public void setRelType(String value);
	public void setHouseId(long value);
	public void setRelPictureId(long value);
	public void setDelFlag(int value);
	public void setCreateDate(Date value);
	public void setModifyDate(Date value);
	public void setOperId(long value);	
	
	public long getRelId();
	public String getRelType();
	public long getHouseId();
	public long getRelPictureId();
	public int getDelFlag();
	public Date getCreateDate();
	public Date getModifyDate();
	public long getOperId();
}
