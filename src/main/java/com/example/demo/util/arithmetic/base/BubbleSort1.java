package com.example.demo.util.arithmetic.base;

public class BubbleSort1 {

	public static void main(String[] args) {
		

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
