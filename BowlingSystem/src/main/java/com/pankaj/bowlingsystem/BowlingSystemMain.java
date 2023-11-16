package com.pankaj.bowlingsystem;

import com.pankaj.bowlingsystem.models.Lane;
import com.pankaj.bowlingsystem.models.Player;
import com.pankaj.bowlingsystem.service.BowlingSystemService;

public class BowlingSystemMain {

	public static void main(String[] args) {
		Lane lane1 = new Lane();
		Lane lane2 = new Lane();
		Lane lane3 = new Lane();
		
		//Add Players
		Player player1 = new Player("Pankaj", "9090909090");
		Player player2 = new Player("Tripti", "9090909090");
		Player player3 = new Player("Ankit", "9090909090");
		
		lane1.addPlayer(player1, 0);
		lane1.addPlayer(player2, 0);
		//lane1.addPlayer(player3 ,0);
		
		
		BowlingSystemService bowlingSystemService = new BowlingSystemService();		
		
		bowlingSystemService.addLanes(lane1);
		bowlingSystemService.addLanes(lane2);
		bowlingSystemService.addLanes(lane3);
		
		
		bowlingSystemService.startGame(lane1);
		
	}

}
