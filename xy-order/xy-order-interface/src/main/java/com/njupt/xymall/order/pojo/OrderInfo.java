package com.njupt.xymall.order.pojo;


import com.njupt.xymall.pojo.TbOrder;
import com.njupt.xymall.pojo.TbOrderItem;
import com.njupt.xymall.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;


public class OrderInfo extends TbOrder implements Serializable {

	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
}
