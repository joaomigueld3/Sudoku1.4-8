package br.com.poli.game;

public class Cell {
	private int value; //valor de 1 a 9 que preenche a celula
	private boolean valid; // indica se o valor da celula é válido ou não
	private boolean fixed; // pré determina o valor da célula, não pode ser alterado
	
	
	public Cell (int value, boolean valid, boolean fixed) {
		this.value = value;
		this.valid = valid;
		this.fixed = fixed;
		
	}
	public Cell() {
		
	}

	//provavelmente o metodo que verifica se o valor na celula é valido vai estar aqui
	
	
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.value + "";
	}
	
	
}
