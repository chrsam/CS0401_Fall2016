
package assignment2;

import java.text.DecimalFormat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/**
 *
 * @author chrisman
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private RadioButton newCustomer;
    
    @FXML
    private RadioButton regCustomer;
    
    @FXML
    private CheckBox chkBrakes;
    
    @FXML
    private CheckBox chkTireRot;
    
    @FXML
    private CheckBox chkFluid;
    
    @FXML
    private CheckBox chkCar;
    
    @FXML
    private CheckBox chkAEI;
    
    @FXML
    private CheckBox chkTireRep;
    
    @FXML
    private CheckBox chkOil;
    
    @FXML
    private Button btnPrint;
    
    @FXML
    private Button btnReset;
    
    @FXML
    private Label lblService;
    
    @FXML
    private ChoiceBox tireTypeBox;
    
    @FXML
    private ChoiceBox oilTypeBox;
    
    @FXML
    private ToggleGroup group;
    
    @FXML
    private TextField nameInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private TextField eInput;

    @FXML
    private TextField adInput;
    
    //table of service costs
    private final double newCustomerDiscount = 0.10;
    private final double regularCustomerDiscount = 0.15;
    private final double brakes = 30.30;
    private final double tireRotation = 20.20;
    private final double fluidCheck = 10.10;
    private final double carWash = 5.05;
    private final double annualEmissionInspection = 40.40;
    private final double regularTires = 250.25;
    private final double sportTires = 350.35;
    private final double regularOil = 15.15;
    private final double syntheticOil = 27.27;
    private double total;
    private String name, phone, email, address;
    boolean newCus;
    DecimalFormat decim = new DecimalFormat("#0.00");
    String newTotal;
    
    @FXML
    private void updateCost (ActionEvent event) {
        calcTotal();
        displayTotal();
//        total = extra;
//        if (newCus == true || newCus == false) {
//            if (newCus == true) {
//                total *= (1-newCustomerDiscount);
//                newTotal = decim.format(total);
//                lblService.setText("Service Cost: $" + newTotal);
//            } else if (newCus == false) {
//                total *= (1-regularCustomerDiscount);
//                newTotal = decim.format(total);
//                lblService.setText("Service Cost: $" + newTotal);
//            }
//        } else 
//            newTotal = decim.format(total);
//            lblService.setText("Service Cost: $" + newTotal);
    }
    
    @FXML
    private void calcTotal() {
        
        total = 0.00;
        
        if(chkBrakes.isSelected()) 
            total+=brakes;
        if(chkTireRot.isSelected())
            total+=tireRotation;
        if(chkFluid.isSelected()) 
            total+=fluidCheck;
        if(chkCar.isSelected()) 
            total+=carWash;
        if(chkAEI.isSelected()) 
            total+=annualEmissionInspection;
        
        if(chkTireRep.isSelected()) {
            tireTypeBox.setDisable(false);
            int select = tireTypeBox.getSelectionModel().selectedIndexProperty().getValue();
            if (select == 0) {
                total+=regularTires;
            } else if (select == 1) {
                total+=sportTires;
            }
        } else {
            tireTypeBox.setDisable(true);
        }
        
        if(chkOil.isSelected()) {
            oilTypeBox.setDisable(false);
            int select = oilTypeBox.getSelectionModel().selectedIndexProperty().getValue();
            if (select == 0) {
                total+=regularOil;
            } else if (select == 1) {
                total+=syntheticOil;
            }
        } else {
           oilTypeBox.setDisable(true);
        }   
    }
    
    @FXML
    private void displayTotal() {
        
        if (newCustomer.isSelected())
            newCus = true;
        else if (regCustomer.isSelected())
            newCus = false;
        
        if (newCus == true || newCus == false) {
            if (newCus == true) {
                total *= (1-newCustomerDiscount);
                newTotal = decim.format(total);
                lblService.setText("Service Cost: $" + newTotal);
            } else if (newCus == false) {
                total *= (1-regularCustomerDiscount);
                newTotal = decim.format(total);
                lblService.setText("Service Cost: $" + newTotal);
            }
        } else 
            newTotal = decim.format(total);
            lblService.setText("Service Cost: $" + newTotal);
    }
    
//    @FXML
//    private void chooseAny6 (ActionEvent event) {
//        if(chkTireRep.isSelected()) {
//            tireTypeBox.setDisable(false);
//        } else {
//            tireTypeBox.setDisable(true);
//        }
//    }
    
//    @FXML
//    private void tireChoice () {
//        int select = tireTypeBox.getSelectionModel().selectedIndexProperty().getValue();
//        if (select == 0) {
//            total+=regularTires;
//        } else if (select == 1) {
//            total+=sportTires;
//        }
//        updateCost();
//    }
    
//    @FXML
//    private void chooseAny7 (ActionEvent event) {
//       if(chkOil.isSelected()) {
//            oilTypeBox.setDisable(false);
//        } else {
//           oilTypeBox.setDisable(true);
//       }
//        
//    }
    
//    @FXML
//    private void oilChoice () {
//        int select = tireTypeBox.getSelectionModel().selectedIndexProperty().getValue();
//        if (select == 0) {
//            extra+=regularOil;
//        } else if (select == 1) {
//            extra+=syntheticOil;
//        }
//        updateCost();
//    }
    
    @FXML
    private void handleInvoice(ActionEvent event) {
        
        name = nameInput.getText();
        phone = phoneInput.getText();
        email = eInput.getText();
        address = adInput.getText();
        
        System.out.println("");
        System.out.println("Customer Information: ");
        System.out.println("***********************************************");
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("E-Mail: " + email);
        System.out.println("Address: " + address);
        System.out.println("***********************************************");
        System.out.println("Total Cost: $" + decim.format(total));
    }
    
    @FXML
    private void handleReset(ActionEvent event) {
        nameInput.clear();
        phoneInput.clear();
        eInput.clear();
        adInput.clear();
        
        chkBrakes.setSelected(false);
        chkTireRot.setSelected(false);
        chkFluid.setSelected(false);
        chkCar.setSelected(false);
        chkAEI.setSelected(false);
        chkTireRep.setSelected(false);
        chkOil.setSelected(false);
        
        tireTypeBox.setDisable(true);
        oilTypeBox.setDisable(true);
        
        tireTypeBox.getSelectionModel().select(0);
        oilTypeBox.setValue("Regular Oil");
        
        newCustomer.setSelected(true);
        regCustomer.setSelected(false);
        
        total = 0.00;
        calcTotal();
        displayTotal();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tireTypeBox.setItems(FXCollections
            .observableArrayList("Regular Tires", "Sport Tires"));
        tireTypeBox.getSelectionModel().select(0);
        
        oilTypeBox.setItems(FXCollections
            .observableArrayList("Regular Oil", "Synthetic Oil"));
        oilTypeBox.setValue("Regular Oil");
        
        tireTypeBox.getSelectionModel().selectedIndexProperty().
            addListener(new ChangeListener<Number>() {
                
            @Override
            public void changed(ObservableValue<? extends Number> observable, 
                Number oldValue, Number newValue) {
                    //tireChoice();
                    calcTotal();
                    displayTotal();
                }
            });
        
        oilTypeBox.getSelectionModel().selectedIndexProperty().
            addListener(new ChangeListener<Number>() {
            
            @Override
            public void changed(ObservableValue<? extends Number> ov, 
                Number value, Number new_value) {
                    //oilChoice();
                    calcTotal();
                    displayTotal();
                }
            });
    }    
    
}
