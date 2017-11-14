////
////  iPadVariableHeaderRowHeightViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/17/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;


public class VariableHeaderRowHeight extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_variable_header_row_height);
		this.buildGrid(this.flexDataGrid, R.raw.flxsvariableheaderrowheight);
		this.loadJsonData(this.flexDataGrid, R.raw.flxsdatavariableheaderrowheightdata);
		//this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}
} 
