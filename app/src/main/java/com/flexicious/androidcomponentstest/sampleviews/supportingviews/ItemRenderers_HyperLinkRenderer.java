package com.flexicious.androidcomponentstest.sampleviews.supportingviews;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.widget.TextView;

public class ItemRenderers_HyperLinkRenderer extends TextView {

	public ItemRenderers_HyperLinkRenderer(Context context) {
		super(context);
	}

	public ItemRenderers_HyperLinkRenderer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ItemRenderers_HyperLinkRenderer(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
		this.setAutoLinkMask(Linkify.ALL);
//	    Organization org = (Organization)this.data;
	    this.setLinksClickable(true);
	    this.setMovementMethod(LinkMovementMethod.getInstance());
//    	this.setText(Html.fromHtml("<a href=\""+org.url+"\">View Link</a>"));
    	this.setClickable(true);
    	this.setMovementMethod(LinkMovementMethod.getInstance());
    	String text = "<a href='http://www.google.com'><u>Google</u></a>";
    	this.setTextColor(Color.BLUE);
    	this.setText(Html.fromHtml(text));
    	
	    
//	    //this.titleLabel.text = org.url;
//	    this.setTitle:"View Link" forState:UIControlStateNormal];
//	    this.setTitleColor:[UIColor blueColor] forState:UIControlStateNormal];
			//here we can do custom work to configure our renderer
	} 
	private Object data;

}
