package com.bot.service.botservice.model;

import java.util.Arrays;

public class PropertyTypeList {
	private PropertyType[] propertyType;

	public PropertyType[] getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType[] propertyType) {
		this.propertyType = propertyType;
	}

	@Override
	public String toString() {
		return "PropertyTypeList [propertyType=" + Arrays.toString(propertyType) + "]";
	}
	
	

}
