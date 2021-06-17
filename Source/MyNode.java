import java.util.Random;
import java.util.ArrayList;

public class MyNode<T>
{
    private static final Random R = new Random();
    private static int generateHeight()
    {
        int height = -1;
        boolean flag = true;
        while(flag)
        {
            height++;
            flag = (R.nextInt(2) == 0);
        }
        return height;
    }

    private T data;
    private int height;
    private MyNode[][] nodes;
    public MyNode(T data)
    {
        this.data = data;
        this.height = generateHeight();
        nodes = new MyNode[height+1][2];
    }
    public MyNode(T data, int size)
    {
        this.data = data;
        this.height = size;
        nodes = new MyNode[height+1][2];
    }

    public T getData()
    {
        return data;
    }
    public int getHeight()
    {
        return height;
    }

    public void resize(int size)
    {
        MyNode[][] temp = new MyNode[size+1][2];
        int loopSize = (size < this.height) ? size : this.height;
        for(int i = 0; i < loopSize+1; i++)
        {
            temp[i][0] = this.nodes[i][0];
            temp[i][1] = this.nodes[i][1];
        }
        height = size;
        this.nodes = temp;
    }

    public MyNode getLeft(int level)
    {
        return nodes[level][0];
    }
    public MyNode getRight(int level)
    {
        return nodes[level][1];
    }
    public void setLeft(int level, MyNode node)
    {
        nodes[level][0] = node;
    }
    public void setRight(int level, MyNode node)
    {
        nodes[level][1] = node;
    }
}