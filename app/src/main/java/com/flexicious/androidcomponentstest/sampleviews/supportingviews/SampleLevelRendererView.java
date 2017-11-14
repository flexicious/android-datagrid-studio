package com.flexicious.androidcomponentstest.sampleviews.supportingviews;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flexicious.androidcomponentstest.R;
import com.flexicious.example.model.billing.Invoice;
import com.flexicious.example.model.common.Address;

public class SampleLevelRendererView extends FrameLayout {
	public SampleLevelRendererView(Context context) {
		super(context);

	}

	public SampleLevelRendererView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1); 
	}

	public SampleLevelRendererView(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2); 
	}
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
		if(child==null){
			LayoutInflater factory = LayoutInflater.from(this.getContext());
			View myView = factory.inflate(R.layout.example_support_sample_level_renderer_view, null);
			this.addView(myView);
			this.child=myView;
			ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) myView.getLayoutParams();
			params.width = LayoutParams.MATCH_PARENT;
			params.height= LayoutParams.MATCH_PARENT;
			this.child.setLayoutParams(params);
		}
		Invoice inv = (Invoice) data;

		TextView invoiceNumberTv = (TextView) this.child.findViewById(R.id.lr1_invoicenumber_tv);
		invoiceNumberTv.setText(String.valueOf(inv.getInvoiceNumber()));

		Format formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
		String invoiceDate = formatter.format(inv.invoiceDate);
		TextView invoiceDateTV = (TextView) this.child.findViewById(R.id.lr1_invoicedate_tv);
		invoiceDateTV.setText(invoiceDate);
		
		String dueDate = formatter.format(inv.dueDate);
		TextView dueDateTV = (TextView) this.child.findViewById(R.id.lr1_duedate_tv);
		dueDateTV.setText(dueDate);
		
		TextView dealAmountTV = (TextView) this.child.findViewById(R.id.lr1_dealamount_tv);
		dealAmountTV.setText(String.valueOf(inv.deal.getDealAmount()));
		
		TextView dealDescTV = (TextView) this.child.findViewById(R.id.lr1_dealdescription_tv);
		dealDescTV.setText(inv.deal.dealDescription);
		
		TextView dealStatusTV = (TextView) this.child.findViewById(R.id.lr1_dealstatus_tv);
		dealStatusTV.setText(inv.getInvoiceStatusName());
		
		TextView clientNameTv = (TextView) this.child.findViewById(R.id.lr1_clientname_tv);
		clientNameTv.setText(inv.deal.customer.getName());
		TextView addressTv = (TextView) this.child.findViewById(R.id.lr1_address_tv);
		Address address = inv.deal.customer.headquarterAddress;
		addressTv.setText(address.line1+","+address.line2+","+address.line3+"'"+address.city.name+","+address.country.name+","+address.postalCode);
	}

	private View child=null;

}
