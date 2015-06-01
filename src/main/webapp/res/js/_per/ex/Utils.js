//Object.prototype.new_=function(showtime)
//{
//	var F=function(){};
//	F.prototype=this;
//	return new F;
//}

Util = "undefined" == typeof (Util) ? {} : Util;
Util.BROWSER_NAME = (function () {
	var name = "";
	var ua = navigator.userAgent.toLowerCase();
	if (ua.indexOf("opera") != -1) {
		name = "opera";
	} else if (ua.indexOf("msie") != -1) {
		name = "msie";
	} else if (ua.indexOf("safari") != -1) {
		name = "safari";
	} else if (ua.indexOf("mozilla") != -1) {
		if (ua.indexOf("firefox") != -1) {
			name = "firefox";
		} else {
			name = "mozilla";
		}
	}
	return name;
})();

Util.checkIE = function () {
	return  document.all ? true : false;
}
Util.proxy =function( fn, proxy){
	return function(){
		var target= proxy || this;
		if(!proxy)
		{
			arguments=arguments||[];
			Array.prototype.push.apply(arguments,this);
		}
		return fn.apply(target, arguments);
	};
};
Util.cloneByMap=function(obj,map)
{
	var _clone={};
	for(var k in obj)
	{
		if(map[k]&&typeof map[k] =='string' )//bug {name:'xx',title:'xx'}-->{name:'title'}
		{
			_clone[map[k]]=obj[k];
		}
		else
		{
			_clone[k]=obj[k];
		}
	}
	return _clone;
};
Util.clone = function (obj) {
	// Handle the 3 simple types, and null or undefined
	if (null == obj || "object" != typeof obj)
		return obj;

	// Handle Date
	if (obj instanceof Date) {
		var copy = new Date();
		copy.setTime(obj.getTime());
		return copy;
	}

	// Handle Array
	if (obj instanceof Array) {
		var copy = [];
		for (var i = 0, len = obj.length; i < len; ++i) {
			copy[i] = Util.clone(obj[i]);
		}
		return copy;
	}

	// Handle Object
	if (obj instanceof Object) {
		var copy = {};
		for (var attr in obj) {
			if (obj.hasOwnProperty(attr))
				copy[attr] = Util.clone(obj[attr]);
		}
		return copy;
	}
	throw new Error("Unable to copy obj! Its type isn't supported.");
}
Util.changeMenusToTree=function(menu_obj_list,ids)
{
	var res=[];
	var tree=new Object();
	var map=new Object();
	var clone= Util.clone(menu_obj_list);
	for(var i in clone)
	{
		var node=clone[i];
		map[node.id]=node;
	}
	for(var _i in ids)
	{
		var j=ids[_i];
		if(map[j]&&map[j].parent_id)
		{
			var p=map[j].parent_id;
			if(!tree[p])
			{
				tree[p]={text:map[p].name,children:[]};
			}
			tree[p].children.push({id:map[j].id,url:map[j].url,text:map[j].name,img:map[j].img});
		}
	}
	for(var n in tree)
	{
		res.push(tree[n]);
	}
	return res;
}
Util.turnToId=function(id)
{
	if (typeof id == "string")
	{
		var view_div=document.getElementById(id);
		if(view_div)
		{
			window.location.href=window.location.pathname+"#"+id;
		}
	}
}
Util.changeMenusToTree2=function(menu_obj_list,ids)
{
	console.info(menu_obj_list,ids);
	var res=[];
	var tree=new Object();
	var map=new Object();
	var clone= Util.clone(menu_obj_list);
	for(var i in clone)
	{
		var node=clone[i];
		map[node.id]=node;
	}
	for(var _i in ids)
	{
		if(map[ids[_i]])
			map[ids[_i]]['_checked']=true;
	}
//	console.info(map);
	for(var j in map)
	{
		//var j=ids[_i];
		if(map[j]&&map[j].parent_id)
		{
			var p=map[j].parent_id;
			if(!tree[p])
			{
				tree[p]={text:map[p].name,children:[]};
			}
			tree[p].children.push({checked:map[j]._checked,id:map[j].id,url:map[j].url,text:map[j].name,img:map[j].img});
		}
	}
	for(var n in tree)
	{
		res.push(tree[n]);
	}
	return res;
}
Util.extend=function (destination, source) {
	destination = destination || {};
	if (source) {
		for (var property in source) {
			var value = source[property];
			if (value !== undefined) {
				destination[property] = value;
			}
		}

		/**
		 * IE doesn't include the toString property when iterating over an object's
		 * properties with the for(property in object) syntax.  Explicitly check if
		 * the source has its own toString property.
		 */

		/*
		 * FF/Windows < 2.0.0.13 reports "Illegal operation on WrappedNative
		 * prototype object" when calling hawOwnProperty if the source object
		 * is an instance of window.Event.
		 */

		var sourceIsEvt = typeof window.Event == "function"
			&& source instanceof window.Event;

		if (!sourceIsEvt
				&& source.hasOwnProperty && source.hasOwnProperty("toString")) {
			destination.toString = source.toString;
		}
	}
	return destination;
};
/**
 * "我的名字是{0},我来自{1},今年{1}岁。"
 * formatString("我的名字是{0},我来自{1},今年{1}岁。","leo","山东",18)
 * @returns 格式化字符串
 */
Util.formatString = function (str) {
	for (var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};
Util.getLocation=function(success)
{
	if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(success);
    } else {
        alert("您的浏览器不支持地理定位");
    }
};
Util.getBrowserVersion=function() {
	var browser = {};
	var userAgent = navigator.userAgent.toLowerCase();
	var s;
	(s = userAgent.match(/msie ([\d.]+)/))
	? browser.ie = s[1]
	: (s = userAgent.match(/firefox\/([\d.]+)/))
	? browser.firefox = s[1]
	: (s = userAgent.match(/chrome\/([\d.]+)/))
	? browser.chrome = s[1]
	: (s = userAgent.match(/opera.([\d.]+)/))
	? browser.opera = s[1]
	: (s = userAgent
			.match(/version\/([\d.]+).*safari/))
			? browser.safari = s[1]
	: 0;
			var version = "";
			if (browser.ie) {
				version = 'msie ' + browser.ie;
			} else if (browser.firefox) {
				version = 'firefox ' + browser.firefox;
			} else if (browser.chrome) {
				version = 'chrome ' + browser.chrome;
			} else if (browser.opera) {
				version = 'opera ' + browser.opera;
			} else if (browser.safari) {
				version = 'safari ' + browser.safari;
			} else {
				version = '未知浏览器';
			}
			return version;
}
Util.browser = {
		device: function () {
			var u = navigator.userAgent;
			return {
				trident: u.indexOf('Trident') > -1,
				presto: u.indexOf('Presto') > -1, // opera内核
				webKit: u.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
				gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, // 火狐内核
				deskWebkit: (u.indexOf("Android") == -1 && u.indexOf("Mobile") == -1),
				mobileWebKit: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/) || !!u.match(/.*Mobile.*/), // 是否为移动终端
				ios: !!u.match(/(i[^;]+\;(U;)? CPU.+Mac OS X)/), // ios终端
				android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, // android终端
				iPhone: u.indexOf('iPhone') > -1 && u.indexOf('Mac') > -1, // 是否为iPhoneD
				iPad: u.indexOf('iPad') > -1, // 是否iPad
				webApp: u.indexOf('Safari') == -1
				// 是否为App应用程序，没有头部与底部
			};
		}(),
		language: (navigator.browserLanguage || navigator.language).toLowerCase()
		//version: $.browser.version
};
