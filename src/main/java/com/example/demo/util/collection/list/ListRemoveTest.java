package com.example.demo.util.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListRemoveTest {

	public static void main(String[] args) {

		testRemove();
		
		
	}

	private static void testRemove() {
		
		List<String> arrayList1 = new ArrayList<String>();
		arrayList1.add("1");
		arrayList1.add("2");
		for (String s : arrayList1) {
			 if("1".equals(s)){
			 arrayList1.remove(s);
			 }
		}
		List<String> arrayList2 = new ArrayList<String>();
		arrayList2.add("2");
		arrayList2.add("1");
		/*for (String s : arrayList2) {
			 if("1".equals(s)){
			 arrayList2.remove(s);
			 }
		}*/
		
		Iterator<String> iterator = arrayList2.iterator();
		while(iterator.hasNext()){
		 String item = iterator.next();
		 if("1".equals(item)){
		 iterator.remove();
		 }
		}
		
	}

}
