package com.flexicious.example.serviceproxies;

import com.flexicious.controls.core.Function;
import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.grids.filters.Filter;

/**
 * This is a interface for a webservice or a remoting servicer.
 * 
 * @author Flexicious
 */
public class BusinessService extends ServiceProxyBase {

	private static BusinessService instance;

	public static BusinessService getInstance() {
		if (instance == null) {
			instance = new BusinessService();
		}
		return instance;
	}

	public void getDeepOrgList(Function resultHandler, Function faultHandler) {
		callServiceMethod(FlexiciousMockGenerator.instance().getDeepOrgList(),
				resultHandler, faultHandler);
	}

	public void getFlatOrgList(Function resultHandler, Function faultHandler) {
		callServiceMethod(FlexiciousMockGenerator.instance().getFlatOrgList(),
				resultHandler, faultHandler);
	}

	public void getDeepOrg(Float orgId, Function resultHandler,
			Function faultHandler) {
		callServiceMethod(FlexiciousMockGenerator.instance().getDeepOrg(orgId),
				resultHandler, faultHandler);
	}

	public void getPagedOrganizationList(Filter filter, Function resultHandler,
			Function faultHandler) {
		callServiceMethod(FlexiciousMockGenerator.instance()
				.getPagedOrganizationList(filter), resultHandler, faultHandler);
	}

	public void getDealsForOrganization(int orgId, Filter filter,
			Function resultHandler, Function faultHandler) {
		callServiceMethod(FlexiciousMockGenerator.instance()
				.getDealsForOrganization(orgId, filter), resultHandler,
				faultHandler);
	}

	public void getInvoicesForDeal(int dealId, Filter filter,
			Function resultHandler, Function faultHandler) {
		callServiceMethod(FlexiciousMockGenerator.instance()
				.getInvoicesForDeal(dealId, filter), resultHandler,
				faultHandler);
	}

	public void getLineItemsForInvoice(int invoiceId, Filter filter,
			Function resultHandler, Function faultHandler) {
		callServiceMethod(FlexiciousMockGenerator.instance()
				.getLineItemsForInvoice(invoiceId, filter), resultHandler,
				faultHandler);
	}

	public void getAllLineItems(Function resultHandler, Function faultHandler) {
		callServiceMethod(FlexiciousMockGenerator.instance().getAllLineItems(),
				resultHandler, faultHandler);
	}
}
