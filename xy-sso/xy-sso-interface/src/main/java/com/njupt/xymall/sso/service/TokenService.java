package com.njupt.xymall.sso.service;

import com.njupt.xymall.common.utils.XYResult;

/**
 * 根据token查询用户信息
 * @author ZXL
 *
 */
public interface TokenService {

	XYResult getUserByToken(String token);
}
