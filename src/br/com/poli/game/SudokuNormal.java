package br.com.poli.game;

public class SudokuNormal extends Sudoku {

	public SudokuNormal(Player player) {
		super(player);
		
	}

	@Override
	public void initilizeSudoku() {
		
		this.generateSudokuRandom(0, 0);
		for(int i = 0; i< 9; i++) {
			for(int j = 0; j< 9 ; j++) {
				gridPlayer[i][j].setFixed(true); //Setando todas as casas  para zero
		
				}
			}
		
		removeCellRandom(6);
	}
	}
	


