package com.flexicious.example.model.organizations;
 
	import com.flexicious.example.model.BaseEntity;
import com.flexicious.example.model.common.Address;


	public class CustomerOrganization extends Organization
	{
		public Address billingAddress;
		public CustomerOrganization()
		{
		}
		public BaseEntity createNew(){
			return new CustomerOrganization();
		}
	}
 
