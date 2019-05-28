package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import jsat.DataSet;
import jsat.classifiers.ClassificationDataSet;
import jsat.classifiers.Classifier;
import jsat.classifiers.DataPoint;
import jsat.classifiers.bayesian.NaiveBayes;
import jsat.linear.Vec;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;


public class StatRezultController implements Initializable  {

    @FXML
    public Label target;
    private  DataSet dataSetTEST;


   public StatRezultController(DataSet dataSetTEST) {
        this.dataSetTEST = dataSetTEST;
       System.out.println("зашел в StatRezultController--ниже датаСет который передали:");
       System.out.println(dataSetTEST.getDataMatrix());
       System.out.println("Вызов первый функции расчета:");
//       target.setText(String.valueOf(fum_d(dataSetTEST)));
    }

    public ArrayList<Double>  fum_d(DataSet dataSetTEST){
    // We specify '0' as the class we would like to make the target class.
    ClassificationDataSet cDataSet = new ClassificationDataSet(dataSetTEST, 0);


    ArrayList<Vec> arrayListTEST = new ArrayList<>();
        ArrayList<Double> sumVectors=null;

    Classifier classifier = new NaiveBayes();
    classifier.train(cDataSet);

    // создаём коллекцию для хранения суммирующих векторов

    if (arrayListTEST.size() != 0) { // если векторы вообще существуют
        sumVectors = new ArrayList<Double>(Collections.<Double>nCopies(arrayListTEST.get(0).length(), 0.0));
    }

    for (Vec v : arrayListTEST)
        for (int i = 0; i < v.length(); i++) {
            sumVectors.set(i, sumVectors.get(i) + v.get(i));

        }

    for (Vec v : arrayListTEST)
        for (int i = 0; i < v.length(); i++) {
            sumVectors.set(i, sumVectors.get(i) / v.length());

        }

    System.out.println(sumVectors);
//    target.setText("ТЕКСТ МЕНЯЮ НО ДАННЫЕ НЕ ВЫВЕДУ");
    return sumVectors;
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("зашел init");
        System.out.println("Второй вызов функции расчета:");
        target.setText(String.valueOf(fum_d(dataSetTEST)));


    }


}
