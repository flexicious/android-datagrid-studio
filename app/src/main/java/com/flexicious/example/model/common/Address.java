package com.flexicious.example.model.common; 
	import com.flexicious.example.model.BaseEntity;


	public class Address extends BaseEntity
	{
		public ReferenceData addressType;
		public Boolean isPrimary;
		
		public String line1;
		public String line2;
		public String line3;
		public ReferenceData city;
		public ReferenceData state;
		public ReferenceData country;
		public String postalCode;
		
		public Address()
		{
		}
		public BaseEntity createNew(){
			return new Address();
		}
	} 
