package br.com.poli.view;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.com.poli.game.AutonomousPlayer;
import br.com.poli.game.Cell;
import br.com.poli.game.Player;
import br.com.poli.exceptions.CellIsFixedException;
import br.com.poli.exceptions.CellValueException;
import br.com.poli.exceptions.GenericException;
import br.com.poli.game.SudokuEasy;
import br.com.poli.game.SudokuNormal;
import br.com.poli.game.SudokuHard;
import br.com.poli.view.Main;
import br.com.poli.view.TelaSeguinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SudokuHardController implements Initializable {

    
    
    @FXML
    private Label Tempo;

    @FXML
    private Text tempo;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnVerificar;

    @FXML
    private Button btnIniciar;
    
    @FXML
    private Button btnAutoSolve;
    
    @FXML
    private Button btnClearSolution;
    
    @FXML
    private Button btnGetOneAnswer;
    
    
	@FXML
	private Label txtErro;

	@FXML
	private Label txtTime;
	
	@FXML
	private Label txtAutoTime;
	
	@FXML
	private Label txtMessage;

	@FXML
	private Label Player;

	@FXML
	private Label txtName;

	@FXML
	private TextField txtCoordX;

	@FXML
	private TextField txtCoordY;

	@FXML
	private TextField txtValue;
	
	@FXML
    private GridPane gridPane;
	 
	
	// instanciando objetos
	SudokuHard sudokuHard = new SudokuHard(TelaInicialController.player);
	SudokuHard sudokuHardAutonomous = new SudokuHard(TelaInicialController.player);
	Cell cell = new Cell(0, false, false);
	AutonomousPlayer autonomousPlayer = new AutonomousPlayer(sudokuHard);
	
	
    //botão voltar para tela inicial
    @FXML
    void clicaVoltar(ActionEvent event) {
    	try {
            new TelaSeguinte("TelaInicial.fxml").start(Main.stage);
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }
    
	@FXML
	public void clicarIniciar() {
		

	        
		 if (cell.isValid() == false) { //condição para inicializar o Sudoku caso o cell.isValid seja false


	        	sudokuHard.startGame(); //inicia a contagem do tempo
	  		  

	  	        txtName.setText(sudokuHard.getPlayer().getName()); 	//pega o nome do player
	  	        txtTime.setText(String.valueOf(sudokuHard.getStartTime().get(Calendar.HOUR_OF_DAY) + " h e "		
	  	                + String.valueOf(sudokuHard.getStartTime().get(Calendar.MINUTE)) + " min"));	
	  	        //faz o cast do tempo pra string e seta no label
	  	        
	           
	            autonomousPlayer.executeGame();
	            //sudokuEasy.generateSudokuRandom(0, 0);	//gera o sudokuRandom
	            for(int i=0; i<9; i++) {
	                for(int j=0; j<9; j++) {

	                    sudokuHard.getGridAnswer()[i][j] = sudokuHard.getGridPlayer()[i][j].getValue();
	                    //joga o valor de gridPlayer gerado pelo generateSudokuRandom() no gridResposta
	                    if(sudokuHard.getGridPlayer()[i][j].isFixed() == false) {
	                        sudokuHard.getGridPlayer()[i][j].setValue(0);
	                }
	                }
	            } 
	            cell.setValid(true);
	            sudokuHard.initilizeSudoku();	//inicia o sudoku com os zeros setados
	            
	        }

	        for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < 9; j++) {

	                TextField txtixj = new TextField(); //array de textField
	                txtixj.setPrefHeight(42.67);
	                txtixj.setPrefWidth(42.78);
	                txtixj.setAlignment(Pos.CENTER);
	                txtixj.setEditable(false);
	                
	                if(sudokuHard.getGridPlayer()[i][j].getValue() == 0) {
	                	txtixj.setText("");
	                	gridPane.add(txtixj, j, i);
	                }
	                else {
	                	if(sudokuHard.getGridPlayer()[i][j].isFixed() ==true) {
	                		txtixj.setStyle("-fx-font-weight: bold");
	                	}
	                	txtixj.setText(Integer.toString(sudokuHard.getGridPlayer()[i][j].getValue()));
	                	gridPane.add(txtixj, j, i);
	                }
	                
	            }
	        }
	}
    

	@FXML
	public void setValueSudokuEasy(ActionEvent event) { // botão verificar

		try {
			
			boolean complete = true;

			sudokuHard.setValue(Integer.parseInt(txtCoordX.getText()), Integer.parseInt(txtCoordY.getText()),
					Integer.parseInt(txtValue.getText()));
			txtMessage.setText("Valid value");
			clicarIniciar();
			
			 for (int i = 0; i < 9; i++) {
	              for (int j = 0; j < 9; j++) {

	                    if (sudokuHard.getGridPlayer()[i][j].getValue() == 0) {	//se tiver algum zero, o jogo
	                    														//não está completo

	                        complete = false;
	                        i=9;
	                        j=9;
	                    }
	                }
	            }

	            if (complete == true) {
	            	
	            	//como o jogo completo para, para o tempo e mostra msg
	                sudokuHard.endGame();
	                txtMessage.setVisible(true);
					txtErro.setVisible(false);
	                
	            txtMessage.setText("Congratz " + sudokuHard.getPlayer().getName() + ", you have completed the"
	              	+ " Sudoku in " + (sudokuHard.getTotalTime()) + " min. \n" + "Total time: "
	                     + String.valueOf(sudokuHard.getEndTime().get(Calendar.HOUR_OF_DAY) + " h and "
	                             + String.valueOf(sudokuHard.getEndTime().get(Calendar.MINUTE)) + " min"));

	            }
	            else {

					txtMessage.setVisible(true);
					txtErro.setVisible(false);
					txtMessage.setText("Valid Value");
				}
		}

		catch (CellIsFixedException | CellValueException | GenericException e) {
			txtMessage.setVisible(false);
			txtErro.setVisible(true);
			txtErro.setText(e.getMessage());

		} catch (RuntimeException e) {

			txtMessage.setVisible(false);
			txtErro.setVisible(true);
			txtErro.setText(e.getMessage());
		}

	}
	
	public void clearSolution(ActionEvent event) {
		
		txtAutoTime.setVisible(false);
		for(int i=0; i<9; i++) {
			for(int j =0; j<9; j++) {
				if(sudokuHard.getGridPlayer()[i][j].isFixed() == false) {
					//Cell[][] gridPayer = new Cell[i][j];
					sudokuHard.getGridPlayer()[i][j].setValue(0);
				
				}
			}
			sudokuHardAutonomous.getCell().setValid(false);
			clicarIniciar();
		}
		
	}
	
	public void executeMoviment(ActionEvent event) {
		autonomousPlayer.AutoMove(sudokuHard);
		clicarIniciar();
		txtMessage.setText("Valid play!");
	}
	
	public void solveSudokuEasy(ActionEvent event) {
		
		txtAutoTime.setVisible(true);
		sudokuHard.endGame();
		autonomousPlayer.executeGame();
			
        clicarIniciar();

        txtAutoTime.setText(sudokuHard.getEndTime().get(Calendar.MILLISECOND) + "ms");
       
    }
		
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtName.setText(sudokuHard.getPlayer().getName());
		clicarIniciar();
	}

}
