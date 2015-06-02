<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title></title>


    <script  src="../res/js/load.js"></script>
    <script  type="text/javascript">
    	loadJsCssFileRelToLoadJs("../css/header.css");
    	loadJsCssFileRelToLoadJs("../css/chanpinliebiao.css");
		//bootstrap
    	loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/css/bootstrap.css");
    	loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/js/bootstrap.min.js");
    	
    	//masonry
    	loadJsCssFileRelToLoadJs("masonry/dist/masonry.pkgd.min.js");
    	//imagesloaded
    	loadJsCssFileRelToLoadJs("imagesloaded/imagesloaded.js");
    	
    	//lightbox
    	loadJsCssFileRelToLoadJs("lightbox/css/lightbox.css");
    	loadJsCssFileRelToLoadJs("lightbox/lightbox-2.6.min.js");
    	
    	//store.js
    	loadJsCssFileRelToLoadJs("store.js/store.js");
    	
    </script>
    <style>
    </style>
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
                <img class="logo-img" src="../res/img/logo.png"/>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class=" nav navbar-nav navbar-right">
                    <li>
                        <a href="index.jsp">首页</a>
                    </li>
                    <li>
                        <a href="chanpinliebiao3.jsp" class="clicked">热门产品</a>
                    </li>
                    <li>
                        <a href="gouwuche.jsp">购物车</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row" id="contentBox">
            <div id="leftDiv" class="col-xs-6 col-sm-3 categary-list-box">
                <div class="list-group categary-list" id="goodstype">
<!--                     <a href="#" class="list-group-item categary-item">雪糕系列<span class="left-arrow">></span></a> -->
<!--                     <a href="#" class="list-group-item categary-item">冰饮系列<span class="left-arrow">></span></a> -->
<!--                     <a href="#" class="list-group-item categary-item">杯系列<span class="left-arrow">></span></a> -->
                </div>
            </div>
            <div id="rightDiv" class="col-xs-12 col-sm-9">
                <div class="row product-title-box">
                    <div class="col-xs-4">
                        <button type="button" class="btn btn-primary visible-xs" id="categaryBtn">
                            <span class="glyphicon glyphicon glyphicon-option-vertical"></span>分类
                        </button>
                    </div>
                    <div class="col-xs-4 text-center">
                        <span class="product-title">产品列表</span>
                    </div>
                </div>
                <div id="con" class="row tab_content">
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <a href="../res/img/goods/1.jpg" data-lightbox="1" data-title="哈哈哈哈"> -->
<!--                                 <img src="../res/img/goods/1.jpg" /> -->
<!--                             </a> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->
<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../res/img/shopping_cart.png" /> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <a href="../res/img/goods/2.jpg" data-lightbox="111111"> -->
<!--                                 <img src="../res/img/goods/2.jpg" /> -->
<!--                             </a> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->

<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../img/shopping_cart.png" /> -->

<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <img src="../res/img/goods/3.jpg" /> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->

<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../res/img/shopping_cart.png" /> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <img src="../res/img/goods/4.jpg" /> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->

<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../res/img/shopping_cart.png" /> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <img src="../res/img/goods/5.jpg" /> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->

<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../res/img/shopping_cart.png" /> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <img src="../res/img/goods/6.jpg" /> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->

<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../res/img/shopping_cart.png" /> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <img src="../res/img/goods/7.jpg" /> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->

<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../res/img/shopping_cart.png" /> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <img src="../res/img/goods/8.jpg" /> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->

<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../res/img/shopping_cart.png" /> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="box_img"> -->
<!--                             <img src="../res/img/goods/9.jpg" /> -->
<!--                             <p>商品名称</p> -->
<!--                             <p>价格</p> -->
<!--                             <p>剩余数量</p> -->

<!--                             <button type="button" class="btn btn-default btn-minus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input class="number" type="text" style="width:30px;" /> -->
<!--                             <button type="button" class="btn btn-default btn-plus" aria-label="Left Align"> -->
<!--                                 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!--                             </button> -->
<!--                             <input type="image" src="../res/img/shopping_cart.png" /> -->
<!--                         </div> -->
                    </div>

                </div>
            </div>
        </div>
    <script>
        $(document).ready(function () {
        	var typeid_=GetQueryString('typeid');
        	if(typeid_!=null){
        	 $.ajax({
                 type:"GET",
                 url:"/get_goods_type",
                 data:{},
                 datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                 beforeSend:function(){
                 },
                 success:function(data){
                	for(var i=0;i<data.result.length;i++){
                		$('#goodstype').append('<a href="#" class="list-group-item categary-item" onclick="getGoods('+data.result[i].goodtype+');">'+data.result[i].name+'<span class="left-arrow">></span></a>');
                	}
                	getGoods(parseInt(typeid_));
// 		        	$('#goodstype').append('<a href="#" class="list-group-item categary-item">雪糕系列<span class="left-arrow">></span></a>'
// 		                    +'<a href="#" class="list-group-item categary-item">冰饮系列<span class="left-arrow">></span></a>'
// 		                    +'<a href="#" class="list-group-item categary-item">杯系列<span class="left-arrow">></span></a>');
                 },
                 complete: function(XMLHttpRequest, textStatus){
                 },
                 error: function(){
                 }         
              });
        	}else{
        	 $.ajax({
                 type:"GET",
                 url:"/get_goods_type",
                 data:{},
                 datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                 beforeSend:function(){
                 },
                 success:function(data){
                	for(var i=0;i<data.result.length;i++){
                		$('#goodstype').append('<a href="#" class="list-group-item categary-item" onclick="getGoods('+data.result[i].goodtype+');">'+data.result[i].name+'<span class="left-arrow">></span></a>');
                	}
                	getAllGoods();
// 		        	$('#goodstype').append('<a href="#" class="list-group-item categary-item">雪糕系列<span class="left-arrow">></span></a>'
// 		                    +'<a href="#" class="list-group-item categary-item">冰饮系列<span class="left-arrow">></span></a>'
// 		                    +'<a href="#" class="list-group-item categary-item">杯系列<span class="left-arrow">></span></a>');
                 },
                 complete: function(XMLHttpRequest, textStatus){
                 },
                 error: function(){
                 }         
              });
        	}
        	
            var flag = false;
            $("#categaryBtn").click(function () {
                if (!flag) {
                    $("#contentBox").addClass("all-to-right");
                    $("#rightDiv").animate({
                        position: "relative",
                        left: "50%"
                    }, 500);
                    $("#leftDiv").animate({
                        position: "absolute",
                        left: "0px"
                    }, 500);
                    //$("#rightDiv").addClass("right-to-right");
                    //$("#contentBox").addClass("all-to-right");
                    //$("#leftDiv").addClass("left-to-right");
                    flag = true;
                }
                else {
                    $("#rightDiv").animate({
                        left: "0px",
                        position: "static"
                    }, 500);

                    $("#leftDiv").animate({
                        left: "-50%"
                    }, 500);
                    $("#contentBox").removeClass("all-to-right");
                    //$("#contentBox").removeClass("all-to-right");
                    //$("#rightDiv").removeClass("right-to-right");

                    //$("#leftDiv").removeClass("left-to-right");
                    flag = false;
                }

            });

            var con = document.querySelector('#con');
            var msnry;
            imagesLoaded(con, function () {
                msnry = new Masonry(con, {
                    "gutter": 0
                });
            });
        });
        //获取商品
        function getGoods(goodtype){
        	$.ajax({
                type:"GET",
                url:"/get_goods_by_goodtype",
                data:{goodtype:goodtype},
                datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                beforeSend:function(){
                },
                success:function(data){
                	$('#con').html('');
                	for(var i=0;i<data.result.length;i++){
                		$('#con').append('<div class="box">'
                                +'<div class="box_img">'
                                +'<a href="'+data.result[i].address+'" data-lightbox="1" data-title="哈哈哈哈">'
                                    +'<img src="'+changeImg(data.result[i].address,"_1")+'" />'
                                +'</a>'
                                +'<p>商品名称:'+data.result[i].name+'</p>'
                                +'<p>价格:'+data.result[i].price+'元</p>'
                                +'<p>剩余数量:'+data.result[i].num+'</p>'
                                +'<button type="button" class="btn btn-default btn-minus" aria-label="Left Align" onclick="minus('+data.result[i].id+');">'
                                +'<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>'
                                +'</button>'
                                +'<input id="input_'+data.result[i].id+'" class="number" type="text" style="width:30px;" value="0"  onblur="check('+data.result[i].id+','+data.result[i].num+');"/>'
                                +'<button id="btn_'+data.result[i].id+'" type="button" class="btn btn-default btn-plus" aria-label="Left Align" onclick="add('+data.result[i].id+','+data.result[i].num+');">'
                                +'<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>'
                                +'</button>'
                                +'<input title="添加购物车" type="image" src="../res/img/shopping_cart.png" onclick="addShopping_cart(\''+data.result[i].address+'\','+data.result[i].id+','+data.result[i].num+',\''+data.result[i].name+'\',\''+data.result[i].price+'\');"/>'
                                +'</div>'
                                +'</div>');
                	}
                	var con = document.querySelector('#con');
                    var msnry;
                    imagesLoaded(con, function () {
                        msnry = new Masonry(con, {
                            "gutter": 0
                        });
                    });
                },
                complete: function(XMLHttpRequest, textStatus){
                },
                error: function(){
                }         
             });
        } 
        
        //获取所有的商品
        function getAllGoods(){
        	$.ajax({
                type:"GET",
                url:"/get_all_goods",
                data:{},
                datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                beforeSend:function(){
                },
                success:function(data){
                	for(var i=0;i<data.result.length;i++){
                		$('#con').append('<div class="box">'
                                +'<div class="box_img">'
                                +'<a href="'+data.result[i].address+'" data-lightbox="1" data-title="哈哈哈哈">'
                                    +'<img src="'+changeImg(data.result[i].address,"_1")+'" />'
                                +'</a>'
                                +'<p>商品名称:'+data.result[i].name+'</p>'
                                +'<p>价格:'+data.result[i].price+'元</p>'
                                +'<p>剩余数量:'+data.result[i].num+'</p>'
                                +'<button type="button" class="btn btn-default btn-minus" aria-label="Left Align" onclick="minus('+data.result[i].id+');">'
                                +'<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>'
                                +'</button>'
                                +'<input id="input_'+data.result[i].id+'" class="number" type="text" style="width:30px;" value="0"  onblur="check('+data.result[i].id+','+data.result[i].num+');"/>'
                                +'<button id="btn_'+data.result[i].id+'" type="button" class="btn btn-default btn-plus" aria-label="Left Align" onclick="add('+data.result[i].id+','+data.result[i].num+');">'
                                +'<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>'
                                +'</button>'
                                +'<input title="添加购物车" type="image" src="../res/img/shopping_cart.png" onclick="addShopping_cart(\''+data.result[i].address+'\','+data.result[i].id+','+data.result[i].num+',\''+data.result[i].name+'\',\''+data.result[i].price+'\');"/>'
                                +'</div>'
                                +'</div>');
                	}
                	var con = document.querySelector('#con');
                    var msnry;
                    imagesLoaded(con, function () {
                        msnry = new Masonry(con, {
                            "gutter": 0
                        });
                    });
                },
                complete: function(XMLHttpRequest, textStatus){
                },
                error: function(){
                }         
             });
        } 
        
        function add(id,num){
        	var number=$('#input_'+id).val();
        	var number_=parseInt(number);
        	if(number>=num){
        		$('#btn_'+id).attr("disabled",true);
        	}else{
	        	$('#input_'+id).val(number_+1);
	        	if((number_+1)==num){
	        		$('#btn_'+id).attr("disabled",true);
	        	}
        	}
        }
        
        function minus(id){
        	var num=$('#input_'+id).val();
        	var number=parseInt(num);
        	if(number>0){
	        	$('#input_'+id).val(number-1);
	        	$('#btn_'+id).attr("disabled",false);
        	}
        }
        
        //验证输入框的数量是超载
        function check(id,surplusnum){
        	var value=$('#input_'+id).val();
        	if(value>surplusnum){
        		$('#input_'+id).val(0);
        		alert('您购买的数量超过了剩余商品数量:'+surplusnum);
        	}
        }
        
        function addShopping_cart(img_address,id,surplusnum,name,price){  //surplusnum剩余数量
        	var num=$('#input_'+id).val();  //获取输入框中购买的数量
        	if(parseInt(num)==0){
    			alert('您未选择商品数量，请选择后再进行添加购物车！');
    		}else{
        	if(confirm("是否加入购物车？")){
        		if(store.get('xugao')==null){
		        	var xugao={
		        			xugao:{
		        				address:{},
		        				orderinfo:{
		        					address:{}, //订单地址
		        					serialnumber:{},  //订单号
		        					userId:{},  //用户id
		        					goods:[],  //商品id 购买数量 商品单价
		        					status:{}  //订单状态
		        				}
		        			},
		        			phnone:{}
		        	};
		        	xugao.xugao.orderinfo.goods.push({id:id,surplusnum:surplusnum,name:name,price:price,buynumber:parseInt(num),img_address:img_address});
		        	store.set( 'xugao' , xugao);
        		}else{
        			//如果购物车中已经有这个商品了，就不能再添加了
        			var xugao=store.get('xugao');
        			var goods=xugao.xugao.orderinfo.goods;
        			var flag=true;
        			for(var i=0;i<goods.length;i++){
        				console.info(goods[i].id+':'+id);
        				if(parseInt(goods[i].id)==parseInt(id)){
        					if(confirm('您好,您的购物车中已经存在此商品,可以前往购物车中操作!')){
        						flag=false;
        						window.location="gouwuche.jsp";
        					}
        					break;
        				}
        			}
        			if(flag==true){
	        			xugao.xugao.orderinfo.goods.push({id:id,surplusnum:surplusnum,name:name,price:price,buynumber:parseInt(num),img_address:img_address});
			        	store.set( 'xugao' , xugao);
        			}
        		}
        		}
        	}
        }
        function GetQueryString(name) {
        	   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
        	   var r = window.location.search.substr(1).match(reg);
        	   if (r!=null) return (r[2]); return null;
        }
        function changeImg(img,add) {
        	if(!img||img.length<=0)
            {
                return img;
            }
        	var pos = img.lastIndexOf(".");
        	var strFileName=img.substr(0,pos);
        	var FileExt=img.replace(/.+\./,"");
        	return strFileName+add+"."+FileExt;
        }
    </script>

</body>
</html>
