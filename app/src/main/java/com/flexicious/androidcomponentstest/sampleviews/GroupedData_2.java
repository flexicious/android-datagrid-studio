package com.flexicious.androidcomponentstest.sampleviews;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.Function;
import com.flexicious.example.model.billing.Invoice;
import com.flexicious.example.model.common.SystemConstants;
import com.flexicious.example.model.organizations.Organization;
import com.flexicious.example.model.transactions.Deal;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.events.ExtendedFilterPageSortChangeEvent;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridDataCell;
import com.flexicious.utils.UIUtils;


public class GroupedData_2 extends ExampleActivityBase{
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_grouped_data_2);
		this.buildGrid(this.flexDataGrid, R.raw.flxsgroupeddata2); 
	}
	
	ExtendedFilterPageSortChangeEvent evt1;
	public List<?> getInvoiceStatuses(){
	    return Arrays.asList(SystemConstants.invoiceStatuses);
	}
	public void groupedData2_CreationComplete(FlexDataGridEvent ns)
	{
	
	     BusinessService.getInstance().getDeepOrgList(new Function(this,"getDeepOrgList_result"), null);
	}
	public void getDeepOrgList_result(final List<Object> result){
		final GroupedData_2 view=this;
		this.runOnUiThread(new Runnable() {
		    public void run() {   

			    view.flexDataGrid.setDataProvider(result);
		    	
		    }
		}); 
	}
	public Object groupedData2_checkCellDisabled(IFlexDataGridDataCell cell){
		return new Boolean(!(cell.getRowInfo().getData() instanceof Invoice));
	}
	public boolean groupedData2_returnFalse(IFlexDataGridCell cell,Object data){
	    return false;
	}
	public String groupedData2_getInvoiceAmount(Object data, FlexDataGridColumn col){
	    float val=0;
	    if(data instanceof Invoice )
	        val=((Invoice)data).getInvoiceAmount();
	    else if(data instanceof Deal)
	        val=((Deal)data).getDealAmount();
	    else if(data instanceof Organization )
	        val= ((Organization)data ).getRelationshipAmount();
	    return UIUtils.formatCurrency(val, "");
	}
	public int groupedData2_amountSortCompareFunction(Object obj1, Object obj2){
	    
	    if(obj1 instanceof Organization && obj2 instanceof Organization){
	        return ((Organization)obj1).getRelationshipAmount() > ((Organization)obj2).getRelationshipAmount()?-1:1;
	
	    }
	    else if(obj1 instanceof Deal && obj2 instanceof Deal ){
	        return ((Deal) obj1).getDealAmount() > ((Deal)obj2).getDealAmount()?-1:1;
	    }
	    else if(obj1 instanceof Invoice && obj2 instanceof Invoice){
	        return ((Invoice)obj1).getInvoiceAmount() > ((Invoice)obj2).getInvoiceAmount()?-1:1;
	    }
	    return 0;
	}
}
