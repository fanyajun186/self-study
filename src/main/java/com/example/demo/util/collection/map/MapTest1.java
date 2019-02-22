package com.example.demo.util.collection.map;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class MapTest1 {

	public static void main(String[] args) {

		Map<String, Object> param1 = Maps.newHashMap();
		Map<String, Object> param3 = Maps.newHashMap();
		
		param1.putAll(param3);
		
		
		param1.put("name", "youcai");
		param1.put("value", "fan");
		Map<String, Object> param2 = Maps.newHashMap();
		param2.put("name", "test1");
		param2.put("value", "test2");
		
		param1.putAll(param2);
		System.out.println(param1.size());
		
		System.out.println(param1.get("aa"));
		
		String count = "2";        	
    	count=StringUtils.isEmpty(count)?"1":(Integer.parseInt(count)+1)+"";
    	switch (count) {
		case "1":
			System.out.println("1111_"+count);
			break;
		case "2":
			System.out.println("2222_"+count);
			break;
		default:
			System.out.println("333_"+count);
			break;
		}
	}


}
