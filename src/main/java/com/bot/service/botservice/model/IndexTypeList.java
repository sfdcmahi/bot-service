package com.bot.service.botservice.model;

import java.util.Arrays;

public class IndexTypeList {
	private IndexResponse[] indexType;

	public IndexResponse[] getIndexType() {
		return indexType;
	}

	public void setIndexType(IndexResponse[] indexType) {
		this.indexType = indexType;
	}

	@Override
	public String toString() {
		return "IndexTypeList [indexType=" + Arrays.toString(indexType) + "]";
	}
	

}
