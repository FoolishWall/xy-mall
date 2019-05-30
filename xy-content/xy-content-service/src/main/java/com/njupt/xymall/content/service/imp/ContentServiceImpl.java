package com.njupt.xymall.content.service.imp;

import com.njupt.xymall.common.jedis.JedisClient;
import com.njupt.xymall.common.utils.JsonUtils;
import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.content.service.ContentService;
import com.njupt.xymall.mapper.TbContentMapper;
import com.njupt.xymall.pojo.TbContent;
import com.njupt.xymall.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;


	@Override
	public XYResult addContent(TbContent content) {
		// 将内容补充完整
		content.setCreated(new Date());
		content.setUpdated(new Date());
		// 插入到数据库
		contentMapper.insert(content);
		//缓存同步，删除缓存中对应的数据
		jedisClient.hdel("CONTENT_LIST", content.getCategoryId().toString());
		return XYResult.ok();
	}

	/**
	 * 根据内容分类id查询内容列表
	 */
	@Override
	public List<TbContent> getContentListByCid(long cid) {
		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.hget("CONTENT_LIST", cid + "");
			if (StringUtils.isNotBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果没有查询数据库
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andCategoryIdEqualTo(cid);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		// 把结果添加到缓存
		try {
			jedisClient.hset("CONTENT_LIST", cid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
