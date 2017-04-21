package com.ncu.table.ivalue;

import java.util.Date;

public interface IAppointmentValue{
  public final static String S_OrderId = "ORDER_ID";
  public final static String S_HouseId = "HOUSE_ID";
  public final static String S_RenterId = "RENTER_ID";
  public final static String S_GroupId = "GROUP_ID";
  public final static String S_RenterType = "RENTER_TYPE";
  public final static String S_OrderDate = "ORDER_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setOrderId(Long value);
  public void setHouseId(Long value);
  public void setRenterId(Long value);
  public void setGroupId(Long value);
  public void setRenterType(Long value);
  public void setOrderDate(Date value);
  public void setDelFlag(Long value);
  public Long getOrderId();
  public Long getHouseId();
  public Long getRenterId();
  public Long getGroupId();
  public Long getRenterType();
  public Date getOrderDate();
  public Long getDelFlag();
}