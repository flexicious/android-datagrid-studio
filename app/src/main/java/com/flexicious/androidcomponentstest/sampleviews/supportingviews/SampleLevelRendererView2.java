package com.flexicious.androidcomponentstest.sampleviews.supportingviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.model.common.Address;
import com.flexicious.example.model.organizations.Organization;

public class SampleLevelRendererView2 extends FrameLayout {
	public SampleLevelRendererView2(Context context) {
		super(context);

	}

	public SampleLevelRendererView2(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
	}

	public SampleLevelRendererView2(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2);
	}

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
		if (child == null) {
			LayoutInflater factory = LayoutInflater.from(this.getContext());
			View myView = factory
					.inflate(
							R.layout.example_support_sample_level_renderer_view_2,
							null);
			this.addView(myView);
			this.child = myView;

		}
		Organization customer = (Organization) data;

		TextView orgNameTv = (TextView) this.child
				.findViewById(R.id.lr2_organizationname_tv);
		orgNameTv.setText(customer.getName());

		TextView salesContactTv = (TextView) this.child
				.findViewById(R.id.lr2_salescontact_tv);
		salesContactTv.setText(customer.salesContact.firstName + " "
				+ customer.salesContact.lastName);

		TextView salesContactPhoneTv = (TextView) this.child
				.findViewById(R.id.lr2_salescontactphone_tv);
		salesContactPhoneTv.setText(customer.billingContact.telephone);

		TextView annualrevenueTv = (TextView) this.child
				.findViewById(R.id.lr2_annualrevenue_tv);
		annualrevenueTv.setText(String.valueOf(customer.annualRevenue));

		TextView epsTv = (TextView) this.child.findViewById(R.id.lr2_eps_tv);
		epsTv.setText(String.valueOf(customer.earningsPerShare));

		TextView laststockpriceTv = (TextView) this.child
				.findViewById(R.id.lr2_laststockprice_tv);
		laststockpriceTv.setText(String.valueOf(customer.lastStockPrice));

		TextView employeesTv = (TextView) this.child
				.findViewById(R.id.lr2_employees_tv);
		employeesTv.setText(String.valueOf(customer.numEmployees));

		Address address = customer.headquarterAddress;
		TextView addressTv = (TextView) this.child
				.findViewById(R.id.lr2_address_tv);
		addressTv.setText(address.line1 + "," + address.line2 + ","
				+ address.line3 + "'" + address.city.name + ","
				+ address.country.name + "," + address.postalCode);
	}

	private View child = null;

}
