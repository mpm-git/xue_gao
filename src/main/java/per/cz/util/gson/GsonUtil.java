package per.cz.util.gson;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;

/** 
 * Gson类库的封装工具类，专门负责解析json数据</br> 
 * 内部实现了Gson对象的单例 
 * @author zhiweiofli 
 * @author hjl
 * @version 1.2 
 * @since 2012-9-18 
 */  
public class GsonUtil {  

//	private static Gson gson = null;  
//
//	static {  
//		if (gson == null) {  
//			gson = new Gson();  
//		}  
//	}  

	private GsonUtil() {  

	}  

	/** 
	 * 将对象转换成json格式 
	 *  
	 * @param ts 
	 * @return 
	 */  
	public static String objectToJson(Object ts) {
		Gson gson = new Gson(); 
		String jsonStr = null;  
		if (gson != null) {  
			jsonStr = gson.toJson(ts);  
		}  
		return jsonStr;  
	}  

	/** 
	 * 将对象转换成json格式(并自定义日期格式) 
	 *  
	 * @param ts 
	 * @return 
	 */  
	public static String objectToJsonDateSerializer(Object ts,  
			final String dateformat) {  
		String jsonStr = null;  
		Gson gson = new GsonBuilder()  
		.registerTypeHierarchyAdapter(Date.class,  
				new JsonSerializer<Date>() {  
			public JsonElement serialize(Date src, Type typeOfSrc,
					JsonSerializationContext context) {
				SimpleDateFormat format = new SimpleDateFormat(  
						dateformat);  
				return new JsonPrimitive(format.format(src));  
			}  
		}).setDateFormat(dateformat).create();  
		if (gson != null) {  
			jsonStr = gson.toJson(ts);  
		}  
		return jsonStr;  
	}  

	/** 
	 * 将json格式转换成list对象 
	 *  
	 * @param jsonStr 
	 * @return 
	 */  
	public static List<?> jsonToList(String jsonStr) {  
		List<?> objList = null;  
		Gson gson = new Gson(); 
		if (gson != null) {  
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>() {  
			}.getType();  
			objList = gson.fromJson(jsonStr, type);  
		}  
		return objList;  
	}  

	/** 
	 * 将json格式转换成list对象，并准确指定类型 
	 * @param jsonStr 
	 * @param type 
	 * @return 
	 */  
	public static List<?> jsonToList(String jsonStr, java.lang.reflect.Type type) {  
		List<?> objList = null; 
		Gson gson = new Gson(); 
		if (gson != null) {  
			objList = gson.fromJson(jsonStr, type);  
		}  
		return objList;  
	}  

	/** 
	 * 将json格式转换成map对象 
	 *  
	 * @param jsonStr 
	 * @return 
	 */  
	public static Map<?, ?> jsonToMap(String jsonStr) {  
		Map<?, ?> objMap = null;  
		Gson gson = new Gson(); 
		if (gson != null) {  
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {  
			}.getType();  
			objMap = gson.fromJson(jsonStr, type);  
		}  
		return objMap;  
	}  
	/** 
	 * 将json格式转换成map对象 
	 *  
	 * @param jsonStr 
	 * @return 
	 */  
	public static Map<String, String> jsonToMap4String(String jsonStr) {  
		Map<String, String> objMap = null;  
		Gson gson = new Gson(); 
		if (gson != null) {  
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<String, String>>() {  
			}.getType();  
			objMap = gson.fromJson(jsonStr, type);  
		}  
		return objMap;  
	}  

	/** 
	 * 将json转换成bean对象 
	 *  
	 * @param jsonStr 
	 * @return 
	 */  
	public static Object jsonToBean(String jsonStr, Class<?> cl) {  
		Object obj = null; 
		Gson gson = new Gson(); 
		if (gson != null) {  
			obj = gson.fromJson(jsonStr, cl);  
		}  
		return obj;  
	}  

	/** 
	 * 将json转换成bean对象 
	 *  
	 * @param jsonStr 
	 * @param cl 
	 * @return 
	 */  
	@SuppressWarnings("unchecked")  
	public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl,  
			final String pattern) {  
		Object obj = null;  
		Gson gson = new GsonBuilder()  
		.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {  
			public Date deserialize(JsonElement json, Type typeOfT,  
					JsonDeserializationContext context)  
			throws JsonParseException {  
				SimpleDateFormat format = new SimpleDateFormat(pattern);  
				String dateStr = json.getAsString();  
				try {  
					return format.parse(dateStr);  
				} catch (ParseException e) {  
					e.printStackTrace();  
				}  
				return null;  
			}
		}).setDateFormat(pattern).create();  
		if (gson != null) {  
			obj = gson.fromJson(jsonStr, cl);  
		}  
		return (T) obj;  
	}  

	/** 
	 * 根据 
	 *  
	 * @param jsonStr 
	 * @param key 
	 * @return 
	 */  
	public static Object getJsonValue(String jsonStr, String key) {  
		Object rulsObj = null;  
		Map<?, ?> rulsMap = jsonToMap(jsonStr);  
		if (rulsMap != null && rulsMap.size() > 0) {  
			rulsObj = rulsMap.get(key);  
		}  
		return rulsObj;  
	}  
	public static void main(String[] args) {
		String s="{\"status\":\"200\",\"message\":\"成功\",\"result\":{\"type_sn\":3,\"logo_per_price\":11.2,\"logo_upload_price\":22.3,\"logo_draw_price\":2.0,\"logo_text_price\":33.4,\"logo_color_price\":44.5,\"currency\":2}}";
		String c="[{'color':'#ff0000'},{'color':'#004466'},{'color':'#ffff00'},{'color':'#ff005e'},{'color':'#a60de3'},{'color':'#0d1fe3'},{'color':'#0daae3'},{'color':'#0de338'},{'color':'#9fe30d'},{'color':'#d5e30d'},{'color':'#e3510d'},{'color':'#e3260d'},{'color':'#c620e0'}]";
		
		List<?> list = GsonUtil.jsonToList(c);
		//System.out.println(list.get(0));
		Map<?, ?> map0 = GsonUtil.jsonToMap(s);
		//System.out.println(map0.get("result"));
		Map<?, ?> map1 = GsonUtil.jsonToMap(map0.get("result").toString());
		//System.out.println(map1.get("logo_per_price"));
//		Map<String, String> map =  GsonUtil.jsonToMap4String(s);
//		//System.out.println(map);
//		//System.out.println(map.get("result"));
		Map<?, ?> map = GsonUtil.jsonToMap(s);
		
		List<?> lists = GsonUtil.jsonToList(s, new Type(){
			@Expose
			private String status;
			@Expose
			private String message;
//			@Expose
//			private T result;
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
//			public T getResult() {
//				return result;
//			}
//			public void setResult(T result) {
//				this.result = result;
//			}
			});
		//System.out.println(list);
	}

}  