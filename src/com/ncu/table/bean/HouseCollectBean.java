package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IHouseCollectValue;

@Entity
@Table(name ="house_collect")
public class HouseCollectBean implements IHouseCollectValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IHouseCollectValue.S_HouseCollectId)
  private Long houseCollectId;

  @Column(name = IHouseCollectValue.S_CollectorId)
  private Long collectorId;

  @Column(name = IHouseCollectValue.S_HouseId)
  private Long houseId;

  @Column(name = IHouseCollectValue.S_CollectDate)
  private Date collectDate;

  @Transient
  public static Class beanClass = HouseCollectBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setHouseCollectId(Long value){
    this.houseCollectId = value;
  }

  public Long getHouseCollectId(){
    return houseCollectId;
  }

  public void setCollectorId(Long value){
    this.collectorId = value;
  }

  public Long getCollectorId(){
    return collectorId;
  }

  public void setHouseId(Long value){
    this.houseId = value;
  }

  public Long getHouseId(){
    return houseId;
  }

  public void setCollectDate(Date value){
    this.collectDate = value;
  }

  public Date getCollectDate(){
    return collectDate;
  }

}