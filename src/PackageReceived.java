/**
 * Created by ${TomaszJaniec} on 14.01.2017.
 */
public class PackageReceived extends MyEvent {

    private int connectionNumber;
    private int packageNumber;
    PackageReceived(int time, int connectionNumber,int packageNumber)
    {
        super(time);
        this.connectionNumber = connectionNumber;
        this.packageNumber = packageNumber;
    }

    public int getConnectionNumber() {
        return connectionNumber;
    }
}
