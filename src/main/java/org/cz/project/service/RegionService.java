package org.cz.project.service;

import org.cz.project.dao.QueryResult;
import org.cz.project.entity.table.Region;


public interface RegionService {
	
	/**
	 * 获取省份的中的城市
	 * @param province
	 * @return
	 */
	public QueryResult<Region> getCitysOfProvince(String province);
	/**
	 * 获取城市中的县区
	 * @param city
	 * @return
	 */
	public QueryResult<Region> getAreasOfCity(String city);
	
	/**
	 * @param province
	 * @param city
	 * @return
	 */
	public QueryResult<Region> getAreas(String province,String city);
}
