////
////  iPadMultiSelectSetFilterValueViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/17/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.Arrays;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.flexicious.androidcomponentstest.R;


public class MultiSelectSetFilterValue extends ExampleActivityBase{
	
	Button setFilterValueBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_multi_select_setfilter_value);
		
		setFilterValueBtn = (Button) findViewById(R.id.multiselect_filterValueBtn);
		setFilterValueBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View paramView) {
				btnSetFilterValue_clickHandler();
			}
		});
		
		this.buildGrid(this.flexDataGrid, R.raw.flxsmultiselectsetfiltervalue);
		//this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
		this.loadJsonData(this.flexDataGrid, R.raw.flxsdatamultiselectsetfiltervaluedata);
		
	}
	
	public void btnSetFilterValue_clickHandler() {
		this.flexDataGrid.setFilterValue("state", Arrays.asList(new String[]{"CT","NY"}), true);
	}
} 
