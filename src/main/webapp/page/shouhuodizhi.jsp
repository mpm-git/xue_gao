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
    	loadJsCssFileRelToLoadJs("../css/shouhuodizhi.css");
    	//bootstrap
    	loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/css/bootstrap.css");
    	loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/js/bootstrap.min.js");
    	
    	//masonry
    	loadJsCssFileRelToLoadJs("masonry/dist/masonry.pkgd.min.js");
    	
    	//lightbox
    	loadJsCssFileRelToLoadJs("lightbox/css/lightbox.css");
    	loadJsCssFileRelToLoadJs("lightbox/lightbox-2.6.min.js");
    	
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
                        <a href="gouwuche.jsp">购物车</a>
                    </li>
                    <li>
                        <a href="#">注册</a>
                    </li>
                    <li>
                        <a href="#">登陆</a>
                    </li>
                    <li>
                        <a href="#" class="clicked">会员中心</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="text-center flow-box">
            <img class="flow-chart" src="/img/222.png" />
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
                        <select class="form-control">
                            <option>dsaf</option>
                            <option>dsaf</option>
                            <option>dsaf</option>
                            <option>dsaf</option>
                        </select>
                    </div>
                    <div class="col-xs-5">
                        <select class="form-control">
                            <option>dsaf</option>
                            <option>dsaf</option>
                            <option>dsaf</option>
                            <option>dsaf</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">详细地址</label>
                    <div class="col-xs-9">
                        <textarea class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">邮政编码</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">收件人姓名</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">联系方式</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" />
                    </div>
                </div>
                <div class="form-divider"></div>
                <div class="text-center">
                    <button id="comfirmAdd" type="button" class="btn">确认收货地址</button>
                </div>
                <div class="bottom-border">

                </div>
            </form>
        </div>
    </div>
</body>
</html>
