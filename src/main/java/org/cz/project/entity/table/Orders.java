package org.cz.project.entity.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@NamedQueries({ @NamedQuery(name = "Orders.findAll", query = "SELECT g FROM Orders g")})
public class Orders extends IdEntity {
	
	@Column(name="usertId",columnDefinition="int default 0",nullable=false,insertable=false,updatable=false)
	private int usertId;
	private String serialNumber;//订单编号:用户id+时间毫秒
	@Column(name="address",columnDefinition="varchar(255) default ''",nullable=false,insertable=false,updatable=false)
	private String address;
//	private long expireTime;
	private long createTime;
	private long deadTime;
	private long updateTime;
	private int status;//1:配送中，2：完成，3：取消
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public int getUsertId() {
		return usertId;
	}
	public void setUsertId(int usetId) {
		this.usertId = usetId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getDeadTime() {
		return deadTime;
	}
	public void setDeadTime(long deadTime) {
		this.deadTime = deadTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
