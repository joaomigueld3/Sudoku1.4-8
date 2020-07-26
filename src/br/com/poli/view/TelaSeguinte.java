package br.com.poli.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaSeguinte extends Application {

	/*
	 * Esta classe serve para fazer a transicao de telas e dar o launch na main.
	 */

	public static Stage stage;
	private static String link; // vai salvar as strings para abrir uma nova pagina

	public TelaSeguinte(String link) {
		TelaSeguinte.link = link;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource(link));

		Scene scene = new Scene(root);

		TelaSeguinte.stage = primaryStage;
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(Main.class);
	}

}
