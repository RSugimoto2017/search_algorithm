import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Vector;
import java.util.Hashtable;

public class Bunki {
	
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

		tree[0].addChild(tree[1], 2);
		tree[0].addChild(tree[2], 1);
		tree[0].addChild(tree[3], 1);
		tree[1].addChild(tree[2], 1);
		tree[2].addChild(tree[3], 2);
		tree[2].addChild(tree[4], 1);
		tree[3].addChild(tree[4], 1);
		tree[3].addChild(tree[6], 1);
		tree[3].addChild(tree[8], 1);
		tree[4].addChild(tree[5], 1);
		tree[4].addChild(tree[8], 1);
		tree[5].addChild(tree[7], 1);
		tree[6].addChild(tree[8], 1);
		tree[7].addChild(tree[9], 1);
		tree[8].addChild(tree[9], 1);	
		
		startNode = tree[0];
		goalNode = tree[9];
		
		Vector openlist = new Vector();
		Vector closedlist = new Vector();
		
		Node checkNode;
		int Cost = 0;
		int totalCost = 0;
		
		openlist.add(startNode);
		
		while(!openlist.isEmpty()){
			
			checkNode = (Node) openlist.get(0);
			openlist.remove(0);
			
			printNodename(checkNode);
			System.out.println("を探索中");
		
			
			if(checkNode == goalNode) {
				
				printNodename(goalNode);
				System.out.println("への経路が見つかりました");
				System.out.println("経路を以下に示します。");
				print_keiro(checkNode);
				System.out.println("コスト：" + checkNode.gCost);
				break;
				
			}else {
				
				closedlist.add(checkNode);
				
				for(int i = 0 ;i < checkNode.children.size(); i++) {
					
					Cost = checkNode.getCost(checkNode.getChild(i));
					if(openlist.contains(checkNode.getChild(i)) == false && closedlist.contains(checkNode.getChild(i)) == false) {
						
						checkNode.getChild(i).setParent(checkNode);
						totalCost = checkNode.gCost + Cost;
						checkNode.getChild(i).gCost = totalCost;
						
					}else if(openlist.contains(checkNode.getChild(i)) == true) {
						
						totalCost = checkNode.gCost + Cost;
						
						if(totalCost < Cost) {
							checkNode.getChild(i).gCost = totalCost;
						}
						
					}
					
					openlist.add((Node) checkNode.getChild(i));
					System.out.print("ソート前オープンリスト：");
					printList(openlist);
					openlist = sortList(openlist);
					System.out.print("ソート後オープンリスト：");
					printList(openlist);
					
				}
			}
		} 

	}
	
	private static void printNodename(Node n) {	//ノードの名前を表示する
		System.out.print(n.name);
	}
	
	private static void printList(Vector list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print("[" + ((Node)list.get(i)).name + "]");
		}
		System.out.println();
	}
	
	private static void print_keiro(Node n) {	//経路を表示する
		if(n != startNode) {
			printNodename(n);
			System.out.println();
			print_keiro(n.Parent);
		}else {
			printNodename(startNode);
			System.out.println();
		}
	}
	private static Vector sortList(Vector list) {// リストの要素をソートする
		Node n;
		Vector newopenlist = new Vector();
		n = (Node) list.get(0);
		int temp = n.gCost;
		int No;
		while (list.size() != 0) {
			n = (Node) list.get(0);
			temp = n.gCost;
			No = 0;
			for (int j = 1; j < list.size(); j++) {
				n = (Node) list.get(j);
				if (temp > n.gCost) {
					temp = n.gCost;
					No = j;
				}
			}
			newopenlist.add(list.get(No));
			list.remove(No);
		}
		return newopenlist;
	}
}

class Node{
	
	String name;
	Vector<Node> children = new Vector<>();
	Node Parent;	//親
	int gCost;	//親からのコスト
	Hashtable CostOfChildren = new Hashtable();
	
	Node(String name){
		this.name = name;
	}
	
	public Node getChild(int i) {
		return this.children.get(i);
	}
	
	public void setParent(Node n) {
		this.Parent = n;
	}
	
	public int getCost(Node n) {
		return ((Integer) CostOfChildren.get(n));
	}
	
	public void addChild(Node theChild, int theCost) {
		children.addElement(theChild);
		CostOfChildren.put(theChild, new Integer(theCost));
	}
}
