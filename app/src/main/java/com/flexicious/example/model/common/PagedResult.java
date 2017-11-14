package com.flexicious.example.model.common;

	import java.util.ArrayList;
import java.util.List;

import com.flexicious.example.model.BaseEntity;
	
	public class PagedResult
	{
		public PagedResult(List<?>  collection, int totalRecords /*0*/, Object summaryData /*null*/, Boolean deepClone /*true*/)
		{
			this.collection=new ArrayList<Object>();
			for (Object entity : collection){
				this.collection.add(((BaseEntity)entity).clone(deepClone));	
			}
			
			this.totalRecords=totalRecords;
			this.summaryData=summaryData;
		}
		
		public ArrayList<Object> collection;
		public int totalRecords;
		public Object summaryData;
	}