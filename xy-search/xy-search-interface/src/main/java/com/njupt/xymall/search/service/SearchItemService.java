package com.njupt.xymall.search.service;

import com.njupt.xymall.common.utils.XYResult;

public interface SearchItemService {
	/**
	 * 将数据库商品信息中的
	 * ID
	 * 标题
	 * 卖点
	 * 价格
	 * 图片
	 * 分类名称 全部导入索引库
	 * @return
	 */
	XYResult importAllItems();
}
