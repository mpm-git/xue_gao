package org.cz.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cz.project.entity.bean.Result;
import org.cz.project.entity.table.OrderDetail;
import org.cz.project.entity.table.Orders;
import org.cz.project.entity.table.User;
import org.cz.project.service.GoodsService;
import org.cz.project.service.OrderService;
import org.cz.project.service.RegionService;
import org.cz.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import per.cz.util.gson.GsonUtil;
import per.cz.util.http.HttpUtil;

@Controller
public class OrderController {
	@Autowired OrderService orderService;
	@Autowired UserService userService;
	
//	@RequestMapping(value="/get_areas_", method=RequestMethod.GET)
//	@ResponseBody
//	public Result getAreas(HttpServletRequest request){
//		Map<String, String> params = HttpUtil.getParameters(request, "utf-8");
//		Result result=new Result();
//		String city = params.get("city");
//		String province = params.get("province");
//		if(city==null||province==null)
//		{
//			result.setStatus("error");
//			result.setMessage("city or province are not allow null");
//			return result;
//		}
//		result.setStatus("success");
//		result.setResult(regionService.getAreas(province.trim(), city.trim()));
//		return result;
//	}
	@RequestMapping(value="/save_order", method=RequestMethod.GET)
	@ResponseBody
	public Result saveOrder(HttpServletRequest request){
		Map<String, String> params = HttpUtil.getParameters(request, "utf-8");
		Result res=new Result();
		String area = params.get("area");
		String address = params.get("address");
		String name = params.get("address");
		String phone = params.get("telphone");
		String order_detail = params.get("order_detail");
		if(area==null||area.length()<=0||address==null||address.length()<=0||name==null||name.length()<=0||phone==null||phone.length()<=0)
		{
			res.setStatus("error");
			res.setMessage("area , address , name or phone are not allow null");
			return res;
		}
		res.setStatus("success");
		Orders o=new Orders();
		o.setAddress(address);
		o.setCreateTime(new Date().getTime());
		
		User u=new User();
		u.setAddress(address);
		u.setName(name);
		u.setPhone(phone);
		User userByPhone = userService.getUserByPhone("phone");
		if(userByPhone!=null)
		{
			o.setUsertId(userByPhone.getId());
		}else
		{
			userService.saveUser(u);
			o.setUsertId(u.getId());
		}
		o.setSerialNumber(u.getId()+new Date().getTime()+"");
		orderService.saveOrder(o);
		
		List<Map<String,Object>> _order_detail_list = (List<Map<String, Object>>) GsonUtil.jsonToList(order_detail);
		System.out.println(_order_detail_list);
		List<OrderDetail> order_detail_list=new ArrayList<OrderDetail>();
		for (Map<String,Object> od : _order_detail_list) {
			OrderDetail _o=new OrderDetail();
			int _id = Integer.parseInt(od.get("id").toString().replaceAll("\\.0$", ""));
			_o.setGoodId(_id);
			_o.setNum(Integer.parseInt(od.get("buynumber").toString().replaceAll("\\.0$", "")));
			_o.setOrderSerialNumber(o.getSerialNumber());
			_o.setPrice(Double.parseDouble(od.get("price").toString()));
			order_detail_list.add(_o);
		}
		orderService.saveOrderDetail(order_detail_list);
		res.setMessage("success");
		res.setStatus("success");
		res.setResult(order_detail_list);
//		orderService.saveOrderDetail(order_detail_list);
//		result.setResult(regionService.getAreas(province.trim(), city.trim()));
		return res;
	}
}
