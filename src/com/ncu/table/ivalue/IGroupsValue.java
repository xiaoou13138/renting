package com.ncu.table.ivalue;

import java.util.Date;

public interface IGroupsValue{
  public final static String S_GroupId = "GROUP_ID";
  public final static String S_GroupName = "GROUP_NAME";
  public final static String S_GroupNumber = "GROUP_NUMBER";
  public final static String S_GroupAddress = "GROUP_ADDRESS";
  public final static String S_CurrentNumber = "CURRENT_NUMBER";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_GroupInfor = "GROUP_INFOR";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setGroupId(Long value);
  public void setGroupName(String value);
  public void setGroupNumber(Long value);
  public void setGroupAddress(String value);
  public void setCurrentNumber(Long value);
  public void setCreateDate(Date value);
  public void setGroupInfor(String value);
  public void setDelFlag(Long value);
  public Long getGroupId();
  public String getGroupName();
  public Long getGroupNumber();
  public String getGroupAddress();
  public Long getCurrentNumber();
  public Date getCreateDate();
  public String getGroupInfor();
  public Long getDelFlag();
}