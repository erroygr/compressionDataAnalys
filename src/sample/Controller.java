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


    private Controller controllerParent;
    private FilterDataController filterDataController;
    private DataTestReportController dataTestReportController;


    private ArrayList<CompressionData> compressionDataArrayList;
    private ObservableList<CompressionData> compressionDataObservableList;
    private ArrayList<CompressionData> compressionDataArrayListFilter;

    private ReportData reportData =new ReportData();

    //������� ������� ��� ��������� ������ �� �����������
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
    }

    //����� ���������� ������� ����� ����������
    public void init(){
        System.out.println("����� � ������� �����������??");
        tableCompression.getItems().clear();
        compressionDataObservableList= FXCollections.observableArrayList(compressionDataArrayListFilter);
        tableCompression.setItems(compressionDataObservableList);
        tableCompression.refresh();
    }

    public void sendReportData(ReportData reportDataa){
        reportData=reportDataa;
        System.out.println("���� �� ��� �������� ����� � ������� ���������� �� ������_����:");
            System.out.println(reportData.outDataReport());

    }
    //����� ��������� ��������������� ������ �� FilterData
    public void sendDataController(ArrayList<CompressionData> compressionData){
        compressionDataArrayListFilter=compressionData;
        System.out.println("���� �� ��� �������� ����� � ������� ����������:");
        for(int i=0;i<compressionDataArrayListFilter.size();i++) {
            System.out.println(compressionDataArrayListFilter.get(i).outDataCompr());
        }
        init();
    }

    //����� �������� ����� - �������� �� ���� ���� --���������� ��� ������ ������������� �����
    public void loadfile(ActionEvent event) throws IOException {
        String filePath = "Test.1.log";
        Scanner scanner = new Scanner(new File(filePath));
        ReadFromFile readFromFile = new ReadFromFile(scanner);
        compressionDataArrayList =  readFromFile.parserToCompressionData();
        compressionDataObservableList= FXCollections.observableArrayList(compressionDataArrayList);
         tableCompression.setItems(compressionDataObservableList);
    }

    //����� ������ ���� ����������, �������� � ������ ���������� ������
    public void getFilterCompressionData(ActionEvent event) throws IOException {
        if (compressionDataObservableList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("��������������: ");
            alert.setHeaderText(null);
            alert.setContentText("���� �� ��� ��������.");
            alert.showAndWait();
            return;
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filterData.fxml"));
            Parent root = fxmlLoader.load();
            Stage st = new Stage();
            st.setResizable(false);
            st.setTitle("������");
            st.setScene(new Scene(root, 697, 354));
            filterDataController=fxmlLoader.getController();
            filterDataController.setControllerParent(this);
          //  filterDataController.sendData(compressionDataArrayList); // ������� ���� ������
            filterDataController.initialize(compressionDataArrayList);
          //  filterDataController.initComboBox();
            st.show();

        }
    }


    //����� ������ ���� ������ ��� ������, �������� � ������ ���������� ������
    public void settingDataReport(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dataReport.fxml"));
        Parent root = fxmlLoader.load();
        Stage st = new Stage();
        st.setResizable(false);
        st.setTitle("��������� ������ ��� ������ �� ���������");
        st.setScene(new Scene(root, 450, 500));
        dataTestReportController=fxmlLoader.getController();
        dataTestReportController.setControllerParent(this);
        dataTestReportController.initialize(reportData);
        st.show();

    }
}

