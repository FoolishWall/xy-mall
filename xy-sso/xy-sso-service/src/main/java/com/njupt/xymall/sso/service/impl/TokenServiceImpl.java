package com.njupt.xymall.sso.service.impl;

import com.njupt.xymall.common.jedis.JedisClient;
import com.njupt.xymall.common.utils.JsonUtils;
import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.pojo.TbUser;
import com.njupt.xymall.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 根据token查询用户信息
 * 
 * @author ZXL
 *
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Override
	public XYResult getUserByToken(String token) {
		// 根据token到redis中取用户信息
		String json = jedisClient.get("SESSION:" + token);
		// 取不到用户信息，登录已经过期，返回登录过期
		if (StringUtils.isBlank(json)) {
			return XYResult.build(201, "用户登录已经过期");
		}
		// 取到用户信息更新token的过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		// 返回结果，E3Result其中包含TbUser对象
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		return XYResult.ok(user);
	}

}
