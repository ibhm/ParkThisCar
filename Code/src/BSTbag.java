

public class BSTbag<Value> {
	private Node root;
	private Stack st;
	
	private class Node{
		private Comparable val;
		private Node left, right;
		private Boolean red;
		private int sz;
		private Value i;
		
		public Node(Comparable value,Value intersectionNum, int n, Boolean red){
			val = value;
			i = intersectionNum;
			left = null;
			right = null;
			sz = n;
			this.red = red;
		}
	}
	
	public BSTbag(){
		root = null;
	}
	
	public void add(Comparable v, Value intersectionNum){
		root = add(root, v,intersectionNum);
		root.red = false;
	}
	private Node add(Node x, Comparable v, Value intersectionNum){
		if(x == null) return new Node(v,intersectionNum,1,true); 
		int cmp = v.compareTo(x.val);
		if (cmp < 0) x.left = add(x.left, v,intersectionNum);
		else if (cmp > 0) x.right = add(x.right, v,intersectionNum);
		if(x.right!=null && x.left!=null) {
			if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
			if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
			if (isRed(x.left) && isRed(x.right)) flipColours(x);
		}
		x.sz = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	//Temporary, not perfect, should rebalence the tree each time
	public Comparable getItem(){
		if (root == null) return null; 
		if(root.left != null) return getItem(root.left, root);
		if (root.right != null) return getItem(root.right, root);
		return root.val; 
	}
	private Comparable getItem(Node h, Node parent){
		if(h.left != null) return getItem(h.left, h);
		if(h.right != null) return getItem(h.right,h);
		if(parent.left == h) parent.left = null;
		else parent.right = null;
		return h.val;
	}
	
	public Value get(Comparable val){
		return get(val,root);
	}
	private Value get(Comparable v, Node root){
		if(v.equals(root.val)) return root.i;
		if(less(v,root.val) && root.left != null){
			return get(v,root.left);
		}
		else if (root.right != null){
			return get(v,root.right);
		}
		else return null;
	}
	
	public int size(){
		return size(root);
	}
	private int size(Node x){
		if (x == null) return 0;
		else return x.sz;
	}
	
	public Boolean contains(Comparable v){
		return contains(v,root);
	}
	
	private Boolean contains (Comparable v, Node root){
		if(root == null) return false;
		if(v.equals(root.val)) return true;
		if(less(v,root.val) && root.left != null){
			return contains(v,root.left);
		}
		else if (root.right != null){
			return contains(v,root.right);
		}
		else return false;
	}
	
	private Node rotateLeft(Node n){
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		x.red = n.red;
		n.red = true;
		x.sz = n.sz;
		n.sz = 1 + size(n.left) + size(n.right);
		return x;
	}
	private Node rotateRight(Node n){
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		x.red = n.red;
		n.red = true;
		x.sz = n.sz;
		n.sz = 1 + size(n.left) + size(n.right);
		return x;
	}
	private void flipColours(Node n){
		n.red = true;
		n.left.red = false;
		n.right.red = false;
	}
	
	private Boolean less(Comparable a, Comparable b){
		return a.compareTo(b) < 0;
	}
	private Boolean isRed(Node n){
		if(n!=null)
			return n.red;
		return false;
	}
}
