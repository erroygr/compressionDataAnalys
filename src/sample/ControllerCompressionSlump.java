package sample;

import CompressionData.CompressionData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import CompressionData.ReadFromFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerCompressionSlump extends Window {

    public TableView<CompressionData> tableCompression1;
    public TableColumn timeId1;
    public TableColumn actionId1;
    public TableColumn actionChangedId1;
    public TableColumn verticalPressKPAId1;
    public TableColumn porePressKPAId1;
    public TableColumn varticalDeformationId1;
    public TableColumn verticalpressMPaId1;
    public TableColumn porepressMPaId1;
    public TableColumn verticalStrainId1;
    public TableColumn tarDeformationId1;
    public TableColumn stageId1;

    public TableView<CompressionData> tableCompression2;
    public TableColumn timeId2;
    public TableColumn actionId2;
    public TableColumn actionChangedId2;
    public TableColumn verticalPressKPAId2;
    public TableColumn porePressKPAId2;
    public TableColumn varticalDeformationId2;
    public TableColumn verticalpressMPaId2;
    public TableColumn porepressMPaId2;
    public TableColumn verticalStrainId2;
    public TableColumn tarDeformationId2;
    public TableColumn stageId2;

    private ControllerCompressionSlump compressionSlump;
    private FilterDataController filterDataController;
    private DataTestReportController dataTestReportController;

    private ArrayList<CompressionData> compressionDataArrayList_1;
    private ObservableList<CompressionData> compressionDataObservableList_1;
    private ArrayList<CompressionData> compressionDataArrayListFilter_1;

    private ArrayList<CompressionData> compressionDataArrayList_2;
    private ObservableList<CompressionData> compressionDataObservableList_2;
    private ArrayList<CompressionData> compressionDataArrayListFilter_2;

    private ReportData reportData =new ReportData();

    //Геттеры сеттеры для получения ссылки на контроллеры
    public FilterDataController getFilterDataController(){
        return filterDataController;
    }

    public ControllerCompressionSlump getControllerParent() {
        return compressionSlump;
    }

    public void setControllerParent(ControllerCompressionSlump controllerParent) {
        this.compressionSlump = controllerParent;
    }

    public void initialize() {
        timeId1.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("Time"));
        actionId1.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Action"));
        actionChangedId1.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Action_Changed"));
        verticalPressKPAId1.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalPress_kPa"));
        porePressKPAId1.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("PorePress_kPa"));
        varticalDeformationId1.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalDeformation_mm"));
        verticalpressMPaId1.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalPress_MPa"));
        porepressMPaId1.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("PorePress_MPa"));
        verticalStrainId1.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalStrain"));
        tarDeformationId1.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("TarDeformation_mm"));
        stageId1.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Stage"));

        timeId2.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("Time"));
        actionId2.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Action"));
        actionChangedId2.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Action_Changed"));
        verticalPressKPAId2.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalPress_kPa"));
        porePressKPAId2.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("PorePress_kPa"));
        varticalDeformationId2.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalDeformation_mm"));
        verticalpressMPaId2.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalPress_MPa"));
        porepressMPaId2.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("PorePress_MPa"));
        verticalStrainId2.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("VerticalStrain"));
        tarDeformationId2.setCellValueFactory(new PropertyValueFactory<CompressionData, Double>("TarDeformation_mm"));
        stageId2.setCellValueFactory(new PropertyValueFactory<CompressionData, String>("Stage"));
    }

    //Метод обновления таблицы 1 после фильтрации
    public void init1(){
        System.out.println("Функция обновления 1");
        tableCompression1.getItems().clear();
        compressionDataObservableList_1= FXCollections.observableArrayList(compressionDataArrayListFilter_1);
        tableCompression1.setItems(compressionDataObservableList_1);
        tableCompression1.refresh();
    }

    //Метод обновления таблицы 2 после фильтрации
    public void init2(){
        System.out.println("Функция обновления 1");
        tableCompression2.getItems().clear();
        compressionDataObservableList_2= FXCollections.observableArrayList(compressionDataArrayListFilter_2);
        tableCompression2.setItems(compressionDataObservableList_2);
        tableCompression2.refresh();
    }

    public void sendReportData(ReportData reportDataa){
        reportData=reportDataa;
        System.out.println("Проверка, вернулись ли данные из контроллера reportData:");
        System.out.println(reportData.outDataReport());
    }

    //Метод получение ОТФИЛЬТРОВАННЫХ данных из FilterData 1
    public void sendDataController1(ArrayList<CompressionData> compressionData){
        compressionDataArrayListFilter_1=compressionData;
        System.out.println("НИЖЕ то что передали НАЗАД В ГЛАВНЫЙ КОНТРОЛЛЕР:");
        for(int i=0;i<compressionDataArrayListFilter_1.size();i++) {
            System.out.println(compressionDataArrayListFilter_1.get(i).outDataCompr());
        }
        init1();
    }

    //Метод получение ОТФИЛЬТРОВАННЫХ данных из FilterData 2
    public void sendDataController2(ArrayList<CompressionData> compressionData){
        compressionDataArrayListFilter_2=compressionData;
        System.out.println("НИЖЕ то что передали НАЗАД В ГЛАВНЫЙ КОНТРОЛЛЕР:");
        for(int i=0;i<compressionDataArrayListFilter_2.size();i++) {
            System.out.println(compressionDataArrayListFilter_2.get(i).outDataCompr());
        }
        init2();
    }

    //Метод загрузки файла 1!
    public void loadfile1(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Выбор файла с данными");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Log files (*.log)", "*.log");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(this);//Указываем текущую сцену
        if (file != null) {
            System.out.println("Процесс открытия файла. ФАЙЛ:"+file);
            Scanner scanner = new Scanner(file);
            ReadFromFile readFromFile = new ReadFromFile(scanner);
            compressionDataArrayList_1=  readFromFile.parserToCompressionData();
            compressionDataObservableList_1= FXCollections.observableArrayList(compressionDataArrayList_1);
            tableCompression1.setItems(compressionDataObservableList_1);
        }
    }


    public void loadfile2(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Выбор файла с данными");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Log files (*.log)", "*.log");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(this);//Указываем текущую сцену
        if (file != null) {
            System.out.println("Процесс открытия файла. ФАЙЛ:"+file);
            Scanner scanner = new Scanner(file);
            ReadFromFile readFromFile = new ReadFromFile(scanner);
            compressionDataArrayList_2=  readFromFile.parserToCompressionData();
            compressionDataObservableList_2= FXCollections.observableArrayList(compressionDataArrayList_2);
            tableCompression2.setItems(compressionDataObservableList_2);
        }
    }

    public void createReport(ActionEvent event) {
    }

    public void settingDataReport(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dataReport.fxml"));
        Parent root = fxmlLoader.load();
        Stage st = new Stage();
        st.setResizable(false);
        st.setTitle("Заполните данные для отчета об испытании");
        st.setScene(new Scene(root, 450, 500));
        dataTestReportController=fxmlLoader.getController();
        dataTestReportController.setControllerParentSlump(this);
        dataTestReportController.initialize(reportData);
        st.show();
    }

    public void FastFilter1(ActionEvent event) throws IOException {
        if (compressionDataObservableList_1==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Файл не был загружен.");
            alert.showAndWait();
            return;
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filterData.fxml"));
            Parent root = fxmlLoader.load();
            filterDataController=fxmlLoader.getController();
            filterDataController.setControllerParentSlump(this);
            filterDataController.initialize(compressionDataArrayList_1);
            filterDataController.fastFilterSlump1();
        }
    }

    public void FastFilter2(ActionEvent event) throws IOException {
        if (compressionDataObservableList_2==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Файл не был загружен.");
            alert.showAndWait();
            return;
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filterData.fxml"));
            Parent root = fxmlLoader.load();
            filterDataController=fxmlLoader.getController();
            filterDataController.setControllerParentSlump(this);
            filterDataController.initialize(compressionDataArrayList_2);
            filterDataController.fastFilterSlump2();
        }
    }


}
