package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;


public class CustomItemRenderers extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_custom_item_renderes);
		this.buildGrid(this.flexDataGrid, R.raw.flxsitemrenderers);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}
	public void ItemRenderers_grid_itemClickHandler(FlexDataGridEvent event)
	{
		//we can wire up click handler here
	}
}
