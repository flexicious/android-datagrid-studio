package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.model.classic.Employee;


public class ColumnWidthMode extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_column_width_mode);
		this.buildGrid(this.flexDataGrid, R.raw.flxscolumnwidthmode);
		this.flexDataGrid.setDataProvider(Employee.getAllEmployees());
	}
} 
