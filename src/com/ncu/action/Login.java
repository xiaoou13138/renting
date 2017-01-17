package com.ncu.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ncu.bean.LoginBean;

@Controller
@RequestMapping("/login")
public class Login {
	
	//用户请求网页
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model)throws Exception {
	    model.addAttribute("message", "Hello Spring MVC Framework!");
	    return "login";
    }
    
    /**
     * 用户提交登录
     * @param pojo
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String printHello(@ModelAttribute( " pojo " ) LoginBean loginBean)throws Exception {
    	String userName = loginBean.getUserName();
    	String password = loginBean.getPassword();
    	if(Stringu)
	    return "login";
    }
}
