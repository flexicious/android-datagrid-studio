////
////  iPadDynamicColumnsViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/9/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.Function;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;


public class DynamicColumns extends ExampleActivityBase
{
	Button addLastStockPrice;
	Button removeLastStockPrice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_dynamic_columns);
		
		addLastStockPrice = (Button) findViewById(R.id.dynamiccolumn_addlaststockprice);
		removeLastStockPrice = (Button) findViewById(R.id.dynamiccolumn_removelaststockprice);
		this.buildGrid(this.flexDataGrid, R.raw.flxsdynamiccolumns);
 		
		addLastStockPrice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btnAddCol_clickHandler(v);
				
			}
		});
		
		removeLastStockPrice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			btnRemoveCol_clickHandler(v);
				
			}
		});
	}
	public FlexDataGridColumn addColumn(String dataField, String headerText)
	{
	    FlexDataGridColumn dgCol = new FlexDataGridColumn ();
	    dgCol.setDataField(dataField);
	    dgCol.setHeaderText(headerText);
	    dgCol.setFilterControl("TextInput");
	    dgCol.setFilterOperation("BeginsWith");
	    dgCol.setFilterWaterMark("Begins With");
	    return dgCol;
	}
	public FlexDataGridColumn addCurrencyColumn(String dataField,String headerText)
	{
	    FlexDataGridColumn dgCol = this.addDateColumn(dataField, headerText);
	    dgCol.setLabelFunction(new Function(this,"dataGridFormatCurrencyLabelFunction"));
	    //[dgCol setStyle:"textAlign" :"right"];
	    dgCol.textAlign="right";
	    dgCol.setFooterOperation("average");
	    dgCol.setFooterLabel("Avg: ");
	    dgCol.setFooterAlign("right");
	    dgCol.paddingRight =Integer.valueOf(15);
	    //[dgCol setStyle:"paddingRight" :[NSNumber numberWithInt:15]];
	    //[dgCol setStyle:"paddingRight":[NSNumber numberWithInt:15]];
	    dgCol.setFilterOperation("GreaterThan");
	    dgCol.setFilterWaterMark( "Greater Than");
	    return dgCol;
	}
	public FlexDataGridColumn addDateColumn(String dataField , String headerText)
	{
	    FlexDataGridColumn dgCol =this.addColumn(dataField,headerText);
	    //todo sal
////	    dgCol.labelFunction=SampleUIUtils.dataGridFormatDateLabelFuncti;
	    dgCol.setFilterControl("DateComboBox");
	    return dgCol;
	}
	public void DynamicColumns_grid_creationCompleteHandler(FlexDataGridEvent  ns){
	    this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	    this.flexDataGrid.clearColumns(true);
	    FlexDataGridColumn col=addColumn("id" ,"Company ID");
	    col.setColumnLockMode(FlexDataGridColumn.LOCK_MODE_LEFT);
	    this.flexDataGrid.addColumn(col);
	    col=this.addColumn("legalName" ,"Company Name");
	    col.setColumnLockMode(FlexDataGridColumn.LOCK_MODE_RIGHT);
	    this.flexDataGrid.addColumn(col);
	    this.flexDataGrid.addColumn(this.addColumn("headquarterAddress.line1" ,"Address Line 1"));
	    this.flexDataGrid.addColumn(this.addColumn("headquarterAddress.line2","Address Line2"));
	    this.flexDataGrid.addColumn(this.addCurrencyColumn("earningsPerShare","EPS"));
	    this.flexDataGrid.addColumn(this.addColumn("headquarterAddress.line1","Address Line 1"));
	    this.flexDataGrid.addColumn(this.addColumn("headquarterAddress.line2" ,"Address Line2"));
	    this.flexDataGrid.addColumn(this.addCurrencyColumn("earningsPerShare" ,"EPS"));
	    this.flexDataGrid.addColumn(this.addColumn("headquarterAddress.line1" ,"Address Line 1"));
	    this.flexDataGrid.addColumn(this.addColumn("headquarterAddress.line2" ,"Address Line2"));
	    this.flexDataGrid.addColumn(this.addCurrencyColumn("earningsPerShare" ,"EPS"));
	    this.flexDataGrid.addColumn(this.addColumn("headquarterAddress.line1" ,"Address Line 1"));
	    this.flexDataGrid.addColumn(this.addColumn("headquarterAddress.line2" ,"Address Line2"));
	    this.flexDataGrid.addColumn(this.addCurrencyColumn("earningsPerShare" ,"EPS"));
	    this.flexDataGrid.distributeColumnWidthsEqually();
	    this.flexDataGrid.reDraw();
	}
	
	public void btnAddCol_clickHandler(Object sender) {
	    
	    FlexDataGridColumn col=this.addCurrencyColumn("lastStockPrice","Last Stock Price");
	    this.flexDataGrid.addColumn(col);
	    this.flexDataGrid.distributeColumnWidthsEqually();
	    this.flexDataGrid.reDraw();
	
	}
	
	public void btnAddCol_clickHandler(FlexDataGridEvent ns)
	{
	    FlexDataGridColumn col=this.addCurrencyColumn("lastStockPrice" ,"Last Stock Price");
	    this.flexDataGrid.addColumn(col);
	    this.flexDataGrid.distributeColumnWidthsEqually();
	    this.flexDataGrid.reDraw();
	}
	public void btnRemoveCol_clickHandler(Object sender) {
	    
	    this.flexDataGrid.removeColumn(this.flexDataGrid.getColumnByDataField("lastStockPrice"));
	    this.flexDataGrid.distributeColumnWidthsEqually();
	    this.flexDataGrid.reDraw();
	}
	
	
	public void btnRemoveCol_clickHandler(FlexDataGridEvent event)
	{
	   this.flexDataGrid.removeColumn(this.flexDataGrid.getColumnByDataField("lastStockPrice"));
	    this.flexDataGrid.distributeColumnWidthsEqually();
	    this.flexDataGrid.reDraw();
	}
	
}