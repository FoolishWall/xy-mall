package com.njupt.xymall.sso.service;


import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.pojo.TbUser;

public interface RegisterService {
	
	XYResult checkData(String param, int type);
	XYResult register(TbUser user);
}
