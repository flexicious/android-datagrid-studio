////
////  iPadLevelItemRenderers-2ViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/8/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.List;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.ClassFactory;
import com.flexicious.controls.core.Function;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;


public class LevelItemRenderers_2 extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_levelitem_renderers2);
		this.buildGrid(this.flexDataGrid, R.raw.flxslevelrenderers2);
	}
	public void levelRenderers2_creationCompleteHandler(FlexDataGridEvent ns)
	{
	    //evt1 = (ExtendedFilterPageSortChangeEvent)[ ns.userInfo objectForKey:"event"];
	
	     BusinessService.getInstance().getDeepOrgList(new Function(this,"getDeepOrgList_result"), faultHandler);
	}
	
	public ClassFactory levelRenderers_getNextLevelRenderer()
	{
		return new ClassFactory(com.flexicious.androidcomponentstest.sampleviews.supportingviews.SampleLevelRendererView2.class);
	   /* return [[ClassFactory alloc] initWithNibName:"SampleLevelRenderer2ViewController"
	                                  andControllerClass:[SampleLevelRenderer2ViewController class]
	                                      withProperties:nil];*/
	
	}
	
	public void getDeepOrgList_result( final List<Object> result)
	{
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				 flexDataGrid.setDataProvider(result);
				
			}
		});
	   
	}
}
 
