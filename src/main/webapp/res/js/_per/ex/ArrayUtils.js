ArrayUtils=typeof ArrayUtils=='undefined'?{}:ArrayUtils;
Array.prototype.toStrings=function(){
	var str='[';
	var f=null;
	if(arguments.length>0&&typeof arguments[0] =='function')
	{
		f=arguments[0];
	}
	else
	{
		f=function(target){
			return target.toString();
		};
	}
	for(var i=0;i<this.length;i++)
	{
		str+=f(this[i])+",";
	}
	str=str.replace(/(,$)/,"")+"]";
	return str;
};
/**
 * @author hjl
 *
 */
Array.prototype.getElementByAttr=function(attr){
	if(attr===null)
	{
		return null;
	}
	var res=[];
	for(var i=0;i<this.length;i++)
	{
		var t=this[i];
		var tag=true;
		for(var k in attr)
		{
			if(typeof attr[k] !='function')
			{
				if(t[k]!=attr[k])
				{
					tag=false;
					break;
				}
			}
		}
		if(tag===true)
		{
			res.push(t);
		}
	}
	return res.length>0?res:null;
};
Array.prototype.changeTo=function(paramMap)
{
	var _data=[]; 
	var data=this;
	for(var i in data)
	{
		_data.push(changeByMap(data[i],paramMap));
//		_data.push(cloneByMap(data[i],{title:'label',name:'value'}));
	}
	return _data;
}
changeByMap=function(obj,map)
{
	var _clone={};
	for(var k in map)
	{
		if(map[k]&&typeof map[k] =='string' )//bug {name:'xx',title:'xx'}-->{name:'title'}
		{
			_clone[k]=obj[map[k]];
		}
	}
	return _clone;
};
cloneByMap=function(obj,map,t)
{
	var _clone={};
	for(var k in obj)
	{
		if(map[k]&&typeof map[k] =='string' )//bug {name:'xx',title:'xx'}-->{name:'title'}
		{
			_clone[map[k]]=obj[k];
		}
		else if(t)
		{
			_clone[k]=obj[k];
		}
	}
	return _clone;
};

Object.defineProperty(Array.prototype,"getElementByAttr",{ 
//    value : "Prince Yu", 
    writable : false, 
    enumerable : false, 
    configurable :false 
}); 
Object.defineProperty(Array.prototype,"toStrings",{ 
//    value : "Prince Yu", 
	writable : false, 
	enumerable : false, 
	configurable :false 
}); 
Object.defineProperty(Array.prototype,"changeTo",{ 
//    value : "Prince Yu", 
	writable : false, 
	enumerable : false, 
	configurable :false 
}); 
function main()
{
//	[]
}
