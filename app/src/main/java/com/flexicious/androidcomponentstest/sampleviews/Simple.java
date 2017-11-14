package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;

public class Simple extends ExampleActivityBase {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_simple);
		this.buildGrid(this.flexDataGrid, R.raw.flxssimplegrid);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance()
				.getFlatOrgList());
	}
}
