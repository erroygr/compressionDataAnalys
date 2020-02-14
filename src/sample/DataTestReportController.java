package sample;

import CompressionData.CompressionData;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DataTestReportController {


    public TextField labNomer;
    public TextField objk;
    public TextField nameVyrab;
    public TextField nameSoil;
    public TextField shemaTest;
    public TextField conditionSoil;
    public TextField customerName;
    public TextField depthSelection;
    public TextField equipment;
    public Button butClose;

    private ReportData reportData_;

    private Controller controllerParent;
    private DataTestReportController dataTestReportController;


    //Геттер и сеттер для ссылки на контроллер родителя
    public Controller getControllerParent() {
        return controllerParent;
    }

    public void setControllerParent(Controller controllerParent) {
        //controllerParent.setControllerParent(controllerParent);
        this.controllerParent = controllerParent;
    }
    //Геттер  для ссылки на этот контроллер (?)
    public DataTestReportController getDataTestReportController() {
        return dataTestReportController;
    }

    public void initialize(ReportData reportData) {
        reportData_=reportData;
    }

    public void getClear(ActionEvent event) {
    }

    public void getOk(ActionEvent event) {

        reportData_.setLaboratoryNumber(labNomer.getText());
        reportData_.setObjectTest(objk.getText());
        reportData_.setProductionName(nameVyrab.getText());
        reportData_.setSoilName(nameSoil.getText());
        reportData_.setSchemeTest(shemaTest.getText());
        reportData_.setSoilCondition(conditionSoil.getText());
        reportData_.setNameCustomer(customerName.getText());
        reportData_.setDepthSelection(depthSelection.getText());
        reportData_.setEquipment(equipment.getText());




        if (reportData_ == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Предупреждение: ");
            alert.setHeaderText(null);
            alert.setContentText("Пусто.");
            alert.showAndWait();
            return;
        } else {
            controllerParent.getControllerParent(); // получаем контроллер
            controllerParent.sendReportData(reportData_);
        }

        Stage stage = (Stage) butClose.getScene().getWindow();
        stage.close();
    }

    public void getCancel(ActionEvent event) {
        Stage stage = (Stage) butClose.getScene().getWindow();
        stage.close();
    }
}
