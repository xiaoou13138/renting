package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IPostValue;

@Entity
@Table(name ="post")
public class PostBean implements IPostValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IPostValue.S_PostId)
  private Long postId;

  @Column(name = IPostValue.S_PostTitle)
  private String postTitle;

  @Column(name = IPostValue.S_HostId)
  private Long hostId;

  @Column(name = IPostValue.S_IsNoHouse)
  private Long isNoHouse;

  @Column(name = IPostValue.S_SexLimit)
  private String sexLimit;

  @Column(name = IPostValue.S_Content)
  private String content;

  @Column(name = IPostValue.S_CrateDate)
  private Date crateDate;

  @Column(name = IPostValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = PostBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setPostId(Long value){
    this.postId = value;
  }

  public Long getPostId(){
    return postId;
  }

  public void setPostTitle(String value){
    this.postTitle = value;
  }

  public String getPostTitle(){
    return postTitle;
  }

  public void setHostId(Long value){
    this.hostId = value;
  }

  public Long getHostId(){
    return hostId;
  }

  public void setIsNoHouse(Long value){
    this.isNoHouse = value;
  }

  public Long getIsNoHouse(){
    return isNoHouse;
  }

  public void setSexLimit(String value){
    this.sexLimit = value;
  }

  public String getSexLimit(){
    return sexLimit;
  }

  public void setContent(String value){
    this.content = value;
  }

  public String getContent(){
    return content;
  }

  public void setCrateDate(Date value){
    this.crateDate = value;
  }

  public Date getCrateDate(){
    return crateDate;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}