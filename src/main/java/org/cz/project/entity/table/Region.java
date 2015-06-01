package org.cz.project.entity.table;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author HJL
 *
	payment_snint(11) NOT NULL
	province_namevarchar(15) NOT NULL
	city_namevarchar(15) NOT NULL
	area_namevarchar(20) NOT NULL
	service_areavarchar(80) NOT NULL
	noservice_areavarchar(80) NOT NULL
	operationvarchar(2) NOT NULL
 */
@Entity
@Table(name = "region")
@NamedQueries({ @NamedQuery(name = "Region.findAll", query = "SELECT g FROM Region g")})
public class Region extends IdEntity {
	private String province;
	private String city;
	private String area;
	private String service;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
}
