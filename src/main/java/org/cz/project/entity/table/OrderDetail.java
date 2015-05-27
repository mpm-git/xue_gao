package org.cz.project.entity.table;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
@NamedQueries({ @NamedQuery(name = "OrderDetail.findAll", query = "SELECT g FROM OrderDetail g")})
public class OrderDetail extends IdEntity {

	private String orderSerialNumber;//订单编号
	private int goodId;
	private int num;
	private double price;
	public String getOrderSerialNumber() {
		return orderSerialNumber;
	}
	public void setOrderSerialNumber(String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
