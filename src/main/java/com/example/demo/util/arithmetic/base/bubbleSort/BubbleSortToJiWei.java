package com.example.demo.util.arithmetic.base.bubbleSort;

import java.util.Arrays;

public class BubbleSortToJiWei {

	public static void main(String[] args) {
		int[] arr = {2,3,4,5,6,7,8,1};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void sort(int[] arr) {
		int tmp = 0;
		//最大执行轮数，数组
		for (int i = 0; i < arr.length/2; i++) {
			//有序标识，每一轮的初始值都是true
			boolean isSorted = true;
			//奇数轮从左到右比较和交换
			for (int j = i; j < arr.length-i-1; j++) {
				if(arr[j]>arr[j+1]) {
					tmp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
					isSorted=false;
				}				
			}
			if(isSorted) {
				break;
			}
			
			//偶数轮开始前将标识符复位
			isSorted = true;
			//偶数轮,从右到左比较交换
			for (int j = arr.length-i-1; j >i; j--) {
				if(arr[j] < arr[j-1]){
					tmp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = tmp;
					// 因为有元素进行交换，所以不是有序的，标记变为false
					isSorted = false;
				}
			}
			if(isSorted) {
				break;
			}
			
		}
		
	}
	
}
