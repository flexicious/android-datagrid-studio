package com.flexicious.example.model;
 
	import java.util.Date;
import java.util.List;

import com.flexicious.controls.core.ReflectionException;
import com.flexicious.example.model.persons.SystemUser;
import com.flexicious.nestedtreedatagrid.valueobjects.NameValue;
import com.flexicious.utils.UIUtils;
	 

	
	public class BaseEntity extends Object
	{
		public SystemUser addedBy;
		public Date addedDate;
		public SystemUser updatedBy;
		public Date updatedDate;
		public int id;
		
		public BaseEntity()
		{
		}
		
		
		
		@SuppressWarnings("unchecked")
		public BaseEntity clone(Boolean deepClone /*true*/){
			 
			BaseEntity entity =createNew();
			entity.addedBy=addedBy;
			for (NameValue levelPropNv : UIUtils.getClassInfo(this, null)) //we're cloning columns here (to) so not upset the orignial grid
			{
				String levelProp=levelPropNv.data;
				if(levelProp.equals("getChildren"))continue;
				if(levelProp.equals("getClass"))continue;
				if(levelProp.equals("getName"))continue;
				if(levelProp.equals("getOrgIndex"))continue;
				if(levelProp.equals("getRelationshipAmount"))continue;
				Object propVal=UIUtils.getPropertyValue(this,levelProp) ;
				if(UIUtils.isPrimitive(UIUtils.getPropertyValue(this,levelProp))){
					UIUtils.setPropertyValue(entity,levelProp,propVal);
				}
				else if(propVal instanceof BaseEntity){
					UIUtils.setPropertyValue(entity,levelProp,propVal);
					//entity[levelProp]= (BaseEntity)(this[levelProp]);
				}
				else if(deepClone && propVal instanceof List )
				{
					//entity[levelProp]=new ArrayCollection();
					try {
						UIUtils.setPropertyValue(entity, levelProp, propVal.getClass().newInstance());
					} catch (InstantiationException e) {
						throw new ReflectionException(e.getMessage(), e);
					} catch (IllegalAccessException e) {
						throw new ReflectionException(e.getMessage(), e);
					}
					List<Object> propValList=(List<Object>) propVal;
					for (Object item : propValList)
					{
						propValList.add(((BaseEntity)item).clone(deepClone));
					}	
				}
			}
			return entity;
		}
		public BaseEntity createNew(){
			throw new Error("Psuedo abstract method, need to override");
		}
	} 
