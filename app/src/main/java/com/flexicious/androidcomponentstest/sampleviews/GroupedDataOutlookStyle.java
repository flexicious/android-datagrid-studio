////
////  iPadGrouped Data-OutlookStyleViewController.m
////  IOSComponentsSample
////
////  Created by Flexicious-110 on 7/8/13.
////  Copyright (c) 2013 ___IOSComponents___. All rights reserved.
////
package com.flexicious.androidcomponentstest.sampleviews;

import java.util.List;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.androidcomponentstest.sampleviews.supportingviews.StyleManager;
import com.flexicious.controls.core.Function;
import com.flexicious.example.model.billing.Invoice;
import com.flexicious.example.model.organizations.Organization;
import com.flexicious.example.model.transactions.Deal;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.events.ExtendedFilterPageSortChangeEvent;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridDataCell;
import com.flexicious.utils.UIUtils;

public class GroupedDataOutlookStyle extends ExampleActivityBase{
	
	ExtendedFilterPageSortChangeEvent evt1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_grouped_data_outlookstyle);
		this.buildGrid(this.flexDataGrid, R.raw.flxsoutlookgroupeddata);
		this.flexDataGrid.showSpinner("Loading!");
	    this.flexDataGrid.delegate=this; 
	
	    this.flexDataGrid.verticalGridLines = false;
	    this.flexDataGrid.horizontalGridLines=true;
	   
	    this.flexDataGrid.headerColors = new Integer[]{StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE"),
	                    StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE")};
	
	    this.flexDataGrid.verticalGridLines = false;
	    
	    this.flexDataGrid.horizontalGridLines=true;
	    this.flexDataGrid.headerColors = new Integer[]{StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE"),StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE")};
	    this.flexDataGrid.headerRollOverColors= new Integer[]{StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE"),StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE")}; 
	    this.flexDataGrid.headerVerticalGridLineColor= StyleManager.instance().getUIColorObjectFromHexString("0xD0D0D0");
	    this.flexDataGrid.filterColors= new Integer[]{StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE"),StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE")};
	    this.flexDataGrid.filterRollOverColors= new Integer[]{StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE"),StyleManager.instance().getUIColorObjectFromHexString("0xEEEEEE")};
	    this.flexDataGrid.filterVerticalGridLineColor = StyleManager.instance().getUIColorObjectFromHexString("0xD0D0D0"); 
	    this.flexDataGrid.footerColors = new Integer[]{StyleManager.instance().getUIColorObjectFromHexString("0xFFFFFF"),StyleManager.instance().getUIColorObjectFromHexString("0xFFFFFF")};
	    this.flexDataGrid.footerRollOverColors=new Integer[]{StyleManager.instance().getUIColorObjectFromHexString("0xFFFFFF"),StyleManager.instance().getUIColorObjectFromHexString("0xFFFFFF")};
	    this.flexDataGrid.footerVerticalGridLines = false;
	    this.flexDataGrid.footerHorizontalGridLineColor= StyleManager.instance().getUIColorObjectFromHexString("0xEDEDED");
	    this.flexDataGrid.headerHorizontalGridLineColor= StyleManager.instance().getUIColorObjectFromHexString("0xD0D0D0");
	    this.flexDataGrid.selectionColor= StyleManager.instance().getUIColorObjectFromHexString("0xCEDBEF");
	    this.flexDataGrid.alternatingItemColors=new Integer[]{StyleManager.instance().getUIColorObjectFromHexString("0xFFFFFF"),StyleManager.instance().getUIColorObjectFromHexString("0xFFFFFF")};
	    this.flexDataGrid.rollOverColor= StyleManager.instance().getUIColorObjectFromHexString("0xFFFFFF");
	    //TODO Sal
        //this.flexDataGrid.disclosureOpenIcon="/minus.png";//make sure you put these images in flexiciousNmsp.Constants.IMAGE_PATH
        //this.flexDataGrid.disclosureClosedIcon="/plus.png";//make sure you put these images in flexiciousNmsp.Constants.IMAGE_PATH
	    this.flexDataGrid.horizontalGridLineColor= StyleManager.instance().getUIColorObjectFromHexString("0x99BBE8");
	
	}
	public void outlookGroupedData_CreationComplete(FlexDataGridEvent ns)
	{
	    evt1 = (ExtendedFilterPageSortChangeEvent) ns.triggerEvent;
	
	     BusinessService.getInstance().getDeepOrgList(new Function(this,"getDeepOrgList_result"), faultHandler);
	}
	
	public void getDeepOrgList_result(final List<Object> result)
	{
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				flexDataGrid.setDataProvider(result);
			}
		});
	    
	}
	
	public boolean outlookGroupedData_returnFalse(IFlexDataGridCell cell, Object data){
	    return false;
	}
	
	public String outlookGroupedData_getInvoiceAmount(Object data, FlexDataGridColumn col){
	    float val=0;
	    if(data instanceof  Deal)
	        val=((Deal)data).getDealAmount();
	    else if(data instanceof Organization)
	        val= ((Organization)data).getRelationshipAmount();
	    return UIUtils.formatCurrency(val,"");
	}
	
	public boolean outlookGroupedData_amountSortCompareFunction(Object obj1, Object obj2){
	    if(obj1 instanceof Organization && obj2 instanceof Organization){
	        return ((Organization)obj1).getRelationshipAmount() > ((Organization)obj2).getRelationshipAmount();
	    }
	    else if(obj1 instanceof Deal && obj2 instanceof Deal){
	        return ((Deal)obj1).getDealAmount()>((Deal)obj2).getDealAmount();
	    }
	    else if(obj1 instanceof Invoice && obj2 instanceof Invoice){
	        return ((Invoice)obj1).getInvoiceAmount()>((Invoice)obj2).getInvoiceAmount();
	    }
	    return false;
	    
	}
	
	public Integer outlookGroupedData_getBlueColor(IFlexDataGridCell cell)
	{
	    return StyleManager.instance().getUIColorObjectFromHexString("0x3764A0");
	}
	public void outlookGroupedData_gridrendererInitializedHandler(FlexDataGridEvent ns)
	{
	    FlexDataGridEvent event = (FlexDataGridEvent)ns.triggerEvent;
	
	    IFlexDataGridCell cell=event.cell;
	    if(cell instanceof IFlexDataGridDataCell){//the dg has various types of cells. we only want to style the data cells...
	        if(cell.getLevel().getNestDepth()==1){
	            //at the first level, we want font to be bold ...
	            cell.setStyle("fontWeight", "bold");
	
	        }
	        else{
	        	cell.setStyle("fontWeight", "normal");
	        }
	
	    }
	}
	
	
}
