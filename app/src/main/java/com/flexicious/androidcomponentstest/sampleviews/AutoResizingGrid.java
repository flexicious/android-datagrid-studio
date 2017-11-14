package com.flexicious.androidcomponentstest.sampleviews;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;

/**
 * This example demonstrates support of autosizing the grid based upton number of rows being displayed.
 * 
 */
public class AutoResizingGrid extends ExampleActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_autoresizing_grid);
		this.buildGrid(this.flexDataGrid, R.raw.flxsautoresizinggrid);
		
		Button button= (Button) findViewById(R.id.btnAddNew);
		button.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) { 
		    	AutoResizingGrid.this.autoResizingGrid_newObject();
		    }
		});
	}
	ArrayList<Object>  autoResizingGrid_listOfObjects;
	int autoResizingGrid_number=1;
	
	public void autoResizingGrid_newObject(){
	    HashMap<String,Object>  obj =new HashMap<String,Object>();
	    obj.put("number", autoResizingGrid_number++);
	    obj.put("value", 1);
	    autoResizingGrid_listOfObjects.add(obj);
	    this.flexDataGrid.rebuild();
	};
	public void autoResizingGrid_creationCompleteHandler(FlexDataGridEvent event){
	    autoResizingGrid_listOfObjects  =new ArrayList<Object>();;
	    for (int i=0;i<3;i++){
	        this.autoResizingGrid_newObject();
	    }
	
	    this.flexDataGrid.setDataProvider(autoResizingGrid_listOfObjects);
	}
	public void addItem(Object sender) {
	    this.autoResizingGrid_newObject();	
	} 
}
  
