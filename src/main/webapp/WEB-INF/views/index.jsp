<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title></title>
    <script  src="../res/js/load.js"></script>
    <script  type="text/javascript">

    	loadJsCssFileRelToLoadJs("../css/header.css");
    	loadJsCssFileRelToLoadJs("../css/chanpinliebiao.css");
    	//<!-- bootstrap -->
    	loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/css/bootstrap.css");
    	loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/js/bootstrap.min.js");
    	
    	//<!-- lightbox -->
    	loadJsCssFileRelToLoadJs("lightbox/css/lightbox.css");
    	loadJsCssFileRelToLoadJs("lightbox/lightbox-2.6.min.js");
    	
    </script>
    <script type="text/javascript">
        function switchTab(tab,con) {
            for (var i = 1; i <= $(".tab_content").length; i++) {
                if ("tab" + i == tab)
                    $("#tab" + i).addClass("active");
                else
                    $("#tab" + i).removeClass("active");
                if ("con" + i == con)
                    $("#con" + i).css("display", "block");
                else
                    $("#con" + i).css("display", "none");
            }
            
        }
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
                <a class="navbar-brand">Project name</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class=" nav navbar-nav navbar-right">
                    <li>
                        <a class="clicked" href="index.jsp">首页</a>
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
<!--                         <a href="#">会员中心</a> -->
<!--                     </li> -->
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fuild" >
        <img style="width:100%;height:auto;" src="../res/img/index.jpg" />
        
    </div>

    
    
</body>
</html>
