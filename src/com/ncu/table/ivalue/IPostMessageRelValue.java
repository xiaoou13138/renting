package com.ncu.table.ivalue;

import java.util.Date;

public interface IPostMessageRelValue{
  public final static String S_PostMessageRelId = "POST_MESSAGE_REL_ID";
  public final static String S_PostId = "POST_ID";
  public final static String S_MessageId = "MESSAGE_ID";
  public void setPostMessageRelId(Long value);
  public void setPostId(Long value);
  public void setMessageId(Long value);
  public Long getPostMessageRelId();
  public Long getPostId();
  public Long getMessageId();
}