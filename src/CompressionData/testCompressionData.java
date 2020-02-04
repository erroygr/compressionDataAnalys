package CompressionData;


import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class testCompressionData {

    public static void main(String[] args) throws IOException {

        CompressionData compressionData1 =new CompressionData(0.1, "Start","True", 0,
                                                              0, 0, 0.00545,
                                                              -7.6565, 0.6555,
                                                              65.6565, "Пуск");

        CompressionData compressionData2 =new CompressionData(0.321, "Start1","True1", 0.54,
                                                              0.65, 0.11, 111.00545,
                                                              -111.6565, 111.6555,
                                                              1121.6565, "Пуск2");

        ArrayList<CompressionData> compressionDataArrayList = new ArrayList<>();
        compressionDataArrayList.add(compressionData1);
        compressionDataArrayList.add(compressionData2);

        System.out.println(compressionDataArrayList.get(0).outDataCompression());
        System.out.println(compressionDataArrayList.get(1).outDataCompression());



        String filePath = "Test.1.log";

        Scanner scanner = new Scanner(new File(filePath));
        ReadFromFile readFromFile = new ReadFromFile(scanner);


        ArrayList<CompressionData> compressionDataArrayListTEST = readFromFile.parserToCompressionData();

        for(int i=0; i<compressionDataArrayListTEST.size();i++) {
            System.out.println(compressionDataArrayListTEST.get(i).outDataCompr());
        }

    }
}
