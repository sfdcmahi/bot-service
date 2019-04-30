package com.bot.service.botservice.model;

public class IndexResponse {
	private int indexTypeId;
	private String indexType;
	private int companyId;
	public int getIndexTypeId() {
		return indexTypeId;
	}
	public void setIndexTypeId(int indexTypeId) {
		this.indexTypeId = indexTypeId;
	}
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "IndexResponse [indexTypeId=" + indexTypeId + ", indexType=" + indexType + ", companyId=" + companyId
				+ "]";
	}
	 
}
