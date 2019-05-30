package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jsat.DataSet;
import jsat.classifiers.ClassificationDataSet;
import jsat.classifiers.Classifier;
import jsat.classifiers.DataPoint;
import jsat.classifiers.bayesian.NaiveBayes;
import jsat.linear.Vec;
import java.util.ArrayList;
import java.util.Collections;



public class StatRezultController  {

    @FXML
    public Label target;
    private  DataSet dataSetTEST;
    private  ArrayList<Double> sumVectorsS = null;

    public void sendData(DataSet data){
        dataSetTEST = data;
        System.out.println("НИЖЕ то что передали датасет:");
        System.out.println(dataSetTEST.getDataMatrix());
    }

    public void init(){
        // метод вызывается из первого контроллера
        // изменяем уже поолученные данные
        sumVectorsS=fum_d(dataSetTEST);
        // и осталось вывести данные на контрол
        initOutputLabel();

    }

    public void initOutputLabel(){
        target.setText(sumVectorsS.toString());
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
        //System.out.println(dataSetTEST.getDataMatrix());

        // создаём коллекцию для хранения суммирующих векторов
        if(arrayListTEST.size() != 0){ // если векторы вообще существуют
            sumVectors = new ArrayList<>(Collections.nCopies(arrayListTEST.get(0).length(), 0.0));
        // так как все векторы одинаковой длины (т.е. в твоем случае это 4-ре)
        // то инициализируем коллекцию размерность в 4 элемента,и задаём начальное значение в 0
        }
        // for each по всем векторам
        for(Vec v: arrayListTEST)
            for (int i = 0; i < v.length(); i++) { // перебираем все измеренения конкретного вектора
                sumVectors.set(i, sumVectors.get(i) + v.get(i)); // добавляем к значениям, хранящимся в колекции финальных значений

            }

        //System.out.println(sumVectors.toString()); // просто вывод на экран, для теста
        for(Vec v: arrayListTEST)
            for (int i = 0; i < v.length(); i++) {
                sumVectors.set(i, sumVectors.get(i)/v.length());

            }
        System.out.println("Среднее значение:");
        System.out.println(sumVectors.toString());

        return sumVectors;
}





}
