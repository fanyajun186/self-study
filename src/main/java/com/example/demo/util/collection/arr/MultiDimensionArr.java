package com.example.demo.util.collection.arr;
/**
 * 多维度数组
 * @author: yajun.fan
 * @date: 2019年11月13日
 */
public class MultiDimensionArr {
	
	public static void main(String[] args) {
	    String[][][] namelist = {
	    		{ { "张阳", "李风", "陈飞" }, { "乐乐", "飞飞", "小风" } },
	            { { "Jack", "Kobe" }, { "Lucy", "Lily", "Rose" } }, 
	            { { "徐璐璐", "陈海" }, { "李丽丽", "陈海清" } } 
	            };
	    for (int i = 0; i < namelist.length; i++) {
	        for (int j = 0; j < namelist[i].length; j++) {
	            for (int k = 0; k < namelist[i][j].length; k++) {
	                System.out.println("namelist[" + i + "][" + j + "][" + k + "]=" + namelist[i][j][k]);
	            }
	        }
	    }
	}

}
