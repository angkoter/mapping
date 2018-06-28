package com.glonation.tester;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.glonation.mapper.Mapper;

public class TestXML {
	public static void main(String[] args) {
		Mapper mapper = new Mapper();
		String stringXML = "<kisel><msisdn>08123456780</msisdn><stan>654321</stan><denom>SI150</denom><mti>0200</mti><userid>M1tr40001</userid><passwd>K15el#2017</passwd><rsid>RS000001</rsid><trxid>RC01705001</trxid><date>2017-05-20 12:33:46</date></kisel>";
		LinkedHashMap<String, Object> testHasMap = mapper.XMLToMap(stringXML, "kisel");
		for (HashMap.Entry<String, Object> subEntry : testHasMap.entrySet()) {
			System.out.println("Key : " + subEntry.getKey() + " Value : " + subEntry.getValue().toString());
			subEntry.setValue("----------" + subEntry + "----------");
		}
		System.out.println("================================================");
		System.out.println(mapper.MapToStringXML(testHasMap, "kisel"));
	}
}
