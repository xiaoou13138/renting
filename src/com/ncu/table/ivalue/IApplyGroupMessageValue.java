package com.ncu.table.ivalue;

import java.util.Date;

public interface IApplyGroupMessageValue{
  public final static String S_ApplyId = "APPLY_ID";
  public final static String S_ApplyUserId = "APPLY_USER_ID";
  public final static String S_AcceptApplyUserId = "ACCEPT_APPLY_USER_ID";
  public final static String S_ApplyGroupId = "APPLY_GROUP_ID";
  public final static String S_State = "STATE";
  public final static String S_CreateDate = "CREATE_DATE";
  public void setApplyId(Long value);
  public void setApplyUserId(Long value);
  public void setAcceptApplyUserId(Long value);
  public void setApplyGroupId(Long value);
  public void setState(Long value);
  public void setCreateDate(Date value);
  public Long getApplyId();
  public Long getApplyUserId();
  public Long getAcceptApplyUserId();
  public Long getApplyGroupId();
  public Long getState();
  public Date getCreateDate();
}