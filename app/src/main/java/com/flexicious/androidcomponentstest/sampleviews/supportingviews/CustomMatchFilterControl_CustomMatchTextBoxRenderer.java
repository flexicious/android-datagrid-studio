package com.flexicious.androidcomponentstest.sampleviews.supportingviews;

import android.content.Context;
import android.util.AttributeSet;

import com.flexicious.controls.TextInput;
import com.flexicious.controls.interfaces.filters.ICustomMatchFilterControl;
import com.flexicious.example.model.classic.Employee;
//////we need to implement ICustomMatchFilterControl because we want to tell the grid to call our isMatch method to do the filter
//////we need to implement (TextInput already does) IFilterControl to tell the grid that we are actually a filter control, and not a placeholder for non-filterable columns
//////we need to implement iDelayedChange (TextInput already does)  so that the grid listens to our Event.EVENT_DELAYED_CHANGE event, and not the regular change method.
//////if we had set filterTriggerEvent on the column to "enterKeyUp", we would not have had to implement IDelayedChange, but then the
//////user would have had to hit the enter key to run the filter.

public class CustomMatchFilterControl_CustomMatchTextBoxRenderer extends
		TextInput implements ICustomMatchFilterControl {

	 
	public CustomMatchFilterControl_CustomMatchTextBoxRenderer(Context context) {
		super(context); 
	}

	public CustomMatchFilterControl_CustomMatchTextBoxRenderer(Context arg0, AttributeSet arg1) {
		super(arg0, arg1); 
	}

	public CustomMatchFilterControl_CustomMatchTextBoxRenderer(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2); 
	}

	@Override
	public boolean isMatch(Object item) {
		Employee emp = (Employee) item;
		String text = this.getText().toString();
		if(emp != null && text.length()>0){
	        return emp.firstName.toLowerCase().indexOf(text.toLowerCase()) != -1
	            || emp.lastName.toLowerCase().indexOf(text.toLowerCase()) != -1;
	    }
	    return true;
	}
}
