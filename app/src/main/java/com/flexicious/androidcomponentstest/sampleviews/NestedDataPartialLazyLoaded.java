package com.flexicious.androidcomponentstest.sampleviews;

import java.util.List;

import android.os.Bundle;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.controls.core.Function;
import com.flexicious.example.model.common.PagedResult;
import com.flexicious.example.model.organizations.Organization;
import com.flexicious.example.serviceproxies.BusinessService;
import com.flexicious.nestedtreedatagrid.events.ExtendedFilterPageSortChangeEvent;
import com.flexicious.nestedtreedatagrid.events.FlexDataGridEvent;

public class NestedDataPartialLazyLoaded extends ExampleActivityBase {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_nested_data_partial_lazy_loaded);
		this.buildGrid(this.flexDataGrid, R.raw.flxspartiallazyloaded); 
	}

	ExtendedFilterPageSortChangeEvent evt1;

	public void partialLazyLoaded_flexdatagridcolumnlevel1_itemLoadHandler(
			ExtendedFilterPageSortChangeEvent event) {
		// this means we were requested to load all the details for a specific
		// organization.
		this.evt1 = event;
		Organization org = (Organization) event.filter.parentObject;
		BusinessService.getInstance().getDealsForOrganization(org.id,
				evt1.filter,
				new Function(this, "getDealsForOrganization_result"),
				faultHandler);

	};

	public void getDealsForOrganization_result(final PagedResult result) {

		// this.flexDataGrid setChildData(evt1.filter.parentObject
		// ,result.collection ,evt1.filter.level.getParentLevel()
		// :result.totalRecords ,false);
		final NestedDataPartialLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {
				view.flexDataGrid.setChildData(evt1.filter.parentObject,
						result.collection, evt1.filter.level.getParentLevel(),
						result.totalRecords, false);

		    }
		});
		
	}

	public void partialLazyLoaded_CreationComplete(FlexDataGridEvent evt) {
		BusinessService.getInstance().getFlatOrgList(
				new Function(this, "getFlatOrgList_Result"), faultHandler);
	}

	public void getFlatOrgList_Result(final List<?> result) {
		

		final NestedDataPartialLazyLoaded view = this;
		this.runOnUiThread(new Runnable() {
		    public void run() {  

				view.flexDataGrid.setPreservePager(true);
				view.flexDataGrid.setDataProvider(result);
		    	
		    }
		});
	}
} 
