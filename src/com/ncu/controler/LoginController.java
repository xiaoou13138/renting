package com.ncu.controler;


import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IUserSV;

@Controller
public class LoginController extends BaseController{
	@Resource(name="UserSVImpl")
	private IUserSV sv;
	
	public static Logger log = Logger.getLogger(LoginController.class);
	
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/login")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = new ViewData();
		data.put("SYSNAME", "测试"); //读取系统名称
		mv.setViewName("login");
		mv.addObject("data",data);
		return mv;
	}
    
    /**
     * 用户提交登录
     * @param pojo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login_login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
    public Object checkPassword() throws Exception{
    	
    	//判断用户的密码是否正确
    	String rtn = "";
    	ViewData data = this.getViewData();
    	JSONObject userInfo = (JSONObject)data.get("USERINFO");
    	String code = userInfo.getString("code");
    	String password = userInfo.getString("password");
    	if(StringUtils.isNotBlank(code)||StringUtils.isNotBlank(password)){
    		log.info("开始验证"+code+"用户的身份");
    		if(sv.checkUserInfo(code, password)){
    			//用户信息验证通过
    			rtn = "success";
    		}else{
    			rtn = "errorPassword";
    		}
    		
    	}else{
    		rtn = "emptyInfo";
    	}
    	JSONObject rtnObject = new JSONObject();
    	rtnObject.put("result", rtn);
    	return rtnObject;
    }
   /* public String printHello(@ModelAttribute( " pojo " ) LoginBean loginBean)throws Exception {
    	String userName = loginBean.getUserName();
    	String password = loginBean.getPassword();
    	if(StringUtils.isBlank(userName)){
    		UserBean  bean = sv.queryUserInfoByUserName(userName);
    		if(bean)
    	}else{
    		
    	}
	    return "login";
    }*/
}
