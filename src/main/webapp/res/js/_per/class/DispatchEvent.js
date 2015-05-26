var DispatchEvent={
		mathodMap:{},
		dispatchEvent:function (event){
			var list = this.mathodMap[event.type];
			if(list==null||!(list instanceof Array))
				return ;
			for (var i in list)
			{
				if(typeof list[i]=="function")
				{
					list[i](event);
				}
			}
		},
		addEventListener:function( type, method)
		{
			if(!type)
				return;
			var list = this.mathodMap[type];
			if(list==null||!(list instanceof Array))
			{
				list = []; 
				this.mathodMap[type]= list;
			}
			if(method&&typeof method=="function")
			{
				list.push(method);
			}
//			mathodMap.put(type, method);
		},
		removeListener:function(type)
		{
			if(!type)
				return null;
			var list = this.mathodMap[type];
			if(list==null)
			{
				return null;
			}
			this.mathodMap[type]=undefined;
			return list;
		},
		 removeListener:function(method)
		{
			 var arr=[];
			for(var type in this.mathodMap)
			{
				var list=this.mathodMap[type];
				if(list&&!(list instanceof Array))
				{
					for(var i in list)
					{
						var del=list.splice(i,1);
						if(del)
						{
							arr.push(del);
						}
					}
				}
			}
			return arr.length>0?arr:null;
		}
}
