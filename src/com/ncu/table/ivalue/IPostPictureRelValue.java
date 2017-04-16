package com.ncu.table.ivalue;

import java.util.Date;

public interface IPostPictureRelValue{
  public final static String S_PostPictureRelId = "POST_PICTURE_REL_ID";
  public final static String S_PostId = "POST_ID";
  public final static String S_PostPictureId = "POST_PICTURE_ID";
  public void setPostPictureRelId(Long value);
  public void setPostId(Long value);
  public void setPostPictureId(Long value);
  public Long getPostPictureRelId();
  public Long getPostId();
  public Long getPostPictureId();
}