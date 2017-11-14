////
////  iPadXmlDataViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/9/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.utils.ExtendedUIUtils;

public class XmlData extends ExampleActivityBase {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_xml_data);
		this.buildGrid(this.flexDataGrid, R.raw.flxsxmldata);
		try {
			JSONObject json = this.convertXmlToJson(this.flexDataGrid,
					R.raw.flxsbookdata);
			this.flexDataGrid.setDataProviderJson(json.getJSONObject("root")
					.getJSONArray("book").toString());
		} catch (JSONException e) {
			Log.e("FlexDataGrid", e.getMessage(), e);
		}
	}

	// //because XML is sending strings, we need to convert to date object for
	// filter comparision.
	public Date XMLData_convertDate(Object item, FlexDataGridColumn col) {

		String dt = (String) ExtendedUIUtils.resolveExpression(item,
				col.getDataField(), null, false, false);
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD",
				Locale.getDefault());
		Date date = null;
		try {
			date = formatter.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}
}