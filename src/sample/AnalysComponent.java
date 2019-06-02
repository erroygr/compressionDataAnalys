package sample;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.AnchorPane;
import jsat.DataSet;
import jsat.classifiers.ClassificationDataSet;
import jsat.datatransform.DataTransform;
import jsat.datatransform.PCA;
import jsat.datatransform.ZeroMeanTransform;
import jsatfx.Plot;



public class AnalysComponent {


    public AnchorPane ahropane;
    private  DataSet dataSetTEST;
    private ScatterChart<Number, Number> plot;
    public void init(){
        plot= fum_PCA(dataSetTEST);
        plot.getXAxis().setLabel("Признак 1");
        plot.getYAxis().setLabel("Признак 2");
        initOutputScatter();
    }

    public void sendData(DataSet data){
        dataSetTEST = data;
    }

    public void initOutputScatter(){
        ahropane.getChildren().add(plot);
    }

    public ScatterChart fum_PCA(DataSet dataSetTEST) {
        ClassificationDataSet cDataSet = new ClassificationDataSet(dataSetTEST, 0);

        // Для визуализации и осознания информации, с 4 атрибутами, используется PCA- уменьшим размерность атрибутов до 2х

        DataTransform zeroMean = new ZeroMeanTransform(cDataSet);
        cDataSet.applyTransform(zeroMean);

        //PCA-это преобразование,в котором  уменьшиается размерность при сохранении всех дисперсий в данных.
        //PCA также позволяет нам указать точное количество измерений, которые мы хотели бы
        DataTransform pca = new PCA(cDataSet, 2, 1e-9);

        cDataSet.applyTransform(pca);
        return Plot.scatterC(cDataSet);
    }
}
