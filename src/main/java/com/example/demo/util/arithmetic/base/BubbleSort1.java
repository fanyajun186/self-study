package com.example.demo.util.arithmetic.base;

import java.util.Arrays;

/**
 * 冒泡排序法基础版
 * @author: yajun.fan
 * @date: 2019年11月14日
 */
public class BubbleSort1 {

	public static void main(String[] args) {
		int[] array = new int[]{5,8,6,3,9,2,1,7};
		sort(array);
		System.out.println(Arrays.toString(array));
	}
	
	 public static void sort(int array[]){

	      for(int i = 0; i < array.length - 1; i++){

	          for(int j = 0; j < array.length - i - 1; j++) {

	              int tmp  = 0;

	             if(array[j] > array[j+1]){

	                  tmp = array[j];

	                  array[j] = array[j+1];

	                  array[j+1] = tmp;
	                  
	              }

	          }

	      }
	 }
}
