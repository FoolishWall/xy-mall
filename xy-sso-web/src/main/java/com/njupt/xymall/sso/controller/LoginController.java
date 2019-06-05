package com.njupt.xymall.sso.controller;

import com.njupt.xymall.common.utils.CookieUtils;
import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录处理
 * @author ZXL
 *
 */
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@RequestMapping("/page/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping(value="/user/login", method= RequestMethod.POST)
	@ResponseBody
	public XYResult login(String username, String password,
						  HttpServletRequest request, HttpServletResponse response) {
		XYResult e3Result = loginService.userLogin(username, password);
		//判断是否登录成功
		if(e3Result.getStatus() == 200) {
			String token = e3Result.getData().toString();
			//如果登录成功需要把token写入cookie
			CookieUtils.setCookie(request, response, TOKEN_KEY, token);
		}
		//返回结果
		return e3Result;
	}
}
