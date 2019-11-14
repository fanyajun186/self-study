package com.example.demo.util.collection.arr;

import java.util.Scanner;

public class SanJiao {
    public static void main(String[] args) {
        // 从控制台获取行数
        Scanner sc = new Scanner(System.in);
        System.out.print("打印杨辉三角形的行数：");
        int row = sc.nextInt();
        // 根据行数定义好二维数组，由于每一行的元素个数不同，所以不定义每一行的个数
        int[][] arr = new int[row][];
        // 遍历二维数组
        for (int i = 0; i < row; i++) {
            // 初始化每一行的这个一维数组
            arr[i] = new int[i + 1];
            //数字前空格占位符
            for (int j = 1; j <= row - i; j++) {
                System.out.print(" ");
            }
            // 遍历这个一维数组，添加元素
            for (int j = 0; j <= i; j++) {
                // 每一列的开头和结尾元素为1，开头的时候，j=0，结尾的时候，j=i
                if (j == 0 || j == i) {
                    arr[i][j] = 1;
                } else { 
                    // 每一个元素是它上一行的元素和斜对角元素之和
                    arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

