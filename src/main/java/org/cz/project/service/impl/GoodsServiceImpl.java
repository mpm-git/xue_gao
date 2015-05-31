package org.cz.project.service.impl;

import java.util.List;

import org.cz.project.dao.BaseDao;
import org.cz.project.entity.bean.Result;
import org.cz.project.entity.table.Goods;
import org.cz.project.entity.table.Kind;
import org.cz.project.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired BaseDao baseDao;
	
	@Override
	@Transactional
	public List<Kind> getGoodsItem() {
		String hql="from Kind group by goodtype";
		return baseDao.find(hql);
	}

	@Override
	@Transactional
	public List<Goods> getGoodsByKindType(int goodType) {
		System.out.println("in getGoodsByKindType");
		String hql1="from Kind where goodid<>0 and goodtype="+goodType;
		List<Kind> list=baseDao.find(hql1);
		System.out.println("size:"+list.size());
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<list.size();i++){
			sb=sb.append("'"+list.get(i).getGoodid()+"',");
		}
		String st=sb.substring(0, sb.length()-1);
		System.out.println("st:"+st);
		String hql="from Goods where id in ("+st+")";
		return baseDao.find(hql);
		
	}

	@Override
	@Transactional
	public List<Goods> getAllGoods() {
		String hql="from Goods";
		return baseDao.find(hql);
	}

}
