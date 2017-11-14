////
////  iPadExternalFilterViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/10/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.Function;
import com.flexicious.utils.UIUtils;

public class ExternalFilter extends ExampleActivityBase {


	Button searchButton;
	ToggleButton timeSheet1;
	ToggleButton timeSheet2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_external_filter);
		searchButton = (Button) findViewById(R.id.externalfilter_search);
		timeSheet1 = (ToggleButton) findViewById(R.id.externalfilter_timesheet1toggleBtn);
		timeSheet2 = (ToggleButton) findViewById(R.id.externalfilter_timesheet2toggleBtn);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				ExternalFilter.this.flexDataGrid.rebuildBody(true);
			}
		});

		this.buildGrid(this.flexDataGrid, R.raw.flxsexternalfilter);
		this.loadJsonData(flexDataGrid, R.raw.flxsdatamocknesteddata);
		this.flexDataGrid.getColumnLevel().getNextLevel().getNextLevel()
				.setFilterFunction(new Function(this, "filterDeviceTypes"));
		this.flexDataGrid.validateNow();
		this.flexDataGrid.expandAll();
		// this.flexDataGrid.setDataProvider(FlexiciosMockGenerator.instance().getFlatOrgList());
	}

	public Boolean filterDeviceTypes(Object item) {
		if (!timeSheet1.isChecked() && UIUtils.resolveExpression(item, "timeSheetTitle").equals("Time Sheet-1"))
			return false;
		if (!timeSheet2.isChecked() && UIUtils.resolveExpression(item, "timeSheetTitle").equals("Time Sheet-2"))
			return false;
		return true;
	}
}