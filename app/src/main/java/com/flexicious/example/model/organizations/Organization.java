package com.flexicious.example.model.organizations;
 
	import java.util.ArrayList;
import java.util.List;

import com.flexicious.example.FlexiciousMockGenerator;
import com.flexicious.example.model.BaseEntity;
import com.flexicious.example.model.common.Address;
import com.flexicious.example.model.persons.CommercialContact;
import com.flexicious.example.model.transactions.Deal;
	 

	public class Organization extends BaseEntity
	{
		public float getOrgIndex() {
			return FlexiciousMockGenerator.simpleList.indexOf(this);
		}
			
		public Address headquarterAddress;
		public Address mailingAddress;
		public String legalName;
		public String url;
		
		public CommercialContact billingContact;
		public CommercialContact salesContact;
		
		public float annualRevenue;
		public float numEmployees;
		public float earningsPerShare;
		public float lastStockPrice;
		public String chartUrl;
		
		public ArrayList<Deal> deals =new ArrayList<Deal>();
		public Boolean isActive =true;
		public Organization()
		{
		}
		public Boolean headQuartersSameAsMailing;
		public float getRelationshipAmount(){
			float total =0;
			for(Deal deal :  deals){
				total+= deal.getDealAmount();
			}
			return total;
		}
		public BaseEntity createNew(){
			return new Organization();
		}
		public String getName(){
			return this.legalName;
		}
		//HierarchicalData Support.
		public List<?> getChildren(){
			return deals;
		}
		
	}
 
