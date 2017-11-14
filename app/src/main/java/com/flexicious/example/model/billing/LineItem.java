package com.flexicious.example.model.billing; 
	import com.flexicious.example.model.BaseEntity;
	 
 
	public class LineItem extends BaseEntity
	{
		public Invoice invoice;
		public float lineItemAmount;
		public String lineItemDescription;
		public float units;

		public LineItem()
		{
		}
		public  BaseEntity createNew(){
			return new LineItem();
		}
		public Object getParent(){
			return invoice;
		}
		public void setParent(Object val){
invoice= (Invoice)val;
		}
	}
