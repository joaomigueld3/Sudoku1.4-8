package br.com.poli.game;

public class SudokuHard extends Sudoku {

	public SudokuHard(Player player) {
		super(player);
		
	}

	@Override
	public void initilizeSudoku() {
		this.generateSudokuRandom(0, 0);
		for(int i = 0; i<=8; i++) {
			for(int j = 0; j<=8 ; j++) {
				gridPlayer[i][j].setFixed(true); //Setando todas as casas  para zero
		
				}
			}
		
		removeCellRandom(7);
	}
	

}
