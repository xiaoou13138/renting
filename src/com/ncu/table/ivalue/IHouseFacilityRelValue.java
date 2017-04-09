package com.ncu.table.ivalue;

import java.util.Date;

public interface IHouseFacilityRelValue{
  public final static String S_HouseFacilityRelId = "HOUSE_FACILITY_REL_ID";
  public final static String S_HouseId = "HOUSE_ID";
  public final static String S_CodeType = "CODE_TYPE";
  public void setHouseFacilityRelId(Long value);
  public void setHouseId(Long value);
  public void setCodeType(String value);
  public Long getHouseFacilityRelId();
  public Long getHouseId();
  public String getCodeType();
}