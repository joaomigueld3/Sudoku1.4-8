package br.com.poli.game;

import java.util.Calendar;

public class Game {
	private Player player;
	
	private Calendar startTime;
	private Calendar endTime;
	
	public Game() {
		
	}
	public Game(Player player){
		
		this.player = player;
		
	}
	
	
	public Calendar getStartTime() {
		return startTime;
	}


	public void startGame() {
		
		this.startTime = Calendar.getInstance();
		
	}
	public Player getPlayer() {
		return this.player;
	}
	public void setPlayer(Player player) {
		this.player = player;
		
	}
	public void endGame() {
		this.endTime = Calendar.getInstance();
	}
	
	public Calendar getEndTime() {
		return this.endTime;
	}
	
}
