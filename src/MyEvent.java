/**
 * Created by ${TomaszJaniec} on 14.01.2017.
 */
public abstract class MyEvent {
    private int time;
    public MyEvent (int time)
    {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
    public int getConnectionNumber()
    {
        return 0;
    }
}

