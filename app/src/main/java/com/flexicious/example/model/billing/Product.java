package com.flexicious.example.model.billing; 
	import com.flexicious.example.model.BaseEntity;


	public class Product extends BaseEntity
	{
		public String name;
		public float unitPrice;
		public String description;
		public String partNumber;
		
		public Product()
		{
		}
		public BaseEntity createNew(){
			return new Product();
		}
	} 