package com.flexicious.example.model.billing;
 
	import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flexicious.example.model.BaseEntity;
import com.flexicious.example.model.common.ReferenceData;
import com.flexicious.example.model.transactions.Deal;
	 
	public class Invoice extends BaseEntity
	{
		public Deal deal;
		public Date invoiceDate;
		public Date dueDate;
		public ReferenceData invoiceStatus;
		public ArrayList<LineItem> lineItems =new ArrayList<LineItem>();
		public Boolean hasPdf =true;
		
		public float getInvoiceNumber(){
			return id;
		}
		public float getInvoiceAmount(){
			float total =0;
			for (LineItem lineItem:  lineItems){
				total+= lineItem.lineItemAmount;
			}
			return total;
		}
		public Invoice()
		{
		}
		public  BaseEntity createNew(){
			return new Invoice();
		}
		//HierarchicalData Support.
		public  List<?> getChildren(){
			return lineItems;
		}
		public Object getParent(){
			return deal;
		}
		public void setParent(Object val){
			deal= (Deal)val;
		}
		
		public String getInvoiceStatusName(){
			return invoiceStatus.name;
		}
	}
