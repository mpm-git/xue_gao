var Element_style={
		transparentStyle:{
			'fill-opacity':0.0,
			'stroke-opacity': 0.0 // the border
		}
};
var CZ_Element=Class(NObject,{
	paper:null,
	view:null,
	element:null,
	element_element:null,
	element_text:null,
	finder:null,
	move_animation:null,
	isLogin:false,
	initialize:function()
	{
		this.param={
				src:{type:'image',data:'img/nurse.png'},//{type:"image",data:""}
				l:null,
				w:null,
				pos:{x:0,y:0},
				text:null,
				showMovePath:false,
				center:true,
				movePathStyle:{
					stroke:'green',
					'stroke-opacity': 1 // the border
				},
				click:function(mouseEvent,x,y){
					
				},
				dblclick:function(mouseEvent,x,y){
					
				},
				mouseover:function(){
//					console.info(this.data("src"));
//					console.info(arguments);
				},
				mouseout:function(){
//					console.info(arguments);
				},
				hoverIn:function(){
					
				},
				hoverOut:function(){
					
				}
		};
		
		if(arguments[0])
		{
			/**
			 * if() 参数判断
			 * */
			this.param=Util.extend(this.param,arguments[0]);
		}
		this.initFinder();
		//$(this.param.div).empty();
		//arguments;
	},
	initFinder:function(){
		this.finder = new PF.AStarFinder(
				{
					allowDiagonal: true,
				    dontCrossCorners: true
				});
	},
	loginIn:function(v)
	{
		if(v&&v instanceof View)
		{
			this.view=v;
			var paper =this.view.getPaper();
			var p=this.view.getPos(this.param.pos);
			var l=this.view.getLength(this.param.l);
			var w=this.view.getLength(this.param.w);
			if(this.param.src&&this.param.src.type=="image")
			{
				if(!l) l=10;
				if(!w) w=10;
				this.element=this.view.getPaper().set();
				this.element_element=this.view.getPaper().image(this.param.src.data,p.x , p.y, w,l);
				this.element.push(this.element_element);
			}
			else if (this.param.src&&this.param.src.type=="path")
			{
//				var box=this.getBBox();
				var box=Raphael.pathBBox(this.param.src.data.path);
				w=w?w:box.width;
				l=l?l:box.height;
				var sx=w/box.width;
				var sy=l/box.height;
				var x=box.x;
				var y=box.y;
//				var matrix =Raphael.matrix(1, 0, 0, 1,p.x-x-(w+0.0)/2,p.y-y-(l+0.0)/2);
				var matrix =Raphael.matrix(1, 0, 0, 1,p.x-x,p.y-y);
//				scale(sx, sy, [cx], [cy]);
				matrix.scale(sx, sy,x, y);
//				matrix.scale(sx, sy,p.x-box.x,p.y-box.y);
//				console.info(matrix.split());
				var newPath=Raphael.mapPath(this.param.src.data.path, matrix);
//				Paper.path([pathString])
				this.element=this.view.getPaper().set();
				this.element_element=this.view.getPaper().path(newPath);
				this.element.push(this.element_element);
				if(this.param.src.data.style)
					this.element.attr(this.param.src.data.style);
//				console.info(this.element.getBBox());
//				this.element=this.view.getPaper().path(Raphael.mapPath(this.param.src.data,Raphael.matrix(1, 0, 0, 1, 200, 200)));
				
//				this.element=this.view.getPaper().mapPath(this.param.src.data, Raphael.matrix(1, 0, 0, 1, 200, 200));
			}
			for(var i in this.param)
			{
				if(typeof(this.param[i])!="function")
				{
					this.element=this.element.data(i,this.param[i]);
				}
			}
			var b=this.element.getBBox();
			this.init_element_box={
					x:b.x,
					y:b.y,
					x2:b.x2,
					y2:b.y2,
					width:b.width,
					height:b.height
					};
			var tt={transform:'t-'+this.getOffset().offX+',-'+this.getOffset().offY};
			this.element_element.attr(tt);
			if(this.param.text)
			{
				var render=typeof this.param.text.render=='function'?this.param.text.render:function(text)
					{
						return text.name;
					};
				this.element_text= this.view.getPaper().text(p.x-this.getOffset().offX+this.realWidth()/2, p.y-this.getOffset().offY,render(this.param.text));
				if(this.param.text.style)
				{
					this.element_text.attr(this.param.text.style);
				}
				//var text= this.view.getPaper().print(p.x, p.y, this.param.text.name, this.view.getPaper().getFont("Museo"), 30).attr({fill: "#fff"});
				this.element.push(this.element_text);
			}
			this.element.click(Util.proxy(this.param.click,this));
			this.element.dblclick(Util.proxy(this.param.dblclick,this));
			this.element.mouseover(Util.proxy(this.param.mouseover,this));
			this.element.mouseout(Util.proxy(this.param.mouseout,this));
			this.element.hover(Util.proxy(this.param.hoverIn,this),Util.proxy(this.param.hoverOut,this));
			this.view.addChild(this.element);
			this.toFront();
			isLogin=true;
		}
//		console.info("this.init_element_box:");
//		console.info(this.element.getBBox());
//		console.info(this.init_element_box);
	},
	getBBox:function(){
		if(this.element)
		{
			var n= this.view.getNodeSize();
			var d=this.element.getBBox();
			return  {
				x:parseInt(d.x/(n+0.0)),
				y:parseInt(d.y/(n+0.0)),
				x2:parseInt(d.x2/(n+0.0)),
				y2:parseInt(d.y2/(n+0.0)),
				width:parseInt(d.width/(n+0.0)),
				height:parseInt(d.height/(n+0.0))
				};
		}
		return null;
	},
	stopMove:function()
	{
		if(this.element)
		{
			if(this.move_animation)
				this.element.stop(this.move_animation);
		}
	},
	/**
	 * {
	 * speed:
	 * callback:
	 * }
	 * 
	 * */
	moveTo:function(to,option)
	{
		var center=this.getRealLocation();
		this.moveFromTo(this.toGridCoordinate(center.x,center.y),to,option);
	},
	moveFromTo:function(from,to,option)
	{
		//console.info(this.nid+":moveFromTo["+from.x+","+from.y+"] to["+to.x+","+to.y+"]");
		var path=this.getMovePath(from,to);
		if(path)
		{
			option.from=0;
			option.to=1;
			option.along=true;
			this.moveByPath(path,option);
		}
		else
		{
			if(option.callBack)
			{
				option.callBack();
			}
		}
	},
	toGridCoordinate: function(pageX, pageY) {
		return {
			x:Math.floor(pageX / this.view.getNodeSize()),
			y:Math.floor(pageY / this.view.getNodeSize())
		};
	},
	/**
	 * 
	 * {
	 * 	speed:12,
	 * 	callBack:function(){}
	 * }
	 * */
	_move:function(to,option)
	{
		option=option||{};
		if(this.element)
		{
			this.element.stop(this.move_animation);
			var box=this.init_element_box;
			var _to=this.view.getPos(to);
			var _ms;
			var _speed=option.speed?option.speed:1;
			var _x=(_to.x-box.x);
			var _y=(_to.y-box.y);
			var z=Math.sqrt(_x*_x+_y*_y);
			if(_speed)
			{
				_ms=parseInt(z/_speed/this.view.getNodeSize());
			}
			_x=_x-this.getOffset().offX;
			_y=_y-this.getOffset().offY;
			var ttf="t"+_x+","+_y;
			if(option.callBack)
				this.move_animation=Raphael.animation({transform:ttf}, _ms*1000,'linear',option.callBack);
			else
				this.move_animation=Raphael.animation({transform:ttf}, _ms*1000);
			this.element.animate(this.move_animation);
		}
		
	},
	/**
	 * {
	 * from:
	 * to:
	 * speed:
	 * callback:
	 * }
	 * 
	 * */
	moveByPath:function(path,option)
	{
		if(this.element)
		{
			if(this.move_animation)
				this.element.stop(this.move_animation);
			if(this.move_by_path)
				this.move_by_path.remove();
			var _path=RaphaelUtil.getPath(path,this.view.getNodeSize(),this.view.getNodeSize());
			this.move_by_path=this.view.getPaper().path(_path);
			if(this.param.showMovePath)
			{
				this.move_by_path.attr(this.param.movePathStyle);
			}
			else
			{
				this.move_by_path.attr(Element_style.transparentStyle);
			}
			var e=this.move_by_path;
			var from=option.from?option.from:0;
			var to=option.from?option.to:1;
			var l=this.move_by_path.getTotalLength();
			this.element.data("move_by_path_data", {
				initLocation:this.getInitRealLocation(),
				offset:this.getOffset(),
				path:this.move_by_path,
				pathEndPoint:this.move_by_path.getPointAtLength(to*l),
				pathStartPoint:this.move_by_path.getPointAtLength(from * l),
				along:option.along
			});
			this.view.getPaper().customAttributes.move_by_path=function(to){
				var data=this.data("move_by_path_data");
				var initLocation= data.initLocation;
				var path= data.path;
				var along= data.along;
				var offset= data.offset;
				var pathStartPoint= data.pathStartPoint;
				var pathEndPoint= data.pathEndPoint;
				var p = path.getPointAtLength(to * path.getTotalLength());
				if(!p)
					p=pathEndPoint;
				var _y;
				var _x;
				if(along==null||along)
				{
					_y=(p.y-initLocation.y);
					_x=(p.x-initLocation.x);
				}
				else
				{
					_y=p.y-pathStartPoint.y-offset.offY;
					_x=p.x-pathStartPoint.x-offset.offX;
				}
				var mat=Raphael.matrix(1, 0, 0, 1, _x, _y);
				var ttf=mat.toTransformString();
				return {
					transform: ttf 
				};
			};
			//(e,l,startPoint,option,this)
			this.element.attr({move_by_path:from});
			
			var _speed=option.speed?option.speed:1;
			var _ms=1;
			if(_speed)
			{
				_ms=(l/_speed+0.0)/this.view.getNodeSize();
			}
			var _callBakc=function(path){
				return function(){
					path.remove();
					if(option.callBack)
					{
						option.callBack();
					}
				};
			}(this.move_by_path);
			this.move_animation=Raphael.animation({move_by_path:to}, _ms*1000,'linear',_callBakc);
			this.move_animation_=Raphael.animation({move_by_path:to}, _ms*1000,'linear');
//			this.move_animation=Raphael.animation({move_by_path:to}, _ms*1000,'linear',_callBakc);
//			this.element.animate(this.move_animation);
			this.element.forEach(function(e,index){
				if(index==0)
				{
					e.animate(this.move_animation);
				}
				else
				{
					e.animate(this.move_animation_);
				}
			},this);
		}
		
	},
	hide:function()
	{
		if(this.element)
		{
			this.element.hide();
		}
	},
	show:function()
	{
		if(this.element)
		{
			this.element.show();
		}
	},
	glow:function()
	{
		if(this.element)
		{
//			console.info(this.element);
//			console.info(this.element.glow());
//			this.element=this.element.glow();
		}
	},
	toBack:function()
	{
		if(this.element)
		{
			this.element.toBack();
		}
	},
	toFront:function()
	{
		if(this.element)
		{
			this.element.toFront();
		}
	},
	realWidth:function(){
		return this.element.getBBox().width;
	},
	realHeigth:function(){
		return this.element.getBBox().height;
	},
	getRealLocation:function(){
		var b=this.element_element.getBBox();
		var x=b.x;
		var y=b.y;
		if(this.param.center)
		{
			x=(b.x2-b.width+b.x2)/2;
			y=(b.y2-b.height+b.y2)/2;
		}
		return {x:x,y:y};
	},
	getInitRealLocation:function(){
		var b=this.init_element_box;
		var x=b.x;
		var y=b.y;
		if(this.param.center)
		{
			x=(b.x+b.x2)/2;
			y=(b.y+b.y2)/2;
		}
		return {x:x,y:y};
	},
	showCenter:function(){
		if(isLogin)
		{
			var b=this.element.getBBox();
			var center=this.getRealLocation();
			var r=this.view.getPaper().rect(b.x, b.y, b.width,b.height);
			var c=this.view.getPaper().circle(center.x, center.y, 2);
			c.attr({
				fill: "red",
				stroke: "none",
				'fill-opacity':1.0,
				'stroke-opacity': 1.0 // the border
			});
			this.element.push(c,r);
		}
		else
		{
			throw new Error("-1","need login first");  
		}
		
	},
	/**
	 * 获取元素的偏移量
	 * */
	getOffset:function()
	{
		var b=this.element.getBBox();
		if(this.param.center)
		{
//			return {offX:this.view.getNodeSize()/2,offY:this.view.getNodeSize()/2};
			return {offX:b.width/2-this.view.getNodeSize()/2,offY:b.height/2-this.view.getNodeSize()/2};
		}
		return {offX:0,offY:0};
	},
	loginOut:function()
	{
		if(this.element)
		{
			if(this.view.getChildren())
			{
				var tag=this.view.getChildren().exclude(this.element);
				this.element.remove();
				isLogin=false;
				return tag
			}
		}
	},
	getMovePath:function(from,to)
	{
		if(this.view.checkPoint(from)&&this.view.checkPoint(to))
		{
			var path = this.finder.findPath(from.x, from.y, parseInt(to.x), parseInt(to.y), this.view.getFinderGrid().clone());
			if(path&&path instanceof Array&&path.length>0)
				return this.buildSvgPath(path);
		}
		return null;
	},
	buildSvgPath: function(path) {
		var i, strs = [], size = this.view.getNodeSize();

//		strs.push('M' + path[0][0]  + ' ' +
//				path[0][1] );
//		for (i = 1; i < path.length; ++i) {
//			strs.push('L' + path[i][0]  + ' ' +
//					path[i][1]);
//		}
		strs.push('M' + (path[0][0] * size + size / 2)/size + ' ' +
				(path[0][1] * size + size / 2)/size);
		for (i = 1; i < path.length; ++i) {
			strs.push('L' + (path[i][0] * size + size / 2)/size + ' ' +
					(path[i][1] * size + size / 2)/size);
		}

		return strs.join('');
	}
});
