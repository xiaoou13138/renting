package com.ncu.table.ivalue;

import java.util.Date;

public interface IUserTypeDefineValue{
	public final static String S_UserType = "USER_TYPE";
	public final static String S_TypeDsc = "TYPE_DSC";
	public final static String S_DelFlag = "DEL_FLAG";
	public final static String S_CreateDate = "CREATE_DATE";
	public final static String S_ModifyDate = "MODIFY_DATE";
	
	
	public void setUserType(String value);
	public void setTypeDsc(String value);
	public void setDelFlag(int value);
	public void setCreateDate(Date value);
	public void setModifyDate(Date value);
	
	public String getUserType();
	public String getTypeDsc();
	public int getDelFlag();
	public Date getCreateDate();
	public Date getModifyDate();

}
