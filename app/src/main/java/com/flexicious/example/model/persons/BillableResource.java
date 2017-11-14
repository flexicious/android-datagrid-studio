package com.flexicious.example.model.persons;
 
	import com.flexicious.example.model.BaseEntity;


	public class BillableResource extends Person
	{
		public float billingRate;
		public float utilization;
		public float isCurrentlyUtilized;
		
		public BillableResource()
		{
		}
		public BaseEntity createNew(){
			return new BillableResource();
		}
	}

