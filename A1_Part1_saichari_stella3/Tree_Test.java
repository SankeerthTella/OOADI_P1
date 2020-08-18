class Tree_Test {

	public static void main(String[] args) {
		AbsTree tr = new Tree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
	     //tr=tr.left;
		//System.out.println(tr.left.value);
		
		tr.delete(20);
		tr.delete(20);
		
		tr.delete(20);
		
		tr.delete(150);
		tr.delete(100);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
		/* tr=tr.left;
			System.out.println(tr.left.value);*/
		//System.out.println(tr.value);
		
	}
}

class DupTree_Test {

	public static void main(String[] args) {
		AbsTree tr = new DupTree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		
		tr.delete(20);
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
	}
}

abstract class AbsTree {
	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) {
		if (value == n)
			count_duplicates();
		else if (value < n)
			if (right == null) {
				right = add_node(n);
				right.parent = this;

			} else
			{
				right.insert(n);
			}
		else if (left == null) {
			left = add_node(n);
			left.parent = this;

		} else
		{
			left.insert(n);
		}
	}

	public void delete(int n) {  
		AbsTree t = find(n);

		if (t == null) { // n is not in the tree
			System.out.println("Unable to delete " + n + " -- not in the tree!");
			return;
		}

		int c = t.get_count();
		if (c > 1) {
			t.set_count(c-1);
			return;
		}

		if (t.left == null && t.right == null) { // n is a leaf value
			if (t != this)
				case1(t);
			else
				System.out.println("Unable to delete " + n + " -- tree will become empty!");
			return;
		}
		if (t.left == null || t.right == null) { // t has one subtree only
			if (t != this) { // check whether t is the root of the tree
				case2(t);
				return;
			} else {
				if (t.right == null)
					case3L(t);
				else
					case3R(t);
				return;
			}
		}
		// t has two subtrees; go with smallest in right subtree of t
		case3R(t);
	}

	protected void case1(AbsTree t) { // remove the leaf
					if(t.parent.value>t.value)
					{
						t.parent.left = null;
					}
					else 
					{
						t.parent.right = null;
					}
					t.parent=null;
					t=null;
			}
	
	protected void case2(AbsTree t) { // remove internal node
		// to be filled by you
	
		if(t.left==null && t.right!=null)
		{
			if(t.parent.value>t.value)
			{
			//t.parent = t.right;
			t.parent.left = t.right;
			t.right.parent = t.parent;
			t.right = null;
			t.parent  = null;
			t = null;
			}
			else
			{
				t.parent.right = t.right;
				t.right.parent = t.parent;
				t = null;

			}
		}
		else
		{
		
			if(t.parent.value>t.value)
			{
			//t.parent = t.right;
			t.parent.left = t.left;
			t.left.parent = t.parent;
			t = null;
			}
			else
			{

				t.parent.right = t.left;
				t.left.parent = t.parent;
				t = null;

			}
			
		}
	}
	
	protected void case3L(AbsTree t) { // replace t.value and t.count
		// to be filled by you
		   AbsTree k=t.left.max();
		   int temp=t.value;
			
			t.set_count(k.get_count());
			k.set_count(1);
			t.value=k.value;
			k.value=temp;
		   if(k.left!=null)
			 {
				 k.left.parent=k.parent;
			 k.parent.right=k.left;
			 }
		   k.parent.right=null;
		   k.parent=null;
		  
		  
		   
		   
		}
	protected void case3R(AbsTree t) { // replace t.value
	//if(t.parent==null)
	//{
		AbsTree k=t.right.min();
		int temp=t.value;
	
		t.set_count(k.get_count());
		k.set_count(1);
		t.value=k.value;
		if(t.right!=k)
		 {
		 k.parent.left=null;
		 }
		else
		{
			k.parent.right=null;
		}
		
		if(k.right!=null)
		 {
			 k.right.parent=t;
			 t.right=k.right;
		 }
		
		k.value=temp;
		k.right = null;
		k.parent=null;
		
		
		
		/*//System.out.println(k.value);
		//System.out.println(k.right);
		 if(k.right!=null)
		 {
			 k.right.parent=k.parent;
		 k.parent.left=k.right;
		 }
		 if(t.right!=k)
		 {
		 k.right=t.right;
		 }
		 //System.out.println(k.right);
		// k = t;
		 k.parent = t.parent;
		 t.left.parent=k;
		 t.right.parent=k;
		 //k.parent = null;
		 k.left=t.left;
		 //k.right = t.right;
		 //k = this;
		 //AbsTree temp=t;
		 t.left=null;
		 t.right=null;
		 t=null;
		 System.out.println(this.value);
		
		 System.out.println(this.value);
		// System.out.println(t);
		 //System.out.println(k+" "+k.value+" "+k.right+" "+k.left.value);
		// System.out.println(this+" "+this.value+" "+this.right+" "+this.left.value);
		 /*t.right.left = t.left;
		 t.left.parent = t.right;
		 t.right.parent=null;
		 t = null;*/
		 return;
	//}
	/*AbsTree m=t.right.min();
		 if(t.parent.value>t.value)
		 {
			 if(m.right!=null)
			 {
				 m.right.parent=m.parent;
			 m.parent.left=m.right;
			 }
			 t.parent.left=m;
			 m.right=t.right;
			 m.left=t.left;
			  			 		 
		 }
		 else
		 {
			// t.parent.right=t.right;
			 if(m.right!=null)
			 {
				 m.right.parent=m.parent;
			 m.parent.left=m.right;
			 }
			 if(t.parent!=null)
			 {
			 t.parent.right=m;
			 m.right=t.right;
			 m.left=t.left;
			 }
			 else
			 {
				 m.right=t.right;
				 m.left=t.left;
				 m.parent=null;
			 }
			 
			 
			 t=null;
		 }
		 t.parent=null;
		  t=null;*/
		
	}

	private AbsTree find(int n) {
		//AbsTree f=this;
		if(value==n)
		{
		 return this;
		}
		else if(value< n)
	{
			if(right!=null)
			{
				return right.find(n);
			}
			else
			{
				return null;
			}
	}
		else if(value>n)
		{
			
			if(left!=null)
			{
				return left.find(n);
			}
			else
			{
				return null;
			}		
		}
		else
		{
			return null;
		}
	
	}

	public AbsTree min() {
		AbsTree l=this;
		while(l.left!=null)
		{
			l=l.left;
		}
		
		
		return l;
		// to be filled by you
	}

	public AbsTree max() {
		AbsTree r=this;
		while(r.right!=null)
		{
			r=r.right;
		}
		
		
		return r;
		
		// to be filled by you
	}

	protected int value;
	protected AbsTree left;
	protected AbsTree right;
	protected AbsTree parent;

	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();
	protected abstract int get_count();
	protected abstract void set_count(int v);
}

class Tree extends AbsTree {
	
	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
	
		return;
		
		
	}

	protected int get_count() {
		
		return 1;
		
	}

	protected void set_count(int v) {
		
	       	// to be filled by you
	}
}

class DupTree extends AbsTree {
	public DupTree(int n) {
		super(n);
		count = 1;
		};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}

	protected void count_duplicates() {
		count++;
	}

	protected int get_count() {
	
		return this.count;
		// to be filled by you
	}

	protected void set_count(int v) {
		this.count=v;
		// to be filled by you
	}

	protected int count;
}