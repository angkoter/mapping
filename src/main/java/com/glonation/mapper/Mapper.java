package com.glonation.mapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.glonation.mapper.logic.MapperLogic;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.StaxWriter;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

public class Mapper implements MapperLogic {
	ObjectMapper objectMapper = new ObjectMapper();
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, Object> JSONToMap(String json) {
		LinkedHashMap<String, Object> mapResult = new LinkedHashMap<String, Object>();
		try {
			mapResult= objectMapper.readValue(json, LinkedHashMap.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapResult;
	}

	public String MapToStringJson(Map<String, Object> map) {
		String resultString="";
		try {
			resultString= objectMapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultString;
	}

	public LinkedHashMap<String, Object> XMLToMap(String stringXML,String rootName) {
		LinkedHashMap<String, Object> mapResult = new LinkedHashMap<String, Object>();
		
		XStream xStream = new XStream(new DomDriver());
		xStream.alias(rootName, LinkedHashMap.class);
		xStream.addPermission(NoTypePermission.NONE);
		xStream.addPermission(NullPermission.NULL);
		xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xStream.allowTypeHierarchy(Collection.class);
		xStream.allowTypesByWildcard(
				new String[] { "java.lang.*", "java.util.*", "java.util.concurrent.*" });
		xStream.registerConverter(new MapEntryConverter());
		mapResult = (LinkedHashMap<String, Object>)xStream.fromXML(stringXML);
		return mapResult;
	}

	public String MapToStringXML(Map<String, Object> mapXML, String rootName)  {
		StaxDriver stxdrv = new StaxDriver();
		XStream xStream = new XStream(stxdrv);
		xStream.alias("kisel", LinkedHashMap.class);
		xStream.registerConverter(new MapEntryConverter());
		
		StringWriter strWriter = new StringWriter();
		StaxWriter sw = null;
		try {
			sw = new StaxWriter(stxdrv.getQnameMap(),
					stxdrv.getOutputFactory().createXMLStreamWriter(strWriter),
			   false, // don't do startDocument
			   true);
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xStream.marshal(mapXML, sw);
		sw.close();
		return strWriter.toString();
	}

		
}
