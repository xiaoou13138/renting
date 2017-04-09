package com.ncu.table.ivalue;

import java.util.Date;

public interface IGroupsRenterRelValue{
  public final static String S_GroupRenterRelId = "GROUP_RENTER_REL_ID";
  public final static String S_GroupId = "GROUP_ID";
  public final static String S_RenterId = "RENTER_ID";
  public final static String S_Role = "ROLE";
  public final static String S_CreateDate = "CREATE_DATE";
  public void setGroupRenterRelId(Long value);
  public void setGroupId(Long value);
  public void setRenterId(Long value);
  public void setRole(Long value);
  public void setCreateDate(Date value);
  public Long getGroupRenterRelId();
  public Long getGroupId();
  public Long getRenterId();
  public Long getRole();
  public Date getCreateDate();
}