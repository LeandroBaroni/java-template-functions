package com.leandrobaroni2103.demo.enums;

public enum FirestoreCollection {
	USERS(1, "users"),
	CARDS(2, "cards");

	private final int id;
	private final String name;

	FirestoreCollection(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
