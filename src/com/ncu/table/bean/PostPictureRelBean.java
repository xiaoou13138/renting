package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IPostPictureRelValue;

@Entity
@Table(name ="post_picture_rel")
public class PostPictureRelBean implements IPostPictureRelValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IPostPictureRelValue.S_PostPictureRelId)
  private Long postPictureRelId;

  @Column(name = IPostPictureRelValue.S_PostId)
  private Long postId;

  @Column(name = IPostPictureRelValue.S_PostPictureId)
  private Long postPictureId;

  @Transient
  public static Class beanClass = PostPictureRelBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setPostPictureRelId(Long value){
    this.postPictureRelId = value;
  }

  public Long getPostPictureRelId(){
    return postPictureRelId;
  }

  public void setPostId(Long value){
    this.postId = value;
  }

  public Long getPostId(){
    return postId;
  }

  public void setPostPictureId(Long value){
    this.postPictureId = value;
  }

  public Long getPostPictureId(){
    return postPictureId;
  }

}