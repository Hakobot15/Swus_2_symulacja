import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by ${TomaszJaniec} on 16.01.2017.
 */
public class MyFifo implements Queue {
    private int bufferSize;
    private MyEvent[] myList;
    private int packageSent = 0;
    MyFifo(int bufferSize)
    {
        myList = new MyEvent[bufferSize];
        this.bufferSize = bufferSize;
    }
    @Override
    public int size() {
        for(int k = 0;k<myList.length;k++)
        {
            if(myList[k] == null)
                return k;
        }
        return myList.length;
    }

    @Override
    public boolean isEmpty() {
        if(myList.length==0)
            return true;
            else
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public int getPackageSent() {
        return packageSent;
    }

    private  int check(MyEvent[] me)
    {
        for(int k = 0;k<me.length;k++)
        {
            if(me[k] == null)
                return k;
        }
        return me.length;
    }
    public boolean add(Object o) {
        if(size()<bufferSize) {
            packageSent++;
            MyEvent[] tmp = myList.clone();
            myList[0] = (MyEvent) o;
            for(int i = 1; i < check(tmp)+1;i++)
            {
                myList[i] = tmp[i-1];

            }
            return true;
        } else
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public MyEvent poll() {
        MyEvent tmp = myList[this.size()-1];
        myList[this.size()-1] = null;
        return tmp;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }
}
