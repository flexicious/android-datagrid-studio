package com.flexicious.androidcomponentstest.sampleviews.supportingviews;

import java.util.ArrayList;

public class MakeModel {
	public ArrayList<String> models;
	public ArrayList<Object> children;
	public String make;
	public MakeModel(String make, ArrayList<String> models){
		this.make = make;
		this.models = models;
	}
}
