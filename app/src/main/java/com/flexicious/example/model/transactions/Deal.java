package com.flexicious.example.model.transactions;
 
	import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flexicious.example.model.BaseEntity;
import com.flexicious.example.model.billing.Invoice;
import com.flexicious.example.model.common.ReferenceData;
import com.flexicious.example.model.organizations.CustomerOrganization;
import com.flexicious.example.model.organizations.ProviderOrganization;
import com.flexicious.example.model.persons.Person;
	 

	public class Deal extends BaseEntity
	{
		public CustomerOrganization customer;
		public ArrayList<Invoice> invoices =new ArrayList<Invoice> ();;
		public Date dealDate;
		public ReferenceData dealStatus;
		public String dealDescription;
		public Boolean finalized =true;
		public float getDealAmount(){
			float total =0;
			for (Invoice inv:  invoices){
				total+= inv.getInvoiceAmount();
			}
			return total;
		}
		public ProviderOrganization provider;
		public ArrayList<Person> dealContacts;
		
		public Boolean getIsBillable(){
			return dealStatus.code!="Prospect";
		}
		
		
		public Deal()
		{
		}
		public BaseEntity createNew(){
			return new Deal();
		}
		public String getName(){
			return this.dealDescription;
		}
		//HierarchicalData Support.
		public List<Invoice> getChildren(){
			return invoices;
		}
		public Object getParent(){
			return customer;
		}
		public void setParent(Object val){
customer= (CustomerOrganization)val;
		}
	}

