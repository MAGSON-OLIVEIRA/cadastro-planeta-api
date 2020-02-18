package com.mdo.cadastroplaneta.api.dto;

import java.util.ArrayList;
import java.util.List;

public class PlanetsDto {

    private Integer count;
    private String next;
    private String previous;

	private List<ResultsDto> results = new ArrayList<ResultsDto>();
	
	
	public List<ResultsDto> getResults() {
		return results;
	}
	public void setResults(List<ResultsDto> results) {
		this.results = results;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}


	
}
