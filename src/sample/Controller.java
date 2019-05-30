package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import java.util.ArrayList;

public class Controller {
    public TextField textFileBIO;
    public GridBase bioGridBase;
    public SpreadsheetView bioSpreadsheetView;
    public int mRowCount = 99;
    public int mColumnCount = 26;
    public AnchorPane archBIO;
    public DataSet dataSet;

    public void initialize(){

        initSpreadsheet();
        AnchorPane.setTopAnchor(bioSpreadsheetView, 0.0);
        AnchorPane.setLeftAnchor(bioSpreadsheetView, 5.0);
        AnchorPane.setRightAnchor(bioSpreadsheetView, 5.0);
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
        */


        File file = new File("test1.arff");
         dataSet  = ARFFLoader.loadArffFile(file);
        // We specify '0' as the class we would like to make the target class.
        ClassificationDataSet cDataSet =  new ClassificationDataSet(dataSet, 0);

        // Лист для предпоказа в коман. строку, данных
        ArrayList<Vec> arrayListTEST = new ArrayList<>();

        Classifier classifier =  new NaiveBayes();
        classifier.train(cDataSet);
        int j = 0;
        for(int i =  0; i <  dataSet.getSampleSize(); i++)
        {
            DataPoint dataPoint = cDataSet.getDataPoint(i); //берем i-ую запись из файла
            arrayListTEST.add(dataPoint.getNumericalValues());//кладем ее в лист
            System.out.println( i +  "|" +  arrayListTEST.get(i));// выводим
            while(j!=dataPoint.numNumericalValues()) {
                bioSpreadsheetView.getGrid().setCellValue(i, j, dataPoint.getNumericalValues().get(j)); // добавление записи в таблицу
                j++;
            }
            j=0;
        }
    }

    public void Stat_Aver_Med_Max_Min(ActionEvent event) {

        if(dataSet==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Файл не был загружен.");
            alert.showAndWait();
            return;
        }else
            {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sampleRezult.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
            return;
        }
        Stage st = new Stage();
        st.setResizable(false);
        st.setTitle("Среднее/Медиана/Минимальное/Максимальное");
        st.setScene(new Scene(root, 700, 450));
        StatRezultController statRezultController = fxmlLoader.getController(); // получаем контроллер второй страницы
        statRezultController.sendData(dataSet); // передаём туда данные
        statRezultController.init(); // инициализиуем страницу (работаем с данными)
        st.show();
            }
    }
}
