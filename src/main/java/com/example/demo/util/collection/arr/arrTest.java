package com.example.demo.util.collection.arr;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;


public class arrTest {

    public static void main(String[] args) {
        
        String arrstr="2017.03.15";
        String[] split = arrstr.split("\\.");
        System.out.println(".........."+split.length);
        
        Long lon1=130L;
        Long lon2=130L;
        System.out.println(lon1==lon2);
        System.out.println(lon1.equals(lon2));
        System.out.println(lon1.longValue()==lon2.longValue());
        
        
        System.out.println("=================数组转字符串===================");
        int[] intArray={1,2,3,4,5};        
        String str = Arrays.toString(intArray);
        System.out.println(intArray);
        System.out.println(str);
        
        System.out.println("=================数组转列表===================");
        String[] stringArray={"agddddddddddddd","b","c","d","e"};
        List<String> asList = Arrays.asList(stringArray);
        System.out.println(asList);
        
        System.out.println("=================检查数组是否包含某个值===================");
        System.out.println(asList.contains("ag"));
        
        
        System.out.println("=================数组合并===================");
        int[] intArray2={6,7,8,9,10};        
        int[] addAll = ArrayUtils.addAll(intArray, intArray2);
        System.out.println(Arrays.toString(addAll));
        
        System.out.println("================= 将一个数组列表转换为数组===================");   
        
        String[] stringArray2 = { "a", "b", "c", "d", "e" };  
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(stringArray2));  
        String[] stringArr = new String[arrayList.size()];  
        arrayList.toArray(stringArr);  
        for (String s : stringArr)  
            System.out.println(s); 
        
        System.out.println("================= 将一个数组列表转换为set===================");        
        Set<String> set=new HashSet<String>(Arrays.asList(stringArray));
        for (String str2 : set) {
            System.out.println(str2);
        }
        
        System.out.println("================= 逆向一个数组===================");
        
        ArrayUtils.reverse(intArray2);
        System.out.println(Arrays.toString(intArray2));
        
        System.out.println("================= 移除数组中的元素===================");
        
        int[] newArr=ArrayUtils.removeElement(intArray2, 4);
        System.out.println(Arrays.toString(newArr));
        
        System.out.println("================= 将整数转换为字节数组===================");
        byte[] bytes = ByteBuffer.allocate(4).putInt(8).array();  
        
        for (byte t : bytes) {  
           System.out.format("0x%x ", t);  
        }  
    }
    
    
}
