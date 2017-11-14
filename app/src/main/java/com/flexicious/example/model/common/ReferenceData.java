package com.flexicious.example.model.common; 
	import com.flexicious.example.model.BaseEntity;


	public class ReferenceData extends BaseEntity
	{
		public String code;
		public String name;
		
		public ReferenceData(int id, String code, String name /*null*/)
		{
			this.code=code;
			this.id=id;
			if(name==null || name.length()==0)
				name=code;
			this.name=name;
		}
		public ReferenceData(int i, String code) {
			this.code=code;
			this.id=i; 
			this.name=code;
		}
		public ReferenceData cloneSpecial(){
			ReferenceData  ref = new ReferenceData(id,code,name);
			return ref;
		}
		public BaseEntity createNew(){
			return  cloneSpecial();
		}
	}
