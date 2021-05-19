/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.SchedaT;
import it.polito.tdp.rivers.model.Simulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Simulator sim;
	private Model model;
	SchedaT stemp;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void handleCampi(ActionEvent event) {
    	
    	stemp=model.getScheda(this.boxRiver.getValue());
    	
    	txtStartDate.setText(""+stemp.getPrima());
    	txtEndDate.setText(""+stemp.getUltima());
    	txtFMed.setText(""+stemp.getMedia());
    	this.txtNumMeasurements.setText(""+stemp.getTot());
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	
    	txtResult.clear();
    	String fattoreStringa=this.txtK.getText();
    	float k;
    	try {
    		k=Float.parseFloat(fattoreStringa);
    		
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Errore: devi inserire un numero");
    	    return;
        }
    	
    	//settaggio dei parametri in input
    	this.sim.setK(k);
    	this.sim.setR(boxRiver.getValue());
    	this.sim.setfMed(Float.parseFloat(txtFMed.getText()));
    	
    	//avvio simulazione
    	this.sim.run();
    	
    	txtResult.setText("Giorni di disservizio: "+this.sim.getnGiorniDisservizio()+"\n");
    	txtResult.appendText("Cmed: "+this.sim.getCmed());
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxRiver.getItems().addAll(model.listRivers());
    	this.sim = new Simulator();
    }
}
