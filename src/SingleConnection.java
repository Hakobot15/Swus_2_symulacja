import java.util.Random;

/**
 * Created by ${TomaszJaniec} on 15.01.2017.
 */
public class SingleConnection extends Connection{

    private int packageInSeries;
    private int tON;
    private int tOFF;
    private int iterator = 0;
    private int connectionNumber;
    private int actualTime;
    private int numberOfSeries;

    SingleConnection(int tON, int tOFF, int connectionNumber, int packageInSeries, int numberOfSeries)
    {
        super();
        this.numberOfSeries = numberOfSeries;
        this.packageInSeries = packageInSeries;
        this.tOFF = tOFF;
        this.tON = tON;
        this.connectionNumber = connectionNumber;
        Random rand = new Random();
        actualTime = rand.nextInt(tON* packageInSeries +tOFF);
    }

    private int getActualTime() {
        if (iterator < packageInSeries * numberOfSeries){
            if (iterator == 0) {
                iterator++;
                return actualTime;
            }
        if (iterator % packageInSeries == 0) {
            actualTime += tOFF;
            iterator++;
        } else {
            actualTime += tON;
            iterator++;
        }
        return actualTime;
    }    else return -1;
    }

    public PackageReceived newPackage()
    {
        return new PackageReceived(getActualTime(),connectionNumber,iterator);
    }
}
