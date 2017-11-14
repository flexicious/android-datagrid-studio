package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;


public class CustomLoadingSpinner extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_custom_loading_spinner);
		this.buildGrid(this.flexDataGrid, R.raw.flxscolumnlockmodes);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}
}
