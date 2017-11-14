////
////  iPadFullycustomizedGridPaintingViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/8/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;

public class FullycustomizedGridPainting extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_fullycustomized_grid_painting);
		this.buildGrid(this.flexDataGrid, R.raw.flxscolumnlockmodes);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}
}
