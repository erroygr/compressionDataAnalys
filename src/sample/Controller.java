package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jsat.DataSet;
import jsat.classifiers.ClassificationDataSet;
import jsat.classifiers.Classifier;
import jsat.classifiers.DataPoint;
import jsat.classifiers.bayesian.NaiveBayes;
import jsat.io.ARFFLoader;
import jsat.linear.Vec;
import org.controlsfx.control.spreadsheet.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    public TextField textFileBIO;
    public GridBase bioGridBase;
    public SpreadsheetView bioSpreadsheetView;
    public int mRowCount = 99;
    public int mColumnCount = 26;
    public AnchorPane archBIO;
    public Label sredZnsch;



    public void initialize(){

        initSpreadsheet();

        AnchorPane.setTopAnchor(bioSpreadsheetView, 50.0);
        AnchorPane.setLeftAnchor(bioSpreadsheetView, 10.0);
        AnchorPane.setRightAnchor(bioSpreadsheetView, 10.0);
        AnchorPane.setBottomAnchor(bioSpreadsheetView, 50.0);

        archBIO.getChildren().addAll(bioSpreadsheetView);

    }


    private void initSpreadsheet() {
        bioGridBase = new GridBase(mRowCount, mColumnCount);
        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        for (int row = 0; row < bioGridBase.getRowCount(); ++row) {
            final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
            for (int column = 0; column < bioGridBase.getColumnCount(); ++column) {
                list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, ""));
            }
            rows.add(list);
        }
        bioGridBase.setRows(rows);
        bioSpreadsheetView = new SpreadsheetView(bioGridBase);
        bioSpreadsheetView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for (SpreadsheetColumn c : bioSpreadsheetView.getColumns()) c.setPrefWidth(90);
    }



    //Запись значения из тексбокса в ячейку в фокусе
    public void inputCallRollBIO(ActionEvent event) {
        int focusedRow = bioSpreadsheetView.getSelectionModel().getFocusedCell().getRow();
        int focusedColumn = bioSpreadsheetView.getSelectionModel().getFocusedCell().getColumn();
        bioSpreadsheetView.getGrid().setCellValue(focusedRow, focusedColumn, textFileBIO.getText());
        textFileBIO.setText("");
    }




    public void loadfile(ActionEvent event) {

        // FileChooser fileChooser = new FileChooser();
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        //File loadImageFile = fileChooser.showOpenDialog(archBIO.getScene().getWindow());
        // URL url = loadImageFile.toURI().toURL();
        //Path path = Paths.get(loadImageFile.toURI().toURL().toString());
        // TEXT_TEST.load(url.toString());
       /* String filePath = "file1.txt";
        Controller chartReader = new Controller();
        int[][] myArray = chartReader.getArrayFromFile(filePath);
        int arrWidth = myArray.length;
        int arrLength = myArray[0].length;*/

     /*   for(int i=0; i<arrWidth; i++) {
            for (int j = 0; j < arrLength; j++) {
                bioSpreadsheetView.getGrid().setCellValue(i,j,myArray[i][j]);
                System.out.print(myArray[i][j] + " ");
            }
            System.out.println();
        }*/

        File file = new File("test1.arff");
        DataSet dataSet  = ARFFLoader.loadArffFile(file);
        // We specify '0' as the class we would like to make the target class.
        ClassificationDataSet cDataSet =  new ClassificationDataSet(dataSet, 0);


        ArrayList<Vec> arrayListTEST = new ArrayList<>();

        Classifier classifier =  new NaiveBayes();
        classifier.train(cDataSet);
        int j = 0;
        for(int i =  0; i <  dataSet.getSampleSize(); i++)
        {
            DataPoint dataPoint = cDataSet.getDataPoint(i); //берем i-ую запись из файла
            arrayListTEST.add(dataPoint.getNumericalValues()); //добавляем i-ую запись в коллекцию векторов
            System.out.println( i +  "|" +  arrayListTEST.get(i));//
            while(j!=dataPoint.numNumericalValues()) {
                bioSpreadsheetView.getGrid().setCellValue(i, j, dataPoint.getNumericalValues().get(j)); //запись в таблицу
                j++;
            }
            j=0;
        }

              // создаём коллекцию для хранения суммирующих векторов
        ArrayList<Double> sumVectors = null;
        if(arrayListTEST.size() != 0){ // если векторы вообще существуют
            sumVectors = new ArrayList<Double>(Collections.<Double>nCopies(arrayListTEST.get(0).length(), 0.0)); // так как все векторы одинаковой длины (т.е. в твоем случае это 4-ре)
// то инициализируем коллекцию размерность в 4 элемента,и задаём начальное значение в 0
        }
        // for each по всем векторам
        for(Vec v: arrayListTEST)
            for (int i = 0; i < v.length(); i++) { // перебираем все измеренения конкретного вектора
                sumVectors.set(i, sumVectors.get(i) + v.get(i)); // добавляем к значениям, хранящимся в колекции финальных значений

            }
        for(Vec v: arrayListTEST)
            for (int i = 0; i < v.length(); i++) {
                sumVectors.set(i, sumVectors.get(i)/v.length());

            }

        sredZnsch.setText(sumVectors.toString()); // просто вывод на экран, для теста
        System.out.println("НИЖЕ ДАТАСЕТ ИЗ ПЕРВОГО КОНТРОЛЛЕРА, ТО ЧТО ПЕРЕДАЕМ:");
        System.out.println(dataSet.getDataMatrix());





        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("sample/sampleRezult.fxml"));
        StatRezultController controller = new StatRezultController(dataSet);
        loader.setController(controller);
        Parent mainCallWindowFXML = null;
        try {
            mainCallWindowFXML = loader.load();
            Stage stage =new Stage();
            stage.setScene(new Scene(mainCallWindowFXML, 800, 600));
            stage.show(); //показ сцены
        } catch (IOException e) {
            e.printStackTrace();
        }


      /*  int k=0;
        int j = 0;
        for (int i = 0; i < testDataSet.getSampleSize(); i++) {
            DataPoint dataPoint = testDataSet.getDataPoint(i);k++;
            while(j!=dataPoint.numNumericalValues()) {
                bioSpreadsheetView.getGrid().setCellValue(i, j, dataPoint.getNumericalValues().get(j));
                System.out.println(i+" "+j+" "+dataPoint.getNumericalValues().get(j));
                j++;
            }
            j=0;
        }
*/




    }
}
