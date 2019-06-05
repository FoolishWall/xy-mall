package com.njupt.xymall.sso.service;

import com.njupt.xymall.common.utils.XYResult;

public interface LoginService {

	//参数：用户名和密码
	//业务逻辑：
	/*
	 * 1.判断用户名和密码是否正确
	 * 2.如果不正确，返回登录失败
	 * 3.如果正确生成token
	 * 4.把用户信息写入redis，key:token value:用户信息
	 * 5.设置Session的过期时间
	 * 6.把token返回,需要web层中的（HttpResponse）将token写到cookie
	 */
	//返回值：XYResult，其中包含token信息
	XYResult userLogin(String username, String password);
}
