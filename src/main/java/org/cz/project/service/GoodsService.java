package org.cz.project.service;

import java.util.List;

import org.cz.project.entity.table.Goods;
import org.cz.project.entity.table.Kind;

public interface GoodsService {
	//获取所有商品类型
	public List<Kind> getGoodsItem();
	
	//获取所有商品类型对应的商品
	public List<Goods> getGoodsByKindType(int goodType);
	
	//获取所有的商品
	public List<Goods> getAllGoods();
}
