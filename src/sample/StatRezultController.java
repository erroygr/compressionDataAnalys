package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jsat.DataSet;
import jsat.classifiers.ClassificationDataSet;
import jsat.classifiers.Classifier;
import jsat.classifiers.DataPoint;
import jsat.classifiers.bayesian.NaiveBayes;
import jsat.linear.MatrixOfVecs;
import jsat.linear.Vec;

import java.util.*;


public class StatRezultController  {

    @FXML
    public Label targetMedian;
    public Label targetSred;
    public Label targetMax;
    public Label targetMin;
    private  DataSet dataSetTEST;
    private  ArrayList<Double> arraySred = null;
    private  ArrayList<Double> arrayMediana = null;
    private  ArrayList<Double> arrayMax = null;
    private  ArrayList<Double> arrayMin = null;
    public void sendData(DataSet data){
        dataSetTEST = data;
        System.out.println("НИЖЕ то что передали датасет:");
        System.out.println(dataSetTEST.getDataMatrix());
    }

    public void init(){
        // метод вызывается из первого контроллера
        // изменяем уже поолученные данные
        arraySred =fum_d(dataSetTEST);
        arrayMediana=fun_median(dataSetTEST);
        arrayMax=fun_Max(dataSetTEST);
        arrayMin=fun_MIN(dataSetTEST);
        // и осталось вывести данные на контрол
        initOutputSrednee();
        initOutputMedian();
        initOutputMax();
        initOutputMin();
    }

    public void initOutputSrednee(){
        targetSred.setText(arraySred.toString());
    }

    public void initOutputMedian(){
        targetMedian.setText(arrayMediana.toString());
    }

    public void initOutputMax(){
        targetMax.setText(arrayMax.toString());
    }

    public void initOutputMin(){
        targetMin.setText(arrayMin.toString());
    }


    public ArrayList<Double>  fum_d(DataSet dataSetTEST){
        ClassificationDataSet cDataSet =  new ClassificationDataSet(dataSetTEST, 0);
        ArrayList<Vec> arrayListTEST = new ArrayList<>();
        Classifier classifier =  new NaiveBayes();
        classifier.train(cDataSet);

        for(int i =  0; i <  dataSetTEST.getSampleSize(); i++)
        {
            DataPoint dataPoint = cDataSet.getDataPoint(i); //берем i-ую запись из файла
            arrayListTEST.add(dataPoint.getNumericalValues()); //добавляем i-ую запись в коллекцию векторов
        }
        ArrayList<Double> sumVectors = null;

        if(arrayListTEST.size() != 0){ // если векторы вообще существуют
            sumVectors = new ArrayList<>(Collections.nCopies(arrayListTEST.get(0).length(), 0.0));
        }
        for(Vec v: arrayListTEST)
            for (int i = 0; i < v.length(); i++) { // перебираем все измеренения конкретного вектора
                sumVectors.set(i, sumVectors.get(i) + v.get(i)); // добавляем к значениям, хранящимся в колекции финальных значений

            }

           for (int i = 0; i < sumVectors.size(); i++) {
               sumVectors.set(i, sumVectors.get(i) / arrayListTEST.size());
           }

        System.out.println("Среднее значение: " +arrayListTEST.size());
        System.out.println(sumVectors.toString());

        return sumVectors;
}


    public ArrayList<Double>  fun_Max(DataSet dataSetTEST) {

        ClassificationDataSet cDataSet =  new ClassificationDataSet(dataSetTEST, 0);
        ArrayList<Vec> arrayINPUT = new ArrayList<>();
        Classifier classifier =  new NaiveBayes();
        classifier.train(cDataSet);

        ArrayList <Double> MAXarr=null;

        // лист для трансонированных данных
        ArrayList<Vec> arrayListTRANSPOSE = new ArrayList<>();
        ArrayList<Vec> arrayListVVV=new ArrayList<>();

        for(int i =  0; i <  dataSetTEST.getSampleSize(); i++)
        {
            DataPoint dataPoint = cDataSet.getDataPoint(i); //берем i-ую запись из файла
            arrayINPUT.add( dataPoint.getNumericalValues()); //добавляем i-ую запись в коллекцию векторов
        }

        MatrixOfVecs matrix=new MatrixOfVecs(arrayINPUT);
        // matrix.transpose();
        for (int i = 0; i<matrix.cols(); i++) {
            arrayListTRANSPOSE.add(matrix.getColumn(i));
        }


        for(Vec v: arrayListTRANSPOSE) {
            arrayListVVV.add(v.sortedCopy());
        }

        // создаём коллекцию для хранения суммирующих векторов
        if(arrayListVVV.size() != 0){ // если векторы вообще существуют
            MAXarr = new ArrayList<>();
        }

        // for each по всем векторам
        for(Vec v: arrayListVVV) {
            MAXarr.add(v.max()); // добавляем к значениям, хранящимся в колекции финальных значений
        }


        System.out.println("Максимальные значения: "+MAXarr.size());
        for(int i =  0; i <  MAXarr.size(); i++) {
            System.out.println(i + "|" + MAXarr.get(i));// выводим
        }

        return MAXarr;
    }

    public ArrayList<Double>  fun_MIN(DataSet dataSetTEST) {

        ClassificationDataSet cDataSet =  new ClassificationDataSet(dataSetTEST, 0);
        ArrayList<Vec> arrayINPUT = new ArrayList<>();
        Classifier classifier =  new NaiveBayes();
        classifier.train(cDataSet);


        ArrayList <Double> MINarr=null;

        // лист для трансонированных данных
        ArrayList<Vec> arrayListTRANSPOSE = new ArrayList<>();
        ArrayList<Vec> arrayListVVV=new ArrayList<>();

        for(int i =  0; i <  dataSetTEST.getSampleSize(); i++)
        {
            DataPoint dataPoint = cDataSet.getDataPoint(i); //берем i-ую запись из файла
            arrayINPUT.add( dataPoint.getNumericalValues()); //добавляем i-ую запись в коллекцию векторов
        }

        MatrixOfVecs matrix=new MatrixOfVecs(arrayINPUT);
        // matrix.transpose();
        for (int i = 0; i<matrix.cols(); i++) {
            arrayListTRANSPOSE.add(matrix.getColumn(i));
        }



        for(Vec v: arrayListTRANSPOSE) {
            arrayListVVV.add(v.sortedCopy());
        }
        // создаём коллекцию для хранения суммирующих векторов
        if(arrayListVVV.size() != 0){ // если векторы вообще существуют
            MINarr = new ArrayList<>();
        }

        // for each по всем векторам
        for(Vec v: arrayListVVV) {
            MINarr.add(v.min());
        }

        System.out.println("Минимальные значения: "+MINarr.size());
        for(int i =  0; i <  MINarr.size(); i++) {
            System.out.println(i + "|" + MINarr.get(i));// выводим
        }

        return MINarr;
    }


    public ArrayList<Double>  fun_median(DataSet dataSetTEST) {

        ClassificationDataSet cDataSet =  new ClassificationDataSet(dataSetTEST, 0);
        ArrayList<Vec> arrayINPUT = new ArrayList<>();
        Classifier classifier =  new NaiveBayes();
        classifier.train(cDataSet);

        ArrayList <Double> Medianarr=null;


        // лист для трансонированных данных
        ArrayList<Vec> arrayListTRANSPOSE = new ArrayList<>();
        ArrayList<Vec> arrayListVVV=new ArrayList<>();

        for(int i =  0; i <  dataSetTEST.getSampleSize(); i++)
        {
            DataPoint dataPoint = cDataSet.getDataPoint(i); //берем i-ую запись из файла
            arrayINPUT.add( dataPoint.getNumericalValues()); //добавляем i-ую запись в коллекцию векторов
        }

        MatrixOfVecs matrix=new MatrixOfVecs(arrayINPUT);
      // matrix.transpose();
            for (int i = 0; i<matrix.cols(); i++) {
                arrayListTRANSPOSE.add(matrix.getColumn(i));
            }

            //Проверка на корректное транспонирование
        System.out.println("Транспонируем данные: "+arrayListTRANSPOSE.size());
        for(int i =  0; i <  arrayListTRANSPOSE.size(); i++) {
            System.out.println(i + "|" + arrayListTRANSPOSE.get(i));// выводим
        }

        for(Vec v: arrayListTRANSPOSE) {
            arrayListVVV.add(v.sortedCopy());
        }
        //Проверка на корректное транспонирование
        System.out.println("Транспонируем данные: сортированные");
        for(int i =  0; i <  arrayListVVV.size(); i++) {
            System.out.println(i + "|" + arrayListVVV.get(i));// выводим
        }

        // создаём коллекцию для хранения суммирующих векторов
        if(arrayListVVV.size() != 0){ // если векторы вообще существуют

            Medianarr = new ArrayList<>();
        }

        // for each по всем векторам
       for(Vec v: arrayListVVV) {
           Medianarr.add(v.median());
       }

        System.out.println("Медианные значения: "+Medianarr.size());
        for(int i =  0; i <  Medianarr.size(); i++) {
            System.out.println(i + "|" + Medianarr.get(i));// выводим
        }

        return Medianarr;
    }


}
