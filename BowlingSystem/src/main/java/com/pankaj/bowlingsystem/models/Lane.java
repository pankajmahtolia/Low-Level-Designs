package com.pankaj.bowlingsystem.models;

import java.util.ArrayList;
import java.util.List;

public class Lane {
	public Lane() {
		this.isOccupied=false;
		laneNo=increment;		
		this.playersList = new ArrayList<>();
		increment+=1;
	}
	public int getLaneNo() {
		return laneNo;
	}
	public List<Player> getPlayersList() {
		return playersList;
	}
	public void setPlayersList(List<Player> playersList) {
		this.playersList = playersList;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	public void addPlayer(Player player, int score) {
		if(playersList.size() == 2) {
			this.isOccupied=true;
			System.out.println("Lane " + laneNo + " Is Fully Occupied!!!");
		}
		else
			this.playersList.add(player);
	}
	private int laneNo;
	private static int increment=1;
	private List<Player> playersList;
	private boolean isOccupied;
	
}
