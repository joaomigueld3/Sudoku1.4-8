package br.com.poli.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class TelaComoJogarController implements Initializable{

    @FXML
    private Button btnVoltar;

    @FXML
    void clicaVoltar(ActionEvent event) {
    	try {
            new TelaSeguinte("TelaInicial.fxml").start(Main.stage);
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
    

}
