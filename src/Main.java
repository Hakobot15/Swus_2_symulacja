import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("settings.txt"));

        String tmp = br.readLine();

        int simulationsNumber = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));
        tmp = br.readLine();
        int connectionNumber = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));
        tmp = br.readLine();
        int numberOfSeries = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));
        tmp = br.readLine();
        int packageInSeries = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));
        tmp = br.readLine();
        int peakRate = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));
        tmp = br.readLine();
        int tOFF = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));
        tmp = br.readLine();
        int tON = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));
        tmp = br.readLine();
        int processingTime = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));
        tmp = br.readLine();
        int bufferSize = Integer.parseInt(tmp.substring(tmp.indexOf("#") + 1, tmp.indexOf('@')));

        br.close();

        PrintWriter writer = new PrintWriter("Stats.txt", "UTF-8");
        PrintWriter writer2 = new PrintWriter(new FileWriter("AverageStats.txt", true));

        for (int o = 0; o < simulationsNumber; o++) {
            MyArrayList myArrayList = new MyArrayList(connectionNumber + 1, bufferSize, processingTime);

            Connection[] list = new Connection[connectionNumber];

            list[0] = new FirstConnection(tON, tOFF, 0, packageInSeries, numberOfSeries);

            for (int i = 1; i < connectionNumber; i++) {
                list[i] = new SingleConnection(tON, tOFF, i, packageInSeries, numberOfSeries);
            }

            for (Connection connection : list) {
                myArrayList.add(connection.newPackage());
            }

            int iteratorConnection = 0;
            int check;
           loop: while (true)
           {
               check = myArrayList.remove();
               switch (check) {
                   case 0:
                       break loop;
                   case 1:
                       myArrayList.add(list[iteratorConnection % connectionNumber].newPackage());
                       iteratorConnection++;
                       continue ;
                   case 2:
                       continue ;
                   }
           }
           float packageSent = (float) myArrayList.getPackageSent();
           float totalPackage = (float)connectionNumber*packageInSeries*numberOfSeries;
           float wynikSymulacji = packageSent/totalPackage;
           float workingTime = (float) myArrayList.getTotalWorkingTime();
            System.out.println(workingTime);
           float totalTime = (float)myArrayList.getTotalTime();
            System.out.println(totalTime);
           float zajetoscSystemu = workingTime/totalTime;
            writer.println("Srednia symulacji: "+wynikSymulacji+" ; Zajetosc systemu: "+zajetoscSystemu);
            int time = tOFF + tON * packageInSeries;
            writer2.println("Wynik symulacji:" + wynikSymulacji + " ; Zajetosc systemu: " + zajetoscSystemu + " dla danych: simulationsNumber(" + simulationsNumber + ");connectionNumber(" + connectionNumber + ");" +
                    "packageNumber(" + myArrayList.getTotalPackage() + ");peakRate(" + peakRate + ");tau(" + Integer.toString(time) + "ns);processingTime(" + processingTime + "ns);bufferSize(" + bufferSize + ")");
            System.out.println("Packet sent: "+myArrayList.getPackageSent());
            System.out.println("Total package: "+connectionNumber*packageInSeries*numberOfSeries);
System.out.println(wynikSymulacji+"@"+zajetoscSystemu);
            writer.close();
            writer2.close();

        }
        System.out.println("Koniec");
        System.exit(0);
    }
}