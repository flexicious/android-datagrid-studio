////
////  iPadEditableCellsViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/8/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.Date;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridItemEditEvent;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;


public class EditableCells extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_editable_cells);
		this.buildGrid(this.flexDataGrid, R.raw.flxseditablecells);
		this.flexDataGrid.setDataProvider(FlexiciousMockGenerator.instance().getFlatOrgList());
	}	
	
	public void editableCells_getRowDisabled(){
		//todo sal
	}
////here we validate the user input, and ensure that only valid
public void flexdatagridcolumn1_itemEditValueCommitHandler(FlexDataGridItemEditEvent event)
{
    //FlexDataGridItemEditEvent* event = (FlexDataGridItemEditEvent*)[ ns.userInfo objectForKey:"event"];

   /* if(event.column.dataField.equals("headquarterAddress.city.name")){
        String txt=event.itemEditor.text valueForKey:"text";
        boolean found=false;
        for (ReferenceData  city : SystemConstants.cities){
            if(city.name==txt){
                ((Organization)event.item).headquarterAddress.city=city;
                found=true;
                break;
            }
        }
        if(!found){
            UIAlertView* alert  = [[UIAlertView alloc] initWithTitle:"" message:["Invalid City Entered:"  + txt] delegate:self cancelButtonTitle:"Ok" otherButtonTitles:nil, null];
            [alert show];
            Alert.show("Invalid City Entered: " + txt);
        }
        event.preventDefault();
        //we do this, so when the value is entered, we validate and apply ourselves, dont let the grid apply it..
    }*/
    
}
//or use the validator function....
public boolean validateFutureDate(Date editor){
    
   /* if(editor.selectedDate<new Date()){
        UIAlertView* alert  = [[UIAlertView alloc] initWithTitle:"" message:"Please choose a date in the future."  delegate:self cancelButtonTitle:"Ok" otherButtonTitles:nil, null];
        [alert show];
        Alert.show("Please choose a date in the future.");
        return false;
    }*/
    return true;
}
public boolean getRowDisabled(IFlexDataGridCell cell, Object data){
//        if(data.legalName.equals("Adobe Systems")){
//        return true;
//    }
    return false;//do not disable by default.
}
} 
