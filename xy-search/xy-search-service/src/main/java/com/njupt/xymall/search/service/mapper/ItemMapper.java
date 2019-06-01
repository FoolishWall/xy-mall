package com.njupt.xymall.search.service.mapper;


import com.njupt.xymall.common.pojo.SearchItem;

import java.util.List;

public interface ItemMapper {

	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
}
