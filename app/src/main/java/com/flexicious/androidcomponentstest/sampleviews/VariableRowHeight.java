////
////  iPadVariableRowHeightViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/17/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.json.XML;

import android.os.Bundle;
import android.util.Log;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.nestedtreedatagrid.FlexDataGrid;


public class VariableRowHeight extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_variable_row_height);
		this.buildGrid(this.flexDataGrid, R.raw.flxsvariablerowheight);
		this.loadXmlData(this.flexDataGrid, R.raw.flxsbookdata);
	}
	public void loadXmlData(FlexDataGrid grid, Integer resource) {
    	try {
    		BufferedReader xmlReader = new BufferedReader(new InputStreamReader(
    				this.getResources().openRawResource(resource)));
    		StringBuilder xmlBuilder = new StringBuilder();
    		try {
    			for (String line = null; (line = xmlReader.readLine()) != null;) {
    				xmlBuilder.append(line).append("\n");
    			}
    		} catch (IOException e) {
    			throw new RuntimeException(e);
    		}
    		 
			JSONObject json = XML.toJSONObject(xmlBuilder.toString()); 
			this.flexDataGrid.setDataProviderJson(json.getJSONObject("root").getJSONArray("book").toString());
		} catch (Exception e) {
			Log.e(e.getMessage(),e.getMessage());
		}
    }
} 
