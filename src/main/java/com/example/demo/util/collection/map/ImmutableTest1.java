package com.example.demo.util.collection.map;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * Description:简单地说，如果一个对象实例不能被更改就是一个Immutable的对象，Java SDK提供的大量值对象，比如String等都是Immutable的对象
 * ImmutableMap 的作用就是：可以让java代码也能够创建一个对象常量映射，来保存一些常量映射的键值对。省的写大量的if...else或switch
 * @author fanyj
 * @date 2020年1月14日
 */
/**
 * Description:
 * @author fanyj
 * @date 2020年1月14日
 */
public class ImmutableTest1 {

	public static void main(String[] args) {
		test1("aa","bb","cc");
		Integer key = 30;
        System.out.println("key = " + key + "，的提示语是："+test2(key));
	}

	
	/**   
	 * Description:
	 * @param key
	 * @return  
	 */
	private static String test2(Integer key) {
		Map<Integer, String> INTEGER_STRING_MAP = new ImmutableMap.Builder<Integer, String>().
	                    put(30, "IP地址或地址段").
	                    put(31, "端口号或范围").
	                    put(32, "IP地址或地址段").
	                    put(33, "端口号或范围").
	                    put(34, "代码值").
	                    put(38, "探针名称").
	                    put(39, "网络协议号(protocol)").
	                    put(40, "ipv6源IP(ipv6_src_addr)").
	                    put(41, "ipv6目标IP(ipv6_dst_addr)").
	                    put(42, "网络协议名称(protocol_map)").
	                    put(43, "输入接口snmp(input_snmp)")
	                    .build();
		return INTEGER_STRING_MAP.get(key);
	}


	/**   
	 * Description:
	 * 适合:确定性的配置, 比如根据不同的key值得到不同的请求url。写单元测试 
	 * 不适合:key, value为未知参数, 可能有null产生的情况 
	 * @param arrs
	 * @return  
	 */
	private static String test1(String... arrs) {
		Map<String,Object> map=new HashMap<String,Object>();
		for (String str : arrs) {
			map.put(str, str);
		}
		
	    Map<String,Object> immutableMap = new ImmutableMap.Builder<String,Object>()
	    		.putAll(map)
	            .put("k1","v1")
	            .put("k2","v2")
	            .build();
	    //初始化后不允许再变化
	    //immutableMap.put("k3", "hehe");
	    System.out.println(immutableMap.get("k1"));
	    return null;
	}

}
