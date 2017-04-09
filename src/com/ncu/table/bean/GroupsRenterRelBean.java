package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IGroupsRenterRelValue;

@Entity
@Table(name ="groups_renter_rel")
public class GroupsRenterRelBean implements IGroupsRenterRelValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IGroupsRenterRelValue.S_GroupRenterRelId)
  private Long groupRenterRelId;

  @Column(name = IGroupsRenterRelValue.S_GroupId)
  private Long groupId;

  @Column(name = IGroupsRenterRelValue.S_RenterId)
  private Long renterId;

  @Column(name = IGroupsRenterRelValue.S_Role)
  private Long role;

  @Column(name = IGroupsRenterRelValue.S_CreateDate)
  private Date createDate;

  @Transient
  public static Class beanClass = GroupsRenterRelBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setGroupRenterRelId(Long value){
    this.groupRenterRelId = value;
  }

  public Long getGroupRenterRelId(){
    return groupRenterRelId;
  }

  public void setGroupId(Long value){
    this.groupId = value;
  }

  public Long getGroupId(){
    return groupId;
  }

  public void setRenterId(Long value){
    this.renterId = value;
  }

  public Long getRenterId(){
    return renterId;
  }

  public void setRole(Long value){
    this.role = value;
  }

  public Long getRole(){
    return role;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

}