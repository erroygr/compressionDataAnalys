package CompressionData;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;

public class CompressionData implements Comparable<CompressionData> {

    private double Time;
    private String Action;
    private String Action_Changed;
    private double VerticalPress_kPa;
    private double PorePress_kPa;
    private double  VerticalDeformation_mm;
    private double VerticalPress_MPa;
    private double PorePress_MPa;
    private double VerticalStrain;
    private double TarDeformation_mm;
    private String Stage;


    public CompressionData(){

    }
    public CompressionData(double Time, String Action,String Action_Changed, double VerticalPress_kPa,
                           double PorePress_kPa, double  VerticalDeformation_mm, double VerticalPress_MPa,
                           double PorePress_MPa, double VerticalStrain, double TarDeformation_mm, String Stage)
    {
        this.Time=Time;
        this.Action=Action;
        this.Action_Changed=Action_Changed;
        this.VerticalPress_kPa=VerticalPress_kPa;
        this.PorePress_kPa=PorePress_kPa;
        this.VerticalDeformation_mm=VerticalDeformation_mm;
        this.VerticalPress_MPa=VerticalPress_MPa;
        this.PorePress_MPa=PorePress_MPa;
        this.VerticalStrain=VerticalStrain;
        this.TarDeformation_mm=TarDeformation_mm;
        this.Stage=Stage;
    }

    public double getTime() {
        return Time;
    }

    public void setTime(double time) {
        Time = time;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getAction_Changed() {
        return Action_Changed;
    }

    public void setAction_Changed(String action_Changed) {
        Action_Changed = action_Changed;
    }

    public double getVerticalPress_kPa() {
        return VerticalPress_kPa;
    }

    public void setVerticalPress_kPa(double verticalPress_kPa) {
        VerticalPress_kPa = verticalPress_kPa;
    }

    public double getPorePress_kPa() {
        return PorePress_kPa;
    }

    public void setPorePress_kPa(double porePress_kPa) {
        PorePress_kPa = porePress_kPa;
    }

    public double getVerticalDeformation_mm() {
        return VerticalDeformation_mm;
    }

    public void setVerticalDeformation_mm(double verticalDeformation_mm) {
        VerticalDeformation_mm = verticalDeformation_mm;
    }

    public double getVerticalPress_MPa() {
        return VerticalPress_MPa;
    }

    public void setVerticalPress_MPa(double verticalPress_MPa) {
        VerticalPress_MPa = verticalPress_MPa;
    }

    public double getPorePress_MPa() {
        return PorePress_MPa;
    }

    public void setPorePress_MPa(double porePress_MPa) {
        PorePress_MPa = porePress_MPa;
    }

    public double getVerticalStrain() {
        return VerticalStrain;
    }

    public void setVerticalStrain(double verticalStrain) {
        VerticalStrain = verticalStrain;
    }

    public double getTarDeformation_mm() {
        return TarDeformation_mm;
    }

    public void setTarDeformation_mm(double tarDeformation_mm) {
        TarDeformation_mm = tarDeformation_mm;
    }

    public String getStage() {
        return Stage;
    }

    public void setStage(String stage) {
        Stage = stage;
    }

    public String outDataCompression(){

        return  " | Time: " + getTime() +
                " | Action: " + getAction() +
                " | Action_Changed: " + getAction_Changed() +
                " | VerticalPress_kPa: " + getVerticalPress_kPa()+
                " | PorePress_kPa: " + getPorePress_kPa()+
                " | VerticalDeformation_mm: "+ getVerticalDeformation_mm()+
                " | VerticalPress_MPa: "+ getVerticalPress_MPa()+
                " | PorePress_MPa: " + getPorePress_MPa()+
                " | VerticalStrain: "+ getVerticalStrain()+
                " | TarDeformation_mm: "+ getTarDeformation_mm()+
                " | Stage: "+getStage();
    }

    public String outDataCompr(){

        return  " " + getTime() +
                " " + getAction() +
                " " + getAction_Changed() +
                " " + getVerticalPress_kPa()+
                " " + getPorePress_kPa()+
                " " + getVerticalDeformation_mm()+
                " " + getVerticalPress_MPa()+
                " " + getPorePress_MPa()+
                " " + getVerticalStrain()+
                " " + getTarDeformation_mm()+
                " " + getStage();
    }

    @Override
    public int compareTo(CompressionData o) {
        return (int) (this.Time - o.Time);
    }

    public static Comparator<CompressionData> TimeComparator = new Comparator<CompressionData>() {
        @Override
        public int compare(CompressionData o1, CompressionData o2) {
            return (int) (o1.getTime() - o2.getTime());
        }

    };

}
