package com.ncu.controler;


import javax.annotation.Resource;

import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IUserSV;

import java.util.HashMap;

@Controller
@Scope("prototype")
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
		ViewData data = this.getViewData();
		mv.setViewName("login");
		mv.addObject("data",data);
		return mv;
	}
    
    /**
     * 用户提交登录
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login_login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
    public Object checkPassword(){
    	JSONObject rtnObject = this.getRtnJSONObject();
    	ViewData data = this.getViewData();
		String rtn = "N";
    	try{
			JSONObject viewObject = data.getJSONObject("DATA");
			String code = APPUtil.getSafeStringFromJSONObject(viewObject,"code");
			String password = APPUtil.getSafeStringFromJSONObject(viewObject,"password");
			HashMap map = sv.checkUserInfo(code,password);
			this.getSession().setAttribute("userId",map.get("userId"));
			this.getSession().setAttribute("userName",map.get("userName"));
			this.getSession().setAttribute("userType",map.get("userType"));
			if((boolean)map.get("result")){
				rtn = "Y";
			}
			rtnObject.put("result", rtn);

		}catch (Exception e){
    		e.printStackTrace();
		}
		return rtnObject;
    }

	@RequestMapping(value="/login_clearSession" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object clearSession(){
		JSONObject rtnObject = this.getRtnJSONObject();
		String rtn = "Y";
		try{
			this.getSession().removeAttribute("userId");
			this.getSession().removeAttribute("userName");
			this.getSession().removeAttribute("userType");
		}catch (Exception e){
			rtn = "N";
			e.printStackTrace();
		}
		rtnObject.put("result", rtn);
		return rtnObject;
	}
}
