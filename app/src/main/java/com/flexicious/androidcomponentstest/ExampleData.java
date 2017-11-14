package com.flexicious.androidcomponentstest; 


public class ExampleData {
	public ExampleData(String id, String description, String name,
			Class<?> class1) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.activityClass = class1;
	}
	public String id;
	public String name;
	public String description;
	public Class<?> activityClass;
}
