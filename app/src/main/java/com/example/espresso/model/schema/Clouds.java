package com.example.espresso.model.schema;

import com.google.gson.annotations.SerializedName;

public class Clouds{

	@SerializedName("all")
	private int all;

	public int getAll(){
		return all;
	}
}