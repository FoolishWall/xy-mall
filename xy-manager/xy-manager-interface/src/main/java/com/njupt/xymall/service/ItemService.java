package com.njupt.xymall.service;

import com.njupt.xymall.common.pojo.EasyUIDataGridResult;
import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.pojo.TbItem;
import com.njupt.xymall.pojo.TbItemDesc;

/**
 * @author wall
 * @date 2019/5/21  19:44
 * @description
 */
public interface ItemService {
    TbItem getItemById(long itemId);
    XYResult addItem(TbItem item, String desc);
    EasyUIDataGridResult getItemList(Integer page, Integer rows);
    TbItemDesc getItemDescById(long itemId);
}
