package com.flexicious.example.model.persons;
 
	import com.flexicious.example.model.BaseEntity;


	public class CommercialContact extends Person
	{
		
		public CommercialContact()
		{
		}
		public BaseEntity createNew(){
			return new CommercialContact();
		}
	}
 
