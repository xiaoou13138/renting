package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IApplyGroupMessageValue;

@Entity
@Table(name ="apply_group_message")
public class ApplyGroupMessageBean implements IApplyGroupMessageValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IApplyGroupMessageValue.S_ApplyId)
  private Long applyId;

  @Column(name = IApplyGroupMessageValue.S_ApplyUserId)
  private Long applyUserId;

  @Column(name = IApplyGroupMessageValue.S_AcceptApplyUserId)
  private Long acceptApplyUserId;

  @Column(name = IApplyGroupMessageValue.S_ApplyGroupId)
  private Long applyGroupId;

  @Column(name = IApplyGroupMessageValue.S_State)
  private Long state;

  @Column(name = IApplyGroupMessageValue.S_CreateDate)
  private Date createDate;

  @Transient
  public static Class beanClass = ApplyGroupMessageBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setApplyId(Long value){
    this.applyId = value;
  }

  public Long getApplyId(){
    return applyId;
  }

  public void setApplyUserId(Long value){
    this.applyUserId = value;
  }

  public Long getApplyUserId(){
    return applyUserId;
  }

  public void setAcceptApplyUserId(Long value){
    this.acceptApplyUserId = value;
  }

  public Long getAcceptApplyUserId(){
    return acceptApplyUserId;
  }

  public void setApplyGroupId(Long value){
    this.applyGroupId = value;
  }

  public Long getApplyGroupId(){
    return applyGroupId;
  }

  public void setState(Long value){
    this.state = value;
  }

  public Long getState(){
    return state;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

}