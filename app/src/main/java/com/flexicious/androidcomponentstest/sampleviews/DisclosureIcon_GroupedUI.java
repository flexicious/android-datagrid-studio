
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;


public class DisclosureIcon_GroupedUI extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_disclosureicon_groupedui);
		this.buildGrid(this.flexDataGrid, R.raw.flxsselectionui1);
		this.loadJsonData(flexDataGrid, R.raw.flxsdatamocknesteddata);
		this.flexDataGrid.validateNow();
		this.flexDataGrid.expandAll();
	}
}

