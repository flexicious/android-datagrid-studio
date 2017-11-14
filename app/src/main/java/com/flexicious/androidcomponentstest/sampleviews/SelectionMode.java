////
////  iPadSelectionModeViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/9/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.FlexDataGrid;
import com.flexicious.nestedtreedatagrid.FlexDataGridCheckBoxColumn;



public class SelectionMode extends ExampleActivityBase{
	RadioButton selectionMode_singleCell, selectionMode_multipleCell, selectionMode_singleRow, selectionMode_multipleRows,selectionMode_none;
	RadioGroup selectionModeGroup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_selection_mode);

		selectionModeGroup = (RadioGroup) findViewById(R.id.selectiongruop);
		selectionMode_singleCell = (RadioButton) findViewById(R.id.selectionmode_singlecell);
		selectionMode_singleRow = (RadioButton) findViewById(R.id.selectionmode_singlerow);
		selectionMode_multipleCell = (RadioButton) findViewById(R.id.selectionmode_multiplecell);
		selectionMode_multipleRows = (RadioButton) findViewById(R.id.selectionmode_multiplerow);
		selectionMode_none = (RadioButton) findViewById(R.id.selectionmode_none);

		selectionModeGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int selectedIndex) {
				String mode = FlexDataGrid.SELECTION_MODE_NONE;
				if(selectedIndex == R.id.selectionmode_singlecell)
					mode = FlexDataGrid.SELECTION_MODE_SINGLE_CELL;
				else if(selectedIndex == R.id.selectionmode_singlerow)
					mode = FlexDataGrid.SELECTION_MODE_SINGLE_ROW;
				else if(selectedIndex == R.id.selectionmode_multiplecell)
					mode = FlexDataGrid.SELECTION_MODE_MULTIPLE_CELLS;
				else if(selectedIndex == R.id.selectionmode_multiplerow)
					mode = FlexDataGrid.SELECTION_MODE_MULTIPLE_ROWS;

				applySelectionMode(mode);
			}
		});


		this.buildGrid(this.flexDataGrid, R.raw.flxsselectionmodes);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}
	public void radioClick(Object sender) {
		int selectedIndex = selectionModeGroup.getCheckedRadioButtonId();
		String mode = FlexDataGrid.SELECTION_MODE_NONE;
		if(selectedIndex == R.id.selectionmode_singlecell)
			mode = FlexDataGrid.SELECTION_MODE_SINGLE_CELL;
		else if(selectedIndex == R.id.selectionmode_singlerow)
			mode = FlexDataGrid.SELECTION_MODE_SINGLE_ROW;
		else if(selectedIndex == R.id.selectionmode_multiplecell)
			mode = FlexDataGrid.SELECTION_MODE_MULTIPLE_CELLS;
		else if(selectedIndex == R.id.selectionmode_multiplerow)
			mode = FlexDataGrid.SELECTION_MODE_MULTIPLE_ROWS;

		applySelectionMode(mode);
		/*this.applySelectionMode(this.segmentControl.selectedSegmentIndex == 0 ? [FlexDataGrid SELECTION_MODE_SINGLE_CELL]
				:   this.segmentControl.selectedSegmentIndex == 1 ? [FlexDataGrid SELECTION_MODE_MULTIPLE_CELLS]
						:   this.segmentControl.selectedSegmentIndex == 2 ? [FlexDataGrid SELECTION_MODE_SINGLE_ROW]
								:   this.segmentControl.selectedSegmentIndex == 3 ? [FlexDataGrid SELECTION_MODE_MULTIPLE_ROWS]
										: FlexDataGrid.SELECTION_MODE_falseNE);*/

	}
	public void btn_showSelected_clickHanlder(Object sender) {

		/*if(this.flexDataGrid.isRowSelectionMode)
      UIUtils.showMessage:[[UIUtils extractPropertyValues:this.flexDataGrid.selectedObjects :"id"] componentsJoinedByString:","] :"Selected Ids"];
  else if(this.flexDataGrid.isCellSelectionMode)
      [UIUtils showMessage:[[UIUtils extractPropertyValues:this.flexDataGrid.selectedCells :"cellDescription"] componentsJoinedByString:","] :"Selected Ids"];
		 */

	}
	public void bt_clear_clickHanler(Object sender) {

		this.flexDataGrid.clearSelection();
	}

	public void applySelectionMode( String mode)
	{

		int selectedIndex = this.selectionModeGroup.getCheckedRadioButtonId();
		this.flexDataGrid.setSelectionMode(mode);
		FlexDataGridCheckBoxColumn cbCol =(FlexDataGridCheckBoxColumn)this.flexDataGrid.getColumnLevel().getColumnByUniqueIdentifier("cbCol");
		if(cbCol != null){
			if(cbCol.radioButtonMode != (selectedIndex== R.id.selectionmode_singlerow)){
				cbCol.radioButtonMode = (selectedIndex == R.id.selectionmode_singlerow);
			}

			cbCol.setVisible(selectedIndex == R.id.selectionmode_singlerow || selectedIndex == R.id.selectionmode_multiplerow);
		}
		this.flexDataGrid.reDraw();
	}
} 
