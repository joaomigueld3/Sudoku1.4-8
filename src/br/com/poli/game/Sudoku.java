package br.com.poli.game;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import br.com.poli.exceptions.*;

public abstract class Sudoku extends Game {

	private double totalTime;
	protected Cell[][] gridPlayer;
	private Cell cell;
	private int[][] gridAnswer;
	public static int accounter;
	Random generator = new Random();

	public double getTotalTime() {

		if ((super.getEndTime().get(Calendar.HOUR_OF_DAY) - super.getStartTime().get(Calendar.HOUR_OF_DAY)) >= 1) {

			return (super.getEndTime().get(Calendar.HOUR_OF_DAY) - super.getStartTime().get(Calendar.HOUR_OF_DAY)) * 60
					+ (super.getEndTime().get(Calendar.MINUTE) - super.getStartTime().get(Calendar.MINUTE));
		}

		return (super.getEndTime().get(Calendar.MINUTE) - super.getStartTime().get(Calendar.MINUTE));

	} // retorna o tempo total

	public void endGame() {
		super.endGame();
		this.totalTime = getTotalTime();

	}

	public double autonomousTime() { // utilizado para calcular o tempo que o autonomo resolve o sudoku
		return (super.getEndTime().get(Calendar.MILLISECOND) - super.getStartTime().get(Calendar.MILLISECOND));

	}

	// inicializa o sudoku preenchido
	public Sudoku(Player player) {

		super(player);
		cell = new Cell(0, false, false);
		gridAnswer = new int[9][9];

		gridPlayer = new Cell[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				gridPlayer[i][j] = new Cell(0, false, false);
			}
		}

	}// fim construtor

	public int[][] getGridAnswer() {
		return gridAnswer;
	}

	public void setGridAnswer(int[][] gridAnswer) {
		this.gridAnswer = gridAnswer;
	}

	public Cell[][] getGridPlayer() {
		return gridPlayer;
	}

	public void setGridPlayer(Cell[][] gridPlayer) {
		this.gridPlayer = gridPlayer;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	// Método para gerar um Sudoku Random válido!!!

	// Método para que irá remover células do Sudoku aleatóriamentedependendo da
	// Dificuldade!!!

	public void setValue(int coordX, int coordY, int value)
			throws CellIsFixedException, CellValueException, GenericException {
		accounter+=1;
		cell.setValue(value);
		cell.setValid(false);

		/*
		 * if (checkValidation(new Cell(value, false, false), x, y) == true) { // se o
		 * numero recebido for true, printa // na matriz o numero // recebido que é
		 * válido, não fixo gridPlayer[x][y] = new Cell(value, true, false); }
		 */
		if (checkValidation(cell, coordX, coordY) == true) {

			gridPlayer[coordX][coordY].setValue(cell.getValue());

		}

	}

	private int findBeginingX(int x) {
		if (x <= 2) 	// se o x tiver entre a linha 0 e 2, ele retorna 0, que se refere a linha 0, que
						// é o grid 1,2,3
			return 0; 		// volta pra linha 0
		if (x <= 5) 			// grid 4,5,6
			return 3;
		else 			// grid 7,8,9
			return 6;
	}

	private int findBeginingY(int y) {
		if (y <= 2) 		// mesma coisa, só que para a coluna
			return 0;
		if (y <= 5)
			return 3;
		else
			return 6;
	}

	public boolean checkValidation(Cell cell, int coordX, int coordY)
			throws CellIsFixedException, CellValueException, GenericException {
		// regras

		int value = cell.getValue(); // pega o valor da cell
		int positionFirstElementX = findBeginingX(coordX); // acha inicio recebe como paramentro a coord e
															// joga a função que resolve o grid na variável
		int positionFirstElementY = findBeginingY(coordY);

		/*
		 * if (!(gridPlayer[coordX][coordY].getValue() >0 &&
		 * gridPlayer[coordX][coordY].getValue() <10)) {
		 * 
		 * throw new CellValueException(this.getPlayer().getName() +
		 * ", Valor deve ser entre 1 e 9!!!"); // recebendo apenas numeros entre 1 e 9
		 * // return false; }
		 */
		if (cell.getValue() <= 0 || cell.getValue() >= 10) {

			throw new CellValueException(this.getPlayer().getName() + ", Valor deve ser >1 ou <9!!!");
		}

		else if (coordX < 0 || coordX > 8 || coordY < 0 || coordY > 8) {

			throw new GenericException("Erro: ");
		}

		else if (gridPlayer[coordX][coordY].isFixed() == true) { // se a posicao for fixa, retorna falso e
			// sai do metodo, não modificando a matriz
			throw new CellIsFixedException(
					this.getPlayer().getName() + ", você não pode sobreescrever um número fixo.");
			// return false;
		}

		for (int i = 0; i <= 8; i++) { // percorre o array
			if (gridPlayer[i][coordY].getValue() == value) { // percorre a linha [i] e deixa a coluna coordY fixo.

				throw new CellValueException(this.getPlayer().getName() + ", Valor inválido para a Coluna!!!");
				// return false; //se os valores forem iguais retorna falso

			}
			if (gridPlayer[coordX][i].getValue() == value) { // percorre a linha [j] e deixa a coluna coordX fixo.

				throw new CellValueException(this.getPlayer().getName() + ", Valor inválido para a Linha!!!");
				// return false;
			}
		} // fim dos fors que verificam linhas e colunas

		for (int i = positionFirstElementX; i <= (positionFirstElementX + 2); i++) { // como achainicio(findBegining)
																						// sempre volta
			// pro começo do grid o (+2), permite que ele verifique as outras posições
			for (int j = positionFirstElementY; j <= (positionFirstElementY + 2); j++) {
				if (gridPlayer[i][j].getValue() == value) {
					throw new CellValueException(this.getPlayer().getName() + ", Valor inválido para o Grid!!!");
					// return false;
				}
			} // fim do for para coluna
		} // fim do for para linha

		return true;
	}

	// cell.setValid(true);
	// return cell.isValid();

	// fim do checkValidation

	public boolean generateSudokuRandom(int line, int column) {

		if (line == 9) {		//quando chegar na linha 9, é porque terminou o jogo, retorna true.

			return true;
		} else {				//se não, tenta resolver

			boolean returns = false;

			ArrayList<Integer> list = new ArrayList<>();
			
			for (int i = 0; i < 9; i++) {

				if (returns == false) {		//quando returns é false, o jogo não está terminado, portanto ele faz:

					int numberRandom;

					numberRandom = generator.nextInt(9) + 1;	//gera de 1 a 9 a cada iteração
					
					list.add(numberRandom);		//adiciona o valor gerado à lista.
					
					if (list.size() == 9) {		//se a lista tiver 9 elementos
												//
						return returns;			
					}

					if (list.contains(numberRandom)) {	//se a lista contém o valor gerado...
						i--;
						
					}

					
					try {

						if (checkValidation(new Cell(numberRandom, false, false), line, column)==true) {
								//se o numberRandom (checkValidation) for válido seta o valor
							gridPlayer[line][column].setValue(numberRandom);

							if (column == 8) {		//se estiver na coluna 8, vai pra próxima linha e pra coluna 0

								returns = generateSudokuRandom(line + 1, 0);
							} else {		//se não for a 8, ele continua na mesma linha e vai pra próxima coluna

								returns = generateSudokuRandom(line, column + 1);
								
								//ele começa em [0][0], cria o list e o numberRandom, adiciona o numberRandom
								//no list, entra no check, seta o numberRandom em [0][0], vê que não ta na coluna 8,
								//cai no else, chama o próprio método, na mesma linha e passa pra próxima coluna
								
							}
						} else {		//se não for válido põe 0, que cai na valueException
							gridPlayer[line][column].setValue(0);
						}
					} catch (CellIsFixedException | CellValueException | GenericException e) {

						gridPlayer[line][column].setValue(0);
					}
				}
			}
			return returns;
			
		}
		
	}

	public void removeCellRandom(int CellsZero) { 
		// Recebe a quantidade de casas zeradas de acordo com a dificuldade.

		for (int i = 0; i <= 8; i++) { // cada i do for é um grid

			if (i == 0) { // grid 0
				removeCellAux(CellsZero, 0, 0);

			} else if (i == 1) { // grid 1
				removeCellAux(CellsZero, 0, 3);

			} else if (i == 2) { // grid 2
				removeCellAux(CellsZero, 0, 6);
			} else if (i == 3) { // grid 3
				removeCellAux(CellsZero, 3, 0);

			} else if (i == 4) { // grid 4
				removeCellAux(CellsZero, 3, 3);

			} else if (i == 5) { // grid 5
				removeCellAux(CellsZero, 3, 6);

			} else if (i == 6) { // grid 6
				removeCellAux(CellsZero, 6, 0);

			} else if (i == 7) { // grid 7
				removeCellAux(CellsZero, 6, 3);

			} else if (i == 8) { // grid 8
				removeCellAux(CellsZero, 6, 6);

			}
		}
	}

	public void removeCellAux(int amountOfZeros, int addK, int addY) { 
		/*addK e addY recebem valores (0,3,6) a serem adicionados ao k,y que são índices de gridPlayer, 
		*para chegar aos outros grids do sudoku. Ex: no 4 grid k e y estão entre 3 e 5, assim o generator 
		*gera de 0 a 2 que somado a 3 a 5 engloba o 4 grid.
		*/ 
		int a = 0;
		int k = 0;
		int y = 0;
		while (a < amountOfZeros) { // faz com que até que tenham x(5,6,7) quantidade de casas geradas ele continue 
									//	tentando gerar posições a serem zeradas
									
			k = generator.nextInt(3); //gera de 0 a 2, que é o 1º grid
			y = generator.nextInt(3);
			k += addK;				//soma o número aleatório com o parâmetro recebino no removeCellRandom()
			y += addY;				//de acordo com cada grid

			if (gridPlayer[k][y].getValue() != 0) { 	//se o número da posição for != 0 seta falso
				gridPlayer[k][y].setValue(0);
				gridPlayer[k][y].setFixed(false);
			} else {								//se ele for 0, ele tenta de novo até não ser zero
				amountOfZeros += 1;
			}
			a++;
		}

	}

	

	public abstract void initilizeSudoku(); // inicializa o sudoku dependendo da dificuldade

	@Override
	public String toString() {
		// getTotalTime();
		// initializaSudoku(difficulty.NORMAL);

		String s = "";
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0 && i != 0)
				s = s + '\n';
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0)
					s += "  ";

				if (gridPlayer[i][j] == null) { // se o conteudo de gridplayer na posição i,j for nulo printa espaço 0
					s = s + "0";

				} else {
					s = s + "" + gridPlayer[i][j]; // se não printa o conteudo de gridplayer

				}

			}
			s = s + '\n';
		}

		return s;
	}

}
