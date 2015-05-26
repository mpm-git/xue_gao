var View_style={
		transparentStyle:{
			fill: 'none',
			stroke:'blue',
			'fill-opacity':1,
			'stroke-opacity': 0.1  // the border
		}
};
var View=Class(NObject,{
	data:null,
	paper:null,
	grid:null,
	children:null,
	finderGrid:null,
	initialize:function()
	{
		this.param={
				div:"",
				background:null,//{type:"image",data:""}
				cols:100,
				rows:100,
				nodeSize:10,
				isShowGrid:false,
				isShowBlockedGrid:true,
				blockedGrid:[],
				call_initGridComp:function(){},
				blockedGridStyle:{
					fill: 'grey',
					stroke:'blue',
					'fill-opacity':0.8,
					'stroke-opacity': 0.1 // the border
				},
				gridStyle:{
					fill: 'none',
					stroke:'blue',
					'fill-opacity':1,
					'stroke-opacity': 0.1 // the border
				},
				GPSMouseDown:function(){
					console.info(arguments);
				}
		};
		
		if(arguments[0])
		{
			/**
			 * if() 参数判断
			 * */
			this.param=Util.extend(this.param,arguments[0]);
		}
		this.initPaper();
		this.initFinderGrid();
		this.initGrid(Util.proxy(this.setBlockedGrid,this));
		var view_div =document.getElementById(this.param.div);
		view_div.style.visibility = "visible";  
		view_div.style.display = "block";  
		view_div.style.overflow= "hidden";
		view_div.style.width =(this.param.cols*this.param.nodeSize)+ "px";
		view_div.style.height =(this.param.rows*this.param.nodeSize)+ "px";
		//$(this.param.div).empty();
		//arguments;
	},
	initFinderGrid:function(){
		this.finderGrid = new PF.Grid(this.param.cols, this.param.rows);
	},
	initGrid:function(callBack)
	{
	
		if(!this.grid)
		{
			this.grid=[];
			var _self=this;
			var createColTask = function(colId) {
				return function(done) {
					_self.grid[colId] = [];
					for (var i = 0; i <_self.param.rows; ++i) {
						x = colId * _self.param.nodeSize;
						y = i * _self.param.nodeSize;

						rect = _self.paper.rect(x, y, _self.param.nodeSize, _self.param.nodeSize);
						if(_self.param.GPSMouseDown)
						{
							rect.click(function(){
								arguments[0].vX=Math.floor(arguments[0].offsetX/_self.getNodeSize());
								arguments[0].vY=Math.floor(arguments[0].offsetY/_self.getNodeSize());
								_self.param.GPSMouseDown.apply(this, arguments);
								
							});
						}
						if(_self.param.isShowGrid)
						{
							rect.attr(_self.param.gridStyle);
//							var fill=rect.attr("fill")
//							if(!fill||fill=="none")
//							{
//								rect.attr("fill","white");
//								rect.attr("fill-opacity",0);
//							}
						}
						else
							rect.attr(View_style.transparentStyle);
						_self._addClickAbility(rect);
						_self.grid[colId].push(rect);
					}
//					$stats.text(
//							'generating grid ' +
//							Math.round((rowId + 1) / numRows * 100) + '%'
//					);
					done(null);
				};
			};
			var sleep = function(done) {
				setTimeout(function() {
					done(null);
				}, 0);
			};

			var tasks = [];
			for (i = 0; i < this.param.cols; ++i) {
				tasks.push(createColTask(i));
				tasks.push(sleep);
			}

			async.series(tasks, function() {
				if (_self.param.call_initGridComp) {
					callBack();
					_self.children.toFront();
					_self.param.call_initGridComp();
				}
			});
		}
		
	},
	initPaper:function()
	{
		this.paper=Raphael(this.param.div);
		var numCols=this.param.cols;
		var numRows=this.param.rows;
		var nodeSize=this.param.nodeSize;
		this.paper.setSize(numCols * nodeSize, numRows * nodeSize);
		if(this.param.background)
		{
			if(this.param.background.type=="image")
			{
				if(this.param.background.data)
				{
					this.paper.image(this.param.background.data, 0, 0, numCols * nodeSize,numRows * nodeSize);
				}
			}
		}
		this.children=this.paper.set();
	},
	setBlockedGrid:function()
	{
		for(var i in this.param.blockedGrid)
		{
			var g=this.param.blockedGrid[i];
			if(g[0]<this.param.cols&&g[0]>=0&&g[1]<this.param.rows&&g[1]>=0)
			{
				this.setBlockedAt({x:g[0],y:g[1]},true);
			}
		}
	},
	setBlockedAt:function(p,tag)
	{
		this.finderGrid.setWalkableAt(p.x,p.y, !tag);
		if(this.param.isShowBlockedGrid&&tag)
			this.grid[p.x][p.y].attr(this.param.blockedGridStyle);
		else 
			this.grid[p.x][p.y].attr(View_style.transparentStyle);
		this._addClickAbility(this.grid[p.x][p.y]);
	},
	_addClickAbility:function(rect)
	{
		
		var fill=rect.attr("fill")
		if(!fill||fill=="none")
		{
			rect.attr("fill","white");
			rect.attr("fill-opacity",0);
		}
	},
	showGrid:function(isShow)
	{
		if(!this.param.isShowGrid&&isShow)
		{
			for(var i=0;i<this.grid.length;i++)
			{
				for(var j=0;j<this.grid[i].length;j++)
				{
					this.grid[i][j].attr(this.param.gridStyle);
				}
			}
		}else if(this.param.isShowGrid&&!isShow)
		{
			for(var i=0;i<this.grid.length;i++)
			{
				for(var j=0;j<this.grid[i].length;j++)
				{
					this.grid[i][j].attr(View_style.transparentStyle);
				}
			}
			this.param.isShowGrid=isShow;
		}
		
	},
	getPaper:function(){
		return this.paper;
	},
	getNodeSize:function(){
		return this.param.nodeSize;
	},
	getPos:function(p){
		return {x:p.x*this.param.nodeSize,y:p.y*this.param.nodeSize};
	},
	getLength:function(l){
		if(l)
			return l*this.param.nodeSize;
		return null;
	},
	getCols:function(){
		return this.param.cols;
	},
	getRows:function(){
		return this.param.rows;
	},
	getChildren:function(){
		return  this.children;
	},
	addChild:function(e){
		return this.children.push(e);
	},
	removeAllChildren:function(){
		console.info(this.children);
		if(this.children)
		{
			//this.children.clear();
			return this.children.remove();
		}
/*		if(this.paper.set())
		{
			return this.paper.set().clear();
		}
*/	},
	removeChildren:function(e){
		if(this.children)
			return this.children.exclude(e);
	},
	getFinderGrid:function()
	{
		return this.finderGrid;
	},
	getBlockedArray:function(tag)
	{
		var arr =[];
		if(tag==null)
			tag=true;
		for(var i in this.finderGrid.nodes)
		{
//			var y=[];
			for(var j in this.finderGrid.nodes[i])
			{
				var node=this.finderGrid.nodes[i][j];
				if(!(node.walkable&&tag))
					arr.push([node.x,node.y]);
			}
		}
		return arr;
	},
	moveByPath:function(path,option)
	{
		
	},
	checkPoint:function(p)
	{
		if(p.x<this.param.cols&&p.x>=0&&p.y<this.param.rows&&p.y>=0)
		{
			return true;
		}
		return false;
	},
	checkBlocked:function(p)
	{
		if(this.finderGrid&&p&&p.x>=0&p.y>=0)
		{
			return !this.finderGrid.isWalkableAt(p.x,p.y);
		}
		throw 'finderGrid is not exist or p is wrrong';
	},
	setGPSMouseDown:function(handler)
	{
		this.param.GPSMouseDown=handler;
	},
	setAttrAt:function(p,param)
	{
		if(this.checkPoint(p)&&param)
			this.grid[p.x][p.y].attr(param);
	}
});
