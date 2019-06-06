package com.njupt.xymall.order.service;


import com.njupt.xymall.common.utils.XYResult;
import com.njupt.xymall.order.pojo.OrderInfo;

public interface OrderService {

	XYResult createOrder(OrderInfo orderInfo);
}
