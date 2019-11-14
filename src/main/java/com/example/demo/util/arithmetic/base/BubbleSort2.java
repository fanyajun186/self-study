package com.example.demo.util.arithmetic.base;

import java.util.Arrays;

/**
 * 冒泡排序最优版
 * @author: yajun.fan
 * @date: 2019年11月14日
 */
public class BubbleSort2 {
	
	public static void main(String[] args){
		int[] array = new int[]{3,4,2,1,5,6,7,8};
		sort(array);
		System.out.println(Arrays.toString(array));
	}
	
	public static void sort(int array[]){
		//记录最后一次交换的位置
		int lastExchangeIndex = 0;
		//无序数列的边界，每次比较只需要比到这里为止
		int sortBorder = array.length - 1;
		for(int i = 0; i < array.length - 1; i++){
		 //有序标记，每一轮的初始值都是true
			boolean isSorted = true;
			for(int j = 0; j < sortBorder; j++){
				int tmp = 0;
				if(array[j] > array[j+1]){
					tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
					// 因为有元素进行交换，所以不是有序的，标记变为false
					isSorted = false;
					// 更新为最后一次交换元素的位置
					lastExchangeIndex = j;					
				}
			}
			sortBorder = lastExchangeIndex;
			System.out.println("第"+(i+1)+"轮有序界点索引"+sortBorder);
			if(isSorted){
				break;
			}
		}
	}
}
