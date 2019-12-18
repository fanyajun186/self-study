package com.example.demo.util.arithmetic.tree.NTree;

import java.util.List;
 
public class Node {
 
	protected int value;
	
	protected List<Node> sonList;
	
	public Node(int value,List<Node> sonList){
		this.value = value;
		this.sonList = sonList;
	}
	
}