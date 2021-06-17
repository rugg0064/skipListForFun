
public class MySkipList <T extends Comparable> 
{
	private MyNode<T> head;
	private MyNode<T> tail;

	public MySkipList()
	{
		head = new MyNode<T>(null, 0);
		tail = new MyNode<T>(null, 0);
		head.setRight(0, tail);
		tail.setLeft(0, head);
	}

	public void insert(T data)
	{
		MyNode<T> node = new MyNode<T>(data);
		//System.out.printf("New Node %s height %d%n", node.getData(), node.getHeight());
		//System.out.printf("head height: %d%n", head.getHeight());
		int nodeHeight = node.getHeight();
		if(nodeHeight > head.getHeight())
		{
			int oldHeight = head.getHeight();
			head.resize(nodeHeight);
			tail.resize(nodeHeight);
			System.out.println("New head height: " + head.getHeight());
			for(int i = oldHeight+1; i <= nodeHeight; i++)
			{
				head.setRight(i, tail);
				tail.setLeft(i, head);
			}
		}
		//System.out.println("Getting bounds");
		MyNode[] bounds = getBoundingNodes(data);
		//System.out.println("Got bounds");
		
		for(int i = 0; i < nodeHeight+1; i++)
		{
			while(i > bounds[0].getHeight())
			{
				bounds[0] = bounds[0].getLeft(i-1);
			}

			while(i > bounds[1].getHeight())
			{
				bounds[1] = bounds[1].getRight(i-1);
			}

			node.setLeft(i, bounds[0]);
			node.setRight(i, bounds[1]);
	
			bounds[0].setRight(i, node);
			bounds[1].setLeft(i, node);
		}
	}

	private MyNode<T>[] getBoundingNodes(T data)
	{
		MyNode[] bounds = new MyNode[2];
		bounds[0] = head;
		bounds[1] = tail;

		int height = head.getHeight();

		MyNode<T> current = head;
		boolean direction = true; //left:false; right:true
		while(height >= 0)
		{
			//System.out.printf("Current node: %d%n", current.getData());
			//System.out.printf("Current height: %d%n", height);
			//System.out.printf("%n");
			//System.out.println(current);
			if(current != null)
			{
				//System.out.println("data: " + current.getData());
			}
			int diff = 0;
			if(current.getData() != null)
			{
				diff = data.compareTo(current.getData());
			}
			
			if(direction)
			{
				if(diff > 0)
				{
					bounds[0] = current;
				}
				else if(diff < 0)
				{
					bounds[1] = current;
					direction = !direction;
					//height--;
				}
			}
			else
			{
				if(diff < 0)
				{
					bounds[1] = current;
				}
				else if(diff > 0)
				{
					bounds[0] = current;
					direction = !direction;
					//height--;
				}
			}
			//System.out.printf("Height: %d%n", height);
			if(height >= 0)
			{
				//System.out.println("A");
				//System.out.println("B");
				MyNode<T> next = direction ? current.getRight(height) : current.getLeft(height);
				if(next == bounds[1] || next == bounds[0])
				{
					height--;
					direction = !direction;
				}
				/*
				if(current == bounds[1] || current == bounds[1])
				{
					next = direction ? current.getRight(height) : current.getLeft(height);
				}
				*/
				current = next;
			}
			//System.out.println("C");
		}
		
		return bounds;
	}

	public void printList()
	{
		MyNode<T> n = head;
		System.out.println("Head");
		while(n != null)
		{
			if(n.getData() != null)
			{
				System.out.printf("%s(%d)", n.getData(), n.getHeight());
			}
			n = n.getRight(0);
		}
		System.out.println();
		System.out.println("Tail");
	}

	public static void test()
	{
		MySkipList<Integer> msl = new MySkipList<Integer>();
		/*
		MyNode<Integer> newNode1 = new MyNode<Integer>(Integer.valueOf(1), 0);
		MyNode<Integer> newNode2 = new MyNode<Integer>(Integer.valueOf(3), 0);

		msl.head.setRight(0, newNode1);
		newNode1.setLeft(0, msl.head);

		newNode1.setRight(0, newNode2);
		newNode2.setLeft(0, newNode1);

		newNode2.setRight(0, msl.tail);
		msl.tail.setLeft(0, newNode2);
		*/

		/*
		msl.printList();
		msl.insert(Integer.valueOf(2));
		msl.printList();
		msl.insert(Integer.valueOf(3));
		msl.printList();
		msl.insert(Integer.valueOf(4));
		msl.printList();
		msl.insert(Integer.valueOf(6));
		msl.printList();
		msl.insert(Integer.valueOf(5));
		msl.printList();
		msl.insert(Integer.valueOf(0));
		msl.printList();
		*/

		

	}

}
