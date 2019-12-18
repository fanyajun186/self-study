package com.example.demo.util.arithmetic.tree.NTree;


import java.util.ArrayList;
import java.util.List;
 
public class TestNode {
 
	public static void main(String[] args) {
		List<Node> sonList = new ArrayList<Node>();//根结点第一层结点list
		List<Node> sonList11 = new ArrayList<Node>();//第一层第一个结点的子层节点list
		List<Node> sonList31 = new ArrayList<Node>();//第一层第三个结点的子层节点list
			Node node11 = new Node(11, null);
			Node node12 = new Node(12, null);
			Node node13 = new Node(13, null);
			sonList11.add(node11);
			sonList11.add(node12);
			sonList11.add(node13);
			Node node1 = new Node(1, sonList11);
			Node node2 = new Node(2, null);
			Node node3 = new Node(3, sonList31);
			sonList.add(node1);
			sonList.add(node2);
			sonList.add(node3);
			Node node31 = new Node(31, null);
			Node node32 = new Node(32, null);
			Node node33 = new Node(33, null);
			sonList31.add(node31);
			sonList31.add(node32);
			sonList31.add(node33);
		Node root = new Node(0, sonList);
		preOrder(root);
	}
 
	/**
	 * @方法的功能 前序遍历
	 * @返回类型void
	 * @作者835890
	 * @日期2017年4月26日
	 */
	private static void preOrder(Node root) {
		if(root != null){
			System.out.print(root.value + " ");
			if(root.sonList != null){
				/*for (int i = 0; i < root.sonList.size(); i++) {
					preOrder(root.sonList.get(i));
				}*/
				
				for(Node node : root.sonList) {
					preOrder(node);
				}
			}
		}
	}
	
}
