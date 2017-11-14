package com.flexicious.androidcomponentstest.sampleviews;

import android.os.Bundle;
import android.widget.TextView;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.androidcomponentstest.sampleviews.supportingviews.StyleManager;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;
import com.flexicious.nestedtreedatagrid.valueobjects.ChangeInfo;

public class ChangeTrackingAPI extends ExampleActivityBase
{
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_change_tracking_api);
		
		textView = (TextView) findViewById(R.id.changetrackingapi_textview);
		
		this.buildGrid(this.flexDataGrid, R.raw.flxschangetrackingapi);
		this.loadJsonData(flexDataGrid, R.raw.flxsdatamocknesteddata);
		this.flexDataGrid.validateNow();
		this.flexDataGrid.expandAll();
	}
	
	public void changeTrackingAPI_CreationComplete(FlexDataGridEvent event)
	{
	
	}
	public void changeTrackingAPI_ItemEditEnd(FlexDataGridEvent event)
	{
		String str = "";
		for (ChangeInfo chg : this.flexDataGrid.getChanges()){
			str += chg.toString() + System.getProperty("line.separator");
		}
		this.textView.setText(str);
	}
	
	public void btnGetChanges_clickHandler(){
	    
	}
	
	
	public Object changeTrackingAPI_getCellBackgroundColor(IFlexDataGridCell cell){
	    
	    if(!cell.getRowInfo().getIsDataRow()){
	        return null;
	    }
	    for(ChangeInfo changeInfo : this.flexDataGrid.getChanges())
	        if(changeInfo.changedItem == cell.getRowInfo().getData()
	           && changeInfo.changedProperty.equals(cell.getColumn().getDataField())
	           && changeInfo.previousValue!=changeInfo.newValue){
	            return  StyleManager.instance().getUIColorObjectFromHexString("0x119933");
	        }
	    return null;
	}
	
}
