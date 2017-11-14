////
////  iPadLargeDynamicGridViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/9/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.androidcomponentstest.sampleviews.supportingviews.MakeModel;
import com.flexicious.controls.core.Function;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumnGroup;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumnLevel;
import com.flexicious.utils.UIUtils;

public class LargeDynamicGrid extends ExampleActivityBase{

	ArrayList<String> colors;
	NumberFormat numberFormatter;
	int colCount;
	int rowCount;
	ArrayList<MakeModel> makeModels;


	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_large_dynamicgrid);

		MakeModel toyota = new MakeModel("Toyota" ,new ArrayList<String>(Arrays.asList("4Runner","Avalon","Camry","Celica","Corolla","Corona","Cressida","Echo","FJ Cruiser","Highlander","Land Cruiser","MR2","Matrix","Paseo","Pickup","Previa","Prius","RAV4","Seqouia","Sienna","Solara","Supra")));
		MakeModel lexus =  new MakeModel("Lexus",new ArrayList<String>(Arrays.asList("CT","ES250","ES300","ES330","ES350","GS300","GS350","GS400","GS430","GS460","GX460","GX470")));
		MakeModel honda = new MakeModel("Honda",new ArrayList<String>(Arrays.asList("Accord","CRV","CRX","Civic","Del Sol","Element","Fit","Insight","Odyssey","Passport","Pilot","Prelude","S2000")));
		MakeModel acura =  new MakeModel("Acura",new ArrayList<String>(Arrays.asList("Integra","Legend","MDX","NSX","RDX","RSX","SLX","3.2TL","2.5TL","Vigor","ZDX")));
		MakeModel nissan = new MakeModel("Nissan",new ArrayList<String>(Arrays.asList("200SX","240SX","300ZX","350Z","370Z","Altima","Armada","Cube","Frontier","GT-R","Juke","Leaf","Maxima","Murano","NX","PathFinder","Pickup","Pulsar","Quest")));
		MakeModel infiniti = new MakeModel("Infiniti",new ArrayList<String>(Arrays.asList("EX35","FX35","FX45","G20","G25","G35","I30","I35","J30","M30")));
		makeModels = new ArrayList<MakeModel>(Arrays.asList(toyota,lexus,honda,acura,nissan,infiniti));		

		colors=new ArrayList<String>(Arrays.asList("Red" ,"Yellow","Silver","Green","Tan","White","Blue","Burgundy","Black","Orange"));
		numberFormatter = NumberFormat.getInstance();
		this.buildGrid(this.flexDataGrid, R.raw.flxslargedynamicgrid);
		this.buildGrid();
		//flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}	
	@SuppressWarnings("unchecked")
	public void buildGrid()
	{

		numberFormatter.setMinimumFractionDigits(0);
		colCount=0;
		ArrayList<HashMap<String,Object>> dp =new ArrayList<HashMap<String,Object>>();;
		ArrayList<Object> gridCgs =new ArrayList<Object>();;
		ArrayList<FlexDataGridColumn>  allCols =new ArrayList<FlexDataGridColumn>();;
		FlexDataGridColumn mCol= new FlexDataGridColumn();
		mCol.setHeaderText("Make");
		mCol.setDataField("make");
		mCol.setWidth(100);
		mCol.setColumnLockMode("left");
		gridCgs.add(mCol);

		mCol = new FlexDataGridColumn();
		mCol.setHeaderText("Model");
		mCol.setDataField("model");
		mCol.setWidth(100);
		mCol.setColumnLockMode("left");
		gridCgs.add(mCol);
		mCol = new FlexDataGridColumn();
		mCol.setHeaderText("Color");
		mCol.setDataField("color");
		mCol.setWidth(100);
		mCol.setColumnLockMode("left");
		gridCgs.add(mCol);
		for (MakeModel mt : makeModels){
			HashMap<String,Object>  mk =new HashMap<String,Object>();
			dp.add(mk);
			mk.put("make", mt.make);
			ArrayList<HashMap<String,Object>>  children = new ArrayList<HashMap<String,Object>>();
			mk.put("children", children);
			for (String  mod : mt.models){
				HashMap<String,Object>  modDic =new HashMap<String,Object>();
				modDic.put("model",mod);
				children.add(modDic);
			}
		}
		for(int i=2000;i<=2012;i++){

			FlexDataGridColumnGroup ycg = new FlexDataGridColumnGroup();
			gridCgs.add(0, ycg);
			ycg.setEnableExpandCollapse(true);
			ycg.setHeaderText(String.valueOf(i));
			for (int j=1;j<=4;j++){
				FlexDataGridColumnGroup  qcg = new FlexDataGridColumnGroup();
				ycg.getColumnGroups().add(0,qcg);
				qcg.setEnableExpandCollapse(true);
				qcg.setHeaderText(ycg.getHeaderText()+"-Q\t"+String.valueOf(j));
				ArrayList<FlexDataGridColumn> qCols =new ArrayList<FlexDataGridColumn>();;
				for(String month : this.getMonths(j )){
					mCol = new FlexDataGridColumn();
					mCol.setHeaderText(ycg.getHeaderText()+"\t"+month);
					mCol.setDataField(qcg.getHeaderText()+ycg.getHeaderText()+month);
					mCol.setFooterOperation("sum");
					mCol.setFooterAlign("right");
					mCol.setEditable(true);
					//TODO Sal//mCol.setIsEditableFunction(this.isEditable(mCol));
					mCol.setFooterFormatter( numberFormatter);
					mCol.setLabelFunction(new Function(this, "dataGridFormatNumberLabelFunction"));
					//	                //mCol.useHandCursor = true
					mCol.setFooterOperationPrecision( 0);
					mCol.setWidth(90);
					mCol.textAlign =  "right";
					qCols.add(0, mCol);
					allCols.add(0, mCol);
					colCount++;
				}
				qcg.setColumns(qCols);
			}
		}

		for (HashMap<String, Object> dpItem : dp){
			for (HashMap<String, Object> model : (ArrayList<HashMap<String,Object>>)dpItem.get("children")){
				ArrayList<Object> modelChildren =new ArrayList<Object>();;
				model.put("children",modelChildren);
				for (String cl : colors){
					HashMap<String,Object>  colorDic = new HashMap<String, Object>();
					colorDic.put("color", cl);
					modelChildren.add(colorDic);
					for(FlexDataGridColumn mCol1 : allCols){
						colorDic.put(mCol1.getDataField(), FlexiciousMockGenerator.getRandom(100, 1000).floatValue());
					}
					rowCount++;
				}
			}
		}
		for (FlexDataGridColumn mCol1 : allCols){
			for (HashMap<String,Object>  dpItem : dp){
				float total=0;
				for (HashMap<String,Object>  model : (ArrayList<HashMap<String, Object>>)dpItem.get("children")){
					float modeltotal=0;
					for ( HashMap<String, Object> color : (ArrayList<HashMap<String, Object>>)model.get("children")){
						modeltotal+= (Float)color.get(mCol1.getDataField());
					}
					model.put(mCol1.getDataField(), Float.valueOf(modeltotal));
					total+=modeltotal;
				}
				dpItem.put(mCol1.getDataField(),Float.valueOf(total));
			}
		}
		this.flexDataGrid.getColumnLevel().setGroupedColumns( gridCgs);
		this.flexDataGrid.getColumnLevel().setChildrenField("children");
		this.flexDataGrid.getColumnLevel().setNextLevel(new FlexDataGridColumnLevel());
		this.flexDataGrid.getColumnLevel().getNextLevel().setReusePreviousLevelColumns(true);
		this.flexDataGrid.getColumnLevel().getNextLevel().setChildrenField("children");
		this.flexDataGrid.getColumnLevel().getNextLevel().setNextLevel(new FlexDataGridColumnLevel());
		this.flexDataGrid.getColumnLevel().getNextLevel().getNextLevel().setReusePreviousLevelColumns(true);
		this.flexDataGrid.setDataProvider(dp);
	}

	public boolean isEditable(FlexDataGridColumn cell){
		return (cell.getLevel().getNestDepth()==3);
	}
	public ArrayList<String> getMonths(int j)
	{
		if(j==1){
			return new ArrayList<String>(Arrays.asList("Jan", "Feb", "Mar"));
		}else if(j==2){
			return new ArrayList<String>(Arrays.asList("Apr", "May", "June"));
		}else if(j==3){
			return new ArrayList<String>(Arrays.asList("Jul", "Aug", "Sep"));
		}else if(j==4){
			return new ArrayList<String>(Arrays.asList("Oct", "Nov", "Dec"));
		}
		return new ArrayList<String>();
	}
	public String dataGridFormatNumberLabelFunction(HashMap<String, Object> item, FlexDataGridColumn dgColumn)
	{
		Object  result=item.get(dgColumn.getDataField());
		if(result instanceof Number){
			return UIUtils.formatCurrency((Float) result,"");
		}
		else
			return item.get(dgColumn.getDataField()).toString();
	}

	public String formatCurrency(Number number,String param1)
	{
		return UIUtils.formatCurrency((Float)number,"");
	}
}
 
