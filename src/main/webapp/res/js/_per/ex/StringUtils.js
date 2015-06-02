String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim=function(){
	return this.replace(/(^\s*)/g,"");
}
String.prototype.rtrim=function(){
	return this.replace(/(\s*$)/g,"");
}
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
	} else {  
		return this.replace(reallyDo, replaceWith);  
	}  
}
String.prototype.toExcuteJson = function() {  
	return this.replace(/(")/g, "&#34;").replace(/(')/g, "&#39;");
}

Object.defineProperty(String.prototype,"trim",{ 
	writable : false, 
	enumerable : false, 
	configurable :false 
}); 
Object.defineProperty(String.prototype,"rtrim",{ 
	writable : false, 
	enumerable : false, 
	configurable :false 
});
Object.defineProperty(String.prototype,"ltrim",{ 
	writable : false, 
	enumerable : false, 
	configurable :false 
});
Object.defineProperty(String.prototype,"replaceAll",{ 
  writable : false, 
  enumerable : false, 
  configurable :false 
}); 
Object.defineProperty(String.prototype,"toExcuteJson",{ 
	writable : false, 
	enumerable : false, 
	configurable :false 
}); 
