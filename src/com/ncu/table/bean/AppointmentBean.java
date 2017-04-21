package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IAppointmentValue;

@Entity
@Table(name ="appointment")
public class AppointmentBean implements IAppointmentValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IAppointmentValue.S_OrderId)
  private Long orderId;

  @Column(name = IAppointmentValue.S_HouseId)
  private Long houseId;

  @Column(name = IAppointmentValue.S_RenterId)
  private Long renterId;

  @Column(name = IAppointmentValue.S_GroupId)
  private Long groupId;

  @Column(name = IAppointmentValue.S_RenterType)
  private Long renterType;

  @Column(name = IAppointmentValue.S_OrderDate)
  private Date orderDate;

  @Column(name = IAppointmentValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = AppointmentBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setOrderId(Long value){
    this.orderId = value;
  }

  public Long getOrderId(){
    return orderId;
  }

  public void setHouseId(Long value){
    this.houseId = value;
  }

  public Long getHouseId(){
    return houseId;
  }

  public void setRenterId(Long value){
    this.renterId = value;
  }

  public Long getRenterId(){
    return renterId;
  }

  public void setGroupId(Long value){
    this.groupId = value;
  }

  public Long getGroupId(){
    return groupId;
  }

  public void setRenterType(Long value){
    this.renterType = value;
  }

  public Long getRenterType(){
    return renterType;
  }

  public void setOrderDate(Date value){
    this.orderDate = value;
  }

  public Date getOrderDate(){
    return orderDate;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}