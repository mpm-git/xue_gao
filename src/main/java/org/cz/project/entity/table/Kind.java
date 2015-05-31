package org.cz.project.entity.table;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "kind")
@NamedQueries({ @NamedQuery(name = "Kind.findAll", query = "SELECT g FROM Kind g")})
public class Kind extends IdEntity {
	private int level;
	private int pid;  //0 一级  1二级
	private String code;//类别编号:由父类 code+{level}+自己id组成
	private String name;  //类型名称
	private String icon;
	private String url;
	private int rank;  //二级菜单的排序
	private int goodid;  //商品id 商品类型跟商品id one to many
	private int goodtype;   //商品类型
	
	public int getGoodtype() {
		return goodtype;
	}
	public void setGoodtype(int goodtype) {
		this.goodtype = goodtype;
	}
	public int getGoodid() {
		return goodid;
	}
	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
