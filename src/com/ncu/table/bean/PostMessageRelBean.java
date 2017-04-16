package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IPostMessageRelValue;

@Entity
@Table(name ="post_message_rel")
public class PostMessageRelBean implements IPostMessageRelValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IPostMessageRelValue.S_PostMessageRelId)
  private Long postMessageRelId;

  @Column(name = IPostMessageRelValue.S_PostId)
  private Long postId;

  @Column(name = IPostMessageRelValue.S_MessageId)
  private Long messageId;

  @Transient
  public static Class beanClass = PostMessageRelBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setPostMessageRelId(Long value){
    this.postMessageRelId = value;
  }

  public Long getPostMessageRelId(){
    return postMessageRelId;
  }

  public void setPostId(Long value){
    this.postId = value;
  }

  public Long getPostId(){
    return postId;
  }

  public void setMessageId(Long value){
    this.messageId = value;
  }

  public Long getMessageId(){
    return messageId;
  }

}