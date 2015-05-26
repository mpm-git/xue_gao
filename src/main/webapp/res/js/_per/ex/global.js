var __root="/";//MPM/path/r_person_resltime_location.action?empNO=001;
var r_person_resltime_location=__root+"path/r_person_realtime_location.action";
var r_person_history_location=__root+"path/r_person_history_location.action";
var r_person=__root+"path/r_person.action";
var r_person_tree={};
var r_person_array=[];
var r_person_data;
function load_r_person_array(callback){
	$.ajax({ 
		url: r_person, 
		//data:{empNO:e.param.text.empNO},
		success: function(data){
			r_person_data=data;
			for(var i=0;i<data.length;i++)
			{
				if(!r_person_tree[data[i]["inpatientArea"]])
					r_person_tree[data[i]["inpatientArea"]]={};
				if(!r_person_tree[data[i]["inpatientArea"]][data[i]['type']])
					r_person_tree[data[i]["inpatientArea"]][data[i]['type']]={};
				r_person_tree[data[i]["inpatientArea"]][data[i]['type']][data[i]['name']+"("+data[i]['empNO']+")"]=data[i];
			}
			console.info(r_person_data);
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
			callback();
	}});
}

//http://localhost:8080/MPM/path/r_person.action?empNO=001
//var r_person_resltime_location=__root+"path/r_person_realtime_location.action";