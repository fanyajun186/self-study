package com.example.demo.util.arithmetic.tree.BinTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinTreeForEach {
	
	private static class TreeNode{
		private Integer data;
		TreeNode leftChild;
		TreeNode rightChild;
		
		TreeNode(int data){
			this.data=data;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Integer> inputList =new LinkedList<Integer>(Arrays.asList(new Integer[] {3,2,9,null,null,10,null,null,8,null,4}));
		TreeNode treeNode=createBinaryTree(inputList);
		System.out.println("深度遍历======");
		System.out.println("前序遍历：");
		preOrderTraveral(treeNode);
		System.out.println("中序遍历：");
		inOrderTraveral(treeNode);
		System.out.println("后序遍历：");
		postOrderTraveral(treeNode);		
	
		System.out.println("非递归前序遍历：");
		preOrderTraveralWithStack(treeNode);
		
		System.out.println("广度遍历======");
		levelOrderTraversal(treeNode);
	}



	/**
	 * 创建二叉树-递归遍历
	 * @param inputList
	 * @return
	 */
	private static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
		TreeNode node=null;
		if(inputList!=null && inputList.size()>0) {
			Integer data=inputList.removeFirst();
			if(data!=null) {
				node=new TreeNode(data);
				node.leftChild=createBinaryTree(inputList);
				node.rightChild=createBinaryTree(inputList);
			}
		}
		return node;
	}
	
	/**
	 * 广度遍历
	 * @param treeNode
	 */
	private static void levelOrderTraversal(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode treeNode = queue.poll();
			System.out.println(treeNode.data);
			if(treeNode.leftChild!=null) {
				queue.offer(treeNode.leftChild);
			}
			if(treeNode.rightChild!=null) {
				queue.offer(treeNode.rightChild);
			}
		}
	}
	
	/**
	 * 前序遍历-非递归
	 * @param treeNode
	 */
	private static void preOrderTraveralWithStack(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode treeNode = node;
		while(treeNode!=null || !stack.isEmpty()) {
			//迭代访问节点的左孩子，并入栈
			while(treeNode!=null) {
				System.out.println(treeNode.data);
				stack.push(treeNode);				
				treeNode = treeNode.leftChild;
			}
			//没有左孩子，父节点出栈顶，访问节点右孩子
			if(!stack.isEmpty()) {
				treeNode = stack.pop();
				treeNode=treeNode.rightChild;
				
			}
		}
	}

	/**
	 * 前序遍历-递归遍历
	 * @param inputList
	 */
	private static void preOrderTraveral(TreeNode treeNode) {
		if(treeNode!=null) {
			Integer data = treeNode.data;
			System.out.println(data);
			preOrderTraveral(treeNode.leftChild);
			preOrderTraveral(treeNode.rightChild);
		}		
	}
	
	/**
	 * 中序遍历-递归遍历
	 * @param treeNode
	 */
	private static void inOrderTraveral(TreeNode treeNode) {		
		if(treeNode!=null) {
			inOrderTraveral(treeNode.leftChild);
			System.out.println(treeNode.data);
			inOrderTraveral(treeNode.rightChild);
		}
	}
	
	/**
	 * 后续遍历-递归遍历
	 * @param treeNode
	 */
	private static void postOrderTraveral(TreeNode treeNode) {
		if(treeNode!=null) {
			postOrderTraveral(treeNode.leftChild); 
			postOrderTraveral(treeNode.rightChild); 
			System.out.println(treeNode.data);
		}		
	}


	
}
