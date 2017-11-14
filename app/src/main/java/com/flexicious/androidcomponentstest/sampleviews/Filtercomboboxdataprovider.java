////
////  iPadFiltercomboboxdataproviderViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/17/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.List;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.Event;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.utils.ExtendedUIUtils;


public class Filtercomboboxdataprovider extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_filtercomboboxdataprovider);
		this.buildGrid(this.flexDataGrid, R.raw.flxsfiltercomboboxdataprovider);
		this.loadJsonData(this.flexDataGrid, R.raw.flxsdatafiltercomboboxdataproviderdata);		
	}	

	public void filterComboboxDataprovider_creationCompleteHandler(Event e){
	    this.filterComboboxDataprovider_loadFilters();
	};
	public void filterComboboxDataprovider_loadFilters(){
		List<?> filteredArray = ExtendedUIUtils.filterArray(this.flexDataGrid.getDataProvider()
	    		, this.flexDataGrid.createFilter()	, this.flexDataGrid, this.flexDataGrid.getColumnLevel()	, false);
	    FlexDataGridColumn stateCol= this.flexDataGrid.getColumnByDataField("state");
	    stateCol.setFilterComboBoxDataProvider(stateCol.getDistinctValues(filteredArray, null, null, null));
	    this.flexDataGrid.rebuildFilter();
	};
	//
	public void filterComboboxDataprovider_filterPageSortChangeHandler(Event e){
	    this.filterComboboxDataprovider_loadFilters();
	};
}