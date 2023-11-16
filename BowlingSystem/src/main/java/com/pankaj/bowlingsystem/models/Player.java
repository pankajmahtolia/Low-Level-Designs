package com.pankaj.bowlingsystem.models;

import java.util.UUID;

public class Player {
	public Player(String name, String mobileNo) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.id = generateId();
		this.score = 0;
		this.isWin=false;
	}
	
	private String generateId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Lane getLane() {
		return lane;
	}
	public void setLane(Lane lane) {
		this.lane = lane;
	}
	public boolean getIsWin() {
		return isWin;
	}

	public void setIsWin(boolean isWin) {
		this.isWin = isWin;
	}
	
	@Override
	public String toString() {
		return "Score of " + this.name + " is " + this.score; 
	}
	private String name;
	private String mobileNo;
	private String id;
	private int score;
	private Lane lane;
	private boolean isWin;

	
}
