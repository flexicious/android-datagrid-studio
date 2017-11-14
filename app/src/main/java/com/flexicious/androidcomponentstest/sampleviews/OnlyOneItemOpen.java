////
////  iPadOnlyOneItemOpenViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/17/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;


public class OnlyOneItemOpen extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_only_oneitem_open);
		this.buildGrid(this.flexDataGrid, R.raw.flxsonlyoneitemopen);
		try {
			JSONObject json = this.convertXmlToJson(this.flexDataGrid, R.raw.flxsxmlgroupeddataxmldata);
			this.flexDataGrid.setDataProviderJson(json.getJSONObject("Regions").getJSONArray("Region").toString());			
		} catch (JSONException e) {
			Log.e("FlexDataGrid", e.getMessage(), e);
		}

	}

	public void onlyOneItemOpen_itemOpeningHandler(FlexDataGridEvent evt){
		evt.preventDefault();
		ArrayList<Object>  itemsToRemove=new ArrayList<Object>();
		List<?> items= this.flexDataGrid.getOpenObjects();
		for(int i=0;i<items.size();i++){
			Object openItem= items.get(i);
			//need to ensure we do not close our own parent
			if(this.onlyOneItemOpen_existsInParentHierarchy(openItem,evt.item)){
				continue;
			}else{
				itemsToRemove.add(openItem);
			}
		}
		//remove all open items except our ancestors
		for(int j=0;j<itemsToRemove.size();j++){
			Object  itemToRemove= itemsToRemove.get(j);
			this.flexDataGrid.getOpenItems().remove(this.flexDataGrid.getOpenItems().indexOf(itemToRemove));
		}
		//add ourselves
		this.flexDataGrid.getOpenItems().add(evt.item);
		this.flexDataGrid.rebuildBody(false);

	}
	public boolean onlyOneItemOpen_existsInParentHierarchy(Object openItem, Object item){

		if(item==openItem){
			return true;
		}
		Object parent = this.flexDataGrid.getParent(item,this.flexDataGrid.getColumnLevel());
		if(parent != null){
			return this.onlyOneItemOpen_existsInParentHierarchy(openItem, parent); //since this is xml, we are using item.getParent(). We could also use grid.getParent(item) for non-lazy loaded grids.
		}
		return false;
	}

} 