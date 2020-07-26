package br.com.poli.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;

import br.com.poli.exceptions.GenericException;
import br.com.poli.game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class TelaInicialController implements Initializable{
	
	 @FXML
	    private Button btnGerarGrid;

	 @FXML
	    private TextField txtName;
	 @FXML
	 private Label txtErro;
	 
	 @FXML
	    private MenuItem btnFacil;

	    @FXML
	    private MenuItem btnNormal;

	    @FXML
	    private MenuItem btnDificil;

	    static Player player = new Player("");

	    @FXML
	    void clicarDificil(ActionEvent event) {
	    	

	    	 try {
	    		 player.setName(txtName.getText());
	             new TelaSeguinte("SudokuHard.fxml").start(Main.stage);
	             
	         }catch(GenericException e) {
	        	 txtErro.setText(e.getMessage());
	         }
	    	 
	    	 catch(Exception e) {
	             System.err.println(e.getMessage());
	         }

	    }

	    @FXML
	    void clicarFacil(ActionEvent event) {
	    	
	    	
	    	 try {
	    		 player.setName(txtName.getText());
	             new TelaSeguinte("SudokuEasy.fxml").start(Main.stage);
	             
	         }catch (GenericException e) {

	 			txtErro.setText(e.getMessage());
	 		}
	    	 
	    	 catch(Exception e) {
	             System.err.println(e.getMessage());
	         }
	    	

	    }

	    @FXML
	    void clicarNormal(ActionEvent event) {
	    	
	    	try {
	    		player.setName(txtName.getText());
	             new TelaSeguinte("SudokuNormal.fxml").start(Main.stage);
	             
	         }catch (GenericException e) {

		 			txtErro.setText(e.getMessage());
		 		}
	    	
	    	catch(Exception e) {
	             System.err.println(e.getMessage());
	         }

	    }
	    
	 @FXML
	    void clicarOutraJanela(ActionEvent event) {	
		
	    	
	    }
	 
	 @FXML
	    private Button btnComoJogar;

	 @FXML
	    void clicarComoJogar(ActionEvent event) {
		 try {
			 new TelaSeguinte("TelaComoJogar.fxml").start(Main.stage);
		} catch(Exception e) {
             System.err.println(e.getMessage());
	    }
	 }
	    
	 @FXML
	    private MenuItem SudokuEasy;
	   
	
	 @FXML
	    private MenuItem SudokuNormal;

	    
	 @FXML
	    private MenuItem SudokuHard;
	 
	 @Override
		public void initialize(URL location, ResourceBundle resources) {
			
			
		}

}
