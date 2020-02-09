package FilterDataStrategyClass;

import CompressionData.CompressionData;

import java.util.ArrayList;

public class FilterDataActionStrategy implements FilterDataStrategy {

    private String nameColumn;
    private String nameCondition;
    private String nameValue;
    private ArrayList<CompressionData> arrayListFilterAction = new ArrayList<CompressionData>();
    private ArrayList<CompressionData> arrayListCompressionData;

    public FilterDataActionStrategy(ArrayList<CompressionData> arrayListCompressionData, String nameColumn,
                                  String nameCondition, String nameValue) {
        this.arrayListCompressionData=arrayListCompressionData;
        this.nameColumn=nameColumn;
        this.nameCondition=nameCondition;
        this.nameValue= nameValue;
    }


    @Override
    public ArrayList<CompressionData> goFilterData() {
        if(nameCondition=="="){
            for(int i=0;i<arrayListCompressionData.size();i++) {
                if(nameValue.equalsIgnoreCase(arrayListCompressionData.get(i).getAction())){
                    arrayListFilterAction.add(arrayListCompressionData.get(i));
                }
            }
        }

        if(nameCondition=="<>"){
            for(int i=0;i<arrayListCompressionData.size();i++) {
                if(!nameValue.equalsIgnoreCase(arrayListCompressionData.get(i).getAction())){
                    arrayListFilterAction.add(arrayListCompressionData.get(i));
                }
            }
        }

        return arrayListFilterAction;
    }
}
