package org.cz.project.service.impl;

import java.io.Serializable;
import java.util.List;

import org.cz.project.dao.BaseDao;
import org.cz.project.entity.bean.Result;
import org.cz.project.entity.table.Goods;
import org.cz.project.entity.table.Kind;
import org.cz.project.entity.table.OrderDetail;
import org.cz.project.entity.table.Orders;
import org.cz.project.service.GoodsService;
import org.cz.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements OrderService{
	@Autowired BaseDao baseDao;

	@Override
	@Transactional
	public void saveOrder(Orders order) {
		Serializable save = baseDao.save(order);
	}

	@Override
	@Transactional
	public void saveOrderDetail(List<OrderDetail> order_detail_list) {
		baseDao.save(order_detail_list);
	}

}
