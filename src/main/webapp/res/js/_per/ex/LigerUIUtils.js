
function tipOnce(title, content, time) {
//	var tip_once=$.ligerDialog.tip({ title: title, content: content });
//    if (time) {
//        setTimeout(function () {
//            tip_once.close();
//        }, time);
//    }
	 ERPUtils.tip_once(title,content,time);
}
function dialogWait(waitTitle,func,args1,success,args2,error,args3){
	var d=$.ligerDialog.waitting(waitTitle?waitTitle:'正在执行,请稍候...');
	setTimeout(function ()
	{
		if(func(args1))
		{
			if(success)
				success(args2);
		}
		else
		{
			if(error)
				error(args3);
		}
		d.close();
		//tipOnce("分析成功", "点击查看分析结果查看分析信息");
	},200);
}
/*
$.ligerDialog.confirm(content, title, callback（[true|false]）) 
*/
/*{
"rules": [
    {
        "field": "CustomerID",
        "op": "equal",
        "value": "11",
        "type": "string"
    },
    {
        "field": "CustomerID",
        "op": "equal",
        "value": "123",
        "type": "string"
    },
    {
        "field": "CustomerID",
        "op": "equal",
        "value": "231",
        "type": "string"
    },
    {
        "field": "CustomerID",
        "op": "equal",
        "value": "23",
        "type": "string"
    },
    {
        "field": "CustomerID",
        "op": "equal",
        "value": "",
        "type": "string"
    },
    {
        "field": "CustomerID",
        "op": "equal",
        "value": "",
        "type": "string"
    }
],
"groups": [
    {
        "rules": [
            {
                "field": "CompanyName",
                "op": "endwith",
                "value": "23",
                "type": "string"
            },
            {
                "field": "CustomerID",
                "op": "equal",
                "value": "nn",
                "type": "string"
            }
        ],
        "op": "and"
    }
],
"op": "or"
}*/
change_ligerui_filter_to_python=function(filter)
{
py="";
var rules=filter["rules"];
var op=_getOperatorQueryText(filter["op"]);
var tag=0;
for(var i in rules)
{
	var r=rules[i];
	if(r["value"]&&r["value"]!="")
	{	
		if(tag==0)
		{
			py+=r["field"]+_getOperatorQueryText(r["op"])+"="+r["value"];
			tag++;
		}
		else
		{
			py+=op+r["field"]+_getOperatorQueryText(r["op"])+"="+r["value"];
		}
	}
}
return py;
}
change_ligerui_rule_to_python=function(r)
{
	py="";
	if(r["value"]&&r["value"]!="")
	{	
		py+=r["field"]+_getOperatorQueryText(r["op"])+"="+r["value"];
	}
	return py;
}
function _getOperatorQueryText (op)
{
switch (op)
{
    case "equal":
        return "__exact";
    case "notequal":
        return "__iexact";
    case "greater":
        return "__gt";
    case "greaterorequal":
        return "__gte";
    case "less":
        return "__lt";
    case "lessorequal":
        return "__lte ";
    case "startwith":
    	return "__startswith";
    case "endwith":
    	return "__endswith";
    case "like":
    	return "__contains";
    case "contain":
    	return "__contains";
    case "in":
    	return "__in";
    case "and":
        return "&";
    case "or":
        return "|";
    default:
        return "__exact";
}
/* "and": "并且",
"or": "或者",
"equal": "相等",
"notequal": "不相等",
"startwith": "以..开始",
"endwith": "以..结束",
"like": "相似",
"greater": "大于",
"greaterorequal": "大于或等于",
"less": "小于",
"lessorequal": "小于或等于",
"in": "包括在...",
"notin": "不包括...",
"addgroup": "增加分组",
"addrule": "增加条件",
"deletegroup": "删除分组"*/
}

$.ligerDefaults.FilterString = {
        strings: {
            "and": "并且",
            "or": "或者",
            "equal": "相等",
            "notequal": "不相等",
            "startwith": "以..开头",
            "endwith": "以..结尾",
            "like": "相似",
            "contain": "包含",
            "greater": "大于",
            "greaterorequal": "大于或等于",
            "less": "小于",
            "lessorequal": "小于或等于",
            "in": "包括在...",
            "notin": "不包括...",
            "addgroup": "增加分组",
            "addrule": "增加条件",
            "deletegroup": "删除分组"
        }
    };

    $.ligerDefaults.Filter.operators['string'] =
    $.ligerDefaults.Filter.operators['text'] =
    ["like","equal", "startwith", "endwith"];
   // ["equal", "notequal", "startwith", "endwith", "like", "greater", "greaterorequal", "less", "lessorequal", "in", "notin"];

    $.ligerDefaults.Filter.operators['number'] =
    $.ligerDefaults.Filter.operators['int'] =
    $.ligerDefaults.Filter.operators['float'] =
    $.ligerDefaults.Filter.operators['date'] =
    ["equal", "greater", "greaterorequal", "less", "lessorequal","like"];
    
    $.ligerDefaults.Filter.operators['select'] =["equal"];
    $.ligerDefaults.Filter.operators['combobox'] =["equal"];
    
    $.ligerDefaults.Grid.editors['textarea'] = {
    	    create: function (container, editParm) {
    	        var input = $("<textarea />");
    	        container.append(input);
    	        container.width('auto').height('auto');
    	        return input;
    	    },
    	    getValue: function (input, editParm) {
    	        return input.val();
    	    },
    	    setValue: function (input, value, editParm) {
    	        input.val(value);
    	    },
    	    resize: function (input, width, height, editParm) {
    	        var column = editParm.column;
    	        input.width(column.editor.width);
    	        input.height(column.editor.height);
    	    }
    	};
    $.ligerDefaults.Grid.editors['file'] =
    {
        
        create: function ( container, editParm )
        {
//        	console_info(p);
        	/*
        	 * 
        	var input = $("<input type='text'/>");
            container.append(input);
            var _erp_file_select=new erp_file_select({select:input});
            //input.ligerDateEditor(field.editor.options || {});
            return input;*/
            var editor = $( '<input class="l-textarea" type="text" />' );
            var id = editParm.column.__domid+'grid_editors_file';
            if ( $( "#" + id ).length )
            {
                editor = $( "#" + id );
            }
            editor.attr( {
                id: id,
                name: id
            } );
            //if ( p.readonly ) editor.attr( "readonly", true );
            container.append( editor );
            var _erp_file_select=new erp_file_select({select:editor});
            return editor;
        },
        getValue: function ( editor, editParm )
        {
            return editor.val();
        },
        setValue: function ( editor, value, editParm )
        {
            editor.val( value );
        },
        resize: function ( editor, width, height, editParm )
        {
            editor.css( {
                width: width - 2
            } ).parent().css( "width", "auto" );
        }
    };
    $(document).bind("contextmenu",function(e){  
        return true;  
    });  
    
    $.ligerDefaults.Form.editors['file1'] =
    {
    		
    		create: function ( container, editParm, p )
    		{
    			//console_info(editParm);
//        	console_info(p);
    			/*
    			 * 
        	var input = $("<input type='text'/>");
            container.append(input);
            var _erp_file_select=new erp_file_select({select:input});
            //input.ligerDateEditor(field.editor.options || {});
            return input;*/
    			var editor = $( '<input class="l-textarea" type="text" />' );
    			var id = ( p.prefixID || "" ) + editParm.field.name;
    			if ( $( "#" + id ).length )
    			{
    				editor = $( "#" + id );
    			}
    			editor.attr( {
    				id: id,
    				name: id
    			} );
    			if ( p.readonly ) editor.attr( "readonly", true );
    			container.append( editor );
    			var _param=Util.extend(Util.extend({},editParm.field.editor.options),{select:editor});
    			var _erp_file_select=new erp_file_select(_param);
    			return editor;
    		},
    		getValue: function ( editor, editParm )
    		{
    			return editor.val();
    		},
    		setValue: function ( editor, value, editParm )
    		{
    			editor.val( value );
    		},
    		resize: function ( editor, width, height, editParm )
    		{
    			editor.css( {
    				width: width - 2
    			} ).parent().css( "width", "auto" );
    		}
    };
    $.ligerDefaults.Form.editors['file'] =
    {
        
        create: function ( container, editParm, p )
        {
        	//console_info(editParm);
//        	console_info(p);
        	/*
        	 * 
        	var input = $("<input type='text'/>");
            container.append(input);
            var _erp_file_select=new erp_file_select({select:input});
            //input.ligerDateEditor(field.editor.options || {});
            return input;*/
            var editor = $( '<input class="l-textarea" type="text" />' );
            var id = ( p.prefixID || "" ) + editParm.field.name;
            if ( $( "#" + id ).length )
            {
                editor = $( "#" + id );
            }
            editor.attr( {
                id: id,
                name: id
            } );
            if ( p.readonly ) editor.attr( "readonly", true );
            container.append( editor );
            var _param=Util.extend(Util.extend({},editParm.field.editor.options),{select:editor});
            var _erp_file_select=new erp_file_select(_param);
            return editor;
        },
        getValue: function ( editor, editParm )
        {
            return editor.val();
        },
        setValue: function ( editor, value, editParm )
        {
            editor.val( value );
        },
        resize: function ( editor, width, height, editParm )
        {
            editor.css( {
                width: width - 2
            } ).parent().css( "width", "auto" );
        }
    };
    $.ligerDefaults.Form.editors['folder'] =
    {
    		
    		create: function ( container, editParm, p )
    		{
    			//console_info(editParm);
//        	console_info(p);
    			/*
    			 * 
        	var input = $("<input type='text'/>");
            container.append(input);
            var _erp_file_select=new erp_file_select({select:input});
            //input.ligerDateEditor(field.editor.options || {});
            return input;*/
    			var editor = $( '<input class="l-textarea" type="text" />' );
    			var id = ( p.prefixID || "" ) + editParm.field.name;
    			if ( $( "#" + id ).length )
    			{
    				editor = $( "#" + id );
    			}
    			editor.attr( {
    				id: id,
    				name: id
    			} );
    			if ( p.readonly ) editor.attr( "readonly", true );
    			container.append( editor );
    			var _param=Util.extend(Util.extend({},editParm.field.editor.options),{select:editor});
    			var _erp_folder_select=new erp_folder_select(_param);
    			//var _erp_file_select=new erp_folder_select(Util.extend({},editParm.field.editor.options),{select:editor});
    			return editor;
    		},
    		getValue: function ( editor, editParm )
    		{
    			return editor.val();
    		},
    		setValue: function ( editor, value, editParm )
    		{
    			editor.val( value );
    		},
    		resize: function ( editor, width, height, editParm )
    		{
    			editor.css( {
    				width: width - 2
    			} ).parent().css( "width", "auto" );
    		}
    };
    $.ligerDefaults.Form.editors['button'] =
    {
    		
    		create: function ( container, editParm, p )
    		{
    			var editor = $( '<input class="l-button" type="button" />' );
    			var id = ( p.prefixID || "" ) + editParm.field.name;
    			if ( $( "#" + id ).length )
    			{
    				editor = $( "#" + id );
    			}
    			editor.attr( {
    				id: id,
    				name: id
    			} );
    			if ( p.readonly ) editor.attr( "readonly", true );
    			container.append( editor );
    			if(editParm.field.editor.options&&editParm.field.editor.options.click&& typeof editParm.field.editor.options.click=='function')
    			{
        			editor.bind('click',function(){
        				editParm.field.editor.options.click.call(editor);
        			});
    			}
    			return editor;
    		},
    		getValue: function ( editor, editParm )
    		{
    			return null;
    		},
    		setValue: function ( editor, value, editParm )
    		{
    		},
    		resize: function ( editor, width, height, editParm )
    		{
    			editor.css( {
    				width: width - 2
    			} ).parent().css( "width", "auto" );
    		}
    };