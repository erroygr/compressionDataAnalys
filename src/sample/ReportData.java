package sample;

public class ReportData {

    private String laboratoryNumber;
    private String objectTest;
    private String productionName;
    private String soilName;
    private String schemeTest;
    private String soilCondition;
    private String nameCustomer;
    private String depthSelection;
    private String equipment;

    ReportData(){

    };
    ReportData(String laboratoryNumber, String objectTest,String productionName, String soilName,String schemeTest, String soilCondition,
               String nameCustomer, String depthSelection, String equipment){
        this.laboratoryNumber=laboratoryNumber;
        this.objectTest=objectTest;
        this.productionName=productionName;
        this.soilName=soilName;
        this.schemeTest=schemeTest;
        this.soilCondition=soilCondition;
        this.nameCustomer=nameCustomer;
        this.depthSelection=depthSelection;
        this.equipment=equipment;

    }

    public String getLaboratoryNumber() {
        return laboratoryNumber;
    }

    public void setLaboratoryNumber(String laboratoryNumber) {
        this.laboratoryNumber = laboratoryNumber;
    }

    public String getObjectTest() {
        return objectTest;
    }

    public void setObjectTest(String objectTest) {
        this.objectTest = objectTest;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getSoilName() {
        return soilName;
    }

    public void setSoilName(String soilName) {
        this.soilName = soilName;
    }

    public String getSchemeTest() {
        return schemeTest;
    }

    public void setSchemeTest(String schemeTest) {
        this.schemeTest = schemeTest;
    }

    public String getSoilCondition() {
        return soilCondition;
    }

    public void setSoilCondition(String soilCondition) {
        this.soilCondition = soilCondition;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getDepthSelection() {
        return depthSelection;
    }

    public void setDepthSelection(String depthSelection) {
        this.depthSelection = depthSelection;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String outDataReport(){

        return  " | laboratoryNumber: " + getLaboratoryNumber() +
                " | objectTest: " + getObjectTest() +
                " | productionName: " + getProductionName() +
                " | soilName: " + getSoilName()+
                " | schemeTest: " + getSchemeTest()+
                " | soilCondition: "+ getSoilCondition()+
                " | nameCustomer: "+ getNameCustomer()+
                " | depthSelection: " + getDepthSelection()+
                " | equipment: "+getEquipment();
    }
}
