package sample;

import CompressionData.CompressionData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FilterData implements Initializable {


    public AnchorPane panelFilter;
    public Button butClose;
    public ComboBox idOperator;
    public ComboBox idName2;
    public ComboBox idName1;
    public ComboBox idCondition2;
    public ComboBox idCondition1;
    public ComboBox idValue2;
    public ComboBox idValue1;
    private  ArrayList<CompressionData> dataCompression_;
    private Controller controllerParent;
    private FilterData filterDataController;


    public Controller getControllerParent() {
        return controllerParent;
    }

    public void setControllerParent(Controller controllerParent) {
        //controllerParent.setControllerParent(controllerParent);
       this.controllerParent = controllerParent;
    }

    public FilterData getFilterDataController() {
        return filterDataController;
    }

    public void getClose(ActionEvent event) {
        Stage stage = (Stage) butClose.getScene().getWindow();
        stage.close();
    }

    public void sendData(ArrayList<CompressionData> compressionDataArrayList)
    {
        dataCompression_=compressionDataArrayList;
        System.out.println("НИЖЕ то что передали В КОНТРОЛЛЕР ФИЛЬТРА:");
        for(int i=0;i<dataCompression_.size();i++) {
            System.out.println(dataCompression_.get(i).outDataCompr());
        }

    }

    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> cellNames = FXCollections.observableArrayList("Action", "Action_Changed", "Stage");
        ObservableList<String> dataCellAction = FXCollections.observableArrayList("Start", "LoadStage", "NULL");
        ObservableList<String> dataCellAction_Changed = FXCollections.observableArrayList("True", "False", "NULL");
        ObservableList<String> dataCellStage = FXCollections.observableArrayList("Пуск", "Компрессионное");

        idName1.setItems(cellNames);
        idValue1.setItems(dataCellAction);
      //  controllerParent.setControllerParent(controllerParent);
    }

    public void getClear(ActionEvent event) {
    }

    public void getOkFilter(ActionEvent event) throws IOException {

        ArrayList<CompressionData> compressionDataArrayList2 = new ArrayList<CompressionData>();
        String cellName;
        String dataCell;
        cellName= (String) idName1.getValue();
        dataCell= (String) idValue1.getValue();
        System.out.println(cellName);
        System.out.println(dataCell);


        for(int i=0;i<dataCompression_.size();i++) {
            if(dataCell.equals(dataCompression_.get(i).getAction())){
                compressionDataArrayList2.add(dataCompression_.get(i));
            }
        }
        System.out.println("Результат фильтрации:");
        for(int i=0;i<compressionDataArrayList2.size();i++) {
            System.out.println(compressionDataArrayList2.get(i).outDataCompr());
        }

        sennd(compressionDataArrayList2);

        Stage stage = (Stage) butClose.getScene().getWindow();
        stage.close();


    }

    public void sennd(ArrayList dataCompression_) throws IOException {

        if (dataCompression_.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Пусто.");
            alert.showAndWait();
            return;
        } else {
           // FXMLLoader fxmlLoader = new FXMLLoader();
            controllerParent.getControllerParent(); // получаем контроллер
            controllerParent.sendDataController(dataCompression_);
           // controllerParent.initialize();
          //  fxmlLoaderController.init(); // инициализиуем страницу (работаем с данными)

    }

    }

}
