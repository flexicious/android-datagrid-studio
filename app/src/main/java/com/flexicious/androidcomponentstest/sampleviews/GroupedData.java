package com.flexicious.androidcomponentstest.sampleviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.Event;
import com.flexicious.controls.core.Function;
import com.flexicious.example.model.billing.Invoice;
import com.flexicious.example.model.common.SystemConstants;
import com.flexicious.example.model.organizations.Organization;
import com.flexicious.example.model.transactions.Deal;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.grids.filters.FilterSort;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.events.ExtendedFilterPageSortChangeEvent;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;
import com.flexicious.nestedtreedatagrid.interfaces.IFlexDataGridCell;
import com.flexicious.utils.UIUtils;

public class GroupedData extends ExampleActivityBase {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_grouped_data);
		this.buildGrid(this.flexDataGrid, R.raw.flxsgroupeddata); 
	}

	ExtendedFilterPageSortChangeEvent evt1;

	public void vbox1_creationCompleteHandler(FlexDataGridEvent evt1) {

		BusinessService.getInstance().getDeepOrgList(
				new Function(this, "getDeepOrgList_result"), null);

	}

	public List<?> getInvoiceStatuses() {
		
		return Arrays.asList(SystemConstants.invoiceStatuses);
	}

	public void getDeepOrgList_result(final List<?> result) {
		final GroupedData view = this;

		this.runOnUiThread(new Runnable() {
			public void run() {

				view.flexDataGrid.setDataProvider(result);
				// set default sort:
				ArrayList<FilterSort> sorts = new ArrayList<FilterSort>();
				sorts.add(new FilterSort("legalName", true));
				sorts.add(new FilterSort("dealDescription", true));
				sorts.add(new FilterSort("invoiceNumber", true));
				flexDataGrid.processSort(sorts);
				/*
				 * [sorts addObject:[[FilterSort alloc ]init:"legalName":YES]];
				 * [sorts addObject:[[FilterSort alloc] init:"dealDescription"
				 * :YES]]; [sorts addObject:[[FilterSort alloc]
				 * init:"invoiceNumber" :YES]];
				 * this.flexDataGrid.processSort:sorts];
				 */
			}
		});

	}

	public boolean checkCellDisabled(IFlexDataGridCell cell) {
		return !(cell.getRowInfo().getData() instanceof Invoice);
		// return !([cell.getRowInfo().getData() isKindOfClass: [Invoice);
	}

	public boolean returnFalse(IFlexDataGridCell cell) {
		return false;
	}

	public String groupedData_getInvoiceAmount(Object data,
			FlexDataGridColumn col) {
		float val = 0;
		if (data instanceof Invoice)
			val = ((Invoice) data).getInvoiceAmount();
		else if (data instanceof Deal)
			val = ((Deal) data).getDealAmount();
		else if (data instanceof Organization)
			val = ((Organization) data).getRelationshipAmount();
		return UIUtils.formatCurrency(val, "");
		/*
		 * if([data instanceof Invoice) val=((Invoice)data).invoiceAmount; else
		 * if([data isKindOfClass: [Deal) val=((Deal)data).dealAmount; else
		 * if([data instanceof Organization) val= ((Organization)data
		 * ).relationshipAmount; return [SampleUIUtils formatCurrency:val];
		 */
	}

	public int groupedData_amountSortCompareFunction(Object obj1, Object obj2) {
		if (obj1 instanceof Organization && obj2 instanceof Organization) {
			return ((Organization) obj1).getRelationshipAmount() > ((Organization) obj2)
					.getRelationshipAmount() ? -1 : 1;

		} else if (obj1 instanceof Deal && obj2 instanceof Deal) {
			return ((Deal) obj1).getDealAmount() > ((Deal) obj2)
					.getDealAmount() ? -1 : 1;
		} else if (obj1 instanceof Invoice && obj2 instanceof Invoice) {
			return ((Invoice) obj1).getInvoiceAmount() > ((Invoice) obj2)
					.getInvoiceAmount() ? -1 : 1;
		}
		return 0;

	}

	public void button1_clickHandler(Event e) {
		// _selectedKeys=this.flexDataGrid.getSelectedKeys().join(",");
		// _selectedObjects=grid.getSelectedObjects().join(",");
	}
}