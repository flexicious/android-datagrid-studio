package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.model.classic.Employee;


public class CustomFooter extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_custom_footer);
		this.buildGrid(this.flexDataGrid, R.raw.flxscustomfooter);
		this.flexDataGrid.setDataProvider(Employee.getAllEmployees());

	}
}
