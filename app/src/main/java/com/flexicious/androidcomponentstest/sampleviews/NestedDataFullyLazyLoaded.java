package com.flexicious.androidcomponentstest.sampleviews;

import java.util.List;
import java.util.Map;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.Event;
import com.flexicious.controls.core.Function;
import com.flexicious.example.model.billing.Invoice;
import com.flexicious.example.model.common.PagedResult;
import com.flexicious.example.model.organizations.Organization;
import com.flexicious.example.model.transactions.Deal;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.grids.filters.Filter;
import com.flexicious.nestedtreedatagrid.FlexDataGridColumn;
import com.flexicious.nestedtreedatagrid.cells.FlexDataGridFooterCell;
import com.flexicious.nestedtreedatagrid.events.ExtendedFilterPageSortChangeEvent;
import com.flexicious.nestedtreedatagrid.utils.KeyValuePairCollection;
import com.flexicious.utils.UIUtils;

public class NestedDataFullyLazyLoaded extends ExampleActivityBase {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_nested_data_fully_lazy_loaded);
		this.buildGrid(this.flexDataGrid, R.raw.flxsfullylazyloaded);
		 
//		this.fullyLazyLoaded_CreationComplete(null);
	}

	KeyValuePairCollection _footerData;

	@SuppressWarnings("rawtypes")
	public String fullyLazyLoaded_getFooterLabel(FlexDataGridColumn col, FlexDataGridFooterCell cell) {
		if (_footerData == null) {
			_footerData = new KeyValuePairCollection();
		}
		if (_footerData.getValue(cell.getRowInfo().getData()) != null) {

			if (cell.getColumn().getDataField().equals("invoiceAmount")
					|| cell.getColumn().getDataField().equals("lineItemAmount")
					|| cell.getColumn().getDataField().equals("dealAmount")) {
				return "Total: "
						+ ((Map) _footerData.getValue(cell.getRowInfo()
								.getData())).get("total");
			} else
				return "Count: "
						+ ((Map) _footerData.getValue(cell.getRowInfo()
								.getData())).get("count");
		}
		return "";

	}

	public void fullyLazyLoaded_grid_printExportDataRequestHandler(Event evt) {

	}

	public void getPagedOrganizationList_Result(List<?> result) {
		// this.flexDataGrid.printExportData = result;
	}

	ExtendedFilterPageSortChangeEvent evt1;

	public void fullyLazyLoaded_flexdatagridcolumnlevel1_itemLoadHandler(
			ExtendedFilterPageSortChangeEvent evt1) {

		this.evt1 = evt1;
		// this means we were requested to load all the details for a specific
		// organization.
		Organization org = (Organization) evt1.filter.parentObject;
		org = (Organization) org.clone(false);
		BusinessService.getInstance().getDealsForOrganization(org.id,
				evt1.filter,
				new Function(this, "getDealsForOrganization_result"),
				faultHandler);

	}

	public void getDealsForOrganization_result(final PagedResult result) {
		if (_footerData == null) {
			_footerData = new KeyValuePairCollection();
		}

		Organization org = (Organization) evt1.filter.parentObject;
		_footerData.addItem(org, result.summaryData); 
		
		final NestedDataFullyLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {   
		    	view.flexDataGrid.setChildData(evt1.filter.parentObject,
						result.collection, evt1.filter.level.getParentLevel(),
						result.totalRecords, true);
		    	
		    }
		}); 
		 
	}

	public void fullyLazyLoaded_flexdatagridcolumnlevel2_itemLoadHandler(
			ExtendedFilterPageSortChangeEvent evt1) {

		// this means we were requested to load all the invoices for a specific
		// deal.
		this.evt1 = evt1;

		Deal deal = (Deal) evt1.filter.parentObject;
		BusinessService.getInstance().getInvoicesForDeal(deal.id, evt1.filter,
				new Function(this, "getInvoicesForDeal_result2"), faultHandler);

	}

	public void getInvoicesForDeal_result2(final PagedResult result) {
		if (_footerData == null) {
			_footerData = new KeyValuePairCollection();
		}
		_footerData.addItem(evt1.filter.parentObject, result.summaryData);
		
		final NestedDataFullyLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {  

		    	view.flexDataGrid.setChildData(
						evt1.filter.parentObject,
						UIUtils.copyOfRange(result.collection, 0,
								result.collection.size()),
						evt1.filter.level.getParentLevel(), result.totalRecords, true);
		    	
		    }
		});
		
		
	}

	public void fullyLazyLoaded_flexdatagridcolumnlevel3_itemLoadHandler(
			ExtendedFilterPageSortChangeEvent evt1) {
		this.evt1 = evt1;

		// this means we were requested to load all the line items for a
		// specific invoice.
		Invoice inv = (Invoice) evt1.filter.parentObject;
		BusinessService.getInstance().getLineItemsForInvoice(inv.id,
				evt1.filter,
				new Function(this, "getLineItemsForInvoice_result"),
				faultHandler);

	}

	public void getLineItemsForInvoice_result(final PagedResult result) {
		if (_footerData == null) {
			_footerData = new KeyValuePairCollection();
		}
		_footerData.addItem(evt1.filter.parentObject, result.summaryData);
		
		
		final NestedDataFullyLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {  

		    	view.flexDataGrid.setChildData(evt1.filter.parentObject,
						result.collection, evt1.filter.level.getParentLevel(),
						result.totalRecords, true);
		    	
		    }
		});
		
		
	}

	public void fullyLazyLoaded_flexdatagridcolumnlevel1_filterPageSortChangeHandler(
			ExtendedFilterPageSortChangeEvent evt1) {
		this.evt1 = evt1;
		BusinessService.getInstance().getPagedOrganizationList(evt1.filter,
				new Function(this, "getPagedOrganizationList_result"),
				faultHandler);
	}

	public void getPagedOrganizationList_result(final PagedResult result) {
		
		final NestedDataFullyLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {  

				view.flexDataGrid.setPreservePager(true);
				view.flexDataGrid.setDataProvider(result.collection);
				view.flexDataGrid.setTotalRecords(result.totalRecords);
		    	
		    }
		});
	}

	public void fullyLazyLoaded_flexdatagridcolumnlevel2_filterPageSortChangeHandler(
			ExtendedFilterPageSortChangeEvent evt1) {
		this.evt1 = evt1;

		// this means that we paged, sorted or filtered the list of deals for an
		// organization.
		Organization org = (Organization) evt1.filter.parentObject;
		BusinessService.getInstance().getDealsForOrganization(org.id,
				evt1.filter,
				new Function(this, "getDealsForOrganization_result2"),
				faultHandler);

	}

	public void getDealsForOrganization_result2(final PagedResult result) {
		final NestedDataFullyLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {  

		    	view.flexDataGrid.setChildData(evt1.filter.parentObject,
						result.collection, evt1.filter.level.getParentLevel(),
						result.totalRecords, false);
		    	
		    }
		});
	}

	public void fullyLazyLoaded_flexdatagridcolumnlevel3_filterPageSortChangeHandler(
			ExtendedFilterPageSortChangeEvent evt1) {
		this.evt1 = evt1;

		// this means that we paged, sorted or filtered the list of invoices for
		// a deal.
		Deal deal = (Deal) evt1.filter.parentObject;
		BusinessService.getInstance().getInvoicesForDeal(deal.id, evt1.filter,
				new Function(this, "getInvoicesForDeal_result"), faultHandler);

	}

	public void getInvoicesForDeal_result(final PagedResult result) {
		final NestedDataFullyLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {  
		    	view.flexDataGrid.setChildData(
				evt1.filter.parentObject,
				UIUtils.copyOfRange(result.collection, 0,
						result.collection.size()),
				evt1.filter.level.getParentLevel(), result.totalRecords, false);

		    }
		});
	}

	public void fullyLazyLoaded_flexdatagridcolumnlevel4_filterPageSortChangeHandler(
			ExtendedFilterPageSortChangeEvent evt1) {
		this.evt1 = evt1;

		// this means that we paged, sorted or filtered the list of line items
		// for an invoicef.
		Invoice inv = (Invoice) evt1.filter.parentObject;
		BusinessService.getInstance().getLineItemsForInvoice(inv.id,
				evt1.filter,
				new Function(this, "getLineItemsForInvoice_result2"),
				faultHandler);

	}

	public void getLineItemsForInvoice_result2(final PagedResult result) {
		final NestedDataFullyLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {
		    	view.flexDataGrid.setChildData(
				evt1.filter.parentObject,
				UIUtils.copyOfRange(result.collection, 0,
						result.collection.size()),
				evt1.filter.level.getParentLevel(), result.totalRecords, false);
		    }
		});
	}

	public void fullyLazyLoaded_CreationComplete(Event evt1) {
		Filter f = new Filter();// new flexiciousNmsp.Filter();
		f.pageIndex = 0;
		f.pageSize = this.flexDataGrid.getPageSize();
		BusinessService.getInstance().getPagedOrganizationList(f,
				new Function(this, "getPagedOrganizationList_result2"),
				faultHandler);

	}

	public void getPagedOrganizationList_result2(final PagedResult result) {
		
		final NestedDataFullyLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {
		    	view.flexDataGrid.setPreservePager(true);
		    	view.flexDataGrid.setDataProvider(result.collection);
		    	view.flexDataGrid.setTotalRecords(result.totalRecords);
		    }
		});

	}
} 
