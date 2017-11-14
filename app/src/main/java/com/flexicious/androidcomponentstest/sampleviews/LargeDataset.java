////
////  iPadLargeDatasetViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/9/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.List;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.Function;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;


public class LargeDataset extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_large_dataset);
		this.buildGrid(this.flexDataGrid, R.raw.flxslargedataset);
		 
	}
	
	public void largeDataset_CreationComplete(FlexDataGridEvent ns)
	{
	    //evt = (Event*)[ ns.userInfo objectForKey:"event"];
	     BusinessService.getInstance().getAllLineItems(new Function(this,"getAllLineItems"), faultHandler);
	}
	public void getAllLineItems(final List<Object> result)
	{
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				flexDataGrid.setDataProvider(result);
				
			}
		});
	   
	}
}