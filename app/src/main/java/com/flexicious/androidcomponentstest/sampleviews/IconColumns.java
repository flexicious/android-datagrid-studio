////
////  iPadIconColumnsViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/9/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;


public class IconColumns extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_icon_columns);
		this.buildGrid(this.flexDataGrid, R.raw.flxsiconcolumns);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}

	public Object IconFunctions_dynamicIconFunction(IFlexDataGridCell cell, String state){
	    if(cell.getRowInfo().getIsDataRow()){
	        return cell.getRowInfo().rowPositionInfo.rowIndex%2==0?R.drawable.flxs_sampleup:R.drawable.flxs_sampledown;
	    }
	    return null;
	}
	public Integer getImageResourceInfo(){
		return R.drawable.flxs_sampleinfo;
	}
	public Integer getImageResourceSearch(){
		return R.drawable.flxs_samplesearch;
	}
} 
