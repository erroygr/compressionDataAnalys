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
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static CompressionData.CompressionData.*;
import static CompressionData.CompressionData.TimeComparator;

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
        FilterDataStrategy filterDataStrategy1 = null;
        FilterDataStrategy filterDataStrategy2 = null;
        FilterDataStrategy filterDataStrategy3 = null;
        FilterDataStrategy filterDataStrategy4 = null;
        FilterDataStrategy filterDataStrategy5 = null;

        String nameColumn1 = idName1.getValue();
        String nameCondition1 = idCondition1.getValue();
        String nameValue1 =idValue1.getValue();

        String nameColumn2 = idName2.getValue();
        String nameCondition2 = idCondition2.getValue();
        String nameValue2 =idValue2.getValue();

        String nameColumn3 = idName3.getValue();
        String nameCondition3 = idCondition3.getValue();
        String nameValue3 =idValue3.getValue();

        String nameColumn4 = idName4.getValue();
        String nameCondition4 = idCondition4.getValue();
        String nameValue4 =idValue4.getValue();

        String nameColumn5 = idName5.getValue();
        String nameCondition5 = idCondition5.getValue();
        String nameValue5 =idValue5.getValue();

        String nameOperator1 = idOperator1.getValue();
        String nameOperator2 = idOperator2.getValue();
        String nameOperator3 = idOperator3.getValue();
        String nameOperator4 = idOperator4.getValue();

        ArrayList<CompressionData> compressionDataArrayListFilter = new ArrayList<CompressionData>();

        ArrayList<CompressionData> compresArrayListFilter1 = new ArrayList<CompressionData>();
        ArrayList<CompressionData> compresArrayListFilter2 = new ArrayList<CompressionData>();
        ArrayList<CompressionData> compresArrayListFilter3 = new ArrayList<CompressionData>();
        ArrayList<CompressionData> compresArrayListFilter4 = new ArrayList<CompressionData>();
        ArrayList<CompressionData> compresArrayListFilter5 = new ArrayList<CompressionData>();


        System.out.println("То что выбрал пользователь в комбобоксах:");
        System.out.println(nameColumn1);
        System.out.println(nameCondition1);
        System.out.println(nameValue1);

        System.out.println(nameColumn2);
        System.out.println(nameCondition2);
        System.out.println(nameValue2);

        System.out.println(nameColumn3);
        System.out.println(nameCondition3);
        System.out.println(nameValue3);

        System.out.println(nameColumn4);
        System.out.println(nameCondition4);
        System.out.println(nameValue4);

        System.out.println(nameColumn5);
        System.out.println(nameCondition5);
        System.out.println(nameValue5);

        System.out.println(nameOperator1);
        System.out.println(nameOperator2);
        System.out.println(nameOperator3);
        System.out.println(nameOperator4);

        if(nameColumn1!=null) {
            if (nameColumn1 == "Time") {
                filterDataStrategy1 = new FilterDataTimeStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "Action") {
                filterDataStrategy1 = new FilterDataActionStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "Action_Changed") {
                filterDataStrategy1 = new FilterDataActionChangedStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "VerticalPress_kPa") {
                filterDataStrategy1 = new FilterDataVerticalPresskPaStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "PorePress_kPa") {
                filterDataStrategy1 = new FilterDataPorePresskPaStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "VerticalDeformation_mm") {
                filterDataStrategy1 = new FilterDataVerticalDeformationmmStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "VerticalPress_MPa") {
                filterDataStrategy1 = new FilterDataVerticalPressMPaStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "PorePress_MPa") {
                filterDataStrategy1 = new FilterDataPorePressMPaStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "VerticalStrain") {
                filterDataStrategy1 = new FilterDataVerticalStrainStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "TarDeformation_mm") {
                filterDataStrategy1 = new FilterDataTarDeformationmmStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }
            if (nameColumn1 == "Stage") {
                filterDataStrategy1 = new FilterDataStageStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
            }

            compresArrayListFilter1 = filterDataStrategy1.goFilterData();
            for(int i=0;i<compresArrayListFilter1.size();i++){
                System.out.println(compresArrayListFilter1.get(i).outDataCompr());
            }
        }
        if(nameColumn2!=null) {
            if (nameColumn2 == "Time") {
                filterDataStrategy2 = new FilterDataTimeStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "Action") {
                filterDataStrategy2 = new FilterDataActionStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "Action_Changed") {
                filterDataStrategy2 = new FilterDataActionChangedStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "VerticalPress_kPa") {
                filterDataStrategy2 = new FilterDataVerticalPresskPaStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "PorePress_kPa") {
                filterDataStrategy2 = new FilterDataPorePresskPaStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "VerticalDeformation_mm") {
                filterDataStrategy2 = new FilterDataVerticalDeformationmmStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "VerticalPress_MPa") {
                filterDataStrategy2 = new FilterDataVerticalPressMPaStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "PorePress_MPa") {
                filterDataStrategy2 = new FilterDataPorePressMPaStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "VerticalStrain") {
                filterDataStrategy2 = new FilterDataVerticalStrainStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "TarDeformation_mm") {
                filterDataStrategy2 = new FilterDataTarDeformationmmStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }
            if (nameColumn2 == "Stage") {
                filterDataStrategy2 = new FilterDataStageStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);
            }

            compresArrayListFilter2 = filterDataStrategy2.goFilterData();
        }
        if(nameColumn3!=null) {
            if (nameColumn3 == "Time") {
                filterDataStrategy3 = new FilterDataTimeStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "Action") {
                filterDataStrategy3 = new FilterDataActionStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "Action_Changed") {
                filterDataStrategy3 = new FilterDataActionChangedStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "VerticalPress_kPa") {
                filterDataStrategy3 = new FilterDataVerticalPresskPaStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "PorePress_kPa") {
                filterDataStrategy3 = new FilterDataPorePresskPaStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "VerticalDeformation_mm") {
                filterDataStrategy3 = new FilterDataVerticalDeformationmmStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "VerticalPress_MPa") {
                filterDataStrategy3 = new FilterDataVerticalPressMPaStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "PorePress_MPa") {
                filterDataStrategy3 = new FilterDataPorePressMPaStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "VerticalStrain") {
                filterDataStrategy3 = new FilterDataVerticalStrainStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "TarDeformation_mm") {
                filterDataStrategy3 = new FilterDataTarDeformationmmStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }
            if (nameColumn3 == "Stage") {
                filterDataStrategy3 = new FilterDataStageStrategy(dataCompression_, nameColumn3, nameCondition3, nameValue3);
            }

            compresArrayListFilter3 = filterDataStrategy3.goFilterData();
        }
        if(nameColumn4!=null) {
            if (nameColumn4 == "Time") {
                filterDataStrategy4 = new FilterDataTimeStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "Action") {
                filterDataStrategy4 = new FilterDataActionStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "Action_Changed") {
                filterDataStrategy4 = new FilterDataActionChangedStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "VerticalPress_kPa") {
                filterDataStrategy4 = new FilterDataVerticalPresskPaStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "PorePress_kPa") {
                filterDataStrategy4 = new FilterDataPorePresskPaStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "VerticalDeformation_mm") {
                filterDataStrategy4 = new FilterDataVerticalDeformationmmStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "VerticalPress_MPa") {
                filterDataStrategy4 = new FilterDataVerticalPressMPaStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "PorePress_MPa") {
                filterDataStrategy4 = new FilterDataPorePressMPaStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "VerticalStrain") {
                filterDataStrategy4 = new FilterDataVerticalStrainStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "TarDeformation_mm") {
                filterDataStrategy4 = new FilterDataTarDeformationmmStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }
            if (nameColumn4 == "Stage") {
                filterDataStrategy4 = new FilterDataStageStrategy(dataCompression_, nameColumn4, nameCondition4, nameValue4);
            }

            compresArrayListFilter4 = filterDataStrategy4.goFilterData();
        }
        if(nameColumn5!=null) {
            if (nameColumn5 == "Time") {
                filterDataStrategy5 = new FilterDataTimeStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "Action") {
                filterDataStrategy5 = new FilterDataActionStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "Action_Changed") {
                filterDataStrategy5 = new FilterDataActionChangedStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "VerticalPress_kPa") {
                filterDataStrategy5 = new FilterDataVerticalPresskPaStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "PorePress_kPa") {
                filterDataStrategy5 = new FilterDataPorePresskPaStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "VerticalDeformation_mm") {
                filterDataStrategy5 = new FilterDataVerticalDeformationmmStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "VerticalPress_MPa") {
                filterDataStrategy5 = new FilterDataVerticalPressMPaStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "PorePress_MPa") {
                filterDataStrategy5 = new FilterDataPorePressMPaStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "VerticalStrain") {
                filterDataStrategy5 = new FilterDataVerticalStrainStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "TarDeformation_mm") {
                filterDataStrategy5 = new FilterDataTarDeformationmmStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }
            if (nameColumn5 == "Stage") {
                filterDataStrategy5 = new FilterDataStageStrategy(dataCompression_, nameColumn5, nameCondition5, nameValue5);
            }

            compresArrayListFilter5 = filterDataStrategy5.goFilterData();
        }

        System.out.println("ФИЛЬТРАЦИЯ 2:");
        for(int i=0;i<compresArrayListFilter2.size();i++) {
            System.out.println(compresArrayListFilter2.get(i).outDataCompr());
        }
        System.out.println("ФИЛЬТРАЦИЯ 3:");
        for(int i=0;i<compresArrayListFilter3.size();i++) {
            System.out.println(compresArrayListFilter3.get(i).outDataCompr());
        }
        System.out.println("ФИЛЬТРАЦИЯ 4:");
        for(int i=0;i<compresArrayListFilter4.size();i++) {
            System.out.println(compresArrayListFilter4.get(i).outDataCompr());
        }
        System.out.println("ФИЛЬТРАЦИЯ 5:");
        for(int i=0;i<compresArrayListFilter5.size();i++) {
            System.out.println(compresArrayListFilter5.get(i).outDataCompr());
        }



        if(nameOperator1!=null) {
            if (nameOperator1 == "И") {
                Set<CompressionData> set1 = new HashSet<>(compresArrayListFilter1);
                Set<CompressionData> set2 = new HashSet<>(compresArrayListFilter2);
                set1.retainAll(set2);
                compressionDataArrayListFilter.clear();
                compressionDataArrayListFilter.addAll(set1);
            }
            if (nameOperator1 == "ИЛИ") {
                Set<CompressionData> set1 = new HashSet<>(compresArrayListFilter1);
                Set<CompressionData> set2 = new HashSet<>(compresArrayListFilter2);
                set1.addAll(set2);
                compressionDataArrayListFilter.clear();
                compressionDataArrayListFilter.addAll(set1);
            }
        } else {
            Set<CompressionData> set1 = new HashSet<>(compresArrayListFilter1);
            compressionDataArrayListFilter.clear();
            compressionDataArrayListFilter.addAll(set1);
        }

        System.out.println("ФИЛЬТРАЦИЯ 1 и 2: Условие "+nameOperator1 +" Столбец1 "+nameColumn1+" Столбец2 "+nameColumn2
                +" знак1 и знак2: "+nameCondition1+" "+nameCondition2+" значение1 и знач2: "+nameValue1+" "+nameValue2);
        for(int i=0;i<compressionDataArrayListFilter.size();i++) {
            System.out.println(compressionDataArrayListFilter.get(i).outDataCompr());
        }

        if(nameOperator2!=null){
            if (nameOperator2=="И"){
                Set<CompressionData> set12 =new HashSet<>(compressionDataArrayListFilter);
                Set<CompressionData> set3 =new HashSet<>(compresArrayListFilter3);
                set12.retainAll(set3);
                compressionDataArrayListFilter.clear();
                compressionDataArrayListFilter.addAll(set12);
            }
            if (nameOperator2=="ИЛИ"){
                Set<CompressionData> set12 =new HashSet<>(compressionDataArrayListFilter);
                Set<CompressionData> set3=new HashSet<>(compresArrayListFilter3);
                set12.addAll(set3);
                compressionDataArrayListFilter.clear();
                compressionDataArrayListFilter.addAll(set12);
            }
        }

        System.out.println("ФИЛЬТРАЦИЯ 12 и 3: Условие "+nameOperator2+" Столбец3 "+nameColumn3
                +" знак3: "+nameCondition3+"знач3: "+nameValue3);
        for(int i=0;i<compressionDataArrayListFilter.size();i++) {
            System.out.println(compressionDataArrayListFilter.get(i).outDataCompr());
        }


        if(nameOperator3!=null){
            if (nameOperator3=="И"){
                Set<CompressionData> set123 =new HashSet<>(compressionDataArrayListFilter);
                Set<CompressionData> set4 =new HashSet<>(compresArrayListFilter4);
                set123.retainAll(set4);
                compressionDataArrayListFilter.clear();
                compressionDataArrayListFilter.addAll(set123);
            }
            if (nameOperator3=="ИЛИ"){
                Set<CompressionData> set123 =new HashSet<>(compressionDataArrayListFilter);
                Set<CompressionData> set4=new HashSet<>(compresArrayListFilter4);
                set123.addAll(set4);
                compressionDataArrayListFilter.clear();
                compressionDataArrayListFilter.addAll(set123);
            }
        }

        System.out.println("ФИЛЬТРАЦИЯ 123 и 4: Условие "+nameOperator3+" Столбец4 "+nameColumn4
                +" знак4: "+nameCondition4+"знач4: "+nameValue4);
        for(int i=0;i<compressionDataArrayListFilter.size();i++) {
            System.out.println(compressionDataArrayListFilter.get(i).outDataCompr());
        }

        if(nameOperator4!=null){
            if (nameOperator4=="И"){
                Set<CompressionData> set1234 =new HashSet<>(compressionDataArrayListFilter);
                Set<CompressionData> set5 =new HashSet<>(compresArrayListFilter5);
                set1234.retainAll(set5);
                compressionDataArrayListFilter.clear();
                compressionDataArrayListFilter.addAll(set1234);
            }
            if (nameOperator4=="ИЛИ"){
                Set<CompressionData> set1234 =new HashSet<>(compressionDataArrayListFilter);
                Set<CompressionData> set5=new HashSet<>(compresArrayListFilter5);
                set1234.addAll(set5);
                compressionDataArrayListFilter.clear();
                compressionDataArrayListFilter.addAll(set1234);
            }
        }

        System.out.println("ФИЛЬТРАЦИЯ 1234 и 5: Условие "+nameOperator4+" Столбец5 "+nameColumn5
                +" знак5: "+nameCondition5+"знач5: "+nameValue5);
        for(int i=0;i<compressionDataArrayListFilter.size();i++) {
            System.out.println(compressionDataArrayListFilter.get(i).outDataCompr());
        }

        if (compressionDataArrayListFilter.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Пусто.");
            alert.showAndWait();
            return;
        } else {
            compressionDataArrayListFilter.sort(TimeComparator);
            controllerParent.getControllerParent(); // получаем контроллер
            controllerParent.sendDataController(compressionDataArrayListFilter);
        }
        Stage stage = (Stage) butClose.getScene().getWindow();
        stage.close();
    }

    public void fastFilter(){

        String nameColumn1 = "Action_Changed";
        String nameCondition1 = "=";
        String nameValue1 ="True";
        String nameColumn2 = "Action";
        String nameCondition2 = "=";
        String nameValue2 ="Stabilization";

        ArrayList<CompressionData> compresListFilter1;
        ArrayList<CompressionData> compresListFilter2;

        FilterDataStrategy filter1 = new FilterDataActionChangedStrategy(dataCompression_, nameColumn1, nameCondition1, nameValue1);
        FilterDataStrategy filter2 = new FilterDataActionStrategy(dataCompression_, nameColumn2, nameCondition2, nameValue2);

        compresListFilter1 = filter1.goFilterData();
        compresListFilter2 = filter2.goFilterData();

        Set<CompressionData> set1 = new HashSet<>(compresListFilter1);
        Set<CompressionData> set2 = new HashSet<>(compresListFilter2);
        set1.retainAll(set2);
        ArrayList<CompressionData> rezultCompressionListFilter = new ArrayList<CompressionData>(set1);

        if (rezultCompressionListFilter.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Фильтрация потерпела неудачу. Ошибка в загруженных данных.");
            alert.showAndWait();
            return;
        } else {
            rezultCompressionListFilter.sort(TimeComparator);
            controllerParent.getControllerParent(); // получаем контроллер
            controllerParent.sendDataController(rezultCompressionListFilter);
        }
    }


}
