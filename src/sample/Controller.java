package sample;

import CompressionData.CompressionData;
import CompressionData.ReadFromFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;


public class Controller {
    public TextField textFileBIO;
    public AnchorPane archBIO;

    public TableColumn porepressMPaId;
    public TableColumn actionId;
    public TableColumn verticalStrainId;
    public TableColumn verticalPressKPAId;
    public TableColumn porePressKPAId;
    public TableColumn varticalDeformationId;
    public TableColumn verticalpressMPaId;
    public TableColumn tarDeformationId;
    public TableColumn stageId;
    public TableColumn timeId;
    public TableColumn actionChangedId;

    public TableView<CompressionData> tableCompression;
    public ComboBox cellName;
    public ComboBox dataCell;


    private Controller controllerParent;
    private FilterDataController filterDataController;


    private ArrayList<CompressionData> compressionDataArrayList;
    private ObservableList<CompressionData> compressionDataObservableList;
    private ArrayList<CompressionData> compressionDataArrayListFilter;

    //Геттеры сеттеры для получения ссылки на контроллеры
    public FilterDataController getFilterDataController(){
        return filterDataController;
    }

    public Controller getControllerParent() {
        return controllerParent;
    }

    public void setControllerParent(Controller controllerParent) {
        this.controllerParent = controllerParent;
    }


    public void initialize() {
        timeId.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("Time"));
        actionId.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Action"));
        actionChangedId.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Action_Changed"));
        verticalPressKPAId.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalPress_kPa"));
        porePressKPAId.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("PorePress_kPa"));
        varticalDeformationId.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalDeformation_mm"));
        verticalpressMPaId.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalPress_MPa"));
        porepressMPaId.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("PorePress_MPa"));
        verticalStrainId.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalStrain"));
        tarDeformationId.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("TarDeformation_mm"));
        stageId.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Stage"));


        //НИЖЕ УСТАРЕЛО
        ObservableList<String> cellNames = FXCollections.observableArrayList("Action", "Action_Changed", "Stage");
        ObservableList<String> dataCellAction = FXCollections.observableArrayList("Start", "LoadStage", "NULL");
        ObservableList<String> dataCellAction_Changed = FXCollections.observableArrayList("True", "False", "NULL");
        ObservableList<String> dataCellStage = FXCollections.observableArrayList("Пуск", "Компрессионное");

         cellName.setItems(cellNames);
         dataCell.setItems(dataCellAction);
    }

    //Метод обновления таблицы после фильтрации
    public void init(){
        System.out.println("ЗАШЛИ в ФУНКЦИЮ ОБНОВНЛЕНИЯ??");
        tableCompression.getItems().clear();
        compressionDataObservableList= FXCollections.observableArrayList(compressionDataArrayListFilter);
        tableCompression.setItems(compressionDataObservableList);
        tableCompression.refresh();
    }

    //Метод получение ОТФИЛЬТРОВАННЫХ данных из FilterData
    public void sendDataController(ArrayList<CompressionData> compressionData){
        compressionDataArrayListFilter=compressionData;
        System.out.println("НИЖЕ то что передали НАЗАД В ГЛАВНЫЙ КОНТРОЛЛЕР:");
        for(int i=0;i<compressionDataArrayListFilter.size();i++) {
            System.out.println(compressionDataArrayListFilter.get(i).outDataCompr());
        }
        init();
    }

    //Метод загрузки файла - забиндин на один файл --доработать для выбора пользователем файла
    public void loadfile(ActionEvent event) throws IOException {
        String filePath = "Test.1.log";
        Scanner scanner = new Scanner(new File(filePath));
        ReadFromFile readFromFile = new ReadFromFile(scanner);
        compressionDataArrayList =  readFromFile.parserToCompressionData();
        compressionDataObservableList= FXCollections.observableArrayList(compressionDataArrayList);
         tableCompression.setItems(compressionDataObservableList);
    }

    //Метод вызова окна фильтрации, передачи в другой контроллер данных
    public void getFilterCompressionData(ActionEvent event) throws IOException {
        if (compressionDataObservableList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Файл не был загружен.");
            alert.showAndWait();
            return;
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filterData.fxml"));
            Parent root = fxmlLoader.load();
            Stage st = new Stage();
            st.setResizable(false);
            st.setTitle("Фильтр");
            st.setScene(new Scene(root, 697, 247));
            filterDataController=fxmlLoader.getController();
            filterDataController.setControllerParent(this);
          //  filterDataController.sendData(compressionDataArrayList); // передаём туда данные
            filterDataController.initialize(compressionDataArrayList);
          //  filterDataController.initComboBox();
            st.show();

        }
    }

    //Метод фильтрации -УСТАРЕЛ
    public void filterss(ActionEvent event) {
          ArrayList<CompressionData> compressionDataArrayList22 = new ArrayList<CompressionData>();
          String cellName1;
          String dataCell1;
        cellName1= (String) cellName.getValue();
        dataCell1= (String) dataCell.getValue();
        System.out.println(cellName1);
        System.out.println(dataCell1);

        for(int i=0;i<compressionDataArrayList.size();i++) {
            if(dataCell1.equals(compressionDataArrayList.get(i).getAction())){
                compressionDataArrayList22.add(compressionDataArrayList.get(i));
            }
        }
        for(int i=0;i<compressionDataArrayList22.size();i++) {
            System.out.println(compressionDataArrayList22.get(i).outDataCompr());
        }
        tableCompression.getItems().clear();
        compressionDataObservableList= FXCollections.observableArrayList(compressionDataArrayList22);
        tableCompression.setItems(compressionDataObservableList);
        }
    }

