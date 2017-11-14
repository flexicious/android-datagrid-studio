package com.flexicious.example.model.persons;
 
	import com.flexicious.example.model.BaseEntity;
import com.flexicious.example.model.common.Address;
	

	public class Person extends BaseEntity
	{
		public String firstName;
		public String lastName;
		public String getDisplayName(){return firstName + " " +lastName;}
		public String telephone;
		public Address homeAddress;
		public Person()
		{
		}
		public BaseEntity createNew(){
			return new Person();
		}
	}
 