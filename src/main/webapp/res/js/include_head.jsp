<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> --%>
<meta http-equiv="X-UA-Compatible" content="IE=10" >
<script  type="text/javascript" src="/js/load.js" charset="utf-8"></script>

<script type="text/javascript">

<!-- jquery -->
loadJsCssFileRelToLoadJs("jquery-1.11.2/jquery.js","js");

<!-- bootstrap -->
loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/css/bootstrap.css","css");
loadJsCssFileRelToLoadJs("bootstrap-3.3.0-dist/dist/js/bootstrap.min.js","js");

<!-- bootstrap -->

<!-- jquery-ui -->
// loadJsCssFile("/styles/smoothness/jquery-ui-1.8.16.custom.css","css");
// loadJsCssFileRelToLoadJs("jquery-ui-1.11.2/jquery-ui.min.css","css");
// loadJsCssFileRelToLoadJs("jquery-ui-1.11.2/jquery-ui.min.js","js");
<!-- select2 -->
loadJsCssFileRelToLoadJs("select2/dist/css/select2.min.css","css");
loadJsCssFileRelToLoadJs("select2/dist/js/select2.min.js","js");
loadJsCssFileRelToLoadJs("select2/dist/js/i18n/zh-CN.js","js");
<!--pinyin-->
loadJsCssFileRelToLoadJs("pinyin/mod.udatas.js","js");
// loadJsCssFile("javascript/jquery.dataTables.min.js","js");


<!-- dataTables -->
loadJsCssFileRelToLoadJs("DataTables/media/css/jquery.dataTables.min.css","css");
loadJsCssFileRelToLoadJs("DataTables/media/js/jquery.dataTables.min.js","js");
// loadJsCssFile("javascript/jquery.dataTables.min.js","js");

<!-- touch-punch -->
loadJsCssFileRelToLoadJs("jquery-ui-touch-punch/jquery.ui.touch-punch.min.js","js");
loadJsCssFileRelToLoadJs("jQuery-Timepicker-Addon/src/jquery-ui-sliderAccess.js","js");





<!-- jQuery UI Bootstrap -->
//loadJsCssFileRelToLoadJs("jquery-ui-1.11.2/jquery-ui_2.css","css");
loadJsCssFileRelToLoadJs("jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.10.0.custom.css","css");
loadJsCssFileRelToLoadJs("jquery-ui-bootstrap/assets/css/font-awesome.min.css","css");
loadJsCssFileRelToLoadJs("jquery-ui-bootstrap/assets/js/jquery-ui-1.10.0.custom.min.js","js");
/*--[if IE 7]>
<link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css">
<![endif]-*/
/*--[if lt IE 9]>
<link rel="stylesheet" type="text/css" href="css/custom-theme/jquery.ui.1.10.0.ie.css"/>
<![endif]--*/
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
if(typeof Util =="undefined")
{
	loadJsFileRelToLoadJs_async("_per/ex/Utils.js",function(){
			if(Util.checkIE())// if lt IE 9
			{
				console.info(Util.getBrowserVersion());
				if(Util.getBrowserVersion()=="msie ")
				{
					loadJsCssFile("http://html5shim.googlecode.com/svn/trunk/html5.js","js");
				}
			}
		});
}


<!-- Timepicker -->
loadJsCssFileRelToLoadJs("jQuery-Timepicker-Addon/src/jquery-ui-timepicker-addon.css","css");
loadJsCssFileRelToLoadJs("jQuery-Timepicker-Addon/src/jquery-ui-sliderAccess.js","js");
loadJsCssFileRelToLoadJs("jQuery-Timepicker-Addon/src/jquery-ui-timepicker-addon.js","js");
loadJsCssFileRelToLoadJs("jQuery-Timepicker-Addon/src/i18n/jquery-ui-timepicker-zh-CN.js","js");

// highcharts-4.0.js
// highcharts-more.js
loadJsCssFileRelToLoadJs("highcharts-4.0.js","js");
loadJsCssFileRelToLoadJs("highcharts-more.js","js");

<!-- keyboard widget css & script (required) -->
loadJsCssFileRelToLoadJs("Keyboard-master/css/keyboard.css","css");
loadJsCssFileRelToLoadJs("Keyboard-master/js/jquery.keyboard.js","js");
<!-- keyboard extensions (optional) -->
loadJsCssFileRelToLoadJs("Keyboard-master/js/jquery.mousewheel.js","js");
loadJsCssFileRelToLoadJs("Keyboard-master/js/jquery.keyboard.extension-typing.js","js");
loadJsCssFileRelToLoadJs("Keyboard-master/js/jquery.keyboard.extension-autocomplete.js","js");

var global_key_p={
		openOn   : null,
		layout: 'custom',
		  display: {
		        'alt'   : '\u2666', // Diamond
		        'accept'  : '确认',
		        'enter'  : '回车',
		        'bksp' : '删除'
		 },
		customLayout: {
			'normal': [
				'1 2 3 4 5 6 7 8 9 0 - = {bksp}',
				'q w e r t y u i o p [ ] \\',
				'a s d f g h j k l ; \' {enter}',
				'z x c v b n m , . / {shift}',
				'{space} {left} {right} {sp:.2} {del} {accept}'
			],
			'shift': [
				'! @ # $ % ^ & * ( ) _ + {bksp}',
				'Q W E R T Y U I O P { } |',
				'A S D F G H J K L : " {enter}',
				'Z X C V B N M < > ? {shift}',
				'{accept} {space} {left} {right} {sp:.2} {del} {accept}'
			]
		}};



loadJsCssFileRelToLoadJs("pathfinding-browser.min.js","js");
loadJsCssFileRelToLoadJs("async.min.js","js");
loadJsCssFileRelToLoadJs("raphael-min.js","js");
	
loadJsCssFileRelToLoadJs("_per/ex/RaphaelUtil.js","js");
loadJsCssFileRelToLoadJs("_per/class/View.js","js");
loadJsCssFileRelToLoadJs("_per/class/CZ_Element.js","js");
loadJsCssFileRelToLoadJs("_per/class/DispatchEvent.js","js");
loadJsCssFileRelToLoadJs("_per/ex/TimerTask.js","js");
loadJsCssFileRelToLoadJs("_per/ex/cz_select.js","js");
loadJsCssFileRelToLoadJs("_per/ex/DataTablesUtils.js","js");

loadJsCssFileRelToLoadJs("../global.js.action","js");
loadJsCssFileRelToLoadJs("ajaxfileupload.js","js");
loadJsCssFileRelToLoadJs("howler.min.js","js");
loadJsCssFileRelToLoadJs("messenger/build/js/messenger.min.js","js");
loadJsCssFileRelToLoadJs("messenger/build/js/messenger-theme-future.js","js");
loadJsCssFileRelToLoadJs("messenger/build/css/messenger.css","css");
loadJsCssFileRelToLoadJs("messenger/build/css/messenger-theme-future.css","css");
loadJsCssFileRelToLoadJs("messenger/build/css/messenger-theme-future.css","css");
loadJsCssFileRelToLoadJs("bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.min.css","css");
loadJsCssFileRelToLoadJs("bootstrap-switch/dist/js/bootstrap-switch.min.js","js");
loadJsCssFileRelToLoadJs("lightbox/lightbox-2.6.min.js","js");
loadJsCssFileRelToLoadJs("lightbox/lightbox.css","css");
</script>
