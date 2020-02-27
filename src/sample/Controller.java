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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class Controller extends Window {
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
    private String fileNames;

    private ArrayList<CompressionData> compressionDataArrayList;
    private ObservableList<CompressionData> compressionDataObservableList;
    private ArrayList<CompressionData> compressionDataArrayListFilter;

    private ReportData reportData =new ReportData();

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
    }

    //Метод обновления таблицы после фильтрации
    public void init(){
        System.out.println("ЗАШЛИ в ФУНКЦИЮ ОБНОВНЛЕНИЯ??");
        tableCompression.getItems().clear();
        compressionDataObservableList= FXCollections.observableArrayList(compressionDataArrayListFilter);
        tableCompression.setItems(compressionDataObservableList);
        tableCompression.refresh();
    }

    public void sendReportData(ReportData reportDataa){
        reportData=reportDataa;
        System.out.println("НИЖЕ то что передали НАЗАД В ГЛАВНЫЙ КОНТРОЛЛЕР ИЗ РЕПОРТ_ДАТЫ:");
            System.out.println(reportData.outDataReport());

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

    //Метод загрузки файла - доработан!
    public void loadfile(ActionEvent event) throws IOException {
        //String filePath=null;
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Выбор файла с данными");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Log files (*.log)", "*.log");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(this);//Указываем текущую сцену
        if (file != null) {
            fileNames = file.getName();
            System.out.println("Процесс открытия файла. ФАЙЛ:"+file);
           // System.out.println(filePath);
            Scanner scanner = new Scanner(file);
            ReadFromFile readFromFile = new ReadFromFile(scanner);
            compressionDataArrayList =  readFromFile.parserToCompressionData();
            compressionDataObservableList= FXCollections.observableArrayList(compressionDataArrayList);
            tableCompression.setItems(compressionDataObservableList);
        }

       // String filePath = "Test.1.log";

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
            st.setScene(new Scene(root, 697, 354));
            filterDataController=fxmlLoader.getController();
            filterDataController.setControllerParent(this);
          //  filterDataController.sendData(compressionDataArrayList); // передаём туда данные
            filterDataController.initialize(compressionDataArrayList);
          //  filterDataController.initComboBox();
            st.show();

        }
    }


    //Метод вызова окна данных для отчета, передачи в другой контроллер данных
    public void settingDataReport(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dataReport.fxml"));
        Parent root = fxmlLoader.load();
        Stage st = new Stage();
        st.setResizable(false);
        st.setTitle("Заполните данные для отчета об испытании");
        st.setScene(new Scene(root, 450, 500));
        dataTestReportController=fxmlLoader.getController();
        dataTestReportController.setControllerParent(this);
        dataTestReportController.initialize(reportData);
        st.show();

    }

    public void createReport(ActionEvent event) throws IOException {
        System.out.println("Зашли в функцию записи в ексель");
        // получаем файл с диска
        String filePath = "ReportTemplate.xls";
        FileInputStream file = new FileInputStream(new File(filePath));
        // считываем его в память
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        // говорим, что хотим работать со 2 листом
        HSSFSheet sheetAt = workbook.getSheetAt(1);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        // счетчик для строк
        int rowNum = 0;
        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
        Row row = sheetAt.createRow(rowNum);
        row.createCell(1).setCellValue("Время от начала испытания, c");
        row.createCell(2).setCellValue("Действие");
        row.createCell(3).setCellValue("Action_Changed");
        row.createCell(4).setCellValue("Вертикальная нагрузка, кПа");
        row.createCell(5).setCellValue("Поровое давление, кПа");
        row.createCell(6).setCellValue("Вертикальная деформация, мм");
        row.createCell(7).setCellValue("Вертикальная нагрузка, МПа");
        row.createCell(8).setCellValue("Поровое давление, МПа");
        row.createCell(9).setCellValue("Относительная вертикальная деформация");
        row.createCell(10).setCellValue("Тарировочная деформация, мм");
        row.createCell(11).setCellValue("Стадия испытания");

        // заполняем 2 лист данными
        for (CompressionData compressionData : compressionDataArrayListFilter) {
            createSheetHeader(sheetAt, ++rowNum, compressionData);
        }

        HSSFSheet sheetAtDataTesting = workbook.getSheetAt(0);


        HSSFRow rLaboratoryNumber = sheetAtDataTesting.getRow(8);
        HSSFRow  rObjectTest = sheetAtDataTesting.getRow(9);
        HSSFRow  rProductionName = sheetAtDataTesting.getRow(10);
        HSSFRow  rSoilName = sheetAtDataTesting.getRow(11);
        HSSFRow  rSchemeTest = sheetAtDataTesting.getRow(12);
        HSSFRow  rSoilCondition = sheetAtDataTesting.getRow(13);
        HSSFRow  rNameCustomer = sheetAtDataTesting.getRow(14);
        HSSFRow  rDepthSelection = sheetAtDataTesting.getRow(15);
        HSSFRow  rEquipment = sheetAtDataTesting.getRow(16);

        HSSFCell cLaboratoryNumber= rLaboratoryNumber.getCell(11);
        HSSFCell  cObjectTest =  rObjectTest.getCell(11);
        HSSFCell  cProductionName =  rProductionName.getCell(11);
        HSSFCell cSoilName =  rSoilName.getCell(11);
        HSSFCell cSchemeTest =  rSchemeTest.getCell(11);
        HSSFCell cSoilCondition =  rSoilCondition.getCell(11);
        HSSFCell cNameCustomer =  rNameCustomer.getCell(11);
        HSSFCell cDepthSelection =  rDepthSelection.getCell(11);
        HSSFCell cEquipment = rEquipment.getCell(11);


        cLaboratoryNumber.setCellValue(reportData.getLaboratoryNumber());
        cObjectTest.setCellValue(reportData.getObjectTest());
        cProductionName.setCellValue(reportData.getProductionName());
        cSoilName.setCellValue(reportData.getSoilName());
        cSchemeTest.setCellValue(reportData.getSchemeTest());
        cSoilCondition.setCellValue(reportData.getSoilCondition());
        cNameCustomer.setCellValue(reportData.getNameCustomer());
        cDepthSelection.setCellValue(reportData.getDepthSelection());
        cEquipment.setCellValue(reportData.getEquipment());


      workbook.setForceFormulaRecalculation(true);
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd'_'hh-mm");
        // получаем доступ к excel файлу и обновляем его
        try (FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Egor\\Desktop\\ГТ 7.1.1 Компрессионное сжатие ГОСТ"
                        +formatForDateNow.format(date)+".xls"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");


    }

    private static void createSheetHeader(HSSFSheet sheetAt, int rowNum, CompressionData compressionData) {
        Row row = sheetAt.createRow(rowNum);
        row.createCell(1).setCellValue(compressionData.getTime());
        row.createCell(2).setCellValue(compressionData.getAction());
        row.createCell(3).setCellValue(compressionData.getAction_Changed());
        row.createCell(4).setCellValue(compressionData.getVerticalPress_kPa());
        row.createCell(5).setCellValue(compressionData.getPorePress_kPa());
        row.createCell(6).setCellValue(compressionData.getVerticalDeformation_mm());
        row.createCell(7).setCellValue(compressionData.getVerticalPress_MPa());
        row.createCell(8).setCellValue(compressionData.getPorePress_MPa());
        row.createCell(9).setCellValue(compressionData.getVerticalStrain());
        row.createCell(10).setCellValue(compressionData.getTarDeformation_mm());
        row.createCell(11).setCellValue(compressionData.getStage());
    }

    public void FastFilter(ActionEvent event) throws IOException {
        if (compressionDataObservableList==null) {
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
            filterDataController.setControllerParent(this);
            filterDataController.initialize(compressionDataArrayList);
            filterDataController.fastFilter();


        }


    }
}

