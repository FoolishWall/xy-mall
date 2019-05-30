package com.njupt.xymall.content.service;


import com.njupt.xymall.common.pojo.EasyUITreeNode;
import com.njupt.xymall.common.utils.XYResult;

import java.util.List;

public interface ContentCategoryService {
	
	List<EasyUITreeNode> getContentCatList(long parentId);
}
