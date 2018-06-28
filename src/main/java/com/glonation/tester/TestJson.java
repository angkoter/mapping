package com.glonation.tester;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.glonation.mapper.Mapper;

public class TestJson {
	public static void main(String[] args) {
		Mapper mapper = new Mapper();
		
		String stringJson = "{\"merchantId\":\"25\",\"orderId\":\"123456ab\",\"custName\":\"angkot\",\"custAddr\":\"Terminal Rawa Bango\",\"vehicleBrand\":\"Toyota\",\"vehicleType\":\"Avanza 1.3 G AT\",\"transmission\":\"AT\",\"vehColor\":\"black\",\"vehiclePlatNo\":\"B2114BFS\",\"vehicleYear\":\"B2114BFS\",\"imgVehRegNo\":\"iVBORw0KGgoAAAANSUhEUgAAARAAAABcCAYAAACm5+q2A\",\"imgCustId\":\"iVBORw0KGgoAAAANSUhEUgAAARAAAABcCAYAAACm5+q2A\",\"imgBodyNo\":\"iVBORw0KGgoAAAANSUhEUgAAARAAAABcCAYAAACm5+q2A\",\"imgVehPic\":\"iVBORw0KGgoAAAANSUhEUgAAARAAAABcCAYAAACm5+q2A\",\"productCode\":\"CP225\",\"amount\":\"22500\",\"checksumHash\":\"ee610778cf39715cbeba8435c7eb60a297fe0a4d\"}";
		LinkedHashMap<String, Object> testHasMap= mapper.JSONToMap(stringJson);
		for (HashMap.Entry<String, Object> subEntry : testHasMap.entrySet()) {
			System.out.println("Key : "+subEntry.getKey()+" Value : "+subEntry.getValue().toString());
			subEntry.setValue("----------"+subEntry+"----------");
		}
		System.out.println("================================================");
		System.out.println(mapper.MapToStringJson(testHasMap));
	}

}
