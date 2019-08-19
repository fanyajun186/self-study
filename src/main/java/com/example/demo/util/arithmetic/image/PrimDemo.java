package com.example.demo.util.arithmetic.image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图-最小生成树
 * https://mp.weixin.qq.com/s?__biz=MzI2NjA3NTc4Ng==&mid=2652081198&idx=1&sn=6a28015380d62696485f48f5bf342586&chksm=f17487cbc6030eddbba700ccf56fff0df9030bdc534a7644d3a196072e2b3000e26270515dd1&scene=0&xtrack=1&key=a6e428af7bc20b4ebb68328701cada8859a05bbe4c994c90113fcf799c2fdbf219b019aff6c465090ab1f459bcaf1edd2faf80b4a7a1b916bc4a86305cc350e856870b61800bb23c67dfa9af85a2cb8e&ascene=1&uin=Njc0NTEzNTIw&devicetype=Windows+10&version=62060833&lang=zh_CN&pass_ticket=SEZbm0bTEgIwl7PQnrN%2BA4yXhimtARaKY3VD7R2sj7ZSuQ3TBkOUCYxT0ogxYjdy
 * @author: yajun.fan
 * @date: 2019年7月5日
 */
public class PrimDemo {
	
	final static int INF = Integer.MAX_VALUE;

	public static int[] prim(int[][] matrix){

	    List<Integer> reachedVertexList = new ArrayList<Integer>();

	    //选择顶点0为初始顶点，放入已触达顶点集合中
	    reachedVertexList.add(0);

	    //创建最小生成树数组，首元素设为-1
	    int[] parents = new int[matrix.length];

	    parents[0] = -1;

	    //边的权重
	    int weight;

	    //源顶点下标
	    int fromIndex = 0;
	    
	    //目标顶点下标
	    int toIndex = 0;

	    while (reachedVertexList.size() < matrix.length) {
	        weight = INF;
	        //在已触达的顶点中，寻找到达新顶点的最短边
	        for (Integer vertexIndex : reachedVertexList) {
	            for (int i = 0; i < matrix.length; i++) {
	                if (!reachedVertexList.contains(i)) {
	                    if (matrix[vertexIndex][i] < weight) {
	                        fromIndex = vertexIndex;
	                        toIndex = i;
	                        weight = matrix[vertexIndex][i];
	                    }
	                }
	            }
	        }

	        //确定了权值最小的目标顶点，放入已触达顶点集合
	        reachedVertexList.add(toIndex);

	        //放入最小生成树的数组
	        parents[toIndex] = fromIndex;
	    }
	    return parents;
	}


	public static void main(String[] args) {
		
	    int[][] matrix = new int[][]{

	            {0, 4, 3, INF, INF},

	            {4, 0, 8, 7, INF},

	            {3, 8, 0, INF, 1},

	            {INF, 7, INF, 0, 9},

	            {INF, INF, 1, 9, 0}
	    };

	    int[] parents = prim(matrix);

	    System.out.println(Arrays.toString(parents));
	}
}
