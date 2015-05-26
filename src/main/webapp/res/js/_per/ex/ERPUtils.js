ERPUtils = "undefined" == typeof (ERPUtils) ? {} : ERPUtils;
function showItemDetail(item_number)//显示Item的详细信息
{
	getItemTree(item_number,function(tree){
	});
}
/*
	获取合成件的树形结构
	注意：使用此方法的时候注意传入的item要是合成件（拥有子件）
 * 
 * */
function getItemTree(item_number,async,callBack,error)
{
	var _tree;
	$.ajax({
        type: 'GET',
        url: erp_api_service.TmpItemFullRel[0]+"?callback=?",
        dataType: 'json',
        cache: false,
        data:[{name:"t",value:item_number}],
        async: async?true:false,
        success: function(data) {
        	if(data&&data.objects)
        	{
        		var tmp=data.objects;
        		 if(typeof callBack=="function"&&tmp instanceof Array&&tmp.length>0)
        		{
        			 _tree=change_tmp_item_full_rel_to_tree(tmp);
        			 callBack(_tree);
        		}
        	}
       	  	else
       	  	{
       	  		callBack(null);
       	  	}
        },
        error: function(XMLHttpRequest) {
        	error(arguments);
        	//return null;
           // alert(XMLHttpRequest.status);
        }
    });
	return _tree;
}
function change_tmp_item_full_rel_to_tree(tmp)//将item的树形结构展示清楚
{
	if(tmp instanceof Array&&tmp.length>0)
	{
		for(var t in tmp)
		{
			//console_info(tmp[t]);
			tmp[t].future_storage=ERPUtils.get_item_future_storage(tmp[t].c);
			tmp[t].comp_storage=ERPUtils.get_item_comp_storage(tmp[t].c);
			tmp[t].half_storage=ERPUtils.get_item_half_storage(tmp[t].c);
		}
			//console_info(tmp);
		return _get_tmp_item_chidren(tmp,tmp[0]['t']);
	}
	return null;
}
function _get_tmp_item_chidren(tmp,item)
{
	var tree=[];
	for(var i in tmp)
	{
		if(tmp[i]['p']==item)
		{
			tree.push(tmp[i]);
		}
	}
	for(var j in tree)
	{
		var children=_get_tmp_item_chidren(tmp,tree[j]['c']);
		if(children.length>0)
		{
			tree[j]["children"]=children;
		}
	}
	return tree;
}

var dialogs=new Object();
function show_item_detail(number)
{
	bridge_map.open_web({
		path:"/tab/show_item_detail.html",
		data:{number: number},
		init_param:{
			width:750,
			height:650,
			title:number+"详情"
		}
	});
}
ERPUtils.show_item_storage_out=function(number)
{
	bridge_map.open_web({
		path:"/tab/show_item_storage_out.html",
		data:{number: number},
		init_param:{
			width:780,
			height:650,
			title:number+"出库"
		}
	});
}
ERPUtils.show_item_list_detail=function()
{
	bridge_map.open_web({
		path:"/tab/item_list.html",
		init_param:{
			width:780,
			height:650,
			title:"部品信息"
		}
	});
}
var all_tip_once=[];
var index=0;
ERPUtils.tip_once=function(title, content, time)
{
	var width=200;
	var height=150;
	var top=bridge_map.system_parameters.workarea_height-height;
	var left=bridge_map.system_parameters.workarea_width-width;
	
	for(var i =0;i<all_tip_once.length;i++)
	{
		if(all_tip_once[i].use)
		{
			top=all_tip_once[i].top-height;
			if(top<0)
			{
				top=bridge_map.system_parameters.workarea_height-height;
				left=all_tip_once[i].left-width;
			}
		}
	}
	var res=bridge_map.open_web({
		path:"/tab/tip_once.html",
		data:{content:content},
		init_param:{
			width:width,
			height:height,
			top:top,
			left:left,
			ShowInTaskbar:false,
			//WindowStyle:"none",
			//AllowsTransparency:true,
			resize:'no_resize',
			title:title
		}
	});
		if(res.status=='success')
		{
//			console_info(all_tip_once.length);
			all_tip_once.push({name:res.result,top:top,left:left,use:true});
			if(parseInt(time)>0)
			{
				setTimeout(function () {
					var r=bridge_map.control_open_web(res.result,
					{
						exe_param:{
							Close:true
						}
					});
					if(r.status=="success")
					{
						for(var i in all_tip_once)
						{
							if(all_tip_once[i].name==res.result)
							{
								all_tip_once[i].use=false;
							}
						}
					}
				}, time);
			}
			else
			{
				
			}
		}
}
ERPUtils.show_item_storage_in=function(data,model)
{
	bridge_map.open_web({
		path:"/tab/show_item_storage_in.html",
		model:model?true:false,
		data:data,
		init_param:{
			width:780,
			height:650,
			title:data.item_number+"入库"
		}
	});
}
function show_storage_out()
{
	bridge_map.open_web({
		path:"/tab/storage_out.html",
		init_param:{
			width:720,
			height:650,
			title:"部品出库"
		}
	});
}
function show_storage_in()
{
	bridge_map.open_web({
		path:"/tab/storage_in.html",
		init_param:{
			width:850,
			height:650,
			title:"部品入库"
		}
	});
}
ERPUtils.show_item_feeding_tracking=function (number)
{
	bridge_map.open_web({
		path:"/tab/show_item_feeding_tracking.html",
		data:{number: number},
		init_param:{
			width:800,
			height:400,
			title:number+"投料信息"
		}
	});
}
ERPUtils.item_select_feeding=function ()
{
	bridge_map.open_web({
		path:"/tab/item_select_feeding.html",
		init_param:{
			width:650,
			height:600,
			title:"部品投料"
		}
	});
}
ERPUtils.show_item_feeding_parent_item=function (number)
{
	bridge_map.open_web({
		path:"/tab/show_item_feeding_parent_item_dialogs.html",
		data:{number: number},
		init_param:{
			width:700,
			height:400,
			title:number+"的父件"
		}
	});
}
ERPUtils.item_feeding=function (number,feeding_count)
{
	bridge_map.open_web({
		path:"/tab/item_feeding.html",
		model:'false',
		data:{
			item_number: number,
			feeding_count:feeding_count},
		init_param:{
			width:600,
			height:600,
			title:number+"投料"
		}
	});
}

function get_item_grid_option()
{
	var grid;
	return {
		columns: [
                    { display: '主键', name: 'id',filter:false, width: 50, type: 'int' },
                    {
                    	display: '编号', name: 'number', width: 150,
                    	//editor: { type: 'string'},
                    	render: function (rowdata, rowindex, value) {
                                            		  //row,
                    		return "<a href=\"javascript:showItemDetail('" +value + "')\">"+value+"</a>";
                                            		  //return "";
                    	}	
                    },
                    {
                         display: '类别', name: 'type', width: 150,type:'int',
                        // editor: { type: 'select',emptyText: null, data:getItemTypeArry(), valueColumnName: 'type', displayColumnName: 'name' },
                         render: function (rowdata, rowindex, value) {
                                   return getItemTypeMap()[value];
                         }
                    },
                    {
                          display: '材料', name: 'material', width: 150, type: 'int',
                          render:function (item)
                          {
                              	for (var i = 0; i < materia_data.length; i++)
                                {
                              		if (materia_data[i]['id']+"" == item.material+"")
                                    {
                                           return materia_data[i]['name'];
                                     }
                                }
                                return "";
                           }
                     },
                     {
                           display: '备注', name: 'remark',  align: 'left'
                          // editor: { type: 'text', height: 60 }
                     },
                     {
                             display: '添加时间', name: 'i_time', type: 'date', format: 'yyyy年MM月dd', width: 150
                                                 //editor: { type: 'date' }
                      },
        ],
		allowHideColumn:true,
        //rownumbers:true,
        colDraggable:true,
        rowDraggable:true,
        //rownumbers:true,
        isScroll: false,
        frozen: false,
        pageSize:30,
        pageSizeOptions:[10,20,30,50],
        enabledEdit: true,
        detailToEdit: false,
        clickToEdit: false,
        url:erp_api_service.EntItem[0],
        method:"get",
        urlFilter:function(){
        	var op=arguments[1].options;
        	var ps=[];
        	var url;
        	if(op.url.match(/callback/))
        		url=op.url;
        	else
        		url=op.url+"?callback=?";
        	if(op.parms&&op.parms.where)
        	{
        		var filter=JSON.parse(op.parms.where);
        		if(filter&&filter!="")
        			url+="&"+change_ligerui_filter_to_python(filter);
        	}
        	return url;
        	
        },
        paramFilter:function(){
        	grid=this;
        	var op=arguments[1].options;
        	var ps=[];
    		var page=op.newPage;
    		var pageSize=op.pageSize;
    		var sortOrder=op.sortOrder=="asc"?"":"-";
        	if(op.sortName)
        		ps.push({name:"order_by",value:sortOrder+op.sortName});
        	ps.push({name:"offset",value:(page-1)*pageSize});
        	ps.push({name:"limit",value:pageSize});
        	ps.push({name:"type",value:2});
        	return ps;
        },
        onSuccess:function()
        {
        	arguments[0].Rows=arguments[0].objects;
        	arguments[0].Total=arguments[0].meta.total_count;
        },
        toolbar: { 
        	items: [
                           { text: '高级自定义查询', click: function(){
                        	   grid.showFilter("");
                           }, icon: 'search2'}
               ]
               },
        width: '100%'
	};
}
/**
 * [
 * 	{	
 * 		name:xx,
 * 		width:xx,
 * 		type:
 * 	}
 * ]
 * */
function get_default_grid_option_for_array(arr,map)
{
	if(!(arr instanceof Array)||arr.length==0)
		return null;
	function get_columns(arr)
	{
		
		//if(!arr[0] instanceof Array||arr.length=0)
		var colums=[];
		for(var i in arr[0])
		{
			var w=100/arr.length;
			colums.push(Util.extend({ display: i+"", name: 'id'+i,filter:false,  type: 'text' },map?(map[i]?map[i]:{}):{}));
		}
		return colums;
	}
	
	return {
		columns:get_columns(arr),
		data:get_array_data_4_grid(arr),
		allowHideColumn:true,
        //rownumbers:true,
        colDraggable:true,
        rowDraggable:true,
        //rownumbers:true,
        isScroll: false,
        frozen: false,
        pageSize:15,
        isResize:true,
        pageSizeOptions:[10,20,30,50],
        enabledEdit: false,
        detailToEdit: false,
        clickToEdit: false,
        width: '100%'
	};
}
function get_array_data_4_grid(arr)
{
	var data={
			Rows:[],
        	Total:arr.length
	};
	for(var i in arr)
	{
		var _d=new Object();
		for(var j in arr[i])
		{
			_d["id"+j]=arr[i][j];
		}
		data.Rows.push(_d);
	}
	return data;
}
 ERPUtils.get_default_grid_option_for_url=function(param)
{
	 var checked_record=[];
	if(!param.url||!param.columns)
	{
		throw new Error("url or columns are not allow null");
		return null;
	}
	function findChecked(id)
	{
	    for(var i =0;i<checked_record.length;i++)
	    {
	        if(checked_record[i] == id) return i;
	    }
	    return -1;
	}
	function addChecked(id)
	{
	    if(findChecked(id) == -1)
	    	checked_record.push(id);
	}
	function removeChecked(id)
	{
	    var i = findChecked(id);
	    if(i==-1) 
	    	return;
	    checked_record.splice(i,1);
	}
	return Util.extend({
        //columns: entity_edit_model.dispaly_columns,
        //checkbox: false,//是否使用多选框
        allowHideColumn:true,
        rownumbers:true,
        //rownumbers:true,
        colDraggable:true,
        //rowDraggable:true,
        //rownumbers:true,
        showTableToggleBtn:true,
        //switchPageSizeApplyComboBox:true,
        alternatingRow:false,
       // isScroll: false,
        frozen: false,
        pageSize:15,
        pageSizeOptions: [20,30,50,100,200],
        width: '100%',
        ///url:entity_edit_model.url[entity_edit_model.url_index],
        method:"get",
        urlFilter:function(){
        	var op=arguments[1].options;
        	var ps=[];
        	var url;
        	if(op.url.match(/\?/))
        		url=op.url+"&callback=?";
        	else
        		url=op.url+"?callback=?";
        	if(op.parms&&op.parms.where)
        	{
        		var filter=JSON.parse(op.parms.where);
        		if(filter&&filter!="")
        			url+="&"+change_ligerui_filter_to_python(filter);
        	}
        	if(op.parms&&op.parms.condition)
        	{
        		var condition=JSON.parse(op.parms.condition);
        		url+="&"+change_ligerui_rule_to_python(condition[0]);
        	}
        	return url;
        },
        paramFilter:function(){
        	var op=arguments[1].options;
        	var ps=[];
    		var page=op.newPage;
    		var pageSize=op.pageSize;
    		var sortOrder=op.sortOrder=="asc"?"":"-";
        	if(op.sortName)
        		ps.push({name:"order_by",value:sortOrder+op.sortName});
        	ps.push({name:"offset",value:(page-1)*pageSize});
        	ps.push({name:"limit",value:pageSize});
        	return ps;
        },
        onSuccess:function()
        {
        	checked_record=[];
        	this.checked_record=checked_record;
        	arguments[0].Rows=arguments[0].objects;
        	arguments[0].Total=arguments[0].meta.total_count;
        },
        onError:function(XMLHttpRequest, textStatus, errorThrown)
        {
        	
        	tipOnce("错误","数据获取错误,请重试。",10000);
        },
        onCheckAllRow:function(checked)
        {
        	 for (var rowid in this.records)
            {
                if(checked)
                    addChecked(this.records[rowid]['id']);
                else
                    removeChecked(this.records[rowid]['id']);
            }
        	
        },
        onCheckRow: function(checked, data)
        {
        	 if (checked) 
        		 addChecked(data.id);
             else removeChecked(data.id);
        },
        isChecked: function(rowdata)
        {
        	 if (findChecked(rowdata.id) == -1)
        		   return false;
        	return true;
        }
    },param);
}
function get_material_data()
{
	$.ajax({
        type: 'GET',
        url: erp_api_service.EntMaterial[0]+"?callback=?&limit=1000",
        dataType: 'json',
        cache: false,
        async: true,
        success: function(data) {
        	if(data&&data.objects)
        	{
        		var res=data.objects;
        		if(res instanceof Array&&res.length>0)
        		{
        			materia_data.push({id:0,name:"(空)",material_type:""})
        			for(var i in res)
        			{
        				materia_data.push(res[i]);
        			}
        		}
        		else
        		{
        			tipOnce("提示", "获取材料信息失败,请刷新。", 10000);
        		}	
        		 
        	}
       	  	else
       	  	{
       		  tipOnce("提示", "获取材料信息失败,请刷新。", 10000);
       	  	}
        },
        error: function(XMLHttpRequest) {
        	 tipOnce("提示", "获取材料信息失败,请刷新。", 10000);
           // alert(XMLHttpRequest.status);
        }
    });
}
ERPUtils.deleteEntityById=function(id,url) {
	if (id) {
		var param = {
			url : url + id + "/",
			method : "DELETE"
		};
		var res = JSON.parse(bridge_map.ajax(JSON.stringify(param)));
		if (res != null && res.status > 199 && res.status < 300) {
			return true;
		} else {
			return false;
		}
	}
}
ERPUtils.deleteAllEntity=function(url) {
	if (url) {
		var param = {
				url : url +"/",
				method : "DELETE"
		};
		var res = JSON.parse(bridge_map.ajax(JSON.stringify(param)));
		if (res != null && res.status > 199 && res.status < 300) {
			return true;
		} else {
			return false;
		}
	}
}
ERPUtils.importEntity=function(parm,url) {
	if (parm&&url) 
	{
		var param = {
			url : url,
			data:parm,
			method : "POST"
			};
		var res = JSON.parse(bridge_map.ajax(JSON.stringify(param)));
		if (res != null && res.status > 199 && res.status < 300) {
			return true;
		} else {
			return false;
		}
	}
	return false;
}
ERPUtils.importEntitys=function(params,url) {
	if (params&&url) 
	{
		var param = {
			url : url,
			data:{objects:params},
			method : "PUT"
			};
		var res = JSON.parse(bridge_map.ajax(JSON.stringify(param)));
		if (res != null && res.status > 199 && res.status < 300) {
			return true;
		} else {
			return res;
		}
	}
	return false;
}
ERPUtils.importOrderToFilterById=function(id) {
	if (id) {
		var param = {
			url : erp_api_service.TmpOrderFilter[0],
			data:{order_id:id},
			method : "POST"
			};
		var res = JSON.parse(bridge_map.ajax(JSON.stringify(param)));
		if (res != null && res.status > 199 && res.status < 300) {
			return true;
		} else {
			return false;
		}
	}
}
ERPUtils.get_item_editer=function()
{
	function getGridOptions() {
		var options = ERPUtils.get_default_grid_option_for_url({url:erp_api_service.EntItem[0],columns: [ {
				display : '部品编号',
				name : 'number',
				width : 150
			},
			{
	      	  display: '类别', name: 'type', width: 150,type:'int',
	      	  editor: { type: 'select',emptyText: null, data:getItemTypeArry(), valueColumnName: 'type', displayColumnName: 'name' },
	      	  render: function (rowdata, rowindex, value) {
	      		  return getItemTypeMap()[value];
	      	  }
	        }],
	        isShowDetailToggle:function(rowData){
	        	if("2"==rowData.type)
	        		return true;
	        	return false;
	        },detail: { height:'auto',onShowDetail: function (row, detailPanel,callback)
	            {
	            	var _data=getItemTree(row.number,true,function(tree){
	            		var grid = document.createElement('div'); 
	                    $(detailPanel).append(grid);
	                    $(grid).css('margin',10).ligerGrid({
	                        columns:
	                        	[
	                             {
	                           	  display: '子件', name: 'c', id:"id1", align: 'left'
	                             },
	                             {
	                           	  display: '层次', name: 'l', width: 40,isSort: false
	                           	 // editor: { type: 'text', height: 60 }
	                             },
	                             {
	                            	 display: '数量', name: 'n', width: 50,isSort: false,
	                            	 editor: { type: 'text', height: 60 }
	                             }
	                             ], 
	                             onSelectRow: function (rowdata, rowindex) {
	                            	 item_NO=rowdata.c;
	             					//init_item_storage(rowdata.c);
	             					//show_item_storage_text(rowdata.c);
	                            	// _comobox_select.setText("sss")
	                            	_comobox_select.setValue("1111","1111")
	                            	 _comobox_select._toggleSelectBox(true);
	             		        },
	                             enabledEdit: true,
	                             detailToEdit: false,
	                             clickToEdit: false,
	                             //height:'90%',
	                             tree: { columnId: 'id1' },
	                             isScroll: false,//当 父容器detail: { height:'auto', 。。设置后  此参数失效 
	                             showToggleColBtn: false,
	                             width: '95%',
	                             data: {Rows:tree}, 
	                             showTitle: false, 
	                             pageSize:30,
	                             //enabledSort:false,//不允许排序
	                            // columnWidth: 100,
	                             onAfterShowData: callback,
	                             rownumbers:true,
	                             frozen:false
	                    });  
	            	}) ;
	                
	            }
			}, 
			onLoaded:function(g)
			{
				selectGrid=g;
			},
			/*toolbar: { items: [
			                      { text: '高级自定义查询', 
			                    	  click: function()
			                    	  {
			                    		  selectGrid.showFilter();
			                    	  }, 
			                    	  icon: 'search2'
			                    }
			                   ]
					},*/
					checkbox:false, 
					onSelectRow: function (rowdata, rowindex) {
						item_NO=rowdata.number;
						//init_item_storage(rowdata.number);
						//show_item_storage_text(rowdata.number);
			        }
			                      
		});
		
		return options;
	}
	return {
    	type : 'select',
		condition: {
            //prefixID : 'conditio',
            fields: [
                { label: '部品号', name: 'number', type: 'text' }
            ]
        },
        onButtonClick:function(){
        	_comobox_select=this;
        },
		selectBoxWidth: 500 ,
  		selectBoxHeight:300,
		valueField : 'number',
		textField : 'number',
		grid : getGridOptions()
	};
}
var _comobox_select;
/**
 * data:
 * {
 * 	feeding_date:
 * 	feeding_count:
 * 	item_number:
 * remark:
 * } 
 * {"feeding_date":feeding_date,"feeding_count":feeding_count,"item_number":item_number,plan_storage_time:plan_storage_time}
 * */
ERPUtils.feeding_and_tracking=function(args)
{
	
	var d=args;
	if(d.feeding_date==null)
	{
		d.feeding_date=new Date().toString(true);
	}
	d.feeding_count=Number(d.feeding_count);
	if(d.feeding_count==null||d.feeding_count<=0)
	{
		tipOnce('提示','投料数量不能小于等于0',5000);
		return false;
	}
	if(d.plan_storage_time==null)
	{
		tipOnce('提示','预期入库时间不能为空',5000);
		return false;
	}
	var param = {
            url: erp_api_service.EntFeeding[0],
            method: "POST",
            data: args
        };
//	console_info(param);
	var res = JSON.parse(bridge_map.ajax(JSON.stringify(param)));
    if (res != null && res.status > 199 && res.status<300) {
    	var tmp =res.header.Location.match(/\/[0-9]+\/$/);
    	var newid=Number(tmp[0].replace(/\//g,""));
    	// alert(Util.formatString(erp_api_sql.procedure_feeding_status_init,
    	// newid,"'"+arguments[0].newdata.item_number+"'",arguments[0].newdata.feeding_count));
    	var args=arguments;
    	var feeding_init_res =(bridge_map.super_api(
        			 Util.formatString(erp_api_sql.procedure_feeding_status_init,
        			newid)));
        if (feeding_init_res.status!= null && feeding_init_res.status > 199 && feeding_init_res.status <300){
	        	tipOnce("提示", "投料成功",5000);
                return true;	
        	}
        else{
                tipOnce("提示", Util.formatString("添加失败，请重试[失败码:{0}]",feeding_init_res.status ), 10000);
                return false;
        	}
    }
    else
    {
    	tipOnce("提示", Util.formatString("添加失败，请重试[失败码:{0}]",res.status), 10000);
    	return false;
    }
}

/**
 * 获取comboBox的映射
 * */

function getItemTypeMap()
{
	var types=getItemTypeArry();
	var map=new Object();
	for(var i in types)
	{
		map[types[i]["type"]]=types[i]["name"];
	}
	return  map;
}
function getItemTypeArry()
{
	return  [{type:1,name:"附件"},{type:2,name:"成品件"}];
}
function getStorageItemTypeMap()
{
	var types=getStorageItemTypeArry();
	var map=new Object();
	for(var i in types)
	{
		map[types[i]["type"]]=types[i]["name"];
	}
	return  map;
}
function getStorageItemTypeArry()
{
	return  [{type:1,name:"成品"},{type:2,name:"半成品"}];
}
function getDrawingTypeMap()
{
	var types=getDrawingTypeArry();
	var map=new Object();
	for(var i in types)
	{
		map[types[i]["type"]]=types[i]["name"];
	}
	return  map;
}
function getDrawingTypeArry()
{
	return  [{type:1,name:"图纸"},{type:2,name:"缩略图"}];
}
function getOrderLeadStatus()
{
	var types=getOrderLeadStatusArry();
	var map=new Object();
	for(var i in types)
	{
		map[types[i]["forecast"]]=types[i]["name"];
	}
	return  map;
}
function getOrderStatusArry()
{
	return  [{status:1,name:"已发货"},{status:null,name:"未发货"}];
}
function getOrderStatus()
{
	var types=getOrderStatusArry();
	var map=new Object();
	for(var i in types)
	{
		map[types[i]["status"]]=types[i]["name"];
	}
	return  map;
}
function getFeedingGroupItemArry()
{
	return  [{type:0,name:'合成件'},{type:1,name:"子件"}];//,{type:2,name:"单品件"}
}
function getFeedingGroupItem()
{
	var types=getFeedingGroupItemArry();
	var map=new Object();
	for(var i in types)
	{
		map[types[i]["type"]]=types[i]["name"];
	}
	return  map;
}
function getOrderLeadStatusArry()
{
	return  [{forecast:0,name:"可直接发货"},{forecast:1,name:"预期可发货（在生产中）"},{forecast:2,name:"需要生产"},{forecast:3,name:"已发货"}];
}
ERPUtils.get_item_future_storage=function(item,callBack)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_future_storage,item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
		return r[0][0];
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.get_item_future_storage_1=function(item,callBack)
{
	bridge_map.super_api_1(Util.formatString(erp_api_sql.item_future_storage,item),function(res){
//		var res=JSON.parse(r);
		if(res&&res.status>199&&res.status<300)
		{
			//var r=res.result;
			eval('var r='+res.result);
			callBack(r[0][0]);
		}
		else
			callBack(null);
	});
}
ERPUtils.item_find_drawing_array_1=function(item,callBack)
{
	bridge_map.super_api_1(Util.formatString(erp_api_sql.item_find_drawing_array,item),function(res){
		 //var t="{\"status\":200,\"message\":\"OK\",\"result\":\"[[\\\"{\\\"type2\\\":{\\\"location\\\":\\\"1\\\",\\\"name\\\":\\\"18330-27881_1.png\\\"}}\\\"]]\",\"header\":{\"Transfer-Encoding\":\"chunked\",\"Content-Type\":\"text/html; charset=utf-8\",\"Date\":\"Wed, 03 Sep 2014 09:57:02 GMT\"}}";
		 //console_info(JSON.parse(t));
		//var res=JSON.parse(r);
		//console_info(res);
		if(res&&res.status>199&&res.status<300)
		{
			//var r=res.result;
			eval('var r='+res.result);
			eval('var r1='+r[0][0]);
			callBack(r1);
		}
		else
			callBack(null);
	});
}
ERPUtils.item_find_drawing_array=function(item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_find_drawing_array,item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
			return r[0][0];
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.get_item_need_feed=function(item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_need_feed,item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
			return r[0][0];
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.get_item_half_storage=function(item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_half_storage,item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
			return r[0][0];
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.get_item_half_storage_1=function(item,callBack)
{
	bridge_map.super_api_1(Util.formatString(erp_api_sql.item_half_storage,item),function(res){
		if(res&&res.status>199&&res.status<300)
		{
			//var r=res.result;
			eval('var r='+res.result);
			callBack(r[0][0]);
			//console_info(r[0][0]);
		}
		else
			callBack(null);
	});
}
ERPUtils.check_item_has_child=function(p_item,c_item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.check_item_has_child,p_item,c_item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
		if(r[0][0]==0)
			return false;
		if(r[0][0]==1)
			return true;
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.item_item_update_after=function()
{
	//item_type_create_all : "CALL `item_type_create_all`()",
	//item_tree_create_all_leaf : "CALL `item_tree_create_all_leaf`()",
	var res1=bridge_map.super_api(Util.formatString(erp_api_sql.item_tree_create_all_leaf));
	if(res1&&res1.status>199&&res1.status<300)
	{
		//var r=res.result;
		//eval('var r='+res.result);
		var res2=bridge_map.super_api(Util.formatString(erp_api_sql.item_type_create_all));
		if(res2&&res2.status>199&&res2.status<300)
			return true;
		return false;
		//console_info(r[0][0]);
	}
	else
	{
		return false;
	}
	return null;
}
ERPUtils.item_item_delete_by_p_c=function(p_item,c_item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.procedure_item_item_delete_by_p_c,p_item,c_item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
		return true;
		//console_info(r[0][0]);
	}
	else
	{
		return false;
	}
	return null;
}
ERPUtils.get_technolog_array=function(item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_find_technology_array,item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
			return r[0][0];
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.item_find_technology_info_array=function(item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_find_technology_info_array,item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
		return r[0][0];
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.machine_item_find_machines_by_item=function(item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.machine_item_find_machines_by_item,item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
		return r[0][0];
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.machine_item_find_machines_by_item_1=function(item,callBack)
{
	bridge_map.super_api_1(Util.formatString(erp_api_sql.machine_item_find_machines_by_item,item),function(r){
		var res=JSON.parse(r);
		//eval('var res='+r);
		if(res&&res.status>199&&res.status<300)
		{
			//var r=res.result;
			eval('var r='+res.result);
			callBack(r[0][0]);
			//console_info(r[0][0]);
		}
		else
			callBack(null);
	});
}
ERPUtils.get_item_comp_storage=function(item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_comp_storage,item));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		eval('var r='+res.result);
			return r[0][0];
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.get_item_comp_storage_1=function(item,callBack)
{
	bridge_map.super_api_1(Util.formatString(erp_api_sql.item_comp_storage,item),function(res){
		//eval('var res='+r);
		if(res&&res.status>199&&res.status<300)
		{
			//var r=res.result;
			eval('var r='+res.result);
			callBack(r[0][0]);
			//console_info(r[0][0]);
		}
		else
			callBack(null);
	});
}
ERPUtils.get_item_actual_storage_1=function(item,callBack)
{
	bridge_map.super_api_1(Util.formatString(erp_api_sql.item_actual_storage,item),function(res){
		if(res&&res.status>199&&res.status<300)
		{
			//var r=res.result;
			eval('var r='+res.result);
			callBack(r[0][0]);
		}
		else
			callBack(null);
	});
}
ERPUtils.get_item_actual_storage=function(item)
{
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_actual_storage,item));
	if(res&&res.status>199&&res.status<299)
	{
		//var r=res.result;
		eval('var r='+res.result);
		return r;
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.get_item_material=function (number)
{
	
	var res=bridge_map.super_api(Util.formatString(erp_api_sql.item_material_info,number));
	if(res&&res.status>199&&res.status<300)
	{
		//var r=res.result;
		//console_info(res.result);
		eval('var r='+res.result);
		if( r[0])
		{
			var rr=r[0];
			var ss ='<h3>&nbsp;&nbsp;&nbsp;材料:'+rr[0]+'&nbsp;&nbsp;&nbsp;尺寸:&nbsp;&nbsp;'+rr[2]+'&nbsp;&nbsp;&nbsp;'+rr[3]+'</h3>';
			return ss;
		}
		//console_info(r[0][0]);
	}
	return null;
}
ERPUtils.feeding_row_attr_render=function (rowdata,rowid)
{
//	if(rowdata.feeding_status_all>0&&rowdata.feeding_status_all==rowdata.feeding_status_now)
	if(rowdata.actual_storage_time)
		return "style='background-color:"+erp_colors.feeding_storage+";'";
	else
	{
		if (rowdata.actual_storage_timeout> 0)//生产超时
		{
			return "style='background-color:"+erp_colors.feeding_timeout+";'";
		}
		else if(rowdata.feeding_status_now>0)//正在生产
		{
			if(rowdata.feeding_status_all==rowdata.feeding_status_now)//可以入库
				return "style='background-color:"+erp_colors.feeding_can_storage+";'";
			else//在生产中
				return "style='background-color:"+erp_colors.feeding_has_produced+";'";
				
		}
		else
		{
			return "";
		}
	}
}
ERPUtils.getGridOptions=function() {
	var options = ERPUtils.get_default_grid_option_for_url({url:erp_api_service.EntItem[0],columns: [ {
			display : '部品编号',
			name : 'number',
			width : 150
		},
		{
      	  display: '类别', name: 'type', width: 150,type:'int',
      	  editor: { type: 'select',emptyText: null, data:getItemTypeArry(), valueColumnName: 'type', displayColumnName: 'name' },
      	  render: function (rowdata, rowindex, value) {
      		  return getItemTypeMap()[value];
      	  }
        }],
        isShowDetailToggle:function(rowData){
        	if("2"==rowData.type)
        		return true;
        	return false;
        },detail: { height:'auto',onShowDetail: function (row, detailPanel,callback)
            {
            	var _data=getItemTree(row.number,true,function(tree){
            		var grid = document.createElement('div'); 
                    $(detailPanel).append(grid);
                    $(grid).css('margin',10).ligerGrid(tree,ERPUtils.getChildGridOptions(function(rowdata,rowindex){
                    	backData.item_NO=rowdata.c;
    					select._toggleSelectBox(true);
                    },callback));  
            	}) ;
                
            }
		}, 
		onLoaded:function(g)
		{
			selectGrid=g;
		},
				checkbox:false, 
				onSelectRow: function (rowdata, rowindex) {
					item_NO=rowdata.number;
		        }
	});
	return options;
}
ERPUtils.getChildGridOptions=function(tree,_onSelectRow,_onAfterShowData)
{
	return {
        columns:
        	[
            // { display: '序号', name: 'id', width: 30, type: 'int' },
             {
           	  display: '子件', name: 'c', id:"id1", align: 'left'
             },
             {
           	  display: '层次', name: 'l', width: 40,isSort: false
           	 // editor: { type: 'text', height: 60 }
             },
             {
            	 display: '数量', name: 'n', width: 50,isSort: false,
            	 editor: { type: 'text', height: 60 }
             }
             ], 
             onSelectRow: _onSelectRow,
             enabledEdit: true,
             detailToEdit: false,
             clickToEdit: false,
             //height:'90%',
             tree: { columnId: 'id1' },
             isScroll: false,//当 父容器detail: { height:'auto', 。。设置后  此参数失效 
             showToggleColBtn: false,
             width: '95%',
             data: {Rows:tree}, 
             showTitle: false, 
             pageSize:30,
             //enabledSort:false,//不允许排序
            // columnWidth: 100,
             onAfterShowData: _onAfterShowData,
             rownumbers:true,
             frozen:false
    };
}
 ERPUtils.downloadAndOpen=function(name)
{
	var hrf=erp_file_service_upload+"/"+name;
	  $.ajax({
        type: 'GET',
        url: erp_file_service_upload,
        cache: false,
        async: true,
        data:{file:name},
        success: function(data) {
        	if(data=="true")
        	{
        		var downloadTo=bridge_map.base_directory()+"download\\"+name;
        		var d=bridge_map.downloadFile(hrf,downloadTo);
        		if(d=='success')
        		{
        			bridge_map.start_process(downloadTo);
        		}
        	}
        	else
        	{
        		alert("此部品没有图纸");
        	}
        },
        error:function(){
        	
        }
	  });
}
 ERPUtils.get_item_drawing_handle=function(item_drawing){
	 var h="";
	 if(item_drawing.name)
	{
		 var _name=item_drawing.name.toString().toLowerCase();
		 var name=item_drawing.name;
		 var location=item_drawing.location;
		 if(_name.match(/\.pdf$/))
		{
			  h += "<a href='javascript: ERPUtils.downloadAndOpen(\"" + name + "\")'>图纸</a> ";
		}
		 else if(_name.match(/(\.png)|(\.jpeg)|(\.jpg)|(.bmp)$/))
		{
			 h += "<img height='50px' src='"+erp_file_service_upload+"/"+location+"/"+name+"'></img> ";
		}
		
			 
	}
	  return h;
 }
 ERPUtils.change_ligerui_filter_to_query=function(filter)
	{
		py=null;
		var rules=filter["rules"];
		var op=ERPUtils._getOperatorQueryText(filter["op"]);
		var tag=0;
		for(var i in rules)
		{
			var r=rules[i];
			if(r["value"]&&r["value"]!="")
			{	var v=r["value"];
				if(r['type']!='int'&&r['type']!='number')
					v="'"+v+"'";
				if(tag==0)
				{
					py='';
					py+=r["field"]+Util.formatString(ERPUtils._getOperatorQueryText(r["op"]),v);
					tag++;
				}
				else
				{
					py+=op+r["field"]+Util.formatString(ERPUtils._getOperatorQueryText(r["op"]),v);
//					py+=op+r["field"]+_getOperatorQueryText(r["op"])+"="+r["value"];
				}
			}
		}
		return py;
	}
 ERPUtils._getOperatorQueryText=function (op)
	{
		 switch (op)
		 {
		 case "equal":
			 return " = {0} ";
		 case "notequal":
			 return " <> {0} ";
		 case "greater":
			 return " > {0} ";
		 case "greaterorequal":
			 return " >= {0} ";
		 case "less":
			 return " < {0} ";
		 case "lessorequal":
			 return " <= {0} ";
		 case "startwith":
			 return " like '{0}%' ";
		 case "endwith":
			 return " like '%{0}' ";
		 case "like":
			 return " like '%{0}%' ";
		 case "contain":
			 return " like '%{0}%' ";
		 case "in":
			 return " in {0} ";
		 case "and":
			 return " and ";
		 case "or":
			 return " or ";
		 default:
			 return " = ";
		 }
	}