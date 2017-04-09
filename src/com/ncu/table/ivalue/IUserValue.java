package com.ncu.table.ivalue;

import java.util.Date;

public interface IUserValue{
  public final static String S_UserId = "USER_ID";
  public final static String S_UserAccountCode = "USER_ACCOUNT_CODE";
  public final static String S_UserName = "USER_NAME";
  public final static String S_RealName = "REAL_NAME";
  public final static String S_Password = "PASSWORD";
  public final static String S_UserPhone = "USER_PHONE";
  public final static String S_UserAge = "USER_AGE";
  public final static String S_UserSex = "USER_SEX";
  public final static String S_UserType = "USER_TYPE";
  public final static String S_ModifyDate = "MODIFY_DATE";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setUserId(Long value);
  public void setUserAccountCode(String value);
  public void setUserName(String value);
  public void setRealName(String value);
  public void setPassword(String value);
  public void setUserPhone(Long value);
  public void setUserAge(Long value);
  public void setUserSex(String value);
  public void setUserType(String value);
  public void setModifyDate(Date value);
  public void setCreateDate(Date value);
  public void setDelFlag(Long value);
  public Long getUserId();
  public String getUserAccountCode();
  public String getUserName();
  public String getRealName();
  public String getPassword();
  public Long getUserPhone();
  public Long getUserAge();
  public String getUserSex();
  public String getUserType();
  public Date getModifyDate();
  public Date getCreateDate();
  public Long getDelFlag();
}