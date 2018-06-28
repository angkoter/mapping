package com.glonation.tester;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.glonation.mapper.MapEntryConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import com.thoughtworks.xstream.security.TypePermission;

public class Testngasal {
	public static void main(String[] args) {
		LinkedHashMap<String,Object> map = new LinkedHashMap();
		map.put("msisdn","08123456780");
		map.put("stan","654321");
		map.put("denom","SI150");
		map.put("mti","0200");
		map.put("userid","M1tr40001");
		map.put("passwd","K15el#2017");
		map.put("rsid","RS000001");
		map.put("trxid","RC01705001");
		map.put("date","2017-05-20 12:33:46");
	

		// convert to XML
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("kisel", LinkedHashMap.class);
		xStream.addPermission(NoTypePermission.NONE);
		// allow some basics
		xStream.addPermission(NullPermission.NULL);
		xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xStream.allowTypeHierarchy(Collection.class);
		xStream.allowTypesByWildcard(
				new String[] { "java.lang.*", "java.util.*", "java.util.concurrent.*" });
		xStream.registerConverter(new MapEntryConverter());
		String xml = xStream.toXML(map);
		System.out.println(xml);
	
		String stringxml ="<kisel><msisdn>08123456780</msisdn><stan>654321</stan><denom>SI150</denom><mti>0200</mti><userid>M1tr40001</userid><passwd>K15el#2017</passwd><rsid>RS000001</rsid><trxid>RC01705001</trxid><date>2017-05-20 12:33:46</date></kisel>";
		HashMap<String,Object> map2 = (LinkedHashMap<String, Object>)xStream.fromXML(stringxml);
		for (HashMap.Entry<String, Object> subEntry : map2.entrySet()) {
			System.out.println("Key : "+subEntry.getKey()+" Value : "+subEntry.getValue().toString());
		}
    }
	
}
