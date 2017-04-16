package com.ncu.table.ivalue;

import java.util.Date;

public interface IPostValue{
  public final static String S_PostId = "POST_ID";
  public final static String S_PostTitle = "POST_TITLE";
  public final static String S_HostId = "HOST_ID";
  public final static String S_IsNoHouse = "IS_NO_HOUSE";
  public final static String S_SexLimit = "SEX_LIMIT";
  public final static String S_Content = "CONTENT";
  public final static String S_CrateDate = "CRATE_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setPostId(Long value);
  public void setPostTitle(String value);
  public void setHostId(Long value);
  public void setIsNoHouse(Long value);
  public void setSexLimit(String value);
  public void setContent(String value);
  public void setCrateDate(Date value);
  public void setDelFlag(Long value);
  public Long getPostId();
  public String getPostTitle();
  public Long getHostId();
  public Long getIsNoHouse();
  public String getSexLimit();
  public String getContent();
  public Date getCrateDate();
  public Long getDelFlag();
}