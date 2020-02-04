package CompressionData;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {
    private Scanner scanner;

    public ReadFromFile(Scanner scanner) {
        this.scanner = scanner;
    }



    public ArrayList parserToCompressionData() {

        ArrayList <CompressionData> compressionDataArrayListTEST = new ArrayList<>();
        String subStep12 = scanner.nextLine();
       //System.out.println(subStep12);
        while(scanner.hasNext()) {
           CompressionData compressionData = new CompressionData();
           String subStep1 = scanner.nextLine().replaceAll("[\\s]", ";");
          // System.out.println(subStep1);
            String subStep2 = subStep1.replaceAll(",",".");
           // System.out.println(subStep2);
            String[] subStr = subStep2.split(";");

            compressionData.setTime(Double.valueOf(subStr[0]));

            if(subStr[1].trim().isEmpty()) {
                compressionData.setAction("NULL");
            }else {
                compressionData.setAction(subStr[1]);
            }
            if(subStr[2].trim().isEmpty()) {
                compressionData.setAction_Changed("NULL");
            }else {
                compressionData.setAction_Changed(subStr[2]);
            }
            compressionData.setVerticalPress_kPa(Double.valueOf(subStr[3]));
            compressionData.setPorePress_kPa(Double.valueOf(subStr[4]));
            compressionData.setVerticalDeformation_mm(Double.valueOf(subStr[5]));
            compressionData.setVerticalPress_MPa(Double.valueOf(subStr[6]));
            compressionData.setPorePress_MPa(Double.valueOf(subStr[7]));
            compressionData.setVerticalStrain(Double.valueOf(subStr[8]));
            compressionData.setTarDeformation_mm(Double.valueOf(subStr[9]));
            compressionData.setStage(subStr[10]);
            compressionDataArrayListTEST.add(compressionData);
        }
        return compressionDataArrayListTEST;
    }





}
