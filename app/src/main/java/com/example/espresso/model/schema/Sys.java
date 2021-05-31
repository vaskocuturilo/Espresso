package com.example.espresso.model.schema;

import com.google.gson.annotations.SerializedName;

public class Sys{

	@SerializedName("country")
	private String country;

	@SerializedName("sunrise")
	private int sunrise;

	@SerializedName("sunset")
	private int sunset;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private int type;

	public String getCountry(){
		return country;
	}

	public int getSunrise(){
		return sunrise;
	}

	public int getSunset(){
		return sunset;
	}

	public int getId(){
		return id;
	}

	public int getType(){
		return type;
	}
}