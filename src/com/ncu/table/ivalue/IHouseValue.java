package com.ncu.table.ivalue;

import java.util.Date;

public interface IHouseValue{
  public final static String S_HouseId = "HOUSE_ID";
  public final static String S_LandlordId = "LANDLORD_ID";
  public final static String S_HouseName = "HOUSE_NAME";
  public final static String S_HouseType = "HOUSE_TYPE";
  public final static String S_RentType = "RENT_TYPE";
  public final static String S_HouseArea = "HOUSE_AREA";
  public final static String S_HouseAddress = "HOUSE_ADDRESS";
  public final static String S_Information = "INFORMATION";
  public final static String S_DepositType = "DEPOSIT_TYPE";
  public final static String S_Money = "MONEY";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_ModifyDate = "MODIFY_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setHouseId(Long value);
  public void setLandlordId(Long value);
  public void setHouseName(String value);
  public void setHouseType(String value);
  public void setRentType(String value);
  public void setHouseArea(Long value);
  public void setHouseAddress(String value);
  public void setInformation(String value);
  public void setDepositType(String value);
  public void setMoney(Long value);
  public void setCreateDate(Date value);
  public void setModifyDate(Date value);
  public void setDelFlag(Long value);
  public Long getHouseId();
  public Long getLandlordId();
  public String getHouseName();
  public String getHouseType();
  public String getRentType();
  public Long getHouseArea();
  public String getHouseAddress();
  public String getInformation();
  public String getDepositType();
  public Long getMoney();
  public Date getCreateDate();
  public Date getModifyDate();
  public Long getDelFlag();
}