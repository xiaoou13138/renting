package com.ncu.table.ivalue;

import java.util.Date;

public interface IMessageValue{
  public final static String S_MessageId = "MESSAGE_ID";
  public final static String S_PostId = "POST_ID";
  public final static String S_SenderId = "SENDER_ID";
  public final static String S_ReceiverId = "RECEIVER_ID";
  public final static String S_Content = "CONTENT";
  public final static String S_IsPrivate = "IS_PRIVATE";
  public final static String S_IsChecked = "IS_CHECKED";
  public final static String S_SenderDate = "SENDER_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setMessageId(Long value);
  public void setPostId(Long value);
  public void setSenderId(Long value);
  public void setReceiverId(Long value);
  public void setContent(String value);
  public void setIsPrivate(Long value);
  public void setIsChecked(Long value);
  public void setSenderDate(Date value);
  public void setDelFlag(Long value);
  public Long getMessageId();
  public Long getPostId();
  public Long getSenderId();
  public Long getReceiverId();
  public String getContent();
  public Long getIsPrivate();
  public Long getIsChecked();
  public Date getSenderDate();
  public Long getDelFlag();
}