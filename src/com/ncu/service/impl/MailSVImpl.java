package com.ncu.service.impl;


import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ncu.cache.MailSetting;
import com.ncu.service.interfaces.IMailSV;


@Service
public class MailSVImpl implements IMailSV{
	@Autowired
	private MailSetting mailSetting;
	
	private static Session session;
	private static String userName;
	private static String password;
	
	private static Logger log = Logger.getLogger(MailSetting.class);
	
	/**
	 * 邮件配置的初始化
	 */
	@PostConstruct
	public void init(){
		log.info("————————————邮件开始初始化");
		userName = mailSetting.getUserName();
		password = mailSetting.getPassword();
		Properties props = new Properties();                // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
		props.setProperty("mail.transport.protocol", mailSetting.getMailTransportProtocol());   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.host", mailSetting.getMailHost());        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", mailSetting.getMailSmtpAuth());            // 请求认证，参数名称与具体实现有关
        session= Session.getDefaultInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
	}
	
	/**
	 * 根据message发送邮件
	 * @param message
	 * @throws Exception
	 */
	public void send(Message message) throws Exception{
		// 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
        //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
        transport.connect(userName, password);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
        log.info("————————————邮件发送成功");
	}
	
	/**
	 * 创建Message对象  里面包含了所有的发送信息
	 * @param mailAddress 发送的地址
	 * @return
	 * @throws Exception
	 */
	public Message crateMessage(String mailAddress) throws Exception{
		
		Message message = new MimeMessage(session);     // 创建邮件对象
        
        // 2. From: 发件人
        //    其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        //    真正要发送时, 邮箱必须是真实有效的邮箱。
        message.setFrom(new InternetAddress(userName, "租房系统", "UTF-8"));
        
        // 3. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailAddress, "租房系统", "UTF-8"));
        
        // 4. Subject: 邮件主题
        message.setSubject("TEST邮件主题oulc");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("oulc测试邮件", "text/html;charset=UTF-8");

        // 6. 设置显示的发件时间
        message.setSentDate(new Date());

        // 7. 保存前面的设置
        message.saveChanges();
        log.info("————————————邮件创建成功");
        return message;
	}
}
