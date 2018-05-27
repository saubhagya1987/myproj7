package com.airtel.user.helper.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonToMapParser {

	public static Map<String,String> parseJsonToMap(JsonElement jsonElement, String appender){
		Map<String, String> resp = new HashMap<String, String>();
		
		if(jsonElement.isJsonObject()){
			JsonObject jsObj = (JsonObject)jsonElement;
			for(Entry<String, JsonElement> param : jsObj.entrySet()){
				if(param.getValue().isJsonArray() || param.getValue().isJsonObject()){
					resp.putAll(parseJsonToMap(param.getValue(), appender+"_"+param.getKey()));
				}else if(param.getValue().isJsonPrimitive()){
					resp.put(appender + "_" + param.getKey(), param.getValue().getAsString());
				}else if(param.getValue().isJsonNull()){
					resp.put(appender + "_" + param.getKey(), null);
				}
			}
		}else if(jsonElement.isJsonArray()){
			JsonArray jsArr = (JsonArray)jsonElement;
			for(int i=0;i<jsArr.size();i++){
				if(jsArr.get(i).isJsonArray() || jsArr.get(i).isJsonObject()){
					resp.putAll(parseJsonToMap(jsArr.get(i), appender+"_"+i));
				}else if(jsArr.get(i).isJsonPrimitive()){
					resp.put(appender + "_" + i, jsArr.get(i).getAsString());
				}else if(jsArr.get(i).isJsonNull()){
					resp.put(appender + "_" + i, null);
				}
			}
		}
		
		return resp;
	}
	
	public static Map<String,String> parseJsonToMap(String jsonStr, String appender){
		return parseJsonToMap(new com.google.gson.JsonParser().parse(jsonStr), appender);
	}
}
