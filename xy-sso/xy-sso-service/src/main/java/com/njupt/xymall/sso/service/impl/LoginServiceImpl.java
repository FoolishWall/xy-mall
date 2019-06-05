package com.njupt.xymall.sso.service.impl;

import com.njupt.xymall.common.jedis.JedisClient;
import com.njupt.xymall.common.utils.JsonUtils;
import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.mapper.TbUserMapper;
import com.njupt.xymall.pojo.TbUser;
import com.njupt.xymall.pojo.TbUserExample;
import com.njupt.xymall.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * 用户登录处理
 * 
 * @author ZXL
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Override
	public XYResult userLogin(String username, String password) {
		// 1、判断用户和密码是否正确
		// 根据用户名查询用户信息
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			// 返回登录失败
			return XYResult.build(400, "用户名或密码错误");
		}
		// 取用户信息
		TbUser user = list.get(0);
		// 判断密码是否正确
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			// 2、如果不正确，返回登录失败
			return XYResult.build(400, "用户名或密码错误");
		}
		// 3、如果正确生成token。
		String token = UUID.randomUUID().toString();
		// 4、把用户信息写入redis，key：token value：用户信息
		user.setPassword(null);
		jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(user));
		// 5、设置Session的过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		// 6、把token返回

		return XYResult.ok(token);
	}

}
