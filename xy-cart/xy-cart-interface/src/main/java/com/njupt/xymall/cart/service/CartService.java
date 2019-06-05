package com.njupt.xymall.cart.service;


import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.pojo.TbItem;

import java.util.List;

public interface CartService {

	XYResult addCart(long userId, long itemId, int num);
	XYResult mergeCart(long userId, List<TbItem> itemList);
	List<TbItem> getCartList(long userId);
	XYResult updateCartNum(long userId, long itemId, int num);
	XYResult deleteCartItem(long userId, long itemId);
	XYResult clearCartItem(long userId);
}
