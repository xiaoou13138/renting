package com.ncu.table.ivalue;

import java.util.Date;

public interface IHouseCollectValue{
  public final static String S_HouseCollectId = "HOUSE_COLLECT_ID";
  public final static String S_CollectorId = "COLLECTOR_ID";
  public final static String S_HouseId = "HOUSE_ID";
  public final static String S_CollectDate = "COLLECT_DATE";
  public void setHouseCollectId(Long value);
  public void setCollectorId(Long value);
  public void setHouseId(Long value);
  public void setCollectDate(Date value);
  public Long getHouseCollectId();
  public Long getCollectorId();
  public Long getHouseId();
  public Date getCollectDate();
}