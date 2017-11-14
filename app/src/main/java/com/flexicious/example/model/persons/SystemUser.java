package com.flexicious.example.model.persons;
 
	import com.flexicious.example.model.BaseEntity;


	public class SystemUser extends Person
	{
		public String loginNm;
		
		public SystemUser()
		{
		}
		public BaseEntity createNew(){
			return new SystemUser();
		}
	}
 
