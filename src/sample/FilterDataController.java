package sample;

import CompressionData.CompressionData;
import FilterDataStrategyClass.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FilterDataController {


    public AnchorPane panelFilter;
    public Button butClose;

    public ComboBox<String> idOperator1;
    public ComboBox<String> idOperator2;
    public ComboBox<String> idOperator3;
    public ComboBox<String> idOperator4;

    public ComboBox<String> idName1;
    public ComboBox<String> idName2;
    public ComboBox<String> idName3;
    public ComboBox<String> idName4;
    public ComboBox<String> idName5;

    public ComboBox<String> idCondition1;
    public ComboBox<String> idCondition2;
    public ComboBox<String> idCondition3;
    public ComboBox<String> idCondition4;
    public ComboBox<String> idCondition5;

    public ComboBox<String> idValue1;
    public ComboBox<String> idValue2;
    public ComboBox<String> idValue3;
    public ComboBox<String> idValue4;
    public ComboBox<String> idValue5;


    private  ArrayList<CompressionData> dataCompression_;
    private Controller controllerParent;
    private FilterDataController filterDataController;


    ArrayList<String> arrTime = new ArrayList<>();
    ArrayList<String> arrAction = new ArrayList<>();
    ArrayList<String> arrAction_Cha = new ArrayList<>();
    ArrayList<String> arrVerticalPress_k = new ArrayList<>();
    ArrayList<String> arrPorePress_kPa = new ArrayList<>();
    ArrayList<String> arrVerticalDeformation_mm = new ArrayList<>();
    ArrayList<String> arrVerticalPress_MPa = new ArrayList<>();
    ArrayList<String> arrPorePress_MPa = new ArrayList<>();
    ArrayList<String> arrVerticalStrain = new ArrayList<>();
    ArrayList<String> arrTarDeformation_mm = new ArrayList<>();
    ArrayList<String> arrStage = new ArrayList<>();

    //Геттер и сеттер для ссылки на контроллер родителя
    public Controller getControllerParent() {
        return controllerParent;
    }

    public void setControllerParent(Controller controllerParent) {
        //controllerParent.setControllerParent(controllerParent);
       this.controllerParent = controllerParent;
    }
    //Геттер  для ссылки на этот контроллер (?)
    public FilterDataController getFilterDataController() {
        return filterDataController;
    }

    //Кнопка закрытия окна -Отмена
    public void getClose(ActionEvent event) {
        Stage stage = (Stage) butClose.getScene().getWindow();
        stage.close();
    }

    //Метод получения данных из одного контроллера в другой (УСТАРЕЛА---Перешла в initialize)
    public void sendData(ArrayList<CompressionData> compressionDataArrayList) {
        dataCompression_=compressionDataArrayList;
        System.out.println("НИЖЕ то что передали В КОНТРОЛЛЕР ФИЛЬТРА:");
        for(int i=0;i<dataCompression_.size();i++) {
            System.out.println(dataCompression_.get(i).outDataCompr());
        }



    }
    //Метод для инициализации комбобоксов и обновления (УСТАРЕЛА---Перешла в initialize)
    public void initComboBox(){
        ArrayList<String> arrTime = new ArrayList<>();
        ArrayList<String> arrAction = new ArrayList<>();
        ArrayList<String> arrAction_Cha = new ArrayList<>();
        ArrayList<String> arrVerticalPress_k = new ArrayList<>();
        ArrayList<String> arrPorePress_kPa = new ArrayList<>();
        ArrayList<String> arrVerticalDeformation_mm = new ArrayList<>();
        ArrayList<String> arrVerticalPress_MPa = new ArrayList<>();
        ArrayList<String> arrPorePress_MPa = new ArrayList<>();
        ArrayList<String> arrVerticalStrain = new ArrayList<>();
        ArrayList<String> arrTarDeformation_mm = new ArrayList<>();
        ArrayList<String> arrStage = new ArrayList<>();

        for(int i=0;i<dataCompression_.size();i++) {
            arrTime.add(String.valueOf(dataCompression_.get(i).getTime()));
            arrAction.add(String.valueOf(dataCompression_.get(i).getAction()));
            arrAction_Cha.add(String.valueOf(dataCompression_.get(i).getAction_Changed()));
            arrVerticalPress_k.add(String.valueOf(dataCompression_.get(i).getVerticalPress_kPa()));
            arrPorePress_kPa.add(String.valueOf(dataCompression_.get(i).getPorePress_kPa()));
            arrVerticalDeformation_mm.add(String.valueOf(dataCompression_.get(i).getVerticalDeformation_mm()));
            arrVerticalPress_MPa.add(String.valueOf(dataCompression_.get(i).getVerticalPress_MPa()));
            arrPorePress_MPa.add(String.valueOf(dataCompression_.get(i).getPorePress_MPa()));
            arrVerticalStrain.add(String.valueOf(dataCompression_.get(i).getVerticalStrain()));
            arrTarDeformation_mm.add(String.valueOf(dataCompression_.get(i).getTarDeformation_mm()));
            arrStage.add(String.valueOf(dataCompression_.get(i).getStage()));
        }

        Map<String, List<String>> valueNameColumn = new HashMap<>();
        valueNameColumn.put("Time", arrTime);
        valueNameColumn.put("Action", arrAction);
        valueNameColumn.put("Action_Changed", arrAction_Cha);
        valueNameColumn.put("VerticalPress_kPa", arrVerticalPress_k);
        valueNameColumn.put("PorePress_kPa", arrPorePress_kPa);
        valueNameColumn.put("VerticalDeformation_mm", arrVerticalDeformation_mm);
        valueNameColumn.put("VerticalPress_MPa", arrVerticalPress_MPa);
        valueNameColumn.put("PorePress_MPa", arrPorePress_MPa);
        valueNameColumn.put("VerticalStrain", arrVerticalStrain);
        valueNameColumn.put("TarDeformation_mm", arrTarDeformation_mm);
        valueNameColumn.put("Stage", arrStage);

        idName1.valueProperty().addListener(((observable, oldValue, newValue) -> {
            List<String> list = valueNameColumn.get(newValue);
            if(list !=null){
                idValue1.setDisable(false);
                idValue1.getItems().setAll(list);
            }else {
                idValue1.getItems().clear();
                idValue1.setDisable(true);
            }
        }));
    }

    //Метод удаления повторок в списках полученных данных
    public ArrayList deleteRepeat(ArrayList<String> array){
       return (ArrayList) array.stream().distinct().collect(Collectors.toList());
    }

    public void initialize(ArrayList<CompressionData> compressionDataArrayList){

        dataCompression_=compressionDataArrayList;
        System.out.println("НИЖЕ то что передали В КОНТРОЛЛЕР ФИЛЬТРА:");
        for(int i=0;i<dataCompression_.size();i++) {
            System.out.println(dataCompression_.get(i).outDataCompr());
        }

        ObservableList<String> dataOperation = FXCollections.observableArrayList("И","ИЛИ");
        ObservableList<String> dataCondition = FXCollections.observableArrayList("=","<>");
        ObservableList<String> nameColumn = FXCollections.observableArrayList("Time", "Action","Action_Changed","VerticalPress_kPa",
                "PorePress_kPa", "VerticalDeformation_mm","VerticalPress_MPa","PorePress_MPa","VerticalStrain","TarDeformation_mm","Stage");


        for(int i=0;i<dataCompression_.size();i++) {
            arrTime.add(String.valueOf(dataCompression_.get(i).getTime()));
            arrAction.add(String.valueOf(dataCompression_.get(i).getAction()));
            arrAction_Cha.add(String.valueOf(dataCompression_.get(i).getAction_Changed()));
            arrVerticalPress_k.add(String.valueOf(dataCompression_.get(i).getVerticalPress_kPa()));
            arrPorePress_kPa.add(String.valueOf(dataCompression_.get(i).getPorePress_kPa()));
            arrVerticalDeformation_mm.add(String.valueOf(dataCompression_.get(i).getVerticalDeformation_mm()));
            arrVerticalPress_MPa.add(String.valueOf(dataCompression_.get(i).getVerticalPress_MPa()));
            arrPorePress_MPa.add(String.valueOf(dataCompression_.get(i).getPorePress_MPa()));
            arrVerticalStrain.add(String.valueOf(dataCompression_.get(i).getVerticalStrain()));
            arrTarDeformation_mm.add(String.valueOf(dataCompression_.get(i).getTarDeformation_mm()));
            arrStage.add(String.valueOf(dataCompression_.get(i).getStage()));
        }


        Map<String, List<String>> valueNameColumn = new HashMap<>();
        valueNameColumn.put(nameColumn.get(0), deleteRepeat(arrTime));
        valueNameColumn.put(nameColumn.get(1), deleteRepeat(arrAction));
        valueNameColumn.put(nameColumn.get(2), deleteRepeat(arrAction_Cha));
        valueNameColumn.put(nameColumn.get(3), deleteRepeat(arrVerticalPress_k));
        valueNameColumn.put(nameColumn.get(4), deleteRepeat(arrPorePress_kPa));
        valueNameColumn.put(nameColumn.get(5), deleteRepeat(arrVerticalDeformation_mm));
        valueNameColumn.put(nameColumn.get(6), deleteRepeat(arrVerticalPress_MPa));
        valueNameColumn.put(nameColumn.get(7), deleteRepeat(arrVerticalStrain));
        valueNameColumn.put(nameColumn.get(8), deleteRepeat(arrVerticalStrain));
        valueNameColumn.put(nameColumn.get(9), deleteRepeat(arrTarDeformation_mm));
        valueNameColumn.put(nameColumn.get(10), deleteRepeat(arrStage));

        System.out.println("НИЖЕ проверка мапы:");
        Iterator<Map.Entry<String, List<String>>> iterator = valueNameColumn.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, List<String>> pair = iterator.next();
            String key = pair.getKey();
            List<String> value = pair.getValue();
            System.out.println(key + ":" + value);
        }

        idName1.setItems(nameColumn);
        idName2.setItems(nameColumn);
        idName3.setItems(nameColumn);
        idName4.setItems(nameColumn);
        idName5.setItems(nameColumn);

        idName2.setOnAction(event -> {
                    if(idName2.getValue() != null) {
                        System.out.println("ЗАШЛИ В ВИЗИБЛ КОМБОБОКСОВ?");
                        idOperator2.setVisible(true);
                        idName3.setVisible(true);
                        idCondition3.setVisible(true);
                        idValue3.setVisible(true);
                    }
        });

        idName3.setOnAction(event -> {
            if(idName3.getValue() != null) {
                System.out.println("ЗАШЛИ В ВИЗИБЛ КОМБОБОКСОВ?");
                idOperator3.setVisible(true);
                idName4.setVisible(true);
                idCondition4.setVisible(true);
                idValue4.setVisible(true);
            }
        });

        idName4.setOnAction(event -> {
            if(idName4.getValue() != null) {
                System.out.println("ЗАШЛИ В ВИЗИБЛ КОМБОБОКСОВ?");
                idOperator4.setVisible(true);
                idName5.setVisible(true);
                idCondition5.setVisible(true);
                idValue5.setVisible(true);
            }
        });

        idName1.valueProperty().addListener((obs, oldValue, newValue) -> {
            ObservableList<String> list = FXCollections.observableArrayList(valueNameColumn.get(newValue));
            if(list !=null){
                idValue1.setDisable(false);
                idValue1.setItems(list);
            }else {
                idValue1.getItems().clear();
                idValue1.setDisable(true);
            }
        });

        idName2.valueProperty().addListener((obs, oldValue, newValue) -> {
            ObservableList<String> list = FXCollections.observableArrayList(valueNameColumn.get(newValue));
            if (list != null) {
                idValue2.setDisable(false);
                idValue2.setItems(list);
            } else {
                idValue2.getItems().clear();
                idValue2.setDisable(true);
            }
        });

        idName3.valueProperty().addListener((obs, oldValue, newValue)-> {
            ObservableList<String> list = FXCollections.observableArrayList(valueNameColumn.get(newValue));
            if (list != null) {
                idValue3.setDisable(false);
                idValue3.setItems(list);
            } else {
                idValue3.getItems().clear();
                idValue3.setDisable(true);
            }
        });

        idName4.valueProperty().addListener((obs, oldValue, newValue)-> {
            ObservableList<String> list = FXCollections.observableArrayList(valueNameColumn.get(newValue));
            if (list != null) {
                idValue4.setDisable(false);
                idValue4.setItems(list);
            } else {
                idValue4.getItems().clear();
                idValue4.setDisable(true);
            }
        });

        idName5.valueProperty().addListener((obs, oldValue, newValue)-> {
            ObservableList<String> list = FXCollections.observableArrayList(valueNameColumn.get(newValue));
            if (list != null) {
                idValue5.setDisable(false);
                idValue5.setItems(list);
            } else {
                idValue5.getItems().clear();
                idValue5.setDisable(true);
            }
        });

        idOperator1.setItems(dataOperation);
        idOperator2.setItems(dataOperation);
        idOperator3.setItems(dataOperation);
        idOperator4.setItems(dataOperation);
        idCondition1.setItems(dataCondition);
        idCondition2.setItems(dataCondition);
        idCondition3.setItems(dataCondition);
        idCondition4.setItems(dataCondition);
        idCondition5.setItems(dataCondition);

    }

    //Кнопка очищения
    public void getClear(ActionEvent event) {
        idOperator2.setVisible(false);
        idOperator3.setVisible(false);
        idOperator4.setVisible(false);

        idName3.setVisible(false);
        idName4.setVisible(false);
        idName5.setVisible(false);

        idCondition3.setVisible(false);
        idCondition4.setVisible(false);
        idCondition5.setVisible(false);

        idValue3.setVisible(false);
        idValue4.setVisible(false);
        idValue5.setVisible(false);

    }

    //Кнопка ОКЕЙ- фильтруем данные по выбранным параметрам, передаем данные в Сontroller, закрываем окно
    public void getOkFilter(ActionEvent event) throws IOException {
        FilterDataStrategy filterDataStrategy = null;
        String nameColumn;
        String nameCondition;
        String nameValue;
        ArrayList<CompressionData> compressionDataArrayListFilter = new ArrayList<CompressionData>();
        ArrayList<CompressionData> compressionDataArrayList2 = new ArrayList<CompressionData>();
        nameColumn= idName1.getValue();
        nameCondition= idCondition1.getValue();
        nameValue=idValue1.getValue();

        System.out.println("То что выбрал пользователь в комбобоксах:");
        System.out.println(nameColumn);
        System.out.println(nameCondition);
        System.out.println(nameValue);

        if(nameColumn=="Time"){
            filterDataStrategy=new FilterDataTimeStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="Action"){
            filterDataStrategy=new FilterDataActionStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="Action_Changed"){
            filterDataStrategy=new FilterDataActionChangedStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="VerticalPress_kPa"){
            filterDataStrategy=new FilterDataVerticalPresskPaStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="PorePress_kPa"){
            filterDataStrategy=new FilterDataPorePresskPaStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="VerticalDeformation_mm"){
            filterDataStrategy=new FilterDataVerticalDeformationmmStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="VerticalPress_MPa"){
            filterDataStrategy=new FilterDataVerticalPressMPaStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="PorePress_MPa"){
            filterDataStrategy=new FilterDataPorePressMPaStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="VerticalStrain"){
            filterDataStrategy=new FilterDataVerticalStrainStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="TarDeformation_mm"){
            filterDataStrategy=new FilterDataTarDeformationmmStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }
        if(nameColumn=="Stage"){
            filterDataStrategy=new FilterDataStageStrategy(dataCompression_,nameColumn,nameCondition,nameValue);
        }

        compressionDataArrayListFilter=filterDataStrategy.goFilterData();

        if (compressionDataArrayListFilter.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Пусто.");
            alert.showAndWait();
            return;
        } else {
            // FXMLLoader fxmlLoader = new FXMLLoader();
            controllerParent.getControllerParent(); // получаем контроллер
            controllerParent.sendDataController(compressionDataArrayListFilter);
            // controllerParent.initialize();
            //  fxmlLoaderController.init(); // инициализиуем страницу (работаем с данными)
        }

        Stage stage = (Stage) butClose.getScene().getWindow();
        stage.close();


    }


}
