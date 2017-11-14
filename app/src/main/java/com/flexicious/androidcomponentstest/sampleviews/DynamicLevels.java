////
////  iPadDynamicLevelsViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/9/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;


public class DynamicLevels extends ExampleActivityBase{

	Button selectBJ_TS;
	String selectedObjects;
	String openObjects;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_dynamic_levels);

		selectBJ_TS = (Button) findViewById(R.id.dynamiclevels_selectBJTS);
		selectBJ_TS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				button1_clickHandler();

			}
		});
		this.buildGrid(this.flexDataGrid, R.raw.flxsdynamiclevels);
		
		JSONObject json;
		try {
			json = this.convertXmlToJson(this.flexDataGrid, R.raw.flxsdataxmldynamiclevelsdata);
			this.flexDataGrid.setDataProviderJson("[" +json.getJSONObject("Region").toString()+"]");		
		} catch (JSONException e) { 
			Log.e("FlexDataGrid", e.getMessage(), e);
		}
		
	}

	public void onDynamicLevelCreated(FlexDataGridEvent event){
		//FlexDataGridEvent  evt = [ns.userInfo objectForKey:"event"];
		event.level.setChildrenField("Territory_Rep");//this will be the newly created level
		event.level.setSelectedKeyField("id");
	}
	public void dynamiclevels_gridchangeHandler(FlexDataGridEvent event)
	{ 
	}

	public void button1_clickHandler() {
		this.flexDataGrid.setOpenKeys(Arrays.asList("SW","AR"));
		this.flexDataGrid.setSelectedKeys(Arrays.asList("BJ","TS") ,true);
	}
}

