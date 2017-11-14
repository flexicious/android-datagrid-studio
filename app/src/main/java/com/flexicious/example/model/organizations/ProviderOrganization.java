package com.flexicious.example.model.organizations;
 
	import com.flexicious.example.model.BaseEntity;
import com.flexicious.example.model.common.Address;


	public class ProviderOrganization  extends Organization
	{
		public Address paymentAddress;
		public ProviderOrganization()
		{
		}
		public BaseEntity createNew(){
			return new ProviderOrganization();
		}
	} 