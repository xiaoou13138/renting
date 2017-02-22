package com.ncu.table.ivalue;

import java.util.Date;

public interface IPictureValue{
	public final static String S_PictureId = "PICTURE_ID";
	public final static String S_PicturePath = "PICTURE_PATH";
	public final static String S_DelFlag = "DEL_FLAG";
	public final static String S_CreateDate = "CREATE_DATE";
	public final static String S_ModifyDate = "MODIFY_DATE";
	public final static String S_OperId = "OPER_ID";
	
	public void setPictureId(long value);
	public void setPicturePath(String value);
	public void setDelFlag(int value);
	public void setCreateDate(Date value);
	public void setModifyDate(Date value);
	public void setOperId(long value);
	
	public long getPictureId();
	public String getPicturePath();
	public int getDelFlag();
	public Date getCreateDate();
	public Date getModifyDate();
	public long getOperId();

}
