package com.example.demo.util.deleteMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DeleteMap {

	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("1_1", "1_1");
		map.put("1_2", "1_2");
		map.put("1_3", "1_3");
		
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String> e = it.next();
			String key=e.getKey();
			if(key.contains("_")){
				it.remove();
			}
		}
		
		
		System.out.println(map.size());
	}
}
