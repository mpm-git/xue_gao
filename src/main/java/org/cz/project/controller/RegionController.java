package org.cz.project.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cz.project.entity.bean.Result;
import org.cz.project.service.GoodsService;
import org.cz.project.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import per.cz.util.http.HttpUtil;

@Controller
public class RegionController {
	@Autowired RegionService regionService;
	
	@RequestMapping(value="/get_areas", method=RequestMethod.GET)
	@ResponseBody
	public Result getAreas(HttpServletRequest request){
		Map<String, String> params = HttpUtil.getParameters(request, "utf-8");
		Result result=new Result();
		String city = params.get("city");
		String province = params.get("province");
		if(city==null||province==null)
		{
			result.setStatus("error");
			result.setMessage("city or province are not allow null");
			return result;
		}
		result.setStatus("success");
		result.setResult(regionService.getAreas(province.trim(), city.trim()));
		return result;
	}
}
