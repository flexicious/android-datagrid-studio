////
////  iPadDynamicGroupingViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/10/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
//
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.ComboBox;
import com.flexicious.controls.core.Function;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.nestedtreedatagrid.utils.ExtendedUIUtils;

public class DynamicGrouping extends ExampleActivityBase {

	public List<?> flatResult;
	ComboBox comboBox;
	Button groupBy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_dynamic_grouping);
		groupBy = (Button) findViewById(R.id.dynamicgrouping_groupby);
		comboBox = (ComboBox) findViewById(R.id.dynamicgrouping_combobox);

		groupBy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				if(comboBox.getSelectedValue()!=null && comboBox.getSelectedValue() instanceof String)
				groupBy((String) comboBox.getSelectedValue());

			}
		});
		comboBox.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Event evt = new Event(Event.EVENT_CHANGE);
				// ComboBox.this.dispatchEvent(evt);
			}

			@Override
			public void onItemSelected(AdapterView<?> paramAdapterView,
					View paramView, int paramInt, long paramLong) {
				if(comboBox.getSelectedValue()!=null && comboBox.getSelectedValue() instanceof String)					
				groupBy((String) comboBox.getSelectedValue());

			}
		});

		this.buildGrid(this.flexDataGrid, R.raw.flxsdynamicgrouping);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance()
				.getFlatOrgList());
		comboBox.setAddAllItem(false);
		comboBox.setDataField("dataField");
		comboBox.setLabelField("dataField");
		comboBox.setDataProvider(this.getFiltered(this.flexDataGrid
				.getColumns()));
	}

	public void comboBox_ClickEvent(FlexDataGridEvent ns) {
		ComboBox comboBox = (ComboBox) ns.target;
		this.groupBy((String) comboBox.getSelectedValue());
	}

	public void dynamicGrouping_CreationComplete(FlexDataGridEvent ns) {
		BusinessService.getInstance().getAllLineItems(
				new Function(this, "getAllLineItems_result"), faultHandler);
	}

	public void getAllLineItems_result(final ArrayList<Object> result) {

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				flatResult = (List<?>) result.clone();
				flexDataGrid.setDataProvider(result);
				groupBy("invoice.deal.customer.legalName");
			}
		});
	}

	// //function that takes a flat collection and groups it on basis of the
	// provided group field.
	@SuppressWarnings("unchecked")
	public void groupBy(String prop) {
		if (prop == null || prop.equals("All") || flatResult==null) {
			return;
		}
		HashMap<String, Object> buckets = new HashMap<String, Object>();
		String key;
		ArrayList<Object> result = new ArrayList<Object>();
		
		// iterate through the flat list and create a hierarchy
		for (Object item : flatResult) {
			key = (String) ExtendedUIUtils.resolveExpression(item, prop, null,
					false, false); // the parent
			if (!buckets.containsKey(key)) {
				buckets.put(key, new ArrayList<Object>());// the children
			}
			ArrayList<Object> temp = (ArrayList<Object>) buckets.get(key);
			temp.add(item);
		}
		for (String key1 : buckets.keySet()) {
			HashMap<String, Object> record = new HashMap<String, Object>();
			record.put("name", key1);
			record.put("children", buckets.get(key1));
			result.add(record);

		}
		this.flexDataGrid.setDataProvider(result); // this will refresh the
													// grid...
	}

	public ArrayList<Object> getFiltered(List<FlexDataGridColumn> list) {
		ArrayList<Object> arr = new ArrayList<Object>();
		
		for (FlexDataGridColumn cl : list) {
			if (cl.getDataField().indexOf(".") != -1) {
				arr.add(cl);
			}
		}
		return arr;
	}

	public void btn_group_clickHanler(Object sender) {

	}
}
