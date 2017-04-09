package com.ncu.table.ivalue;

import java.util.Date;

public interface IPictureValue{
  public final static String S_PictureId = "PICTURE_ID";
  public final static String S_PicturePath = "PICTURE_PATH";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setPictureId(Long value);
  public void setPicturePath(String value);
  public void setCreateDate(Date value);
  public void setDelFlag(Long value);
  public Long getPictureId();
  public String getPicturePath();
  public Date getCreateDate();
  public Long getDelFlag();
}