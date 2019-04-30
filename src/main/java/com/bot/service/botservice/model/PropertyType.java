package com.bot.service.botservice.model;

public class PropertyType {
	private String PROPERTY_ID;
	private String PROPERTY_NAME;
	private int COMPANY_ID;
	public String getPROPERTY_ID() {
		return PROPERTY_ID;
	}
	public void setPROPERTY_ID(String pROPERTY_ID) {
		PROPERTY_ID = pROPERTY_ID;
	}
	public String getPROPERTY_NAME() {
		return PROPERTY_NAME;
	}
	public void setPROPERTY_NAME(String pROPERTY_NAME) {
		PROPERTY_NAME = pROPERTY_NAME;
	}
	public int getCOMPANY_ID() {
		return COMPANY_ID;
	}
	public void setCOMPANY_ID(int cOMPANY_ID) {
		COMPANY_ID = cOMPANY_ID;
	}
	@Override
	public String toString() {
		return "PropertyType [PROPERTY_ID=" + PROPERTY_ID + ", PROPERTY_NAME=" + PROPERTY_NAME + ", COMPANY_ID="
				+ COMPANY_ID + "]";
	}
	

}
