package com.njupt.xymall.controller;


import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 导入商品数据到索引库
 * @author ZXL
 *
 */
@Controller
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/index/item/import")
	@ResponseBody
	public XYResult importItemList(){
		return searchItemService.importAllItems();
	}
}
