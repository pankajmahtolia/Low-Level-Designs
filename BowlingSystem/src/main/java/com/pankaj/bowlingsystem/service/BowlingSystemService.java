package com.pankaj.bowlingsystem.service;

import java.util.ArrayList;
import java.util.List;

import com.pankaj.bowlingsystem.models.Lane;
import com.pankaj.bowlingsystem.models.Player;
import com.pankaj.bowlingsystem.utils.GameConstants;

public class BowlingSystemService {
	private List<Lane> lanes;
	
	DisplayScoreService displayScoreService = new DisplayScoreService();
	GameConstants gameConstants = new GameConstants();
	private static int winingTotal=0;
	public BowlingSystemService() {
		this.lanes = new ArrayList<>();
	}
	
	// ADD LANE
	public void addLanes(Lane lane) {
		this.lanes.add(lane);
	}
	
	public void startGame(Lane lane) {
		for(int i=0;i<lane.getPlayersList().size();i++) {
			int standingPins = GameConstants.MAX_PINS;
			int playerRunningTotal=0;
			for(int j=1; j<=GameConstants.MAX_ATEMPT;j++) {
				for(int k=1;k<=GameConstants.MAX_SUB_ATEMPT;k++) {
					int pinsDown = takeShot(standingPins);
					standingPins-=pinsDown;
					if(standingPins<=0 && k==1)
						playerRunningTotal+=10;
					else if(standingPins<=0)
						playerRunningTotal+=5;
						
				}
				playerRunningTotal+=GameConstants.MAX_PINS-standingPins;
				lane.getPlayersList().get(i).setScore(playerRunningTotal);
				this.displayScoreService.DisplayScores(lane);	
				standingPins = refilPins();
			}
			if(playerRunningTotal > winingTotal) {
				winingTotal=playerRunningTotal;
				GameConstants.winingPlayer=lane.getPlayersList().get(i);
			}
		}
		System.out.println("Lane " + lane.getLaneNo() + " Results!!!");
		System.out.println("Congratulations!!! Player " + GameConstants.winingPlayer.getName() + " has won the game with total score of: " + GameConstants.winingPlayer.getScore());
	}

	private int refilPins() {
		return GameConstants.MAX_PINS;
	}

	private int takeShot(int standingPins) {
		return (int)(Math.random() * (standingPins - 0)) + 0;
	}
}
