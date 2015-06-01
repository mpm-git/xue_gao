package org.cz.project.service;

import java.util.List;

import org.cz.project.entity.table.OrderDetail;
import org.cz.project.entity.table.Orders;


public interface OrderService {
	
	/**
	 * 保存订单
	 * @param province
	 * @return
	 */
	void saveOrder(Orders order);
	

	void saveOrderDetail(List<OrderDetail> order_detail_list);
	
}
