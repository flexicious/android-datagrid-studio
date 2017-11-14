package com.flexicious.example.model.classic; 

import java.util.Date;
	public class Address
	{
		public String street1;
		public String street2;
		public String city;
		public String state;
		public String country;

		public float apartmentNumber;
		public Date validFrom;
		public Date validTo;
		
		public Address()
		{
			
		}
		public String getConcatenatedAddress(){
			return street1+", "+street2 + ", "+ city + ", " + state + ", "+ country; 
		}

	} 
