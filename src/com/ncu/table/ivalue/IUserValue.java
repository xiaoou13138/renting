package com.ncu.table.ivalue;

import java.util.Date;

public interface IUserValue{
	public final static String S_UserId =  "USER_ID";
	public final static String S_Code  = "CODE";
	public final static String S_Password = "PASSWORD";
	public final static String S_DelFlag = "DEL_FLAG";
	public final static String S_CreateDate = "CREATE_DATE";
	public final static String S_ModifyDate = "MODIFY_DATE";
	public final static String S_UserType = "USER_TYPE";
	public final static String S_Phone = "PHONE";
	public final static String S_Name = "NAME";
	public final static String S_Sex = "SEX";
	
	public void setUserId(int value);
	public void setCode(String value);
	public void setPassword(String value);
	public void setDelFlag(int value);
	public void setCreateDate(Date value);
	public void setModifyDate(Date value);
	public void setUserType(String value);
	public void setPhone(String value);
	public void setName(String value);
	public void setSex(String value);
	
	public int getUserId();
	public String getCode();
	public String getPassword();
	public int getDelFlag();
	public Date getCreateDate();
	public Date getModifyDate();
	public String getUserType();
	public String getPhone();
	public String getName();
	public String getSex();
}
