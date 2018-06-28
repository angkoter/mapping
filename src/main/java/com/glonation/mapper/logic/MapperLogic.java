package com.glonation.mapper.logic;

import java.util.LinkedHashMap;
import java.util.Map;

public interface MapperLogic {
	LinkedHashMap<String, Object> JSONToMap(String stringJson);
	String MapToStringJson(Map<String, Object> mapJson);
	LinkedHashMap<String, Object> XMLToMap(String stringXML,String rootName);
	String MapToStringXML(Map<String, Object> mapXML,String rootName);	
}
