package com.airtel.user.helper.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResponseDto extends BaseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startIndex;
	private int slotSize;
	private long totalRecord;
	private List<BaseDto> result;

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getSlotSize() {
		return slotSize;
	}

	public void setSlotSize(int slotSize) {
		this.slotSize = slotSize;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<BaseDto> getResult() {
		return result;
	}

	public void setResult(List<BaseDto> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchDto [startIndex=");
		builder.append(startIndex);
		builder.append(", slotSize=");
		builder.append(slotSize);
		builder.append(", totalRecord=");
		builder.append(totalRecord);
		builder.append("]");
		return builder.toString();
	}
	
	

}
