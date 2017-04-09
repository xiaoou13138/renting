package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IGroupsValue;

@Entity
@Table(name ="groups")
public class GroupsBean implements IGroupsValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IGroupsValue.S_GroupId)
  private Long groupId;

  @Column(name = IGroupsValue.S_GroupName)
  private String groupName;

  @Column(name = IGroupsValue.S_GroupNumber)
  private Long groupNumber;

  @Column(name = IGroupsValue.S_GroupAddress)
  private String groupAddress;

  @Column(name = IGroupsValue.S_CurrentNumber)
  private Long currentNumber;

  @Column(name = IGroupsValue.S_CreateDate)
  private Date createDate;

  @Column(name = IGroupsValue.S_GroupInfor)
  private String groupInfor;

  @Column(name = IGroupsValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = GroupsBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setGroupId(Long value){
    this.groupId = value;
  }

  public Long getGroupId(){
    return groupId;
  }

  public void setGroupName(String value){
    this.groupName = value;
  }

  public String getGroupName(){
    return groupName;
  }

  public void setGroupNumber(Long value){
    this.groupNumber = value;
  }

  public Long getGroupNumber(){
    return groupNumber;
  }

  public void setGroupAddress(String value){
    this.groupAddress = value;
  }

  public String getGroupAddress(){
    return groupAddress;
  }

  public void setCurrentNumber(Long value){
    this.currentNumber = value;
  }

  public Long getCurrentNumber(){
    return currentNumber;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

  public void setGroupInfor(String value){
    this.groupInfor = value;
  }

  public String getGroupInfor(){
    return groupInfor;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}