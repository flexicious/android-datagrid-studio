////
////  iPadProgramaticCellFormattingViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/8/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import android.graphics.Color;
import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.example.model.organizations.Organization;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;
import com.flexicious.nestedtreedatagrid.utils.ExtendedUIUtils;

public class ProgramaticCellFormatting extends ExampleActivityBase {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_programatic_cell_formatting);
		this.buildGrid(this.flexDataGrid, R.raw.flxsprogramaticcellformatting);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance()
				.getFlatOrgList());
	}

	public Object ProgrammaticCellFormatting_getRowBackground(
			IFlexDataGridCell cell) {
		Organization org = (Organization) cell.getRowInfo().getData();
		if (org.headquarterAddress.state.name.equals("New York")) {
			return Color.parseColor("#CFCFCF");
		} else if (cell.getRowInfo().getIsFillRow()) {
			return new Integer[] { Color.parseColor("#CFCFCF"),
					Color.parseColor("#CFCFCF") }; 
		}
		return null;
	}

	public Object ProgrammaticCellFormatting_getRowTextColor(
			IFlexDataGridCell cell) {
		Organization org = (Organization) cell.getRowInfo().getData();

		if (org.headquarterAddress.state.name.equals("New York")) {
			return Color.parseColor("#CC3300");
		}
		return null;
	}

	public Object ProgrammaticCellFormatting_getColumnBackground(
			IFlexDataGridCell cell) {

		if (cell.getLevel()
				.getSelectedKeys()
				.contains(
						ExtendedUIUtils.resolveExpression(cell.getRowInfo()
								.getData(), cell.getLevel()
								.getSelectedKeyField(), null, false, false))) {
			return this.flexDataGrid.getStyle("selectionColor");
		}
		Object val = ExtendedUIUtils
				.resolveExpression(cell.getRowInfo().getData(), cell
						.getColumn().getDataField(), null, false, false);
		if (Float.parseFloat(val.toString()) < 10000) {
			return Color.parseColor("#CC3300");
		} else if (Float.parseFloat(val.toString()) > 50000) {
			return Color.parseColor("#66BB88");
		} else {
			return null;
		}
	}

	public Object ProgrammaticCellFormatting_getColumnTextColor(
			IFlexDataGridCell cell) {

		Object val = ExtendedUIUtils
				.resolveExpression(cell.getRowInfo().getData(), cell
						.getColumn().getDataField(), null, false, false);
		if (Float.parseFloat(val.toString()) < 10000) {
			return Color.parseColor("#FFFFFF");
		} else if (Float.parseFloat(val.toString()) > 50000) {
			return Color.parseColor("#000000");
		} else {
			return Color.parseColor("#000000");
		}
	}

	public Object ProgrammaticCellFormatting_getRowDisabled(
			IFlexDataGridCell cell, Object data) {
		if (((Organization) data).legalName.equals("Adobe Systems")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
