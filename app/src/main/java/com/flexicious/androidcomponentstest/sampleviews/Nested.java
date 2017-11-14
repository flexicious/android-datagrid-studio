package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;
import android.widget.Toast;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;


public class Nested extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_nested);
		this.buildGrid(this.flexDataGrid, R.raw.flxsnested);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getDeepOrgList());
	}
	public void nested_grid_itemDoubleClickHandler(FlexDataGridEvent  evt){
		Toast.makeText(getApplicationContext(), "You double tapped on "  + evt.cell.getText(), 
				   Toast.LENGTH_LONG).show();

	}
	public void nested_grid_ClickHandler(FlexDataGridEvent evt){
		Toast.makeText(getApplicationContext(), "You  tapped on "  + evt.cell.getText(), 
				   Toast.LENGTH_LONG).show();
	}

} 