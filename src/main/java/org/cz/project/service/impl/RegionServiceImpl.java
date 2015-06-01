package org.cz.project.service.impl;

import org.cz.project.dao.BaseDao;
import org.cz.project.dao.QueryResult;
import org.cz.project.entity.table.Region;
import org.cz.project.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionServiceImpl implements RegionService {
	@Autowired BaseDao baseDao;
	@Override
	@Transactional
	public QueryResult<Region> getCitysOfProvince(String province) {
		return baseDao.find2QueryResult("from Region where province ='"+province+"'", null, 0, 0);
	}

	@Override
	@Transactional
	public QueryResult<Region> getAreasOfCity(String city) {
		return baseDao.find2QueryResult("from Region where city ='"+city+"'", null, 0, 0);
	}

	@Override
	@Transactional
	public QueryResult<Region> getAreas(String province, String city) {
		return baseDao.find2QueryResult("from Region where city ='"+city+"' and province='"+province+"'", null, 0, 0);
	}

}
