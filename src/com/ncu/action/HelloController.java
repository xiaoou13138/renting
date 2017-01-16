package com.ncu.action;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ncu.service.ConfigSV;
import com.ncu.service.MailService;

@Controller
@RequestMapping("/login")
public class HelloController{
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model)throws Exception {
	    model.addAttribute("message", "Hello Spring MVC Framework!");
	    return "login";
    }
}
