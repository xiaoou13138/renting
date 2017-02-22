package com.ncu.cache;

import javax.annotation.PostConstruct;

import org.osgi.service.component.annotations.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ncu.service.impl.ConfigSVImpl;

/**
 * 邮箱参数加载到内存当中
 * @author oulc
 *
 */
@Service
@Scope("singleton")
@DependsOn("BeanUtil")
public class MailSetting {
	private  String mailUserName = null;//邮箱用户名
	private  String mailPassword = null;//邮箱密码
	private  String mailHost =null;//邮箱地址
	private String mailSmtpAuth =null;//
	private String mailTransportProtocol =null;//协议
	
	@Autowired
	private ConfigSVImpl sv;
	
	@PostConstruct
	public void init() throws Exception{
		mailUserName=sv.queryConfigByCodeType("mailUserName").get(0).getCodeValue();
		mailPassword=sv.queryConfigByCodeType("mailPassword").get(0).getCodeValue();
		mailHost=sv.queryConfigByCodeType("mailHost").get(0).getCodeValue();
		mailSmtpAuth=sv.queryConfigByCodeType("mailSmtpAuth").get(0).getCodeValue();
		mailTransportProtocol=sv.queryConfigByCodeType("mailTransportProtocol").get(0).getCodeValue();
	}
	
	
	public String getUserName(){
		return mailUserName;
	}
	public String getPassword(){
		return mailPassword;
	}
	public String getMailHost(){
		return mailHost;
	}
	public String getMailSmtpAuth(){
		return mailSmtpAuth;
	}
	public String getMailTransportProtocol(){
		return mailTransportProtocol;
	}
	
	/*public void setUserName(String value){
		this.mailUserName = value;
	}
	public void setPassword(String value){
		this.mailPassword = value;
	}
	public void setMailHost(String value){
		this.mailHost = value;
	}
	public void setMailTransportProtocol(String value){
		this.mailTransportProtocol = value;
	}
	public void setMailSmtpAuth(String value){
		this.mailSmtpAuth =value;
	}*/
	
	/*public static synchronized MailSetting getInstance(){
	if(instance==null){
		instance = new MailSetting();
	}
	return instance;
}*/
	
	/*private void init(){
	properties = new Properties();
	InputStream inputStream = null;
	try {
		File file = new File(filePath);
		inputStream = new BufferedInputStream(new FileInputStream(file));
		properties.load(inputStream);
		Set<String> keys = properties.stringPropertyNames();
		if(keys.size()==0){
			throw new Exception("资源文件:" + file.getAbsolutePath()
				+ "的内容为空");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try { 
			if (inputStream != null) {
				inputStream.close();
			}                                              
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}*/

}
