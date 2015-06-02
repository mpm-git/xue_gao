package org.cz.project.service.impl;

import java.io.Serializable;
import java.util.List;

import org.cz.project.dao.BaseDao;
import org.cz.project.entity.bean.Result;
import org.cz.project.entity.table.Goods;
import org.cz.project.entity.table.Kind;
import org.cz.project.entity.table.OrderDetail;
import org.cz.project.entity.table.Orders;
import org.cz.project.entity.table.User;
import org.cz.project.service.GoodsService;
import org.cz.project.service.OrderService;
import org.cz.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements UserService{
	@Autowired BaseDao baseDao;

	@Override
	@Transactional
	public void saveUser(User user) {
		baseDao.save(user);
		
	}

	@Override
	@Transactional
	public void saveOrUpdateUser(User user) {
		baseDao.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public User getUserByPhone(String phone) {
		if(phone==null||phone.trim().length()<=0)
			return null;
		List<User> f= baseDao.find("from User where phone ='"+phone+"'");
		if(f!=null&&f.size()>0)
		{
			return f.get(0);
		}
		return null;
	}


}
