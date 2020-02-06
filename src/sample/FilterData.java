package sample;

import CompressionData.CompressionData;
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

public class FilterData  {


    public AnchorPane panelFilter;
    public Button butClose;
    public ComboBox<String> idOperator;
    public ComboBox idName2;
    public ComboBox idName1;
    public ComboBox idCondition2;
    public ComboBox<String> idCondition1;
    public ComboBox idValue2;
    public ComboBox<String> idValue1;

    private  ArrayList<CompressionData> dataCompression_;
    private Controller controllerParent;
    private FilterData filterDataController;


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
    public FilterData getFilterDataController() {
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


        idCondition1.setItems(dataCondition);
        idOperator.setItems(dataOperation);

      //  idValue1.setItems(..);
    }

    //Кнопка очищения
    public void getClear(ActionEvent event) {
    }

    //Кнопка ОКЕЙ- фильтруем данные по выбранным параметрам, передаем данные в Сontroller, закрываем окно
    public void getOkFilter(ActionEvent event) throws IOException {

        ArrayList<CompressionData> compressionDataArrayList2 = new ArrayList<CompressionData>();
        String cellName;
        double dataCell;
        cellName= (String) idName1.getValue();
        dataCell= Double.parseDouble(idValue1.getValue());
        System.out.println(cellName);
        System.out.println(dataCell);


        for(int i=0;i<dataCompression_.size();i++) {
            if(dataCell== dataCompression_.get(i).getTime()){
                compressionDataArrayList2.add(dataCompression_.get(i));
            }
        }
        System.out.println("Результат фильтрации:");
        for(int i=0;i<compressionDataArrayList2.size();i++) {
            System.out.println(compressionDataArrayList2.get(i).outDataCompr());
        }

        if (compressionDataArrayList2.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Пусто.");
            alert.showAndWait();
            return;
        } else {
            // FXMLLoader fxmlLoader = new FXMLLoader();
            controllerParent.getControllerParent(); // получаем контроллер
            controllerParent.sendDataController(compressionDataArrayList2);
            // controllerParent.initialize();
            //  fxmlLoaderController.init(); // инициализиуем страницу (работаем с данными)
        }

        Stage stage = (Stage) butClose.getScene().getWindow();
        stage.close();


    }

}
