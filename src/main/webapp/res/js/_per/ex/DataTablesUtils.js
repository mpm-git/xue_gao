DataTablesUtils=typeof DataTablesUtils =="undefined"?{}:DataTablesUtils;
DataTablesUtils.getDefaultOption=function()
{
	return {
		"bPaginate" : true,//翻页功能
		"bSort" : false,
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		//	        "sPaginationType": "bootstrap",
		"bRetrieve" : true,
		"bDestroy" : true,
		"bStateSave" : true,
		"bProcessing" : true,
		"bServerSide" : true,
		sAjaxDataProp : 'aaData',
		//"sAjaxSource" :"get_all_healthpages.action?"+new Date().getTime(),
		/* "aaSorting": [[ 4, "desc" ]], */
		//  	"aaData":aDataSet.values,
		"iDisplayLength" : 20,
		"aLengthMenu" : [ [ 10, 20, 50, 100 ],
				[ 10, 20, 50, 100, "All" ] ],
		"oLanguage" : {
			"sProcessing" : "正在加载中......",
			"sLengthMenu" : "每页显示 _MENU_ 条记录",
			"sZeroRecords" : "没有数据！",
			"sEmptyTable" : "表中无数据存在！",
			"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoEmpty" : "显示0到0条记录",
			"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
			"sSearch" : "搜索",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "末页"
			}
		}
	};
};