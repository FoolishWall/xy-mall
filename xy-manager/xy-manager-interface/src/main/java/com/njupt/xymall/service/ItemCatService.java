package com.njupt.xymall.service;


import com.njupt.xymall.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
	
	List<EasyUITreeNode> getItemCatlist(long parentId);
}
