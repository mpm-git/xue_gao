<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <script  src="../res/js/load.js"></script>
    <script  type="text/javascript">

    	loadJsCssFileRelToLoadJs("../css/header.css");
    	loadJsCssFileRelToLoadJs("../css/gouwuche.css");
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
    </script>
</head>
<body style="padding-top:132px;">
    <nav class="navbar navbar-fixed-top navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class=" navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" style="color:white;">Project name</a>
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
                        <a href="gouwuche.jsp" class="clicked">购物车</a>
                    </li>
                    <li>
                        <a href="#">注册</a>
                    </li>
                    <li>
                        <a href="#">登陆</a>
                    </li>
                    <li>
                        <a href="#">会员中心</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="text-center flow-box">
            <img class="flow-chart" src="../res/img/111.png" />
        </div>
        <div class="row">
            <div class="col-xs-12 title-box">购物列表</div>
        </div>
        <div id="good_list">      
<!-- 	        <div class="row item-list">  -->
<!-- 	            <div class="col-xs-3 text-center " style="padding:0px;padding-left:15px;"> -->
<!-- 	                <img class="item-img img-thumbnail" src="../res/img/goods/1.jpg" /> -->
<!-- 	            </div>       -->
<!-- 	            <div class="col-xs-6 item-info"> -->
<!-- 	                <div class="col-xs-12 col-sm-6"> -->
<!-- 	                    <p>超级大雪糕最爱吃</p>                -->
<!-- 	                </div>                     -->
<!-- 	                <div class="col-xs-12 col-sm-6"> -->
<!-- 	                    <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!-- 	                        <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!-- 	                    </button> -->
<!-- 	                    <input class="number" type="text" style="width:30px;" /> -->
<!-- 	                    <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!-- 	                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!-- 	                    </button> -->
<!-- 	                </div> -->
<!-- 	                <div class="col-xs-12 col-sm-6"> -->
<!-- 	                    <p>数量</p> -->
<!-- 	                </div>                            -->
<!-- 	            </div> -->
<!-- 	            <div class="col-xs-3 item-info"> -->
<!-- 	                <p>13231元 -->
<!-- 	                <button type="button" class="btn btn-default btn-delete" style="margin-top: -10px;"> -->
<!-- 	                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> -->
<!-- 	                </button> -->
<!-- 	                </p> -->
<!-- 	            </div> -->
<!-- 	        </div> -->
<!-- 	        <div class="row item-list"> -->
<!-- 	            <div class="col-xs-3 text-center " style="padding:0px;padding-left:15px;"> -->
<!-- 	                <img class="item-img img-thumbnail" src="../res/img/goods/2.jpg" /> -->
<!-- 	            </div> -->
<!-- 	            <div class="col-xs-6 item-info"> -->
<!-- 	                <div class="col-xs-12 col-sm-6"> -->
<!-- 	                    <p>商品名称</p> -->
<!-- 	                </div> -->
<!-- 	                <div class="col-xs-12 col-sm-6"> -->
<!-- 	                    <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!-- 	                        <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!-- 	                    </button> -->
<!-- 	                    <input class="number" type="text" style="width:30px;" /> -->
<!-- 	                    <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!-- 	                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!-- 	                    </button> -->
<!-- 	                </div> -->
<!-- 	                <div class="col-xs-12 col-sm-6"> -->
<!-- 	                    <p>剩余个数</p> -->
<!-- 	                </div> -->
<!-- 	            </div> -->
<!-- 	            <div class="col-xs-3 item-info"> -->
<!-- 	                <p>商品价格</p> -->
<!-- 	                <button type="button" class="btn btn-default btn-delete"> -->
<!-- 	                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> -->
<!-- 	                </button> -->
<!-- 	            </div> -->
<!-- 	        </div> -->
	    </div>
        
        <div class="row total-box"> 
            <div class="col-xs-12 col-sm-6 text-center">
                <span class="total-text">总金额：<font class="renminbi">￥</font><font id="total"></font></span>
            </div>
            <div class="col-xs-12 col-sm-6 text-center">
                <a href="shouhuodizhi.jsp" id="submit" type="button" class="btn btn-default">结算</a>
            </div> 
        </div>
    </div>
    <script type="text/javascript">
    	$(document).ready(function(){
    		//初始化购物车
    		var xugao=store.get('xugao');
    		console.info(xugao);
    		if(xugao!=null){
    			var goods=xugao.xugao.orderinfo.goods;
    			console.info(goods);
    			var total=0;
    			for(var i=0;i<goods.length;i++){
		    		$('#good_list').append('<div class="row item-list">'
		    				+'<div class="col-xs-3 text-center " style="padding:0px;padding-left:15px;">'
			                +'<img class="item-img img-thumbnail" src="../res/img/goods/1.jpg" />'
			                +'</div>'
			                +'<div class="col-xs-6 item-info">'
			                +'<div class="col-xs-12 col-sm-6">'
			                +'<p>'+goods[i].name+'</p>'       //商品名称
			                +'</div>'
			                +'<div class="col-xs-12 col-sm-6" style="margin-top:13px;">'
			                +'<button type="button" class="btn btn-default btn-minus" aria-label="Left Align" onclick="minus('+i+');">'
			                +'<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>'
			                +'</button>'
			                +'<input id="input_'+i+'" class="number" type="text" style="width:30px;" />'
			                +'<button id="btn_'+i+'" type="button" class="btn btn-default btn-plus" aria-label="Left Align" onclick="add_num('+i+','+goods[i].surplusnum+',\''+goods[i].price+'\');">'
			                +'<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>'
			                +'</button>'
			                +'</div>'
			                +'<div class="col-xs-12 col-sm-6">'
// 			                +'<p>数量</p>'   //购买数量
			                +'</div>'             
			                +'</div>' 	    
			                +'<div class="col-xs-3 item-info">' 
			                +' <p>￥'+goods[i].price+'</p>'    //价格
			                +'<button type="button" class="btn btn-default btn-delete" onclick="canclegood('+goods[i].id+')">' 
			                +'<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' 
			                +'</button>' 	
			                +'</div>');
		    		$('#input_'+i).val(goods[i].buynumber);
		    		total+=parseInt(goods[i].price);
    			}
		    	$('#total').html(''+total);
    			
    		}
    	});
        function add_num(i,surplusnum,price){ //surplusnum剩余数量
        	var number=$('#input_'+i).val();
        	var number_=parseInt(number);
        	if(number>=surplusnum){
        		$('#btn_'+i).attr("disabled",true);
        	}else{
	        	$('#input_'+i).val(number_+1);
	        	if((number_+1)==num){
	        		$('#btn_'+i).attr("disabled",true);
	        	}
        	}
        	//计算总金额
        	var total=0;
        	total=parseInt(price)*number;
        	$('#total').html(''+total);
        }
        
        function minus(i){
        	var num=$('#input_'+i).val();
        	var number=parseInt(num);
        	if(number>0){
	        	$('#input_'+i).val(number-1);
	        	$('#btn_'+i).attr("disabled",false);
        	}
        }
        
        function canclegood(id){
        	var xugao=store.get('xugao');
        	var goods=xugao.xugao.orderinfo.goods;
        	for(var i=0;i<goods.length;i++){
        		console.info(goods[i].id+':'+id);
        		if(goods[i].id==id){
        			goods.remove(goods[i]);
        			store.set('xugao',xugao);
        			window.location.href="gouwuche.jsp";
        			break;
        		}
        	}
        }
        
        Array.prototype.indexOf = function(val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };
        Array.prototype.remove = function(val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };
    </script>
</body>
</html>
