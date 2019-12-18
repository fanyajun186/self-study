package com.example.demo.util.arithmetic.base.insertSort;

import java.util.Arrays;

/**
 * 插入排序
 * 
 * @author: yajun.fan
 * @date: 2019年12月16日
 */
public class insertSort {

	public static void main(String[] args) {
		int[] arr = new int[]{3,4,2,1,5,6,7,8};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 从第一个元素开始，该元素可以认为已经被排序；
	 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
	 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
	 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
	 * 将新元素插入到该位置后；
	 * 重复步骤2~5。
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {	
		int current;
		for (int i = 0; i < arr.length-1; i++) {
			current=arr[i+1];
			int preIndex=i;
			//前面索引必须大于等于0，后面的值小于有序数列最右面的话就会继续往前
			while(preIndex>=0 && arr[preIndex]>current) {
				arr[preIndex+1]=arr[preIndex];
				preIndex--;
			}
			arr[preIndex+1]=current;
		}
	}

}
