package br.com.poli.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

public class Main extends Application {
	
	//dupla: João Miguel, João Vitor Santos.
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
		
		Scene cena = new Scene(root);
		
		Main.stage = primaryStage;
		stage.setScene(cena);
		stage.setTitle("Polidoku");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(Main.class);
	}
}
