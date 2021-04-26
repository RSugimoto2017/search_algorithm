import java.util.*;

public class Width {
	
	static Node tree[] = new Node[10];
	static Node startNode;
	static Node goalNode;

	public static void main(String[] args) {
		
		tree[0] = new Node("和歌山");
		tree[1] = new Node("神戸");
		tree[2] = new Node("大阪");
		tree[3] = new Node("奈良");
		tree[4] = new Node("京都");
		tree[5] = new Node("福井");
		tree[6] = new Node("津");
		tree[7] = new Node("金沢");
		tree[8] = new Node("名古屋");
		tree[9] = new Node("岐阜");
		
		startNode = tree[0];
		goalNode = tree[9];
		
		tree[0].children.addElement(tree[1]);
		tree[0].children.addElement(tree[2]);
		tree[0].children.addElement(tree[3]);
		tree[1].children.addElement(tree[2]);
		tree[2].children.addElement(tree[3]);
		tree[2].children.addElement(tree[4]);
		tree[3].children.addElement(tree[4]);
		tree[3].children.addElement(tree[6]);
		tree[3].children.addElement(tree[8]);
		tree[4].children.addElement(tree[5]);
		tree[4].children.addElement(tree[8]);
		tree[5].children.addElement(tree[7]);
		tree[6].children.addElement(tree[8]);
		tree[7].children.addElement(tree[9]);
		tree[8].children.addElement(tree[9]);	
		
		Queue<Node> queue = new ArrayDeque<>();
		Queue<Node> c_queue = new ArrayDeque<>();
		
		Node checkNode;
		
		queue.offer(startNode);
		
		while(!queue.isEmpty()){
			
			checkNode = queue.poll();
			
			printNodename(checkNode);
			System.out.println("を探索中");
		
			
			if(checkNode == goalNode) {
				printNodename(goalNode);
				System.out.println("への経路が見つかりました");
				System.out.println("経路を以下に示します。");
				print_keiro(checkNode);
				
				break;
			}else {
				c_queue.add(checkNode);
				for(int i = 0 ;i < checkNode.children.size(); i++) {
					if(queue.contains(checkNode.children.get(i)) == false && c_queue.contains(checkNode.children.get(i)) == false) {
						checkNode.children.get(i).setPointer(checkNode);
					}
					queue.add((Node) checkNode.children.get(i));
				}
			}
		} 

	}
	
	private static void printNodename(Node n) {
		System.out.print(n.name);
	}
	
	private static void print_keiro(Node n) {
		if(n != startNode) {
			printNodename(n);
			System.out.println();
			print_keiro(n.Pointer);
		}else {
			printNodename(startNode);
			System.out.println();
		}
	}
}

class Node{
	
	String name;
	Vector<Node> children = new Vector<>();
	Node Pointer;
	Node(String name){
		this.name = name;
	}
	
	public void setPointer(Node n) {
		this.Pointer = n;
	}
}
