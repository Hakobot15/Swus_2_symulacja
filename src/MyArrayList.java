import java.util.Random;

/**
 * Created by ${TomaszJaniec} on 14.01.2017.
 */
public class MyArrayList
{
    private MyEvent[] asArray;
    private boolean isWorking = false;
    private int processingTime;
    private int totalTime;
    private int totalWorkingTime = 0;
    private int totalPackage = 0;
    private MyFifo fifoQueue;

    MyArrayList(int size, int bufferSize, int processingTime)
    {
        this.processingTime = processingTime;
        asArray = new MyEvent[size];
        fifoQueue = new MyFifo(bufferSize);
    }

    void add(MyEvent t)
    {
        if(t.getTime()<0){
            return;
        }
        for (int i = 0; i < asArray.length; i++)
        {
            MyEvent tmp;
           if(asArray[i] != null)
           {
               if (asArray[i].getTime() <= t.getTime()) {
                   if(asArray[i].getTime() < t.getTime())
                   {
                       tmp = asArray[i];
                       asArray[i] = t;
                       for(int k = i+1; k < asArray.length;k++)
                       {
                           if(asArray[k] != null)
                           {
                               MyEvent tmp2 = asArray[k];
                               asArray[k] = tmp;
                               tmp = tmp2;
                           }
                           else {
                               asArray[k] = tmp;
                               return;
                           }
                       }
                   } else
                   {
                       if(t instanceof EndOfService)
                       {
                           tmp = asArray[i];
                           asArray[i] = t;
                           for(int k = i+1; k < asArray.length;k++)
                           {
                               if(asArray[k] != null)
                               {
                                   MyEvent tmp2 = asArray[k];
                                   asArray[k] = tmp;
                                   tmp = tmp2;
                               }
                               else {
                                   asArray[k] = tmp;
                               return;
                               }
                           }
                       }
                       else {
                           while (t.getTime() == asArray[i].getTime()) {
                               Random rand = new Random();
                               int n = rand.nextInt(1);
                               if (n == 0) {
                                   break;
                               } else {
                                   i++;
                               }
                           }
                           tmp = asArray[i];
                           asArray[i] = t;
                           for(int k = i+1; k < asArray.length;k++)
                           {
                               if(asArray[k] != null)
                               {
                                   MyEvent tmp2 = asArray[k];
                                   asArray[k] = tmp;
                                   tmp = tmp2;
                               }
                               else {
                                   asArray[k] = tmp;
                                   return;
                               }
                           }
                       }
                   }
               }
           } else
           {
               asArray[i] = t;
               return;
           }
        }
    }

    int remove() {
        if(this.size() == 0 && fifoQueue.size() == 0) {return 0;}
            totalTime = asArray[this.size() - 1].getTime();
        if (asArray[this.size() - 1] instanceof PackageReceived) {
            totalPackage++;
            fifoQueue.add(asArray[this.size()-1 ]);
            asArray[this.size() - 1] = null;
            if (!isWorking && fifoQueue.size() >= 0) {
                this.add(new EndOfService(processingTime + fifoQueue.poll().getTime()));
                isWorking = true;
                totalWorkingTime += processingTime;
            }
            return 1;
        } else {
            if (fifoQueue.size() > 0) {
                    fifoQueue.poll();
                    int time = asArray[this.size() - 1].getTime();
                    asArray[this.size() - 1] = null;
                    this.add(new EndOfService(processingTime + time));
                    totalWorkingTime += processingTime;
            } else {
                isWorking = false;
                asArray[this.size() - 1] = null;
            }
            return 2;
        }
    }
    int getTotalPackage() {
        return totalPackage;
    }

    private int size()
    {
                for(int k = 0;k<asArray.length;k++)
                {
                    if(asArray[k] == null)
                        return k;
                }
                return asArray.length;
    }

    int getTotalWorkingTime() {
        return totalWorkingTime;
    }

    int getPackageSent() {
        return this.fifoQueue.getPackageSent();}

    int getTotalTime() {
        return totalTime;
    }
}