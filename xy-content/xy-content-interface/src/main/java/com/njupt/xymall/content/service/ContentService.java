package com.njupt.xymall.content.service;


import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.pojo.TbContent;

import java.util.List;

public interface ContentService {

	XYResult addContent(TbContent content);
	List<TbContent> getContentListByCid(long cid);
}
