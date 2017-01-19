/**
 * Created by ${TomaszJaniec} on 15.01.2017.
 */
public class FirstConnection extends Connection {
    private int packageInSeries;
    private int tON;
    private int tOFF;
    private int iterator;
    private int connectionNumber;
    private int actualTime = 0;
    private int numberOfSeries;
    FirstConnection(int tON, int tOFF, int connectionNumber, int packageInOn,int numberOfSeries)
    {
        super();
        this.numberOfSeries = numberOfSeries;
        this.packageInSeries = packageInOn;
        this.tOFF = tOFF;
        this.tON = tON;
        this.connectionNumber = connectionNumber;
        actualTime = 0;
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
