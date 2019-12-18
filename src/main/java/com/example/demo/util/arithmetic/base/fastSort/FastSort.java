package com.example.demo.util.arithmetic.base.fastSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FastSort {

	public static void main(String[] args) {
		
		int[] arr=new int[] {4,7,6,5,3,2,8,1};
		quickSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));

	}

	/**
	 * 快速排序
	 * @param arr
	 * @param startIndex
	 * @param endIndex
	 */
	private static void quickSort(int[] arr, int startIndex, int endIndex) {
		//判断迭代的终点
		if(startIndex>=endIndex) {
			return;
		}
		//双边循环取基准数
		//int pivotIndex=doublePartition(arr,startIndex,endIndex);
		
		//单边循环取基准数
		int pivotIndex=singlePartition(arr, startIndex, endIndex);
		//左边快排
		quickSort(arr,startIndex,pivotIndex-1);
		//右边快排
		quickSort(arr,pivotIndex+1,endIndex);
	}
	
	/**
	 * 快速排序非迭代
	 * @param arr
	 * @param startIndex
	 * @param endIndex
	 */
	private static void quickSortWithStack(int[] arr, int startIndex, int endIndex) {
		//判断迭代的终点
		if(startIndex>=endIndex) {
			return;
		}
		Stack<Map<String,Integer>> quickSortStack=new Stack<Map<String,Integer>>();
		Map<String,Integer> root =new HashMap<String, Integer>();
		root.put("startIndex", startIndex);
		root.put("endIndex", endIndex);
		quickSortStack.push(root);
		if(!quickSortStack.isEmpty()) {
			Map<String, Integer> map = quickSortStack.pop();			
		}
		//单边循环取基准数
		int pivotIndex=singlePartition(arr, startIndex, endIndex);
		//左边快排
		quickSort(arr,startIndex,pivotIndex-1);
		//右边快排
		quickSort(arr,pivotIndex+1,endIndex);
	}
	

	/**
	 *   分治（双边循环取基准数）
	 * @param arr
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static int doublePartition(int[] arr, int startIndex, int endIndex) {
		//取第1个位置（也可以选择随机位置）的元素作为基准元素
		int pivot = arr[startIndex];
		int left=startIndex;
		int right=endIndex;
		
		while(left!=right) {
			while(left<right && arr[right]>pivot) {
				right--;
			}
			
			while(left<right && arr[left]<=pivot) {
				left++;
			}
			//交换left和right 指针所指向的元素
	         if(left<right) {
	             int p = arr[left];
	             arr[left] = arr[right];
	             arr[right] = p;
	         }
		}
		
	    //pivot 和指针重合点交换
        arr[startIndex] = arr[left];
        arr[left] = pivot;
        return left;
	}	
	
	/**
	 * 分治（单边循环取基准数）
	 * @param arr
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static int singlePartition(int[] arr, int startIndex, int endIndex) {
		
		int mark = startIndex;
		int pivot = arr[startIndex];
		
		for (int i = startIndex+1; i <=endIndex; i++) {
			if(arr[i]<pivot) {
				mark++;
				int p=arr[mark];
				arr[mark]=arr[i];
				arr[i]=p;
				
			}
			
		}
		arr[startIndex]=arr[mark];
		arr[mark]=pivot;
		return mark;
		
	}

}
