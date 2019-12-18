package com.example.demo.util.arithmetic.base.bubbleSort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = new int[]{3,4,2,1,5,6,7,8};
		sort(arr);
		System.out.println(Arrays.toString(arr));

	}

	private static void sort(int[] arr) {
		int lastExchangeIndex = 0;
		
		for (int i = 0; i < arr.length-1; i++) {
			int sortBorderIndex=arr.length-1-i;
			
			boolean flag=true;
			for (int j = 0; j <sortBorderIndex ; j++) {
				if(arr[j]>arr[j+1]) {
					int temp=arr[j+1];
					arr[j+1]=arr[j];
					arr[j]=temp;
					flag=false;
					lastExchangeIndex=j;
				}				
			}
			sortBorderIndex=lastExchangeIndex;
			if(flag) {
				break;
			}
		}
		
	}

}
