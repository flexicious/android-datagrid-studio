package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.model.classic.Employee;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;


public class CustomMatchFilterControl extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_custom_match_filter_control);
		this.buildGrid(this.flexDataGrid, R.raw.flxscustommatchfiltercontrol);
		this.flexDataGrid.setDataProvider(Employee.getAllEmployees());
	}
	
	
	public String CustomMatchFilterControl_getFullName(Employee item, FlexDataGridColumn col){
	    return item.firstName  + " "  + item.lastName;
	}
}
