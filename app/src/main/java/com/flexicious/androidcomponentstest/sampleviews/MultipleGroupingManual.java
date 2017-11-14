////
////  iPadMutlipleGroupingManualViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/17/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.utils.UIUtils;

public class MultipleGroupingManual extends ExampleActivityBase {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_multiple_grouping_manual);
		this.buildGrid(this.flexDataGrid, R.raw.flxsmultiplegroupingmanual);
	}
 
	@SuppressWarnings("unchecked")
	public void multipleGrouping_Manual_CreationComplete(FlexDataGridEvent event) {
 

		BufferedReader jsonReader = new BufferedReader(new InputStreamReader(
				this.getResources().openRawResource(R.raw.flxsmultiplegroupingmanualdata)));
		StringBuilder jsonBuilder = new StringBuilder();
		try {
			for (String line = null; (line = jsonReader.readLine()) != null;) {
				jsonBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		JSONTokener tokener = new JSONTokener(jsonBuilder.toString());
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(tokener);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		List<?> multipleGrouping_Manual_dpFlat = this.flexDataGrid.jsonArrayToList(jsonArray);
		
		ArrayList<Object> regions = this
				.multipleGrouping_Manual_groupBy(
						multipleGrouping_Manual_dpFlat,
						"Region",
						"(None)",
						null,
						new ArrayList<Object>(Arrays.asList("RegionCode", null)),
						false);
		for (int i = 0; i < regions.size(); i++) {
			HashMap<Object, Object> region = (HashMap<Object, Object>) regions
					.get(i);
			region.put(
					"children",
					multipleGrouping_Manual_groupBy(
							(ArrayList<Object>) region.get("children"),
							"Territory",
							"(None)",
							null,
							new ArrayList<Object>(Arrays
									.asList("TerritoryCode")), false));
		}
		if (regions != null && regions.size() > 0)
			this.flexDataGrid.setDataProvider(regions);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Object> multipleGrouping_Manual_groupBy(
			List<?> dp, String prop, String nullValue,
			String filterfunction, ArrayList<Object> additionalProperties,
			boolean useOtherBucket) {

		if (additionalProperties != null)
			additionalProperties = new ArrayList<Object>();
		HashMap<Object, List<Object>> buckets = new HashMap<Object, List<Object>>();
		String key;
		ArrayList<Object> result = new ArrayList<Object>();
		;
		// iterate through the flat list and create a hierarchy
		if (useOtherBucket) {
			buckets.put("other", new ArrayList<Object>());
		}
		for (int i = 0; i < dp.size(); i++) {
			Object item = dp.get(i);
			key = UIUtils.toString(UIUtils.resolveExpression(item, prop, false));
			if (key == null) {
				key = "null";
			}
			if (!buckets.containsKey(key)) {
				// buckets[key] = [];//the children
				buckets.put(key, new ArrayList<Object>());
			}
			if (filterfunction == null) {
				(buckets.get(key)).add(item);
			} else if (useOtherBucket) {
				(buckets.get("other")).add(item);
			}
		}
		for (Object key1 : buckets.keySet()) {
			HashMap<Object, Object> obj = new HashMap<Object, Object>();
			obj.put(prop, key1.equals("null") ? null : key1);
			obj.put("children", buckets.get(key1));
			ArrayList<Object> arr = (ArrayList<Object>) buckets.get(key1);
			if (arr.size() > 0) {
				for (int j = 0; j < additionalProperties.size(); j++) {
					String addProp = (String) additionalProperties.get(j);
					obj.put(addProp, buckets.get(key1));
					obj.put(additionalProperties,
							((HashMap<String, Object>) arr.get(0)).get(addProp));
				}
			}
			result.add(obj);
		}
		return result; // this will refresh the grid...

	}

}
 
