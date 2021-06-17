import java.util.Random;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class Tester 
{
	public static void main(String[] args) 
	{
		try
		{
			Random r = new Random();
			for(int i = 0; i < 100_000; i++)
			{
				MyNode<Integer> a = new MyNode<Integer>(Integer.valueOf(15));
				a.resize(r.nextInt(100));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		MySkipList.test();

		MySkipList<Integer> msl = new MySkipList<Integer>();
		msl.printList();
		Random r = new Random();
		ArrayList<Integer> al = new ArrayList<Integer>();
		int initalsize = 100_000;
		for(int i = 0; i < initalsize; i++)
		{
			al.add(i);
		}
		ArrayList<Integer> al2 = new ArrayList<Integer>();
		for(int i = 0; i < initalsize; i++)
		{
			al2.add(al.get(i));
		}
		
		long t1 = System.currentTimeMillis();
		while(al.size() > initalsize/4)
		{
			int index = r.nextInt(al.size());
			msl.insert(al.get(index));
			//System.out.printf("i:%d v:%s%n", index, al.get(index));
			al.remove(index);
		}
		System.out.printf("MySkipList took %d millis%n", System.currentTimeMillis() - t1);
		//msl.printList();

		PriorityQueue<Integer> q = new PriorityQueue<Integer>(); 
		long t2 = System.currentTimeMillis();
		while(al2.size() > initalsize/4)
		{
			int index = r.nextInt(al.size());
			q.add(al.get(index));
			//System.out.printf("i:%d v:%s%n", index, al.get(index));
			al2.remove(index);
		}
		System.out.printf("Queue took %d millis%n", System.currentTimeMillis() - t2);
	}
}
