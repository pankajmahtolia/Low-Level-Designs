package com.pankaj.bowlingsystem.service;

import com.pankaj.bowlingsystem.models.Lane;

public class DisplayScoreService {
	
	
	public DisplayScoreService() {
	}

	public void DisplayScores(Lane lane) {
		System.out.println("Display Score of Lane " + lane.getLaneNo());
		lane.getPlayersList().stream().forEach(player->{			
			System.out.println(player.toString());
		});
	}
	
}
