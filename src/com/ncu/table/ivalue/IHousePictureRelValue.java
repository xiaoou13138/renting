package com.ncu.table.ivalue;

import java.util.Date;

public interface IHousePictureRelValue{
  public final static String S_HousePictureRelId = "HOUSE_PICTURE_REL_ID";
  public final static String S_HouseId = "HOUSE_ID";
  public final static String S_HousePictureId = "HOUSE_PICTURE_ID";
  public final static String S_PictureType = "PICTURE_TYPE";
  public void setHousePictureRelId(Long value);
  public void setHouseId(Long value);
  public void setHousePictureId(Long value);
  public void setPictureType(String value);
  public Long getHousePictureRelId();
  public Long getHouseId();
  public Long getHousePictureId();
  public String getPictureType();
}