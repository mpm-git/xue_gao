package org.cz.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.cz.project.entity.bean.Result;
import org.cz.project.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodsController {
	
	@Autowired GoodsService goodsService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public String index(){
		return result;
	}
	@RequestMapping(value="/get_goods_type", method=RequestMethod.GET)
	@ResponseBody
	public Result getGoodsType(){
		Result result=new Result();
		result.setStatus("success");
		result.setResult(goodsService.getGoodsItem());
		return result;
	}
	
	@RequestMapping(value="/get_goods_by_goodtype", method=RequestMethod.GET)
	@ResponseBody
	public Result getGoods(HttpServletRequest request){
		String type=request.getParameter("goodtype");
		Result result=new Result();
		result.setStatus("success");
		result.setResult(goodsService.getGoodsByKindType(Integer.parseInt(type)));
		return result;
	}
	
	@RequestMapping(value="/get_all_goods", method=RequestMethod.GET)
	@ResponseBody
	public Result getAllGoods(HttpServletRequest request){
		Result result=new Result();
		result.setStatus("success");
		result.setResult(goodsService.getAllGoods());
		return result;
	}

}
