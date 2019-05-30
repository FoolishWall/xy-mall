package com.njupt.xymall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.njupt.xymall.common.pojo.EasyUIDataGridResult;
import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.pojo.TbItem;
import com.njupt.xymall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wall
 * @date 2019/5/21  19:55
 * @description
 */

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item")
    @ResponseBody
    public TbItem getItemById(Long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //调用服务查询商品列表
        return itemService.getItemList(page, rows);
    }

    /**
     * 商品添加功能
     */
    @RequestMapping(value="/item/save", method= RequestMethod.POST)
    @ResponseBody
    public XYResult addItem(TbItem item, String desc){
        return itemService.addItem(item, desc);
    }
}
