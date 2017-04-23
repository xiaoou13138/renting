package com.ncu.table.ivalue;

import java.util.Date;

public interface IMessageNoticeQueueValue{
  public final static String S_UserId = "USER_ID";
  public final static String S_MessageNum = "MESSAGE_NUM";
  public void setUserId(Long value);
  public void setMessageNum(Long value);
  public Long getUserId();
  public Long getMessageNum();
}