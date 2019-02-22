package com.example.demo.util.collection.map;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
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
		
		iterator();
	}
	
	/**
	 * HashMap 中的 Iterator 迭代器是 fail-fast 的，而 Hashtable 的 Enumerator 不是 fail-fast 的。
	 * 所以，当其他线程改变了HashMap 的结构，如：增加、删除元素，将会抛出 ConcurrentModificationException 异常，而 Hashtable 则不会
	 */
	public static void iterator() {
		Map<String,String> hashtable=new Hashtable<>();
		hashtable.put("t1", "1");
	    hashtable.put("t2", "2");
	    hashtable.put("t3", "3");
	    
	    Enumeration<Map.Entry<String, String>> iterator1 = (Enumeration<Map.Entry<String, String>>) hashtable.entrySet().iterator();
	    hashtable.remove(iterator1.nextElement().getKey());
	    while (iterator1.hasMoreElements()) {
	        System.out.println(iterator1.nextElement());
	    }
	    
	    Map<String, String> hashMap = new HashMap<>();
	    hashMap.put("h1", "1");
	    hashMap.put("h2", "2");
	    hashMap.put("h3", "3");
	    //Iterator<Entry<String, String>> iterator2 = hashMap.entrySet().iterator();
	    /*while(iterator2.hasNext()) {
	    	System.out.println(iterator2.next());
	    }*/
	    Enumeration<Map.Entry<String, String>> iterator2 = (Enumeration<Map.Entry<String, String>>) hashMap.entrySet().iterator();
	    hashMap.remove(iterator2.nextElement().getKey());
	    while(iterator2.hasMoreElements()) {
	    	System.out.println(iterator2.nextElement());
	    }
	    
	}
	
}
