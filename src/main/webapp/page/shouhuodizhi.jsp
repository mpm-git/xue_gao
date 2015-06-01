<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <script  src="../res/js/load.js"></script>
    <script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
    <script  type="text/javascript">

    	loadJsCssFileRelToLoadJs("../css/header.css");
    	loadJsCssFileRelToLoadJs("../css/shouhuodizhi.css");
    	//bootstrap
    	loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/css/bootstrap.css");
    	loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/js/bootstrap.min.js");
    	
    	//masonry
    	loadJsCssFileRelToLoadJs("masonry/dist/masonry.pkgd.min.js");
    	
    	//lightbox
    	loadJsCssFileRelToLoadJs("lightbox/css/lightbox.css");
    	loadJsCssFileRelToLoadJs("lightbox/lightbox-2.6.min.js");
    	//store.js
    	loadJsCssFileRelToLoadJs("store.js/store.js");
    	var global_position={};
    	function saveOrder()
    	{
        	var name=$("#name").val();
        	var address=$("#address").val();
        	var name=$("#name").val();
        	var telphone=$("#telphone").val();
        	var area=$("#area").val();
        	var x=store.get('xugao');
        	if(!x)
            {
                alert("购物车为空");
                return;
            }	
        	var xugao=x.xugao;
        	if(!xugao||!xugao.orderinfo||!xugao.orderinfo.goods||xugao.orderinfo.goods.length<=0)
            {
                alert("购物车为空");
                return;
            }
        	var g=xugao.orderinfo.goods;
        	if(!name||name.trim().length<=0||!address||address.trim().length<=0||!name||name.trim().length<=0||!telphone||telphone.trim().length<=0||!area||area.trim().length<=0)
            {
                alert("信息不能为空");
                return;
            }
        	$.ajax({ 
   			 	type:"GET",
                url:"/save_order",
                data:{area:area,address:address,name:name,telphone:telphone,order_detail:JSON.stringify(g)},
                datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                beforeSend:function(){
                },
   				success: function(data){
	       			if(data.status=="success")
	           		{
	       				var xugao=store.get('xugao');
	       	        	xugao.xugao.orderinfo.goods=[];
	        			store.set('xugao',xugao);
	        			window.location.href="index.jsp";
	               	}
   				}
   		});
        }
    	$(function(){
    		Util.getLocation(function(position){
    			lat = position.coords.latitude;
                lon = position.coords.longitude;
                global_position.lat=lat;
                global_position.lon=lon;
                //var map = new BMap.Map("container");            // 创建Map实例
                var point = new BMap.Point(lon, lat);    // 创建点坐标
                //map.centerAndZoom(point,15);                     //
                //map.enableScrollWheelZoom();
                var gc = new BMap.Geocoder();
                gc.getLocation(point, function (rs) {
                    var addComp = rs.addressComponents;
                    $("#address").val(addComp.district+","+addComp.street);
                });
        	});
    		$.ajax({ 
    			 type:"GET",
                 url:"/get_areas",
                 data:{city:"九江市",province:'江西省',order_detail:{a:{name:"xx",sex:"1"},b:"111"}},
                 datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                 beforeSend:function(){
                 },
    			success: function(data){
        			if(data.status=="success")
            		{
	    				$("#city").empty();
		    			$("#city").append("<option value=''>九江</option>");;
// 	    				$("#area").append("<option value=''>--</option>");
			    		var res=data.result;
		    			for(var i in res.result)
		    			{
			    			var d=res.result[i];
		    				$("#area").append("<option value='"+d.id+"'>"+d.area+"</option>");;
	 	    			}
                	}
    			}
    		});
         });
    </script>
</head>
<body style="padding-top:132px;padding-bottom:30px;">
    <nav class="navbar navbar-fixed-top navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class=" navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img class="logo-img" src="../res/img/logo.png"/>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class=" nav navbar-nav navbar-right">
                    <li>
                        <a href="index.jsp">首页</a>
                    </li>
                    <li>
                        <a href="chanpinliebiao3.jsp">热门产品</a>
                    </li>
                    <li>
                        <a href="gouwuche.jsp">购物车</a>
                    </li>
<!--                     <li> -->
<!--                         <a href="#">注册</a> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="#">登陆</a> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="#" class="clicked">会员中心</a> -->
<!--                     </li> -->
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="text-center flow-box">
            <img class="flow-chart" src="../res/img/222.png" />
        </div>
        <div class="address-box">
            <div class="title">
                <h4>新增收货地址</h4>
            </div>
            <div class="divider"></div>
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-xs-3 control-label">所在地区</label>
                    <div class="col-xs-4">
                        <select id="city" class="form-control">
                        </select>
                    </div>
                    <div class="col-xs-5">
                        <select id="area" class="form-control">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">详细地址</label>
                    <div class="col-xs-9">
                        <textarea class="form-control" id="address"></textarea>
                    </div>
                </div>
<!--                 <div class="form-group"> -->
<!--                     <label class="col-xs-3 control-label">邮政编码</label> -->
<!--                     <div class="col-xs-9"> -->
<!--                         <input type="text" class="form-control" /> -->
<!--                     </div> -->
<!--                 </div> -->
                <div class="form-group">
                    <label class="col-xs-3 control-label" >姓名</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" id="name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" >联系方式</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" id="telphone"/>
                    </div>
                </div>
                <div class="form-divider"></div>
                <div class="text-center">
                    <a id="comfirmAdd" type="button" href="javascript:saveOrder()" class="btn">确认收货地址</a>
                </div>
                <div class="bottom-border">

                </div>
            </form>
        </div>
    </div>
</body>
</html>
