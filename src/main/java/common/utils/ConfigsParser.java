package common.utils;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class ConfigsParser {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromJson(String json) {
		Gson gson = new Gson();
		Map<String, Object> map=new HashMap<String, Object>();
		return map = (Map<String, Object>) gson.fromJson(json, map.getClass());
	}

}
