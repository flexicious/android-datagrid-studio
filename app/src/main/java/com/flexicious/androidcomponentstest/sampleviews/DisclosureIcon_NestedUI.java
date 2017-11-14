
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;


public class DisclosureIcon_NestedUI extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_disclosureicon_nestedui);
		this.buildGrid(this.flexDataGrid, R.raw.flxsselectionui2); 
		this.loadJsonData(this.flexDataGrid, R.raw.flxsdataselectionui1data);
		this.flexDataGrid.validateNow();
		this.flexDataGrid.expandAll();
		
	}
} 