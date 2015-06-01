package org.cz.project.service;

import java.util.List;

import org.cz.project.entity.table.OrderDetail;
import org.cz.project.entity.table.Orders;
import org.cz.project.entity.table.User;


public interface UserService {
	
	/**
	 * 保存订单
	 * @param province
	 * @return
	 */
	void saveUser(User user);
	void saveOrUpdateUser(User user);
	User getUserByPhone(String Phone);
	
}
