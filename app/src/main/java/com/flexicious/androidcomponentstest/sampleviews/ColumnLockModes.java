package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;


public class ColumnLockModes extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_column_lock_mode);
		this.buildGrid(this.flexDataGrid, R.raw.flxscolumnlockmodes);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}
} 
