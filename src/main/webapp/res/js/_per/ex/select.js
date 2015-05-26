/**
 * 
 */
var cz_select=Class({
	_id:{index:0},
	param:{
		div:''
	},
	data:null,
	initialize:function()
	{
		if(!arguments[0])
		{
			tipOnce('错误','请刷新重试。');
			throw new Error("select参数错误。");
		}
		
		if(arguments[0])
		{
			/**
			 * if() 参数判断
			 * */
			this.param=Util.extend(this.param,arguments[0]);
		}
		this._id.index++;
		this.id=this._id.index;
		this.name='__select'+this.id;
		window[this.name]=this;
		this.param.div='#'+this.param.div;
		$(this.param.div).empty();
		this.init_form();
		this.setData();
		//arguments;
	},
	init_form:function()
	{
		/*erp_api_sql
	truncate_tmp_order : "TRUNCATE TABLE `tmp_order`",
	truncate_ent_order : "TRUNCATE TABLE `ent_order`",
	truncate_ent_feeding : "TRUNCATE TABLE `ent_feeding`",
	truncate_ent_feeding_status : "TRUNCATE TABLE `ent_feeding_status`",
	truncate_ent_storage_changes_record : "TRUNCATE TABLE `ent_storage_changes_record`",
	truncate_tmp_order_filter : "TRUNCATE TABLE `tmp_order_filter`",
	delete_future_count : "DELETE FROM `ent_rel_storage_item` WHERE future_count IS NOT NULL",*/
		this.admin_form=$(this.param.div).ligerForm({
			inputWidth: 170, labelWidth: 90, space: 40,
			fields: 
				[
//				 { display: "部品号", name: "item_number",newline: true, type: "text",editor:{readonly:true},newline: true,width:100},//, group: "基础信息", groupicon: groupicon},
//				 { display: "现有库存", name: "actual_storage",newline: true, type: "text",editor:{readonly:true},width:100},
//				 { display: "预入库存", name: "future_storage",newline: true, type: "text",editor:{readonly:true},newline: false,width:100}
				 ]
				, buttons: [
		          { text: '删除所有订单', width: 150,newline: false, click:function(){
				        	  var res;
				        	  dialogWait("正在删除所有订单，稍后......",function(){
				        		  res =bridge_map.super_api(erp_api_sql.truncate_ent_order);
				        	  },null,function(){
				        		  if(res&&res.status)
				        		  {
				        			  tipOnce("提示",'删除成功。', 10000);
				        		  }
				        		  else
				        		  {
				        			  tipOnce("提示",'分析错误，请刷新。', 10000);
				        		  }
				        	  });
		          		} 
		          },
		          { text: '删除所有出入库记录', width: 150,newline: false, click:function(){
				        	  var res;
				        	  dialogWait("正在删除所有出入库记录，稍后......",function(){
				        		  res =bridge_map.super_api(erp_api_sql.truncate_ent_storage_changes_record);
				        	  },null,function(){
				        		  if(res&&res.status)
				        		  {
				        			  tipOnce("提示",'删除成功。', 10000);
				        		  }
				        		  else
				        		  {
				        			  tipOnce("提示",'分析错误，请刷新。', 10000);
				        		  }
				        	  });
		          		} 
		          },
		          { text: '删除所有投料', width: 150,newline: false, click:function(){
		        	  
				        	  var res1,res2,res3;
				        	  dialogWait("正在删除所有投料信息，稍后......",function(){
				        		  res1 =bridge_map.super_api(erp_api_sql.truncate_ent_feeding);
				        		  if(res1&&res1.status<300&&res1.status>199)
				        		  {
				        			  tipOnce("提示",'删除投料单成功。', 1000);
				        		  }
				        		  else
				        		  {
				        			  tipOnce("提示",'删除投料单失败。请重试', 10000);
				        			  return false;
				        		  }
				        		  res2 =bridge_map.super_api(erp_api_sql.truncate_ent_feeding_status);
				        		  if( res2&& res2.status<300&& res2.status>199)
				        		  {
				        			  tipOnce("提示",'删除投料单状态成功。', 1000);
				        		  }
				        		  else
				        		  {
				        			  tipOnce("提示",'删除投料单状态失败。请重试', 10000);
				        			  return false;
				        		  }
				        		  res3 =bridge_map.super_api(erp_api_sql.delete_future_count);
				        		  if( res3&& res3.status<300&& res3.status>199)
				        		  {
				        			  tipOnce("提示",'删除预入库存成功。', 1000);
				        		  }
				        		  else
				        		  {
				        			  console_info(res3);
				        			  tipOnce("提示",'删除预入库存失败。请重试', 10000);
				        			  return false;
				        		  }
				        		  return true;
				        	  },null,function(){
				        			  tipOnce("提示",'删除成功。', 10000);
				        	  },null,function(){
				        			  tipOnce("提示",'分析错误，请刷新。', 10000);
				        	  });
		          		}
		          }
		          ]
		});
	},
	setData:function()
	{
		
	}
});
