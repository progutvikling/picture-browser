package bll.utils;

import java.lang.reflect.Type;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ConfigsParser {
	
	public static Map<String, String> getMapFromJson(String json) {
		Gson gson = new Gson();
		Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
		Map<String,String> map = gson.fromJson(json, stringStringMap);
		return map;
	}

}
