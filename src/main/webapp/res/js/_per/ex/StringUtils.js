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
var menu=[
			{
				title:'轨迹管理',
				children:[
						{title:'实时轨迹管理',img:'/styles/image/doctor.png',url:'/path/real-time-path.action'},
						{title:'特定轨迹跟踪',img:'/styles/image/nurse.png',url:'/path/specific-path.action'},
						{title:'病房设备联动',img:'/styles/image/patient.png',url:'/path/sickroom-equip.action'}
						]
			},
			{
				title:'统计分析',
				children:[
				          {title:'护士工作统计',img:'/styles/image/nurse.png',url:'/dispatcher?dispatcher=NurseRoundsStat.jsp'},
				          {title:'医生查房统计',img:'/styles/image/doctor.png',url:'/stat/doctor-rounds-stat.action'},
				          {title:'告警统计分析',img:'/styles/image/patient.png',url:'/alert/patient-leave-alert.action'}
				          ]
			},
			{
				title:'RFID阅读设备管理',
				children:[
				          {title:'可视化管理',url:'#'},
				          {title:'设备控制',url:'#'}
				          ]
			},
			{
				title:'RFID阅读中间件',
				children:[
				          {title:'高并发读取',img:'/styles/image/doctor.png',url:'#'},
				          {title:'底层日志',img:'/styles/image/doctor.png',url:'/dispatcher?dispatcher=RFIDLog.jsp'},
				          {title:'事件映射',img:'/styles/image/doctor.png',url:'/dispatcher?dispatcher=RFIDMap.jsp'}
				          ]
			},
			{
				title:'病房终端设备管理',
				children:[
				          {title:'可视化管理',img:'/styles/image/doctor.png',url:'#'},
				          {title:'设备查询',img:'/styles/image/doctor.png',url:'#'},
				          {title:'设备控制',img:'/styles/image/doctor.png',url:'#'},
				          {title:'健康普及管理',img:'/styles/image/doctor.png',url:'/stat/health-popularization.action'},
				          {title:'健康普及预览',img:'/styles/image/doctor.png',url:'/healthIndex.jsp'}
				          ]
			},
			{
				title:'决策支持',
				children:[
				          {title:'病人护理行为分析',img:'/styles/image/doctor.png',url:'/stat/patientSingleMain.action'},
				          {title:'护士护理行为分析',img:'/styles/image/doctor.png',url:'/stat/nursingEfficiencyStat.action'},
				          {title:'护理强度分析',img:'/styles/image/doctor.png',url:'/stat/nursingStrengthStat.action'},
				          {title:'临床决策支持',img:'/styles/image/doctor.png',url:'/stat/dataMiningStat.action'}
				          ]
			},
			{
				title:'配置管理',
				children:[
				          {title:'标签管理',url:'/tag/tag_manager.jsp'},
				          {title:'地图管理',url:'/dispatcher?dispatcher=ChangeAreaBackgroud.jsp'},
				          {title:'参数配置',url:'/stat/var-set-stat.action'},
				          {title:'用户权限管理',url:'/stat/user-right-stat.action'},
				          {title:'修改密码',url:'/stat/change-pswd-stat.action'},
				          {title:'添加用户',url:'/stat/add-user-stat.action'}
				          ]
			}
			
        ];