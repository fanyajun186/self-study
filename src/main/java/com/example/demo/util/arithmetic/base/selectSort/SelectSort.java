package com.example.demo.util.arithmetic.base.selectSort;

import java.util.Arrays;

/**
 * 选择排序
 * 每次把剩余最小的放在数组遍历起始点
 * @author: yajun.fan
 * @date: 2019年12月16日
 */
public class SelectSort {

	public static void main(String[] args) {
		int[] arr = new int[]{3,4,2,1,5,6,7,8};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minNum=i;
			for (int j = i; j < arr.length; j++) {
				//大于就是升序，小于就是升序
				if(arr[minNum]>arr[j]) {
					minNum=j;
				}
			}
			int temp=arr[i];
			arr[i]=arr[minNum];
			arr[minNum]=temp;
			
		}
		
	}

}
