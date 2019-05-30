package com.njupt.xymall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.xymall.common.pojo.EasyUIDataGridResult;
import com.njupt.xymall.common.utils.IDUtils;
import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.mapper.TbItemDescMapper;
import com.njupt.xymall.mapper.TbItemMapper;
import com.njupt.xymall.pojo.TbItem;
import com.njupt.xymall.pojo.TbItemDesc;
import com.njupt.xymall.pojo.TbItemExample;
import com.njupt.xymall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wall
 * @date 2019/5/21  19:49
 * @description
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItem getItemById(long itemId) {

        return itemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public XYResult addItem(TbItem item, String desc) {
        // 生成商品id
        final long itemId = IDUtils.genItemId();
        // 补全item的属性
        item.setId(itemId);
        // 商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        // 向商品表插入数据
        itemMapper.insert(item);
        // 创建一个商品描述表对应的pojo对象
        TbItemDesc itemDesc = new TbItemDesc();
        // 补全属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        // 向商品描述表插入数据
        itemDescMapper.insert(itemDesc);

        // 返回成功
        return XYResult.ok();
    }

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        // 设置分页信息
        PageHelper.startPage(page, rows);
        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        // 创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        // 取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        // 取总记录数
        long total = pageInfo.getTotal();
        result.setTotal(total);
        return result;
    }

}
