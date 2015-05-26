<%@ page language="java" contentType="text/javascript; charset=UTF-8"
    pageEncoding="UTF-8"%>
var __root="";//MPM/path/r_person_resltime_location.action?empNO=001;
var r_person_resltime_location=__root+"/path/r_person_realtime_location.action";
var r_patient_resltime_location=__root+"/path/r_patient_realtime_location.action";
var r_person_history_location=__root+"/path/r_person_history_location.action";
var r_person=__root+"/path/r_person.action";
var global_sys_info=${global_sys_info};
var r_person_tree={};
var r_person_array=[];
var r_person_data=null;
var r_user_info=null;
if(global_sys_info&&global_sys_info.userInfo)
{
	r_user_info=global_sys_info.userInfo;
	console.info(r_user_info);
}
if(global_sys_info&&global_sys_info.all_person)
	r_person_data=global_sys_info.all_person;
 if(r_person_data&&r_person_data.length>0)
 {
 			var data=r_person_data;
			for(var i=0;i<data.length;i++)
			{
				if(!r_person_tree[data[i]["inpatientArea"]])
					r_person_tree[data[i]["inpatientArea"]]={};
				if(!r_person_tree[data[i]["inpatientArea"]][data[i]['type']])
					r_person_tree[data[i]["inpatientArea"]][data[i]['type']]={};
				r_person_tree[data[i]["inpatientArea"]][data[i]['type']][data[i]['name']+"("+data[i]['empNO']+")"]=data[i];
			}
			r_person_array=[];
			for(var k in r_person_tree)
			{
				if(typeof(r_person_tree[k])!='function' )
				{
					r_person_array.push([k,"--",k]);
					for(var k1 in r_person_tree[k])
					{
						if(typeof(r_person_tree[k][k1])!='function' )
						{
							r_person_array.push([k1,k,k1]);
							for(var k2 in r_person_tree[k][k1])
							{
								if(typeof(r_person_tree[k][k1][k2])!='function' )
								{
									var id=r_person_tree[k][k1][k2]['empNO'];
									if(id)
										r_person_array.push([id,k1,k2]);
								}
							}
						}
						
					}
				}
					
			}
 }
 console.info(r_person_tree);
/*function load_r_person_array(callback){
	$.ajax({ 
		url: r_person, 
		//data:{empNO:e.param.text.empNO},
		success: function(data){
			
			callback();
	}});
}*/
function saveLog(data,callBack)
{
	console.info("saveLog:",data);
	if(id)
	{
		$.ajax({ 
			url: "/save_log.action", 
			data:data,
			success: function(data){
				if(data)
				{
					if(callBack&&typeof(callBack)=="function")
					{
						callBack(data);
					}
				}
			}
		});
	}
}
//http://localhost:8080/MPM/path/r_person.action?empNO=001
//var r_person_resltime_location=__root+"path/r_person_realtime_location.action";